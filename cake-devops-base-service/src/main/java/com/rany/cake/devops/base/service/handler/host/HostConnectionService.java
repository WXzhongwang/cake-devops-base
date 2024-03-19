package com.rany.cake.devops.base.service.handler.host;

import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.entity.ServerKey;
import com.rany.cake.devops.base.domain.entity.ServerProxy;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.service.HostDomainService;
import com.rany.cake.devops.base.service.base.ValueMix;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.devops.base.util.MachineConst;
import com.rany.cake.devops.base.util.MessageConst;
import com.rany.cake.devops.base.util.enums.MachineAuthType;
import com.rany.cake.devops.base.util.enums.ProxyType;
import com.rany.cake.toolkit.lang.exception.AuthenticationException;
import com.rany.cake.toolkit.lang.exception.ConnectionRuntimeException;
import com.rany.cake.toolkit.lang.io.Streams;
import com.rany.cake.toolkit.lang.utils.Exceptions;
import com.rany.cake.toolkit.lang.utils.Strings;
import com.rany.cake.toolkit.lang.utils.Valid;
import com.rany.cake.toolkit.net.remote.channel.SessionHolder;
import com.rany.cake.toolkit.net.remote.channel.SessionStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Component
public class HostConnectionService {

    @Resource
    private HostDomainService hostDomainService;
    @Resource
    private ServerKeyComponent serverKeyComponent;

    public void testConnect(String hostId) {
        HostId hostPk = new HostId(hostId);
        Host host = hostDomainService.getHost(hostPk);
        // 查询秘钥
        ServerKey key = Optional.ofNullable(host.getKeyId())
                .map(hostDomainService::getServerKey)
                .orElse(null);
        // 查询代理
        ServerProxy proxy = Optional.ofNullable(host.getProxyId())
                .map(hostDomainService::getServerProxy)
                .orElse(null);
        // 查询超时时间
        Integer timeout = Optional.ofNullable(host.getHostId())
                .map(hostDomainService::getConnectionTimeout)
                .orElse(MachineConst.CONNECT_TIMEOUT);

        SessionStore s = null;
        try {

            s = this.connectSessionStore(host, key, proxy, timeout);
        } catch (Exception e) {
            String message = e.getMessage();
            if (Strings.contains(message, Const.TIMEOUT)) {
                throw Exceptions.app(MessageConst.TIMEOUT_EXCEPTION_MESSAGE);
            } else if (e instanceof AuthenticationException) {
                throw Exceptions.app(MessageConst.AUTH_EXCEPTION_MESSAGE);
            } else {
                throw Exceptions.app(MessageConst.CONNECT_ERROR);
            }
        } finally {
            Streams.close(s);
        }
    }

    public SessionStore openSessionStore(Host host) {
        HostId hostId = host.getHostId();
        // 重试次数
        Integer retryTimes = hostDomainService.getConnectRetryTimes(hostId);
        // 查询秘钥
        ServerKey key = Optional.ofNullable(host.getKeyId())
                .map(hostDomainService::getServerKey)
                .orElse(null);
        // 查询代理
        ServerProxy proxy = Optional.ofNullable(host.getProxyId())
                .map(hostDomainService::getServerProxy)
                .orElse(null);
        // 查询超时时间
        Integer timeout = Optional.ofNullable(host.getHostId())
                .map(hostDomainService::getConnectionTimeout)
                .orElse(MachineConst.CONNECT_TIMEOUT);

        Exception ex = null;
        String msg = MessageConst.CONNECT_ERROR;
        for (int i = 0, t = retryTimes + 1; i < t; i++) {
            log.info("远程机器建立连接-尝试连接远程服务器 第{}次尝试 machineId: {}, host: {}",
                    (i + 1), hostId.getHostId(), host.getServerAddr());
            try {
                return this.connectSessionStore(host, key, proxy, timeout);
            } catch (Exception e) {
                ex = e;
                String message = e.getMessage();
                if (Strings.contains(message, Const.TIMEOUT)) {
                    log.info("远程机器建立连接-连接超时");
                    msg = MessageConst.TIMEOUT_EXCEPTION_MESSAGE;
                    ex = Exceptions.timeout(message, e);
                } else if (e instanceof ConnectionRuntimeException) {
                    log.info("远程机器建立连接-连接失败");
                } else if (e instanceof AuthenticationException) {
                    msg = MessageConst.AUTH_EXCEPTION_MESSAGE;
                    break;
                } else {
                    break;
                }
            }
        }
        String errorMessage = "机器 " + host.getServerAddr() + " " + msg;
        log.error(errorMessage, ex);
        throw Exceptions.app(errorMessage, ex);
    }

    /**
     * 打开 sessionStore
     *
     * @param machine machine
     * @param key     key
     * @param proxy   proxy
     * @param timeout timeout
     * @return SessionStore
     */
    public SessionStore connectSessionStore(Host machine, ServerKey key, ServerProxy proxy, int timeout) {
        Valid.notNull(machine, MessageConst.INVALID_MACHINE);
        SessionHolder sessionHolder = new SessionHolder();
        SessionStore session;
        try {
            // 加载秘钥
            if (Objects.equals(MachineAuthType.SECRET_KEY.getType(), machine.getAuthType())) {
                String keyPath = serverKeyComponent.getKeyPath(key.getKeyPath());
                String password = key.getPassphrase();
                if (Strings.isEmpty(password)) {
                    sessionHolder.addIdentity(keyPath);
                } else {
                    sessionHolder.addIdentity(keyPath, ValueMix.decrypt(password));
                }
            }
            // 获取会话
            session = sessionHolder.getSession(machine.getServerAddr(), machine.getPort(), machine.getUsername());
            // 密码验证
            if (MachineAuthType.PASSWORD.getType().equals(machine.getAuthType())) {
                String password = machine.getPwd();
                if (Strings.isNotBlank(password)) {
                    session.password(ValueMix.decrypt(password));
                }
            }
            // 加载代理
            if (proxy != null) {
                ProxyType proxyType = ProxyType.of(proxy.getProxyType());
                String proxyPassword = proxy.getProxyPassword();
                if (!Strings.isBlank(proxyPassword)) {
                    proxyPassword = ValueMix.decrypt(proxyPassword);
                }
                if (ProxyType.HTTP.equals(proxyType)) {
                    session.httpProxy(proxy.getProxyHost(), proxy.getProxyPort(), proxy.getProxyUsername(), proxyPassword);
                } else if (ProxyType.SOCKS4.equals(proxyType)) {
                    session.sock4Proxy(proxy.getProxyHost(), proxy.getProxyPort(), proxy.getProxyUsername(), proxyPassword);
                } else if (ProxyType.SOCKS5.equals(proxyType)) {
                    session.sock5Proxy(proxy.getProxyHost(), proxy.getProxyPort(), proxy.getProxyUsername(), proxyPassword);
                }
            }
            // 连接
            session.connect(timeout);
            log.info("远程机器建立连接-成功 {}@{}:{}", machine.getUsername(), machine.getServerAddr(), machine.getPort());
            return session;
        } catch (Exception e) {
            log.error("远程机器建立连接-失败 {}@{}:{}", machine.getUsername(), machine.getServerAddr(), machine.getPort(), e);
            throw e;
        }
    }
}
