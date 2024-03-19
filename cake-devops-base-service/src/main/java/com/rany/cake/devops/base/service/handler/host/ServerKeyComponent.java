package com.rany.cake.devops.base.service.handler.host;

import com.rany.cake.devops.base.util.system.SystemEnvAttr;
import com.rany.cake.toolkit.lang.io.Files1;
import com.rany.cake.toolkit.lang.utils.Exceptions;
import com.rany.cake.toolkit.lang.utils.Strings;
import com.rany.cake.toolkit.net.remote.channel.SessionHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ServerKeyComponent {

    public void checkLoadKey(String path, String password) {
        try {
            if (Strings.isEmpty(password)) {
                SessionHolder.HOLDER.addIdentity(path);
            } else {
                SessionHolder.HOLDER.addIdentity(path, password);
            }
            SessionHolder.HOLDER.removeAllIdentity();
        } catch (Exception e) {
            throw Exceptions.app("秘钥不合法, 请检查密码或使用 ssh-keygen -m PEM -t rsa 重新生成", e);
        }
    }

    public String getKeyPath(String path) {
        return Files1.getPath(SystemEnvAttr.KEY_PATH.getValue(), path);
    }
}
