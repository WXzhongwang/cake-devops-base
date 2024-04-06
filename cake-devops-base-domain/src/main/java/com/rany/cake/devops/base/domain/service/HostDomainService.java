package com.rany.cake.devops.base.domain.service;

import com.cake.framework.common.response.Page;
import com.rany.cake.devops.base.domain.aggregate.Host;
import com.rany.cake.devops.base.domain.aggregate.HostGroup;
import com.rany.cake.devops.base.domain.entity.*;
import com.rany.cake.devops.base.domain.pk.HostId;
import com.rany.cake.devops.base.domain.repository.*;
import com.rany.cake.devops.base.domain.repository.param.HostEnvQueryParam;
import com.rany.cake.devops.base.domain.repository.param.HostPageQueryParam;
import com.rany.cake.devops.base.util.CommandConst;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.devops.base.util.MachineConst;
import com.rany.cake.devops.base.util.enums.MachineEnvAttr;
import com.rany.cake.toolkit.lang.utils.Charsets;
import com.rany.cake.toolkit.lang.utils.Strings;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * 主机
 *
 * @author zhongshengwang
 * @description 主机
 * @date 2023/2/18 22:40
 * @email 18668485565163.com
 */
@Component
@AllArgsConstructor
public class HostDomainService {
    private final HostRepository hostRepository;
    private final ServerProxyRepository serverProxyRepository;
    private final ServerKeyRepository serverKeyRepository;
    private final HostEnvRepository hostEnvRepository;
    private final HostGroupRepository hostGroupRepository;
    private final HostMonitorRepository hostMonitorRepository;

    public List<Host> getPackageMachineList() {
        HostGroup packagingGroup = hostGroupRepository.getPackagingGroup();
        if (packagingGroup == null) {
            return Collections.emptyList();
        }
        List<String> groupIds = new ArrayList<>();
        groupIds.add(packagingGroup.getBizID().getHostGroupId());
        return hostRepository.getHostsByGroupIds(groupIds);
    }

    /**
     * 创建主机
     *
     * @param host
     */
    public void save(Host host, List<GroupHost> groupHosts, HostMonitor monitor) {
        hostRepository.save(host, groupHosts);
        hostEnvRepository.initEnv(host);
        hostMonitorRepository.save(monitor);
    }

    public void update(Host host) {
        hostRepository.update(host);
    }

    public Host getHost(HostId hostId) {
        return hostRepository.find(hostId);
    }

    public List<GroupHost> getGroupHost(HostId hostId) {
        return hostRepository.getGroupHostByHostId(hostId);
    }

    public List<HostGroup> getHostGroupByGroupIds(List<String> groupIds) {
        return hostGroupRepository.findByGroupIds(groupIds);
    }


    public Page<Host> pageHost(HostPageQueryParam hostPageQueryParam) {
        return hostRepository.pageHost(hostPageQueryParam);
    }

    public Integer getConnectionTimeout(HostId hostId) {
        HostEnvQueryParam queryParam = new HostEnvQueryParam();
        queryParam.setHostId(hostId.getHostId());
        List<HostEnv> list = hostEnvRepository.list(queryParam);
        HostEnv hostEnv = list.stream().filter(p -> Strings.eq(MachineEnvAttr.CONNECT_TIMEOUT.getKey(), p.getAttrKey()))
                .findFirst().orElse(null);
        String value = Optional.ofNullable(hostEnv).map(HostEnv::getAttrValue).orElse(Strings.EMPTY);
        int timeout = Strings.isInteger(value) ? Integer.parseInt(value) : MachineConst.CONNECT_TIMEOUT;
        return Math.max(timeout, 0);
    }

    public Integer getConnectRetryTimes(HostId hostId) {
        HostEnvQueryParam queryParam = new HostEnvQueryParam();
        queryParam.setHostId(hostId.getHostId());
        List<HostEnv> list = hostEnvRepository.list(queryParam);
        HostEnv hostEnv = list.stream().filter(p -> Strings.eq(MachineEnvAttr.CONNECT_RETRY_TIMES.getKey(), p.getAttrKey()))
                .findFirst().orElse(null);
        String value = Optional.ofNullable(hostEnv).map(HostEnv::getAttrValue).orElse(Strings.EMPTY);
        int times = Strings.isInteger(value) ? Integer.parseInt(value) : MachineConst.CONNECT_RETRY_TIMES;
        return Math.max(times, 0);
    }

    public ServerProxy getServerProxy(Long proxyId) {
        return serverProxyRepository.find(proxyId);
    }

    public ServerKey getServerKey(Long serverKeyId) {
        return serverKeyRepository.find(serverKeyId);
    }

    /**
     * 获取 sftp 编码格式
     *
     * @param hostId 机器id
     * @return 编码格式
     */
    public String getSftpCharset(String hostId) {
        HostEnv hostEnv = hostEnvRepository.findByKey(hostId, MachineEnvAttr.SFTP_CHARSET.getKey());
        String charset = hostEnv.getAttrValue();
        return Charsets.isSupported(charset) ? charset : Const.UTF_8;
    }

    /**
     * 获取文件 tail 尾行偏移量
     *
     * @param hostId 机器id
     * @return offset line
     */
    public Integer getTailOffset(String hostId) {
        HostEnv hostEnv = hostEnvRepository.findByKey(hostId, MachineEnvAttr.SFTP_CHARSET.getKey());
        int offset = Strings.isInteger(hostEnv.getAttrValue()) ? Integer.parseInt(hostEnv.getAttrValue()) :
                Const.TAIL_OFFSET_LINE;
        return Math.max(offset, 0);
    }

    /**
     * 获取文件 tail 编码集
     *
     * @param hostId 机器id
     * @return 编码集
     */
    public String getTailCharset(String hostId) {
        HostEnv hostEnv = hostEnvRepository.findByKey(hostId, MachineEnvAttr.TAIL_CHARSET.getKey());
        String charset = hostEnv.getAttrValue();
        return Charsets.isSupported(charset) ? charset : Const.UTF_8;
    }

    /**
     * 获取文件 tail 默认命令
     *
     * @param hostId 机器id
     * @return 默认命令
     */
    public String getTailDefaultCommand(String hostId) {
        HostEnv hostEnv = hostEnvRepository.findByKey(hostId, MachineEnvAttr.TAIL_DEFAULT_COMMAND.getKey());
        String command = hostEnv.getAttrValue();
        return Strings.isBlank(command) ? CommandConst.TAIL_FILE_DEFAULT : command;
    }
}
