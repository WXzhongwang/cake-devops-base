package com.rany.cake.devops.base.service;

import com.rany.cake.devops.base.domain.entity.SystemEnv;
import com.rany.cake.devops.base.infra.dao.SystemEnvDao;
import com.rany.cake.devops.base.infra.po.SystemEnvPO;
import com.rany.cake.devops.base.service.base.PathBuilders;
import com.rany.cake.devops.base.util.Const;
import com.rany.cake.devops.base.util.enums.EnableType;
import com.rany.cake.devops.base.util.system.SystemEnvAttr;
import com.rany.cake.devops.base.util.tail.FileTailMode;
import com.rany.cake.toolkit.lang.io.Files1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统环境变量初始化
 *
 * @author zhongshengwang
 * @version 1.0.0
 * @since 2021/4/3 21:16
 */
@Component
@Slf4j
public class SystemEnvInitializeRunner implements CommandLineRunner {

    @Resource
    private SystemEnvDao systemEnvDAO;

    @Override
    public void run(String... args) {
        log.info("初始化系统环境初始化-开始");
        this.initEnv();
        log.info("初始化系统环境初始化-结束");
    }

    /**
     * 初始化环境
     */
    private void initEnv() {
        // 查询所有的key
        List<String> keys = SystemEnvAttr.getKeys();
        List<SystemEnvPO> envList = systemEnvDAO.selectByNames(keys);
        for (SystemEnvPO systemEnvPO : envList) {
            System.out.println("key:" + systemEnvPO.getAttrKey());
        }
        // 初始化数据
        for (String key : keys) {
            SystemEnvAttr attr = SystemEnvAttr.of(key);
            SystemEnvPO env = envList.stream()
                    .filter(s -> s.getAttrKey().equals(key))
                    .findFirst()
                    .orElse(null);
            if (env == null) {
                // 插入数据
                String value = this.getAttrValue(attr);
                SystemEnv insert = new SystemEnv();
                insert.setAttrKey(key);
                insert.setAttrValue(value);
                insert.setDescription(attr.getDescription());
                insert.setSystemEnv(attr.isSystemEnv() ? Const.IS_SYSTEM : Const.NOT_SYSTEM);
                systemEnvDAO.save(insert);
                log.info("初始化系统变量 {} - {}", key, value);
                // 设置本地值
                attr.setValue(value);
            } else {
                // 设置本地值
                attr.setValue(env.getAttrValue());
            }
        }
    }

    /**
     * 获取属性值
     *
     * @param attr attr
     * @return value
     */
    private String getAttrValue(SystemEnvAttr attr) {
        switch (attr) {
            case KEY_PATH:
                return createOrionOpsPath(Const.KEYS_PATH);
            case PIC_PATH:
                return createOrionOpsPath(Const.PIC_PATH);
            case SWAP_PATH:
                return createOrionOpsPath(Const.SWAP_PATH);
            case SCREEN_PATH:
                return createOrionOpsPath(Const.SCREEN_PATH);
            case LOG_PATH:
                return createOrionOpsPath(Const.LOG_PATH);
            case TEMP_PATH:
                return createOrionOpsPath(Const.TEMP_PATH);
            case REPO_PATH:
                return createOrionOpsPath(Const.REPO_PATH);
            case DIST_PATH:
                return createOrionOpsPath(Const.DIST_PATH);
            case MACHINE_MONITOR_AGENT_PATH:
                return createOrionOpsPath(Const.MACHINE_MONITOR_AGENT_PATH);
            case TAIL_FILE_UPLOAD_PATH:
                return createOrionOpsPath(Const.TAIL_FILE_PATH);
            case TAIL_MODE:
                return FileTailMode.TRACKER.getMode();
            case TRACKER_DELAY_TIME:
                return Const.TRACKER_DELAY_MS + Const.EMPTY;
            case ENABLE_IP_FILTER:
            case ENABLE_WHITE_IP_LIST:
            case ENABLE_AUTO_CLEAN_FILE:
            case ALLOW_MULTIPLE_LOGIN:
            case RESUME_ENABLE_SCHEDULER_TASK:
            case TERMINAL_ACTIVE_PUSH_HEARTBEAT:
            case LOGIN_IP_BIND:
                return EnableType.DISABLED.getLabel();
            case LOGIN_FAILURE_LOCK:
            case LOGIN_TOKEN_AUTO_RENEW:
                return EnableType.ENABLED.getLabel();
            case LOGIN_TOKEN_EXPIRE:
                return Const.DEFAULT_LOGIN_TOKEN_EXPIRE_HOUR + Const.EMPTY;
            case LOGIN_FAILURE_LOCK_THRESHOLD:
            case STATISTICS_CACHE_EXPIRE:
                return Const.N_5 + Const.EMPTY;
            case LOGIN_TOKEN_AUTO_RENEW_THRESHOLD:
                return Const.N_2 + Const.EMPTY;
            case FILE_CLEAN_THRESHOLD:
                return Const.DEFAULT_FILE_CLEAN_THRESHOLD + Const.EMPTY;
            case SFTP_UPLOAD_THRESHOLD:
                return Const.SFTP_UPLOAD_THRESHOLD + Const.EMPTY;
            default:
                return null;
        }
    }

    /**
     * 创建项目目录
     *
     * @param path path
     * @return path
     */
    public static String createOrionOpsPath(String path) {
        String dir = PathBuilders.getHostEnvPath(path);
        dir = Files1.getPath(dir);
        Files1.mkdirs(dir);
        return dir;
    }

}
