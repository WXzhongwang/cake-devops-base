-- MySQL dump 10.13  Distrib 8.0.34, for macos12.6 (arm64)
--
-- Host: 127.0.0.1    Database: cake_devops
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alarm_group`
--

DROP TABLE IF EXISTS `alarm_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alarm_group`
(
    `id`                bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `group_name`        varchar(64) COLLATE utf8mb3_bin  DEFAULT NULL COMMENT '报警组名称',
    `group_description` varchar(128) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '报警组描述',
    `is_deleted`        varchar(5) COLLATE utf8mb3_bin   DEFAULT '1' COMMENT '是否删除 0未删除 1已删除',
    `gmt_create`        datetime(4) DEFAULT CURRENT_TIMESTAMP (4) COMMENT '创建时间',
    `gmt_modified`      datetime(4) DEFAULT CURRENT_TIMESTAMP (4) ON UPDATE CURRENT_TIMESTAMP (4) COMMENT '修改时间',
    `creator`           varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
    `modifier`          varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC COMMENT='报警组';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarm_group`
--

LOCK
TABLES `alarm_group` WRITE;
/*!40000 ALTER TABLE `alarm_group` DISABLE KEYS */;
INSERT INTO `alarm_group`
VALUES (1, '自建测试告警组', '自建测试告警组', '0', '2024-04-06 17:38:50.8710', '2024-04-14 20:38:38.5830',
        '768460662077796352', '768460662077796352'),
       (2, 'kkk', 'akkkk', '0', '2024-04-09 21:47:58.5500', '2024-04-14 20:38:35.2880', '768460662077796352',
        '768460662077796352');
/*!40000 ALTER TABLE `alarm_group` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `alarm_group_notify`
--

DROP TABLE IF EXISTS `alarm_group_notify`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alarm_group_notify`
(
    `id`           bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `group_id`     bigint                           DEFAULT NULL COMMENT '报警组id',
    `notify_id`    bigint                           DEFAULT NULL COMMENT '通知id',
    `notify_type`  int                              DEFAULT NULL COMMENT '通知类型 10 webhook',
    `is_deleted`   varchar(5) COLLATE utf8mb3_bin   DEFAULT '0' COMMENT '是否删除 1未删除 2已删除',
    `gmt_create`   datetime(4) DEFAULT CURRENT_TIMESTAMP (4) COMMENT '创建时间',
    `gmt_modified` datetime(4) DEFAULT CURRENT_TIMESTAMP (4) ON UPDATE CURRENT_TIMESTAMP (4) COMMENT '修改时间',
    `creator`      varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
    `modifier`     varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY            `group_idx` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC COMMENT='报警组通知方式';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarm_group_notify`
--

LOCK
TABLES `alarm_group_notify` WRITE;
/*!40000 ALTER TABLE `alarm_group_notify` DISABLE KEYS */;
INSERT INTO `alarm_group_notify`
VALUES (1, 1, 3, 10, '1', '2024-04-06 17:38:50.8710', '2024-04-14 20:38:38.6021', '768460662077796352', NULL),
       (2, 2, 3, 10, '1', '2024-04-09 21:47:58.5500', '2024-04-14 20:38:35.3750', '768460662077796352', NULL),
       (3, 2, 3, 10, '0', '2024-04-09 21:47:58.5500', '2024-04-14 20:38:35.2880', '768460662077796352', NULL),
       (4, 2, 5, 10, '0', '2024-04-09 21:47:58.5500', '2024-04-14 20:38:35.2940', '768460662077796352', NULL),
       (5, 1, 3, 10, '0', '2024-04-06 17:38:50.8710', '2024-04-14 20:38:38.5830', '768460662077796352', NULL),
       (6, 1, 5, 10, '0', '2024-04-06 17:38:50.8710', '2024-04-14 20:38:38.5830', '768460662077796352', NULL);
/*!40000 ALTER TABLE `alarm_group_notify` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `alarm_group_user`
--

DROP TABLE IF EXISTS `alarm_group_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alarm_group_user`
(
    `id`           bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `group_id`     bigint                           DEFAULT NULL COMMENT '报警组id',
    `account_id`   bigint                           DEFAULT NULL COMMENT '报警组成员id',
    `username`     varchar(32) COLLATE utf8mb3_bin  DEFAULT NULL COMMENT '报警组成员用户名',
    `is_deleted`   varchar(5) COLLATE utf8mb3_bin   DEFAULT '0' COMMENT '是否删除 0未删除 1已删除',
    `gmt_create`   datetime(4) DEFAULT CURRENT_TIMESTAMP (4) COMMENT '创建时间',
    `gmt_modified` datetime(4) DEFAULT CURRENT_TIMESTAMP (4) ON UPDATE CURRENT_TIMESTAMP (4) COMMENT '修改时间',
    `creator`      varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
    `modifier`     varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY            `group_id_idx` (`group_id`,`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC COMMENT='报警组成员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarm_group_user`
--

LOCK
TABLES `alarm_group_user` WRITE;
/*!40000 ALTER TABLE `alarm_group_user` DISABLE KEYS */;
INSERT INTO `alarm_group_user`
VALUES (1, 1, 781488231601549312, NULL, '1', '2024-04-06 17:38:50.8710', '2024-04-14 20:38:38.5993',
        '768460662077796352', NULL),
       (2, 1, 768821468053254144, NULL, '1', '2024-04-06 17:38:50.8710', '2024-04-14 20:38:38.5993',
        '768460662077796352', NULL),
       (3, 1, 768460662077796352, NULL, '1', '2024-04-06 17:38:50.8710', '2024-04-14 20:38:38.5993',
        '768460662077796352', NULL),
       (4, 2, 781488231601549312, NULL, '1', '2024-04-09 21:47:58.5500', '2024-04-14 20:38:35.3609',
        '768460662077796352', NULL),
       (5, 2, 768821468053254144, NULL, '1', '2024-04-09 21:47:58.5500', '2024-04-14 20:38:35.3609',
        '768460662077796352', NULL),
       (6, 2, 768821468053254144, NULL, '0', '2024-04-09 21:47:58.5500', '2024-04-14 20:38:35.2880',
        '768460662077796352', NULL),
       (7, 2, 781488231601549312, NULL, '0', '2024-04-09 21:47:58.5500', '2024-04-14 20:38:35.2880',
        '768460662077796352', NULL),
       (8, 1, 768460662077796352, NULL, '0', '2024-04-06 17:38:50.8710', '2024-04-14 20:38:38.5830',
        '768460662077796352', NULL),
       (9, 1, 768821468053254144, NULL, '0', '2024-04-06 17:38:50.8710', '2024-04-14 20:38:38.5830',
        '768460662077796352', NULL),
       (10, 1, 781488231601549312, NULL, '0', '2024-04-06 17:38:50.8710', '2024-04-14 20:38:38.5830',
        '768460662077796352', NULL);
/*!40000 ALTER TABLE `alarm_group_user` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `app`
--

DROP TABLE IF EXISTS `app`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app`
(
    `id`                      bigint                                                        NOT NULL AUTO_INCREMENT,
    `app_id`                  varchar(64)                                                   NOT NULL,
    `app_name`                varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `description`             varchar(255)                                                  DEFAULT NULL,
    `repo`                    varchar(255)                                                  DEFAULT NULL,
    `default_branch`          varchar(255)                                                  DEFAULT NULL,
    `language`                varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `develop_mode`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `owner`                   bigint                                                        DEFAULT NULL,
    `health_check`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `status`                  char(4)                                                       DEFAULT '0',
    `is_deleted`              char(4)                                                       DEFAULT '0',
    `gmt_create`              datetime                                                      DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified`            datetime                                                      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `department_abbreviation` varchar(255)                                                  DEFAULT NULL,
    `department`              varchar(255)                                                  DEFAULT NULL,
    `creator`                 varchar(255)                                                  DEFAULT NULL,
    `modifier`                varchar(255)                                                  DEFAULT NULL,
    `webhook`                 varchar(255)                                                  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=781513981771788289 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app`
--

LOCK
TABLES `app` WRITE;
/*!40000 ALTER TABLE `app` DISABLE KEYS */;
INSERT INTO `app`
VALUES (1, '1', 'cake-devops-base', '测试应用001', 'https://github.com/WXzhongwang/cake-devops-base.git', 'origin/main',
        'JAVA', 'FREEDOM', 768821468053254144, '/ok', '0', '0', '2023-02-03 23:44:31', '2024-01-15 21:27:57', 'Honda',
        'cake-honda', NULL, NULL,
        'https://oapi.dingtalk.com/robot/send?access_token=89ca235dbe9f617f4ca045a1f24b0e61a32e9f845771752416377089c36470b7');
/*!40000 ALTER TABLE `app` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `app_env`
--

DROP TABLE IF EXISTS `app_env`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_env`
(
    `id`            bigint                                                        NOT NULL AUTO_INCREMENT,
    `cluster_id`    varchar(64)                                                   NOT NULL,
    `app_id`        varchar(64)                                                   NOT NULL,
    `domains`       varchar(255)                                             DEFAULT NULL,
    `env_name`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `env`           varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL,
    `replicas`      int                                                      DEFAULT NULL,
    `cpu`           varchar(64)                                              DEFAULT NULL,
    `max_cpu`       varchar(64)                                              DEFAULT NULL,
    `memory`        varchar(64)                                              DEFAULT NULL,
    `max_memory`    varchar(64)                                              DEFAULT NULL,
    `auto_scaling`  char(1)                                                  DEFAULT NULL,
    `need_approval` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
    `status`        char(4)                                                  DEFAULT '0',
    `is_deleted`    char(4)                                                  DEFAULT '0',
    `gmt_create`    datetime                                                 DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified`  datetime                                                 DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `creator`       varchar(255)                                             DEFAULT NULL,
    `modifier`      varchar(255)                                             DEFAULT NULL,
    `env_id`        varchar(64)                                                   NOT NULL,
    `deploy_status` varchar(4)                                                    NOT NULL,
    `progress`      varchar(2000)                                            DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_env`
--

LOCK
TABLES `app_env` WRITE;
/*!40000 ALTER TABLE `app_env` DISABLE KEYS */;
INSERT INTO `app_env`
VALUES (1, '1', '1', 'cake-devops.rany.com', '开发环境', 'TEST', 1, '1C', '1C', '500M', '1024M', '0', '0', '1', '0',
        '2023-11-23 20:51:15', '2024-01-21 15:52:16', NULL, NULL, '897615291213819903', '0',
        '{\"current\":9,\"endDate\":1705823536458,\"pipeKey\":\"202401211548290008\",\"startDate\":1705823311955,\"status\":\"error\",\"steps\":[{\"cost\":0,\"description\":\"EXECUTED\",\"endDate\":1705823312238,\"startDate\":1705823312222,\"title\":\"发布审批校验\"},{\"cost\":0,\"description\":\"EXECUTED\",\"endDate\":1705823312261,\"startDate\":1705823312261,\"title\":\"封网校验\"},{\"cost\":0,\"description\":\"EXECUTED\",\"endDate\":1705823312387,\"startDate\":1705823312287,\"title\":\"工作机选择\"},{\"cost\":2,\"description\":\"EXECUTED\",\"endDate\":1705823315081,\"startDate\":1705823312493,\"title\":\"创建工作空间\"},{\"cost\":71,\"description\":\"EXECUTED\",\"endDate\":1705823386261,\"startDate\":1705823315213,\"title\":\"拉取代码\"},{\"cost\":48,\"description\":\"EXECUTED\",\"endDate\":1705823435426,\"startDate\":1705823386433,\"title\":\"Maven构建\"},{\"cost\":61,\"description\":\"EXECUTED\",\"endDate\":1705823496579,\"startDate\":1705823435478,\"title\":\"sonar代码扫描\"},{\"cost\":22,\"description\":\"EXECUTED\",\"endDate\":1705823519117,\"startDate\":1705823496708,\"title\":\"镜像构建\"},{\"cost\":17,\"description\":\"EXECUTED\",\"endDate\":1705823536394,\"startDate\":1705823519168,\"title\":\"推送镜像到阿里云\"}]}'),
       (2, '1', '1', NULL, '测试环境新增001', 'TEST', 2, '0.5C', '1C', '500M', '1024M', '0', '0', '1', '0',
        '2023-12-21 22:38:04', '2024-01-18 22:47:17', NULL, NULL, '897615291213819904', '0', NULL),
       (3, '1', '1', 'pre.cake.com', '预发', 'PRE', 2, '1C', '1C', '1024M', '1C', '0', '0', '0', '0', NULL,
        '2024-01-18 22:47:17', NULL, NULL, '898888447652147200', '0', NULL);
/*!40000 ALTER TABLE `app_env` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `app_member`
--

DROP TABLE IF EXISTS `app_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_member`
(
    `id`           bigint      NOT NULL AUTO_INCREMENT,
    `member_id`    varchar(64) NOT NULL,
    `account_id`   varchar(64) NOT NULL,
    `app_id`       varchar(64)  DEFAULT NULL,
    `roles`        varchar(512) DEFAULT NULL,
    `status`       char(4)      DEFAULT '0',
    `is_deleted`   char(4)      DEFAULT '0',
    `gmt_create`   datetime     DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `creator`      varchar(255) DEFAULT NULL,
    `modifier`     varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=781514013132599302 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_member`
--

LOCK
TABLES `app_member` WRITE;
/*!40000 ALTER TABLE `app_member` DISABLE KEYS */;
INSERT INTO `app_member`
VALUES (781514010909618176, '1', '768821468053254144', '1', 'OWNER,DEVELOPER,TESTER,OPERATOR', '0', '0',
        '2023-02-03 23:44:31', '2024-04-09 23:04:29', NULL, '768460662077796352'),
       (781514013132599296, '2', '781488231601549312', '1', 'DEVELOPER,CHECKER,REPORTER', '1', '1',
        '2023-02-03 23:44:31', '2024-01-08 23:47:30', NULL, NULL),
       (781514013132599299, '937688668443914240', '768460662077796352', '1',
        'CHECKER,REPORTER,ARCHITECT,OPERATOR,TESTER,DEVELOPER,OWNER', '1', '1', '2024-04-09 22:46:50',
        '2024-04-09 22:53:28', NULL, '768460662077796352'),
       (781514013132599300, '937693253489274880', '781488231601549312', '1', 'ARCHITECT', '1', '1',
        '2024-04-09 23:05:03', '2024-04-09 23:06:34', NULL, '768460662077796352'),
       (781514013132599301, '937695762198966272', '781488231601549312', '1', 'CHECKER', '0', '0', '2024-04-09 23:15:01',
        '2024-04-09 23:15:01', NULL, NULL);
/*!40000 ALTER TABLE `app_member` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `approval`
--

DROP TABLE IF EXISTS `approval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `approval`
(
    `id`              bigint       NOT NULL AUTO_INCREMENT,
    `approval_id`     varchar(64)  NOT NULL,
    `doc_address`     varchar(500) NOT NULL,
    `change_date`     datetime     NOT NULL,
    `approval_status` varchar(64)  NOT NULL,
    `comment`         varchar(64)  NOT NULL,
    `is_deleted`      char(4)      DEFAULT '0',
    `gmt_create`      datetime     DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified`    datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `creator`         varchar(255) DEFAULT NULL,
    `modifier`        varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `approval`
--

LOCK
TABLES `approval` WRITE;
/*!40000 ALTER TABLE `approval` DISABLE KEYS */;
INSERT INTO `approval`
VALUES (1, '906357378688102400', 'http://yuque.com', '2024-01-14 11:47:16', 'APPROVED', '备注', NULL, NULL,
        '2024-01-16 21:31:00', NULL, NULL),
       (2, '906357551837360128', 'http://yuque.com', '2024-01-14 11:47:47', 'APPROVED', '备注', NULL, NULL,
        '2024-01-16 21:31:06', NULL, NULL),
       (3, '906359020359659520', 'http://yuque.com', '2024-01-14 11:53:47', 'APPROVED', '备注内容', '0',
        '2024-01-14 11:54:00', '2024-01-16 21:31:12', NULL, NULL),
       (4, '906669494347378688', 'http://yuque.com', '2024-01-31 08:27:26', 'APPROVED', '备注', '0',
        '2024-01-15 08:27:43', '2024-01-16 21:31:17', NULL, NULL),
       (5, '908309878756487168', 'http://yuque.com', '2024-01-31 21:05:37', 'AUTO_APPROVED', 'v2.0.0-beta', '0',
        '2024-01-19 21:06:01', '2024-01-19 21:06:01', NULL, NULL),
       (6, '908309900323598336', 'http://yuque.com', '2024-01-31 21:05:37', 'AUTO_APPROVED', 'v2.0.0-beta', '0',
        '2024-01-19 21:06:06', '2024-01-19 21:06:06', NULL, NULL),
       (7, '937674263555616768', 'http://yuque.com', '2024-04-09 21:49:26', 'AUTO_APPROVED', '11111', '0',
        '2024-04-09 21:49:36', '2024-04-09 21:49:36', NULL, NULL);
/*!40000 ALTER TABLE `approval` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `cluster`
--

DROP TABLE IF EXISTS `cluster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cluster`
(
    `id`                bigint      NOT NULL AUTO_INCREMENT,
    `cluster_id`        varchar(64) NOT NULL,
    `name`              varchar(255)                                                  DEFAULT NULL,
    `tags`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
    `version`           varchar(255)                                                  DEFAULT NULL,
    `cluster_type`      char(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci     DEFAULT NULL,
    `status`            char(4)                                                       DEFAULT NULL,
    `is_deleted`        char(4)                                                       DEFAULT '0',
    `gmt_create`        datetime                                                      DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified`      datetime                                                      DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `creator`           varchar(255)                                                  DEFAULT NULL,
    `modifier`          varchar(255)                                                  DEFAULT NULL,
    `connection_string` varchar(200)                                                  DEFAULT NULL,
    `token`             varchar(200)                                                  DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cluster`
--

LOCK
TABLES `cluster` WRITE;
/*!40000 ALTER TABLE `cluster` DISABLE KEYS */;
INSERT INTO `cluster`
VALUES (1, '1', 'test-clueter-01', 'test,dev', '1.19', 'K8S', '0', '0', '2023-11-23 08:47:32', '2024-01-07 20:03:06',
        NULL, NULL, '127.0.0.1:8080', 'token123');
/*!40000 ALTER TABLE `cluster` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `file_transfer_log`
--

DROP TABLE IF EXISTS `file_transfer_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file_transfer_log`
(
    `id`              bigint   NOT NULL AUTO_INCREMENT COMMENT 'id',
    `account_id`      bigint            DEFAULT NULL COMMENT '用户id',
    `username`        varchar(32)       DEFAULT NULL COMMENT '用户名',
    `file_token`      varchar(128)      DEFAULT NULL COMMENT '文件token',
    `transfer_type`   tinyint           DEFAULT NULL COMMENT '传输类型 10上传 20下载 30传输 40打包',
    `host_id`         varchar(64)       DEFAULT NULL COMMENT '机器id',
    `remote_file`     varchar(1024)     DEFAULT NULL COMMENT '远程文件',
    `local_file`      varchar(512)      DEFAULT NULL COMMENT '本机文件',
    `current_size`    bigint            DEFAULT NULL COMMENT '当前传输大小',
    `file_size`       bigint            DEFAULT NULL COMMENT '文件大小',
    `now_progress` double (5,2) DEFAULT NULL COMMENT '当前进度',
    `transfer_status` tinyint           DEFAULT NULL COMMENT '传输状态 10未开始 20进行中 30已暂停 40已完成 50已取消 60传输异常',
    `creator`         varchar(255)      DEFAULT NULL,
    `modifier`        varchar(255)      DEFAULT NULL,
    `is_deleted`      char(4)           DEFAULT '0',
    `gmt_create`      datetime          DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified`    datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    KEY               `user_machine_idx` (`account_id`,`host_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=DYNAMIC COMMENT='sftp传输日志表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_transfer_log`
--

LOCK
TABLES `file_transfer_log` WRITE;
/*!40000 ALTER TABLE `file_transfer_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `file_transfer_log` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `group_host`
--

DROP TABLE IF EXISTS `group_host`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_host`
(
    `id`           bigint      NOT NULL AUTO_INCREMENT,
    `group_id`     varchar(64) NOT NULL,
    `host_id`      varchar(64) NOT NULL,
    `is_deleted`   char(4)      DEFAULT '0',
    `gmt_create`   datetime     DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `creator`      varchar(255) DEFAULT NULL,
    `modifier`     varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_host`
--

LOCK
TABLES `group_host` WRITE;
/*!40000 ALTER TABLE `group_host` DISABLE KEYS */;
INSERT INTO `group_host`
VALUES (1, '1', '936499355525984256', '1', '2024-04-06 16:00:56', '2024-05-25 20:08:24', '768460662077796352',
        '768460662077796352'),
       (13, '1', '954318785865723904', '0', '2024-05-25 20:08:59', '2024-05-25 20:08:59', '768460662077796352', NULL);
/*!40000 ALTER TABLE `group_host` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `host`
--

DROP TABLE IF EXISTS `host`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `host`
(
    `id`           bigint       NOT NULL AUTO_INCREMENT,
    `host_id`      varchar(64)  NOT NULL,
    `name`         varchar(255) NOT NULL,
    `host_name`    varchar(255) NOT NULL,
    `server_addr`  varchar(255)          DEFAULT NULL,
    `port`         int          NOT NULL,
    `auth_type`    int                   DEFAULT NULL,
    `username`     varchar(255) NOT NULL,
    `pwd`          varchar(255)          DEFAULT NULL,
    `proxy_id`     bigint                DEFAULT NULL COMMENT '代理ID',
    `key_id`       bigint                DEFAULT NULL COMMENT '秘钥ID',
    `status`       char(4)               DEFAULT '0',
    `is_deleted`   char(4)               DEFAULT '0',
    `gmt_create`   datetime              DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `description`  varchar(255)          DEFAULT NULL,
    `verified`     char(2)               DEFAULT NULL,
    `creator`      varchar(255)          DEFAULT NULL,
    `modifier`     varchar(255)          DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `host`
--

LOCK
TABLES `host` WRITE;
/*!40000 ALTER TABLE `host` DISABLE KEYS */;
INSERT INTO `host`
VALUES (1, '936499355525984256', '本地', 'localhostcdddkkkk', '127.0.0.1', 22, 1, 'yuanjinxiu',
        'hAB2fesg7tLR6YWXFwJd7A==', NULL, NULL, '0', '1', '2024-04-06 16:00:56', '2024-05-25 20:08:24', NULL, NULL,
        '768460662077796352', '768460662077796352'),
       (13, '954318785865723904', 'local', 'local', '127.0.0.1', 22, 1, 'yuanjinxiu', 'hAB2fesg7tLR6YWXFwJd7A==', NULL,
        NULL, '0', '0', '2024-05-25 20:08:59', '2024-05-25 20:08:59', NULL, NULL, '768460662077796352', NULL);
/*!40000 ALTER TABLE `host` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `host_alarm_config`
--

DROP TABLE IF EXISTS `host_alarm_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `host_alarm_config`
(
    `id`                bigint                          NOT NULL AUTO_INCREMENT COMMENT 'id',
    `host_id`           varchar(64) COLLATE utf8mb3_bin NOT NULL COMMENT '机器ID',
    `alarm_type`        int                              DEFAULT NULL COMMENT '报警类型 10: cpu使用率 20: 内存使用率',
    `alarm_threshold` double DEFAULT NULL COMMENT '报警阈值',
    `trigger_threshold` int                              DEFAULT NULL COMMENT '触发报警阈值 次',
    `notify_silence`    int                              DEFAULT NULL COMMENT '报警通知沉默时间 分',
    `is_deleted`        varchar(5) COLLATE utf8mb3_bin   DEFAULT '1' COMMENT '是否删除 0未删除 1已删除',
    `gmt_create`        datetime(4) DEFAULT CURRENT_TIMESTAMP (4) COMMENT '创建时间',
    `gmt_modified`      datetime(4) DEFAULT CURRENT_TIMESTAMP (4) ON UPDATE CURRENT_TIMESTAMP (4) COMMENT '修改时间',
    `creator`           varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
    `modifier`          varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC COMMENT='机器报警配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `host_alarm_config`
--

LOCK
TABLES `host_alarm_config` WRITE;
/*!40000 ALTER TABLE `host_alarm_config` DISABLE KEYS */;
INSERT INTO `host_alarm_config`
VALUES (21, '936499355525984256', 10, 0.05, 1, 1, '0', '2024-04-14 20:38:24.2680', '2024-04-14 20:38:24.2680',
        '768460662077796352', NULL),
       (22, '936499355525984256', 20, 0.05, 1, 1, '0', '2024-04-14 20:38:24.2750', '2024-04-14 20:38:24.2750',
        '768460662077796352', NULL);
/*!40000 ALTER TABLE `host_alarm_config` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `host_alarm_group`
--

DROP TABLE IF EXISTS `host_alarm_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `host_alarm_group`
(
    `id`             bigint      NOT NULL AUTO_INCREMENT,
    `host_id`        varchar(64) NOT NULL,
    `alarm_group_id` bigint      NOT NULL,
    `is_deleted`     char(4)      DEFAULT '0',
    `gmt_create`     datetime     DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified`   datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `creator`        varchar(255) DEFAULT NULL,
    `modifier`       varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `host_alarm_group`
--

LOCK
TABLES `host_alarm_group` WRITE;
/*!40000 ALTER TABLE `host_alarm_group` DISABLE KEYS */;
INSERT INTO `host_alarm_group`
VALUES (8, '936499355525984256', 2, '0', '2024-04-14 20:38:24', '2024-04-14 20:38:24', '768460662077796352', NULL),
       (9, '936499355525984256', 1, '0', '2024-04-14 20:38:24', '2024-04-14 20:38:24', '768460662077796352', NULL);
/*!40000 ALTER TABLE `host_alarm_group` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `host_alarm_history`
--

DROP TABLE IF EXISTS `host_alarm_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `host_alarm_history`
(
    `id`           bigint                          NOT NULL AUTO_INCREMENT COMMENT 'id',
    `host_id`      varchar(64) COLLATE utf8mb3_bin NOT NULL COMMENT '机器ID',
    `alarm_type`   int                            DEFAULT NULL COMMENT '报警类型 10: cpu使用率 20: 内存使用率',
    `alarm_value` double DEFAULT NULL COMMENT '报警值',
    `alarm_time`   datetime(4) DEFAULT NULL COMMENT '报警时间',
    `is_deleted`   varchar(5) COLLATE utf8mb3_bin DEFAULT '1' COMMENT '是否删除 0未删除 1已删除',
    `gmt_create`   datetime(4) DEFAULT CURRENT_TIMESTAMP (4) COMMENT '创建时间',
    `gmt_modified` datetime(4) DEFAULT CURRENT_TIMESTAMP (4) ON UPDATE CURRENT_TIMESTAMP (4) COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=986 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC COMMENT='机器报警历史值';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `host_alarm_history`
--

LOCK
TABLES `host_alarm_history` WRITE;
/*!40000 ALTER TABLE `host_alarm_history` DISABLE KEYS */;
INSERT INTO `host_alarm_history`
VALUES (1, '936499355525984256', 10, 19.281, '2024-04-14 20:46:17.5300', '0', '2024-04-14 20:46:17.6500',
        '2024-04-14 20:46:17.6500'),
       (2, '936499355525984256', 20, 83.026, '2024-04-14 20:46:20.0970', '0', '2024-04-14 20:46:20.1200',
        '2024-04-14 20:46:20.1200'),
       (3, '936499355525984256', 10, 24.64, '2024-04-14 20:47:47.5060', '0', '2024-04-14 20:47:47.5730',
        '2024-04-14 20:47:47.5730'),
       (4, '936499355525984256', 20, 83.344, '2024-04-14 20:47:48.6490', '0', '2024-04-14 20:47:48.6650',
        '2024-04-14 20:47:48.6650'),
       (5, '936499355525984256', 10, 22.905, '2024-04-14 20:49:17.4790', '0', '2024-04-14 20:49:17.5630',
        '2024-04-14 20:49:17.5630'),
       (6, '936499355525984256', 20, 83.101, '2024-04-14 20:49:18.8010', '0', '2024-04-14 20:49:18.8100',
        '2024-04-14 20:49:18.8100'),
       (7, '936499355525984256', 10, 30.517, '2024-04-14 20:50:47.4840', '0', '2024-04-14 20:50:47.6160',
        '2024-04-14 20:50:47.6160'),
       (8, '936499355525984256', 20, 83.146, '2024-04-14 20:50:48.7580', '0', '2024-04-14 20:50:48.7700',
        '2024-04-14 20:50:48.7700'),
       (9, '936499355525984256', 10, 24.993, '2024-04-14 20:51:47.4950', '0', '2024-04-14 20:51:47.5770',
        '2024-04-14 20:51:47.5770'),
       (10, '936499355525984256', 20, 82.861, '2024-04-14 20:51:48.9740', '0', '2024-04-14 20:51:48.9840',
        '2024-04-14 20:51:48.9840'),
       (11, '936499355525984256', 10, 27.357, '2024-04-14 20:53:17.4680', '0', '2024-04-14 20:53:17.5510',
        '2024-04-14 20:53:17.5510'),
       (12, '936499355525984256', 20, 82.728, '2024-04-14 20:53:18.6380', '0', '2024-04-14 20:53:18.6550',
        '2024-04-14 20:53:18.6550'),
       (13, '936499355525984256', 10, 30.626, '2024-04-14 20:54:17.4690', '0', '2024-04-14 20:54:17.5370',
        '2024-04-14 20:54:17.5370'),
       (14, '936499355525984256', 20, 82.754, '2024-04-14 20:54:18.7110', '0', '2024-04-14 20:54:18.7250',
        '2024-04-14 20:54:18.7250'),
       (15, '936499355525984256', 10, 27.772, '2024-04-14 20:55:17.4850', '0', '2024-04-14 20:55:17.5940',
        '2024-04-14 20:55:17.5950'),
       (16, '936499355525984256', 20, 83.125, '2024-04-14 20:55:18.8180', '0', '2024-04-14 20:55:18.8590',
        '2024-04-14 20:55:18.8590'),
       (17, '936499355525984256', 10, 16.544, '2024-04-14 20:56:17.4970', '0', '2024-04-14 20:56:17.5510',
        '2024-04-14 20:56:17.5510'),
       (18, '936499355525984256', 20, 83.075, '2024-04-14 20:56:47.4860', '0', '2024-04-14 20:56:47.5130',
        '2024-04-14 20:56:47.5130'),
       (19, '936499355525984256', 10, 32.75, '2024-04-14 20:57:47.4760', '0', '2024-04-14 20:57:47.5080',
        '2024-04-14 20:57:47.5080'),
       (20, '936499355525984256', 20, 82.513, '2024-04-14 20:57:48.5190', '0', '2024-04-14 20:57:48.5560',
        '2024-04-14 20:57:48.5560'),
       (21, '936499355525984256', 10, 24.145, '2024-04-14 20:58:47.4950', '0', '2024-04-14 20:58:47.5480',
        '2024-04-14 20:58:47.5480'),
       (22, '936499355525984256', 20, 83.018, '2024-04-14 20:58:48.9860', '0', '2024-04-14 20:58:49.0020',
        '2024-04-14 20:58:49.0020'),
       (23, '936499355525984256', 10, 24.94, '2024-04-14 21:00:17.4730', '0', '2024-04-14 21:00:17.5180',
        '2024-04-14 21:00:17.5180'),
       (24, '936499355525984256', 20, 82.095, '2024-04-14 21:00:19.0110', '0', '2024-04-14 21:00:19.0350',
        '2024-04-14 21:00:19.0350'),
       (25, '936499355525984256', 10, 30.838, '2024-04-14 21:01:17.4900', '0', '2024-04-14 21:01:17.5200',
        '2024-04-14 21:01:17.5200'),
       (26, '936499355525984256', 20, 83.052, '2024-04-14 21:01:47.4730', '0', '2024-04-14 21:01:47.5270',
        '2024-04-14 21:01:47.5270'),
       (27, '936499355525984256', 10, 42.967, '2024-04-20 20:47:10.3220', '0', '2024-04-20 20:47:11.2470',
        '2024-04-20 20:47:11.2500'),
       (28, '936499355525984256', 20, 83.164, '2024-04-20 20:47:14.2750', '0', '2024-04-20 20:47:14.2960',
        '2024-04-20 20:47:14.2960'),
       (29, '936499355525984256', 10, 28.542, '2024-04-20 20:48:40.2720', '0', '2024-04-20 20:48:40.4220',
        '2024-04-20 20:48:40.4220'),
       (30, '936499355525984256', 20, 83.28, '2024-04-20 20:48:42.5580', '0', '2024-04-20 20:48:42.5830',
        '2024-04-20 20:48:42.5830'),
       (31, '936499355525984256', 10, 27.168, '2024-04-20 20:49:40.2830', '0', '2024-04-20 20:49:40.3600',
        '2024-04-20 20:49:40.3600'),
       (32, '936499355525984256', 20, 83.242, '2024-04-20 20:50:10.2920', '0', '2024-04-20 20:50:10.3820',
        '2024-04-20 20:50:10.3820'),
       (33, '936499355525984256', 10, 32.769, '2024-04-20 20:50:40.2880', '0', '2024-04-20 20:50:40.3780',
        '2024-04-20 20:50:40.3780'),
       (34, '936499355525984256', 10, 33.468, '2024-04-20 20:52:10.2980', '0', '2024-04-20 20:52:10.7930',
        '2024-04-20 20:52:10.7950'),
       (35, '936499355525984256', 20, 83.207, '2024-04-20 20:52:12.8140', '0', '2024-04-20 20:52:12.8320',
        '2024-04-20 20:52:12.8320'),
       (36, '936499355525984256', 10, 37.58, '2024-04-20 20:53:40.2900', '0', '2024-04-20 20:53:40.4260',
        '2024-04-20 20:53:40.4260'),
       (37, '936499355525984256', 20, 83.4, '2024-04-20 20:53:41.4580', '0', '2024-04-20 20:53:41.4750',
        '2024-04-20 20:53:41.4750'),
       (38, '936499355525984256', 10, 34.568, '2024-04-20 20:55:10.2890', '0', '2024-04-20 20:55:10.4040',
        '2024-04-20 20:55:10.4040'),
       (39, '936499355525984256', 20, 83.242, '2024-04-20 20:55:11.4560', '0', '2024-04-20 20:55:11.4670',
        '2024-04-20 20:55:11.4670'),
       (40, '936499355525984256', 10, 69.698, '2024-04-20 20:56:10.2930', '0', '2024-04-20 20:56:10.4250',
        '2024-04-20 20:56:10.4250'),
       (41, '936499355525984256', 20, 83.148, '2024-04-20 20:56:11.9810', '0', '2024-04-20 20:56:11.9970',
        '2024-04-20 20:56:11.9980'),
       (42, '936499355525984256', 10, 20.631, '2024-04-20 20:57:40.2860', '0', '2024-04-20 20:57:40.4260',
        '2024-04-20 20:57:40.4260'),
       (43, '936499355525984256', 20, 83.169, '2024-04-20 20:57:41.5490', '0', '2024-04-20 20:57:41.5640',
        '2024-04-20 20:57:41.5640'),
       (44, '936499355525984256', 10, 21.965, '2024-04-20 20:59:10.2890', '0', '2024-04-20 20:59:10.3450',
        '2024-04-20 20:59:10.3450'),
       (45, '936499355525984256', 20, 83.028, '2024-04-20 20:59:11.3730', '0', '2024-04-20 20:59:11.3920',
        '2024-04-20 20:59:11.3920'),
       (46, '936499355525984256', 10, 25.064, '2024-04-20 21:00:10.3160', '0', '2024-04-20 21:00:10.4110',
        '2024-04-20 21:00:10.4110'),
       (47, '936499355525984256', 20, 83.188, '2024-04-20 21:00:11.5430', '0', '2024-04-20 21:00:11.5540',
        '2024-04-20 21:00:11.5540'),
       (48, '936499355525984256', 10, 25.053, '2024-04-20 21:01:10.3210', '0', '2024-04-20 21:01:10.4520',
        '2024-04-20 21:01:10.4520'),
       (49, '936499355525984256', 20, 83.082, '2024-04-20 21:01:11.6190', '0', '2024-04-20 21:01:11.6480',
        '2024-04-20 21:01:11.6480'),
       (50, '936499355525984256', 10, 25.328, '2024-04-20 21:02:10.3650', '0', '2024-04-20 21:02:10.4330',
        '2024-04-20 21:02:10.4330'),
       (51, '936499355525984256', 20, 82.819, '2024-04-20 21:02:40.3400', '0', '2024-04-20 21:02:40.4020',
        '2024-04-20 21:02:40.4020'),
       (52, '936499355525984256', 10, 18.781, '2024-04-20 21:03:40.3360', '0', '2024-04-20 21:03:40.4380',
        '2024-04-20 21:03:40.4380'),
       (53, '936499355525984256', 20, 82.73, '2024-04-20 21:04:20.2550', '0', '2024-04-20 21:04:20.3400',
        '2024-04-20 21:04:20.3400'),
       (54, '936499355525984256', 10, 24.28, '2024-04-20 21:04:35.4030', '0', '2024-04-20 21:04:35.4180',
        '2024-04-20 21:04:35.4180'),
       (55, '936499355525984256', 20, 83.019, '2024-04-20 21:20:47.1960', '0', '2024-04-20 21:20:47.2190',
        '2024-04-20 21:20:47.2190'),
       (56, '936499355525984256', 10, 24.679, '2024-04-20 21:53:26.7820', '0', '2024-04-20 21:53:26.8270',
        '2024-04-20 21:53:26.8270'),
       (57, '936499355525984256', 20, 81.366, '2024-04-20 22:11:13.1800', '0', '2024-04-20 22:11:13.2220',
        '2024-04-20 22:11:13.2220'),
       (58, '936499355525984256', 10, 25.869, '2024-04-20 22:28:28.4780', '0', '2024-04-20 22:28:28.5550',
        '2024-04-20 22:28:28.5550'),
       (59, '936499355525984256', 20, 82.501, '2024-04-20 22:28:28.6740', '0', '2024-04-20 22:28:28.7030',
        '2024-04-20 22:28:28.7030'),
       (60, '936499355525984256', 10, 24.363, '2024-04-20 22:46:56.0730', '0', '2024-04-20 22:46:56.1830',
        '2024-04-20 22:46:56.1830'),
       (61, '936499355525984256', 20, 83.047, '2024-04-20 22:46:56.2410', '0', '2024-04-20 22:46:56.2630',
        '2024-04-20 22:46:56.2630'),
       (62, '936499355525984256', 10, 21.866, '2024-04-20 23:22:39.5810', '0', '2024-04-20 23:22:39.7100',
        '2024-04-20 23:22:39.7100'),
       (63, '936499355525984256', 20, 83.003, '2024-04-20 23:22:39.7660', '0', '2024-04-20 23:22:39.7710',
        '2024-04-20 23:22:39.7710'),
       (64, '936499355525984256', 10, 24.745, '2024-04-20 23:43:12.2650', '0', '2024-04-20 23:43:12.4630',
        '2024-04-20 23:43:12.4630'),
       (65, '936499355525984256', 20, 83.019, '2024-04-20 23:44:08.3200', '0', '2024-04-20 23:44:08.3360',
        '2024-04-20 23:44:08.3360'),
       (66, '936499355525984256', 10, 25.2, '2024-04-21 00:02:06.9520', '0', '2024-04-21 00:02:07.1030',
        '2024-04-21 00:02:07.1030'),
       (67, '936499355525984256', 20, 82.922, '2024-04-21 00:02:07.2080', '0', '2024-04-21 00:02:07.2350',
        '2024-04-21 00:02:07.2350'),
       (68, '936499355525984256', 10, 21.687, '2024-04-21 00:36:24.1740', '0', '2024-04-21 00:36:24.2420',
        '2024-04-21 00:36:24.2420'),
       (69, '936499355525984256', 20, 83.06, '2024-04-21 00:36:24.3030', '0', '2024-04-21 00:36:24.3300',
        '2024-04-21 00:36:24.3300'),
       (70, '936499355525984256', 10, 23.264, '2024-04-21 00:53:38.3200', '0', '2024-04-21 00:53:38.4700',
        '2024-04-21 00:53:38.4700'),
       (71, '936499355525984256', 20, 83.013, '2024-04-21 00:53:38.5590', '0', '2024-04-21 00:53:38.5860',
        '2024-04-21 00:53:38.5860'),
       (72, '936499355525984256', 10, 18.625, '2024-04-21 01:12:00.7410', '0', '2024-04-21 01:12:00.8280',
        '2024-04-21 01:12:00.8280'),
       (73, '936499355525984256', 20, 83.046, '2024-04-21 01:12:00.8970', '0', '2024-04-21 01:12:00.9150',
        '2024-04-21 01:12:00.9150'),
       (74, '936499355525984256', 10, 19.507, '2024-04-21 01:45:32.1420', '0', '2024-04-21 01:45:32.2470',
        '2024-04-21 01:45:32.2470'),
       (75, '936499355525984256', 20, 83.055, '2024-04-21 01:45:32.5320', '0', '2024-04-21 01:45:32.5450',
        '2024-04-21 01:45:32.5450'),
       (76, '936499355525984256', 10, 22.814, '2024-04-21 03:06:26.5510', '0', '2024-04-21 03:06:26.6520',
        '2024-04-21 03:06:26.6520'),
       (77, '936499355525984256', 20, 83.01, '2024-04-21 03:22:50.2700', '0', '2024-04-21 03:22:50.3150',
        '2024-04-21 03:22:50.3150'),
       (78, '936499355525984256', 10, 21.049, '2024-04-21 03:52:28.2770', '0', '2024-04-21 03:52:28.3020',
        '2024-04-21 03:52:28.3020'),
       (79, '936499355525984256', 20, 83.113, '2024-04-21 03:52:28.3660', '0', '2024-04-21 03:52:28.3970',
        '2024-04-21 03:52:28.3970'),
       (80, '936499355525984256', 10, 17.139, '2024-04-21 04:10:56.0150', '0', '2024-04-21 04:10:56.0850',
        '2024-04-21 04:10:56.0850'),
       (81, '936499355525984256', 20, 83.184, '2024-04-21 04:10:58.4350', '0', '2024-04-21 04:10:58.4560',
        '2024-04-21 04:10:58.4560'),
       (82, '936499355525984256', 10, 23.53, '2024-04-21 04:40:46.7270', '0', '2024-04-21 04:40:46.7850',
        '2024-04-21 04:40:46.7850'),
       (83, '936499355525984256', 20, 83.122, '2024-04-21 04:40:46.8630', '0', '2024-04-21 04:40:46.8850',
        '2024-04-21 04:40:46.8850'),
       (84, '936499355525984256', 10, 19.84, '2024-04-21 04:56:23.5110', '0', '2024-04-21 04:56:23.6370',
        '2024-04-21 04:56:23.6370'),
       (85, '936499355525984256', 20, 83.004, '2024-04-21 04:56:23.7050', '0', '2024-04-21 04:56:23.7120',
        '2024-04-21 04:56:23.7120'),
       (86, '936499355525984256', 10, 19.508, '2024-04-21 05:14:05.9470', '0', '2024-04-21 05:14:06.0090',
        '2024-04-21 05:14:06.0090'),
       (87, '936499355525984256', 20, 83.042, '2024-04-21 05:14:06.0860', '0', '2024-04-21 05:14:06.1030',
        '2024-04-21 05:14:06.1030'),
       (88, '936499355525984256', 10, 27.501, '2024-04-21 05:30:42.4700', '0', '2024-04-21 05:30:42.6380',
        '2024-04-21 05:30:42.6380'),
       (89, '936499355525984256', 20, 82.679, '2024-04-21 05:30:42.7500', '0', '2024-04-21 05:30:42.7820',
        '2024-04-21 05:30:42.7820'),
       (90, '936499355525984256', 10, 20.478, '2024-04-21 05:47:42.0070', '0', '2024-04-21 05:47:42.0550',
        '2024-04-21 05:47:42.0550'),
       (91, '936499355525984256', 20, 82.999, '2024-04-21 05:47:42.1300', '0', '2024-04-21 05:47:42.1450',
        '2024-04-21 05:47:42.1450'),
       (92, '936499355525984256', 10, 20.968, '2024-04-21 06:04:11.7510', '0', '2024-04-21 06:04:11.8960',
        '2024-04-21 06:04:11.8960'),
       (93, '936499355525984256', 20, 83.045, '2024-04-21 06:04:11.9990', '0', '2024-04-21 06:04:12.0100',
        '2024-04-21 06:04:12.0100'),
       (94, '936499355525984256', 10, 19.742, '2024-04-21 06:36:51.3930', '0', '2024-04-21 06:36:51.4720',
        '2024-04-21 06:36:51.4720'),
       (95, '936499355525984256', 20, 83.077, '2024-04-21 06:36:51.5240', '0', '2024-04-21 06:36:51.5290',
        '2024-04-21 06:36:51.5290'),
       (96, '936499355525984256', 10, 15.438, '2024-04-21 06:42:04.3750', '0', '2024-04-21 06:42:04.4170',
        '2024-04-21 06:42:04.4170'),
       (97, '936499355525984256', 20, 83.095, '2024-04-21 06:42:04.4540', '0', '2024-04-21 06:42:04.4820',
        '2024-04-21 06:42:04.4820'),
       (98, '936499355525984256', 10, 16.33, '2024-04-21 06:58:44.1910', '0', '2024-04-21 06:58:44.2510',
        '2024-04-21 06:58:44.2510'),
       (99, '936499355525984256', 20, 83.071, '2024-04-21 06:58:44.3630', '0', '2024-04-21 06:58:44.3840',
        '2024-04-21 06:58:44.3840'),
       (100, '936499355525984256', 10, 18.833, '2024-04-21 07:14:39.1790', '0', '2024-04-21 07:14:39.2480',
        '2024-04-21 07:14:39.2480'),
       (101, '936499355525984256', 20, 83.055, '2024-04-21 07:14:39.3070', '0', '2024-04-21 07:14:39.3260',
        '2024-04-21 07:14:39.3260'),
       (102, '936499355525984256', 10, 26.167, '2024-04-21 07:29:18.3210', '0', '2024-04-21 07:29:18.4440',
        '2024-04-21 07:29:18.4440'),
       (103, '936499355525984256', 20, 83.059, '2024-04-21 07:29:18.5260', '0', '2024-04-21 07:29:18.5620',
        '2024-04-21 07:29:18.5620'),
       (104, '936499355525984256', 10, 29.501, '2024-04-21 08:00:08.8140', '0', '2024-04-21 08:00:08.9920',
        '2024-04-21 08:00:08.9920'),
       (105, '936499355525984256', 20, 83.132, '2024-04-21 08:00:09.0630', '0', '2024-04-21 08:00:09.0780',
        '2024-04-21 08:00:09.0780'),
       (106, '936499355525984256', 10, 24.06, '2024-04-21 09:40:26.3720', '0', '2024-04-21 09:40:26.4330',
        '2024-04-21 09:40:26.4330'),
       (107, '936499355525984256', 20, 83.091, '2024-04-21 09:40:26.4790', '0', '2024-04-21 09:40:26.4920',
        '2024-04-21 09:40:26.4920'),
       (108, '936499355525984256', 10, 25.039, '2024-04-21 11:01:48.5030', '0', '2024-04-21 11:01:48.6160',
        '2024-04-21 11:01:48.6160'),
       (109, '936499355525984256', 20, 83.069, '2024-04-21 11:01:49.0330', '0', '2024-04-21 11:01:49.0720',
        '2024-04-21 11:01:49.0720'),
       (110, '936499355525984256', 10, 23, '2024-04-21 12:44:09.9520', '0', '2024-04-21 12:44:09.9780',
        '2024-04-21 12:44:09.9780'),
       (111, '936499355525984256', 20, 83.053, '2024-04-21 12:44:10.0590', '0', '2024-04-21 12:44:10.0800',
        '2024-04-21 12:44:10.0800'),
       (112, '936499355525984256', 10, 22, '2024-04-21 14:04:39.7650', '0', '2024-04-21 14:04:39.8990',
        '2024-04-21 14:04:39.8990'),
       (113, '936499355525984256', 20, 83.084, '2024-04-21 14:04:39.9610', '0', '2024-04-21 14:04:39.9830',
        '2024-04-21 14:04:39.9830'),
       (114, '936499355525984256', 10, 22.559, '2024-04-21 15:42:05.4830', '0', '2024-04-21 15:42:05.6030',
        '2024-04-21 15:42:05.6030'),
       (115, '936499355525984256', 20, 83.058, '2024-04-21 15:42:05.6690', '0', '2024-04-21 15:42:05.6820',
        '2024-04-21 15:42:05.6820'),
       (116, '936499355525984256', 10, 23.25, '2024-04-21 16:25:50.6530', '0', '2024-04-21 16:25:50.8810',
        '2024-04-21 16:25:50.8810'),
       (117, '936499355525984256', 20, 83.161, '2024-04-21 16:25:50.9830', '0', '2024-04-21 16:25:51.0050',
        '2024-04-21 16:25:51.0050'),
       (118, '936499355525984256', 10, 19.182, '2024-04-21 16:36:49.8890', '0', '2024-04-21 16:36:50.0890',
        '2024-04-21 16:36:50.0890'),
       (119, '936499355525984256', 20, 83.18, '2024-04-21 16:36:50.2240', '0', '2024-04-21 16:36:50.2590',
        '2024-04-21 16:36:50.2590'),
       (120, '936499355525984256', 10, 18.828, '2024-04-21 16:55:27.5860', '0', '2024-04-21 16:55:27.7570',
        '2024-04-21 16:55:27.7570'),
       (121, '936499355525984256', 20, 83.104, '2024-04-21 16:55:27.8290', '0', '2024-04-21 16:55:27.8360',
        '2024-04-21 16:55:27.8360'),
       (122, '936499355525984256', 10, 20, '2024-04-21 17:13:28.6780', '0', '2024-04-21 17:13:28.7430',
        '2024-04-21 17:13:28.7430'),
       (123, '936499355525984256', 20, 83.132, '2024-04-21 17:13:28.8210', '0', '2024-04-21 17:13:28.8290',
        '2024-04-21 17:13:28.8290'),
       (124, '936499355525984256', 10, 24.382, '2024-04-21 17:30:26.6270', '0', '2024-04-21 17:30:26.7470',
        '2024-04-21 17:30:26.7470'),
       (125, '936499355525984256', 20, 83.128, '2024-04-21 17:30:26.8270', '0', '2024-04-21 17:30:26.8360',
        '2024-04-21 17:30:26.8370'),
       (126, '936499355525984256', 10, 22.508, '2024-04-21 17:47:45.2480', '0', '2024-04-21 17:47:45.4440',
        '2024-04-21 17:47:45.4440'),
       (127, '936499355525984256', 20, 83.067, '2024-04-21 17:47:45.5320', '0', '2024-04-21 17:47:45.5520',
        '2024-04-21 17:47:45.5520'),
       (128, '936499355525984256', 10, 19.908, '2024-04-21 18:05:59.8040', '0', '2024-04-21 18:05:59.9210',
        '2024-04-21 18:05:59.9210'),
       (129, '936499355525984256', 20, 83.052, '2024-04-21 18:05:59.9870', '0', '2024-04-21 18:05:59.9980',
        '2024-04-21 18:05:59.9980'),
       (130, '936499355525984256', 10, 20.289, '2024-04-21 18:23:44.4860', '0', '2024-04-21 18:23:44.6460',
        '2024-04-21 18:23:44.6460'),
       (131, '936499355525984256', 20, 83.161, '2024-04-21 18:23:44.8220', '0', '2024-04-21 18:23:44.8400',
        '2024-04-21 18:23:44.8400'),
       (132, '936499355525984256', 10, 19.501, '2024-04-21 18:42:42.0660', '0', '2024-04-21 18:42:42.1370',
        '2024-04-21 18:42:42.1370'),
       (133, '936499355525984256', 20, 83.056, '2024-04-21 18:42:42.2310', '0', '2024-04-21 18:42:42.2480',
        '2024-04-21 18:42:42.2480'),
       (134, '936499355525984256', 10, 27.672, '2024-04-21 18:59:05.8460', '0', '2024-04-21 18:59:06.1630',
        '2024-04-21 18:59:06.1630'),
       (135, '936499355525984256', 20, 82.455, '2024-04-21 18:59:06.4490', '0', '2024-04-21 18:59:06.4720',
        '2024-04-21 18:59:06.4720'),
       (136, '936499355525984256', 10, 24.087, '2024-04-21 19:15:12.1030', '0', '2024-04-21 19:15:12.2640',
        '2024-04-21 19:15:12.2640'),
       (137, '936499355525984256', 20, 82.85, '2024-04-21 19:15:12.3540', '0', '2024-04-21 19:15:12.3690',
        '2024-04-21 19:15:12.3690'),
       (138, '936499355525984256', 10, 19.377, '2024-04-21 19:30:40.8490', '0', '2024-04-21 19:30:40.9240',
        '2024-04-21 19:30:40.9240'),
       (139, '936499355525984256', 20, 83.084, '2024-04-21 19:30:40.9760', '0', '2024-04-21 19:30:40.9800',
        '2024-04-21 19:30:40.9800'),
       (140, '936499355525984256', 10, 18.249, '2024-04-21 19:48:59.0910', '0', '2024-04-21 19:48:59.2320',
        '2024-04-21 19:48:59.2320'),
       (141, '936499355525984256', 20, 83.053, '2024-04-21 19:48:59.2920', '0', '2024-04-21 19:48:59.3050',
        '2024-04-21 19:48:59.3050'),
       (142, '936499355525984256', 10, 19.901, '2024-04-21 20:23:35.6210', '0', '2024-04-21 20:23:35.7070',
        '2024-04-21 20:23:35.7070'),
       (143, '936499355525984256', 20, 83.072, '2024-04-21 20:23:35.8230', '0', '2024-04-21 20:23:35.8320',
        '2024-04-21 20:23:35.8320'),
       (144, '936499355525984256', 10, 18.842, '2024-04-21 20:40:36.0300', '0', '2024-04-21 20:40:36.0730',
        '2024-04-21 20:40:36.0730'),
       (145, '936499355525984256', 20, 82.975, '2024-04-21 20:40:36.1300', '0', '2024-04-21 20:40:36.1500',
        '2024-04-21 20:40:36.1500'),
       (146, '936499355525984256', 10, 20.395, '2024-04-21 20:59:05.0130', '0', '2024-04-21 20:59:05.0690',
        '2024-04-21 20:59:05.0690'),
       (147, '936499355525984256', 20, 83.11, '2024-04-21 20:59:05.1130', '0', '2024-04-21 20:59:05.1330',
        '2024-04-21 20:59:05.1330'),
       (148, '936499355525984256', 10, 20.093, '2024-04-21 21:14:32.6900', '0', '2024-04-21 21:14:32.7900',
        '2024-04-21 21:14:32.7900'),
       (149, '936499355525984256', 20, 83.072, '2024-04-21 21:14:32.8620', '0', '2024-04-21 21:14:32.8830',
        '2024-04-21 21:14:32.8830'),
       (150, '936499355525984256', 10, 20.81, '2024-04-21 21:31:38.2090', '0', '2024-04-21 21:31:38.2690',
        '2024-04-21 21:31:38.2690'),
       (151, '936499355525984256', 20, 83.086, '2024-04-21 21:31:38.3490', '0', '2024-04-21 21:31:38.3620',
        '2024-04-21 21:31:38.3620'),
       (152, '936499355525984256', 10, 20.295, '2024-04-21 21:49:11.7870', '0', '2024-04-21 21:49:11.9190',
        '2024-04-21 21:49:11.9190'),
       (153, '936499355525984256', 20, 83.063, '2024-04-21 21:49:12.0050', '0', '2024-04-21 21:49:12.0160',
        '2024-04-21 21:49:12.0160'),
       (154, '936499355525984256', 10, 20.004, '2024-04-21 22:07:20.5380', '0', '2024-04-21 22:07:20.6500',
        '2024-04-21 22:07:20.6500'),
       (155, '936499355525984256', 20, 83.081, '2024-04-21 22:07:20.6950', '0', '2024-04-21 22:07:20.7030',
        '2024-04-21 22:07:20.7030'),
       (156, '936499355525984256', 10, 20.368, '2024-04-21 22:26:14.3310', '0', '2024-04-21 22:26:14.4630',
        '2024-04-21 22:26:14.4630'),
       (157, '936499355525984256', 20, 83.074, '2024-04-21 22:42:08.5520', '0', '2024-04-21 22:42:08.5970',
        '2024-04-21 22:42:08.5970'),
       (158, '936499355525984256', 10, 24.246, '2024-04-21 22:49:00.0870', '0', '2024-04-21 22:49:00.1650',
        '2024-04-21 22:49:00.1650'),
       (159, '936499355525984256', 20, 83.078, '2024-04-21 22:49:00.2130', '0', '2024-04-21 22:49:00.2450',
        '2024-04-21 22:49:00.2450'),
       (160, '936499355525984256', 10, 20.484, '2024-04-21 23:06:48.3190', '0', '2024-04-21 23:06:48.8060',
        '2024-04-21 23:06:48.8060'),
       (161, '936499355525984256', 20, 81.316, '2024-04-21 23:06:48.9390', '0', '2024-04-21 23:06:48.9540',
        '2024-04-21 23:06:48.9540'),
       (162, '936499355525984256', 10, 20.655, '2024-04-21 23:24:54.6310', '0', '2024-04-21 23:24:54.7140',
        '2024-04-21 23:24:54.7140'),
       (163, '936499355525984256', 20, 83.116, '2024-04-21 23:24:54.7620', '0', '2024-04-21 23:24:54.7690',
        '2024-04-21 23:24:54.7690'),
       (164, '936499355525984256', 10, 19.303, '2024-04-21 23:42:40.2300', '0', '2024-04-21 23:42:40.3720',
        '2024-04-21 23:42:40.3720'),
       (165, '936499355525984256', 20, 83.085, '2024-04-21 23:42:40.4490', '0', '2024-04-21 23:42:40.4550',
        '2024-04-21 23:42:40.4550'),
       (166, '936499355525984256', 10, 23.798, '2024-04-22 00:17:58.0620', '0', '2024-04-22 00:17:58.1800',
        '2024-04-22 00:17:58.1800'),
       (167, '936499355525984256', 20, 83.108, '2024-04-22 00:17:58.3370', '0', '2024-04-22 00:17:58.3610',
        '2024-04-22 00:17:58.3610'),
       (168, '936499355525984256', 10, 25.587, '2024-04-22 00:52:32.3860', '0', '2024-04-22 00:52:32.6700',
        '2024-04-22 00:52:32.6700'),
       (169, '936499355525984256', 20, 83.026, '2024-04-22 00:52:32.7640', '0', '2024-04-22 00:52:32.7740',
        '2024-04-22 00:52:32.7740'),
       (170, '936499355525984256', 10, 21.058, '2024-04-22 01:09:50.1230', '0', '2024-04-22 01:09:50.2450',
        '2024-04-22 01:09:50.2450'),
       (171, '936499355525984256', 20, 83.038, '2024-04-22 01:09:50.3410', '0', '2024-04-22 01:09:50.3490',
        '2024-04-22 01:09:50.3490'),
       (172, '936499355525984256', 10, 19.089, '2024-04-22 01:43:46.5090', '0', '2024-04-22 01:43:46.5670',
        '2024-04-22 01:43:46.5670'),
       (173, '936499355525984256', 20, 83.121, '2024-04-22 01:43:46.6180', '0', '2024-04-22 01:43:46.6350',
        '2024-04-22 01:43:46.6350'),
       (174, '936499355525984256', 10, 17.711, '2024-04-22 02:00:19.4150', '0', '2024-04-22 02:00:19.6400',
        '2024-04-22 02:00:19.6400'),
       (175, '936499355525984256', 20, 83.082, '2024-04-22 02:00:19.7240', '0', '2024-04-22 02:00:19.7340',
        '2024-04-22 02:00:19.7340'),
       (176, '936499355525984256', 10, 21.481, '2024-04-22 02:16:12.9120', '0', '2024-04-22 02:16:13.1720',
        '2024-04-22 02:16:13.1720'),
       (177, '936499355525984256', 20, 83.034, '2024-04-22 02:16:13.3460', '0', '2024-04-22 02:16:13.3630',
        '2024-04-22 02:16:13.3630'),
       (178, '936499355525984256', 10, 21.831, '2024-04-22 02:50:01.8630', '0', '2024-04-22 02:50:01.9720',
        '2024-04-22 02:50:01.9720'),
       (179, '936499355525984256', 20, 82.873, '2024-04-22 02:50:02.0280', '0', '2024-04-22 02:50:02.0480',
        '2024-04-22 02:50:02.0480'),
       (180, '936499355525984256', 10, 21.483, '2024-04-22 03:25:10.1590', '0', '2024-04-22 03:25:10.2500',
        '2024-04-22 03:25:10.2500'),
       (181, '936499355525984256', 20, 83.045, '2024-04-22 03:25:10.3010', '0', '2024-04-22 03:25:10.3170',
        '2024-04-22 03:25:10.3170'),
       (182, '936499355525984256', 10, 18.232, '2024-04-22 03:42:05.8890', '0', '2024-04-22 03:42:06.0280',
        '2024-04-22 03:42:06.0280'),
       (183, '936499355525984256', 20, 83.049, '2024-04-22 03:42:06.1680', '0', '2024-04-22 03:42:06.1940',
        '2024-04-22 03:42:06.1940'),
       (184, '936499355525984256', 10, 20.716, '2024-04-22 04:00:47.9190', '0', '2024-04-22 04:00:47.9880',
        '2024-04-22 04:00:47.9880'),
       (185, '936499355525984256', 20, 83.115, '2024-04-22 04:00:51.1620', '0', '2024-04-22 04:00:51.1800',
        '2024-04-22 04:00:51.1800'),
       (186, '936499355525984256', 10, 22.661, '2024-04-22 04:16:18.6880', '0', '2024-04-22 04:16:18.7980',
        '2024-04-22 04:16:18.7980'),
       (187, '936499355525984256', 20, 83.083, '2024-04-22 04:16:18.8910', '0', '2024-04-22 04:16:18.9080',
        '2024-04-22 04:16:18.9080'),
       (188, '936499355525984256', 10, 21.107, '2024-04-22 04:49:14.2350', '0', '2024-04-22 04:49:14.3390',
        '2024-04-22 04:49:14.3400'),
       (189, '936499355525984256', 20, 83.211, '2024-04-22 04:49:17.5440', '0', '2024-04-22 04:49:17.5670',
        '2024-04-22 04:49:17.5670'),
       (190, '936499355525984256', 10, 20.125, '2024-04-22 05:06:11.0020', '0', '2024-04-22 05:06:11.1280',
        '2024-04-22 05:06:11.1280'),
       (191, '936499355525984256', 20, 83.135, '2024-04-22 05:06:11.2670', '0', '2024-04-22 05:06:11.2740',
        '2024-04-22 05:06:11.2740'),
       (192, '936499355525984256', 10, 18.394, '2024-04-22 05:24:26.3980', '0', '2024-04-22 05:24:26.5080',
        '2024-04-22 05:24:26.5080'),
       (193, '936499355525984256', 20, 83.148, '2024-04-22 05:24:26.6040', '0', '2024-04-22 05:24:26.6100',
        '2024-04-22 05:24:26.6100'),
       (194, '936499355525984256', 10, 20.377, '2024-04-22 05:40:50.4760', '0', '2024-04-22 05:40:50.6000',
        '2024-04-22 05:40:50.6000'),
       (195, '936499355525984256', 20, 83.157, '2024-04-22 05:40:50.6860', '0', '2024-04-22 05:40:50.6930',
        '2024-04-22 05:40:50.6930'),
       (196, '936499355525984256', 10, 27.358, '2024-04-22 06:12:12.9290', '0', '2024-04-22 06:12:13.1090',
        '2024-04-22 06:12:13.1100'),
       (197, '936499355525984256', 20, 83.035, '2024-04-22 06:12:13.2180', '0', '2024-04-22 06:12:13.2300',
        '2024-04-22 06:12:13.2300'),
       (198, '936499355525984256', 10, 21.768, '2024-04-22 06:29:43.6900', '0', '2024-04-22 06:29:43.7360',
        '2024-04-22 06:29:43.7360'),
       (199, '936499355525984256', 20, 83.063, '2024-04-22 06:29:43.8190', '0', '2024-04-22 06:29:43.8450',
        '2024-04-22 06:29:43.8450'),
       (200, '936499355525984256', 10, 21.332, '2024-04-22 07:03:36.6820', '0', '2024-04-22 07:03:36.8070',
        '2024-04-22 07:03:36.8070'),
       (201, '936499355525984256', 20, 83.052, '2024-04-22 07:03:36.9080', '0', '2024-04-22 07:03:36.9120',
        '2024-04-22 07:03:36.9120'),
       (202, '936499355525984256', 10, 25.97, '2024-04-22 07:37:18.9980', '0', '2024-04-22 07:37:19.1700',
        '2024-04-22 07:37:19.1700'),
       (203, '936499355525984256', 20, 83.08, '2024-04-22 07:37:19.2580', '0', '2024-04-22 07:37:19.2640',
        '2024-04-22 07:37:19.2640'),
       (204, '936499355525984256', 10, 27.558, '2024-04-22 08:02:05.5940', '0', '2024-04-22 08:02:05.8430',
        '2024-04-22 08:02:05.8430'),
       (205, '936499355525984256', 20, 83.039, '2024-04-22 08:02:05.9540', '0', '2024-04-22 08:02:05.9830',
        '2024-04-22 08:02:05.9830'),
       (206, '936499355525984256', 10, 21.893, '2024-04-22 08:20:06.3480', '0', '2024-04-22 08:20:06.4030',
        '2024-04-22 08:20:06.4030'),
       (207, '936499355525984256', 20, 83.09, '2024-04-22 08:20:06.4580', '0', '2024-04-22 08:20:06.4680',
        '2024-04-22 08:20:06.4680'),
       (208, '936499355525984256', 10, 19.13, '2024-04-22 08:44:40.4510', '0', '2024-04-22 08:44:40.4910',
        '2024-04-22 08:44:40.4910'),
       (209, '936499355525984256', 20, 83.072, '2024-04-22 08:44:40.5350', '0', '2024-04-22 08:44:40.5420',
        '2024-04-22 08:44:40.5420'),
       (210, '936499355525984256', 10, 21.682, '2024-04-22 09:17:41.0180', '0', '2024-04-22 09:17:41.1650',
        '2024-04-22 09:17:41.1650'),
       (211, '936499355525984256', 20, 83.091, '2024-04-22 09:17:41.2970', '0', '2024-04-22 09:17:41.3080',
        '2024-04-22 09:17:41.3080'),
       (212, '936499355525984256', 10, 17.773, '2024-04-22 09:34:21.4360', '0', '2024-04-22 09:34:21.5980',
        '2024-04-22 09:34:21.5980'),
       (213, '936499355525984256', 20, 83.082, '2024-04-22 09:34:21.8570', '0', '2024-04-22 09:34:21.8690',
        '2024-04-22 09:34:21.8690'),
       (214, '936499355525984256', 10, 24.244, '2024-04-22 09:52:06.9220', '0', '2024-04-22 09:52:06.9940',
        '2024-04-22 09:52:06.9940'),
       (215, '936499355525984256', 20, 83.086, '2024-04-22 09:52:07.1020', '0', '2024-04-22 09:52:07.1140',
        '2024-04-22 09:52:07.1140'),
       (216, '936499355525984256', 10, 21.892, '2024-04-22 10:09:43.6760', '0', '2024-04-22 10:09:43.8570',
        '2024-04-22 10:09:43.8570'),
       (217, '936499355525984256', 20, 83.242, '2024-04-22 10:09:43.9360', '0', '2024-04-22 10:09:43.9530',
        '2024-04-22 10:09:43.9530'),
       (218, '936499355525984256', 10, 21.942, '2024-04-22 10:42:14.6520', '0', '2024-04-22 10:42:14.7260',
        '2024-04-22 10:42:14.7260'),
       (219, '936499355525984256', 20, 83.107, '2024-04-22 10:42:14.8290', '0', '2024-04-22 10:42:14.8430',
        '2024-04-22 10:42:14.8430'),
       (220, '936499355525984256', 10, 25.83, '2024-04-22 11:06:31.1420', '0', '2024-04-22 11:06:31.4520',
        '2024-04-22 11:06:31.4520'),
       (221, '936499355525984256', 20, 82.986, '2024-04-22 11:06:31.5910', '0', '2024-04-22 11:06:31.6000',
        '2024-04-22 11:06:31.6000'),
       (222, '936499355525984256', 10, 21.515, '2024-04-22 11:23:49.8680', '0', '2024-04-22 11:23:50.0410',
        '2024-04-22 11:23:50.0410'),
       (223, '936499355525984256', 20, 83.112, '2024-04-22 11:23:50.1980', '0', '2024-04-22 11:23:50.2070',
        '2024-04-22 11:23:50.2070'),
       (224, '936499355525984256', 10, 20.623, '2024-04-22 11:40:06.7790', '0', '2024-04-22 11:40:06.8810',
        '2024-04-22 11:40:06.8810'),
       (225, '936499355525984256', 20, 83.063, '2024-04-22 11:40:06.9300', '0', '2024-04-22 11:40:06.9390',
        '2024-04-22 11:40:06.9390'),
       (226, '936499355525984256', 10, 20.946, '2024-04-22 11:55:49.6390', '0', '2024-04-22 11:55:49.8150',
        '2024-04-22 11:55:49.8180'),
       (227, '936499355525984256', 20, 83.126, '2024-04-22 11:55:49.9570', '0', '2024-04-22 11:55:49.9660',
        '2024-04-22 11:55:49.9660'),
       (228, '936499355525984256', 10, 25.876, '2024-04-22 12:28:46.2890', '0', '2024-04-22 12:28:46.4370',
        '2024-04-22 12:28:46.4370'),
       (229, '936499355525984256', 20, 83.134, '2024-04-22 12:28:46.5130', '0', '2024-04-22 12:28:46.5220',
        '2024-04-22 12:28:46.5220'),
       (230, '936499355525984256', 10, 21.313, '2024-04-22 13:04:36.8900', '0', '2024-04-22 13:20:41.1840',
        '2024-04-22 13:20:41.1840'),
       (231, '936499355525984256', 20, 83.252, '2024-04-22 13:20:41.2940', '0', '2024-04-22 13:20:41.3380',
        '2024-04-22 13:20:41.3380'),
       (232, '936499355525984256', 10, 31.604, '2024-04-22 13:38:33.5780', '0', '2024-04-22 13:38:33.7700',
        '2024-04-22 13:38:33.7700'),
       (233, '936499355525984256', 20, 83.194, '2024-04-22 13:38:33.8820', '0', '2024-04-22 13:38:33.8900',
        '2024-04-22 13:38:33.8900'),
       (234, '936499355525984256', 10, 17.966, '2024-04-22 13:54:28.4970', '0', '2024-04-22 13:54:28.6300',
        '2024-04-22 13:54:28.6300'),
       (235, '936499355525984256', 20, 83.216, '2024-04-22 13:54:28.6900', '0', '2024-04-22 13:54:28.6980',
        '2024-04-22 13:54:28.6980'),
       (236, '936499355525984256', 10, 24.125, '2024-04-22 14:59:53.0410', '0', '2024-04-22 14:59:53.1690',
        '2024-04-22 14:59:53.1690'),
       (237, '936499355525984256', 20, 83.076, '2024-04-22 15:15:43.3350', '0', '2024-04-22 15:15:43.4050',
        '2024-04-22 15:15:43.4050'),
       (238, '936499355525984256', 10, 28.027, '2024-04-22 16:37:56.7580', '0', '2024-04-22 16:37:56.8440',
        '2024-04-22 16:37:56.8440'),
       (239, '936499355525984256', 20, 83.114, '2024-04-22 16:37:56.9260', '0', '2024-04-22 16:37:56.9340',
        '2024-04-22 16:37:56.9340'),
       (240, '936499355525984256', 10, 25.048, '2024-04-22 18:17:44.4540', '0', '2024-04-22 18:17:44.5850',
        '2024-04-22 18:17:44.5850'),
       (241, '936499355525984256', 20, 83.088, '2024-04-22 18:17:44.7890', '0', '2024-04-22 18:17:44.7980',
        '2024-04-22 18:17:44.7980'),
       (242, '936499355525984256', 10, 20.797, '2024-04-22 19:41:51.4730', '0', '2024-04-22 19:41:51.5560',
        '2024-04-22 19:41:51.5560'),
       (243, '936499355525984256', 20, 83.125, '2024-04-22 19:41:51.6630', '0', '2024-04-22 19:41:51.6740',
        '2024-04-22 19:41:51.6740'),
       (244, '936499355525984256', 10, 23.784, '2024-04-22 21:03:13.1370', '0', '2024-04-22 21:20:08.3040',
        '2024-04-22 21:20:08.3040'),
       (245, '936499355525984256', 20, 82.918, '2024-04-22 21:20:08.3850', '0', '2024-04-22 21:20:08.4040',
        '2024-04-22 21:20:08.4040'),
       (246, '936499355525984256', 10, 20.891, '2024-04-22 22:44:18.7750', '0', '2024-04-22 22:44:18.8360',
        '2024-04-22 22:44:18.8360'),
       (247, '936499355525984256', 20, 83.194, '2024-04-22 22:44:18.9530', '0', '2024-04-22 22:44:18.9830',
        '2024-04-22 22:44:18.9830'),
       (248, '936499355525984256', 10, 22.899, '2024-04-23 00:21:13.5160', '0', '2024-04-23 00:21:13.7020',
        '2024-04-23 00:21:13.7020'),
       (249, '936499355525984256', 20, 83.13, '2024-04-23 00:21:13.7900', '0', '2024-04-23 00:21:13.8150',
        '2024-04-23 00:21:13.8150'),
       (250, '936499355525984256', 10, 20.555, '2024-04-23 01:04:34.1820', '0', '2024-04-23 01:04:34.2910',
        '2024-04-23 01:04:34.2910'),
       (251, '936499355525984256', 20, 83.074, '2024-04-23 01:04:34.4270', '0', '2024-04-23 01:04:34.4590',
        '2024-04-23 01:04:34.4590'),
       (252, '936499355525984256', 10, 23.252, '2024-04-23 01:09:06.1330', '0', '2024-04-23 01:09:06.3220',
        '2024-04-23 01:09:06.3220'),
       (253, '936499355525984256', 20, 83.093, '2024-04-23 01:09:06.4150', '0', '2024-04-23 01:09:06.4520',
        '2024-04-23 01:09:06.4520'),
       (254, '936499355525984256', 10, 19.212, '2024-04-23 01:13:38.4240', '0', '2024-04-23 01:13:38.5240',
        '2024-04-23 01:13:38.5240'),
       (255, '936499355525984256', 20, 83.169, '2024-04-23 01:13:38.7380', '0', '2024-04-23 01:13:38.7790',
        '2024-04-23 01:13:38.7790'),
       (256, '936499355525984256', 10, 18.152, '2024-04-23 01:18:09.7590', '0', '2024-04-23 01:18:10.0160',
        '2024-04-23 01:18:10.0160'),
       (257, '936499355525984256', 20, 83.046, '2024-04-23 01:18:10.1250', '0', '2024-04-23 01:18:10.1380',
        '2024-04-23 01:18:10.1380'),
       (258, '936499355525984256', 10, 31.693, '2024-04-23 01:22:41.1690', '0', '2024-04-23 01:22:41.2980',
        '2024-04-23 01:22:41.2980'),
       (259, '936499355525984256', 20, 83.017, '2024-04-23 01:22:41.3850', '0', '2024-04-23 01:22:41.3980',
        '2024-04-23 01:22:41.3980'),
       (260, '936499355525984256', 10, 20.958, '2024-04-23 01:27:12.5360', '0', '2024-04-23 01:27:12.5750',
        '2024-04-23 01:27:12.5750'),
       (261, '936499355525984256', 20, 83.292, '2024-04-23 01:27:12.6080', '0', '2024-04-23 01:27:12.6410',
        '2024-04-23 01:27:12.6410'),
       (262, '936499355525984256', 10, 19.939, '2024-04-23 01:45:53.1750', '0', '2024-04-23 01:45:53.3160',
        '2024-04-23 01:45:53.3160'),
       (263, '936499355525984256', 20, 83.059, '2024-04-23 01:45:53.4170', '0', '2024-04-23 01:45:53.4250',
        '2024-04-23 01:45:53.4250'),
       (264, '936499355525984256', 10, 21.121, '2024-04-23 02:04:53.5410', '0', '2024-04-23 02:04:53.5740',
        '2024-04-23 02:04:53.5740'),
       (265, '936499355525984256', 20, 83.287, '2024-04-23 02:04:53.6230', '0', '2024-04-23 02:04:53.6390',
        '2024-04-23 02:04:53.6390'),
       (266, '936499355525984256', 10, 17.571, '2024-04-23 02:41:54.2300', '0', '2024-04-23 02:41:54.3700',
        '2024-04-23 02:41:54.3700'),
       (267, '936499355525984256', 20, 83.187, '2024-04-23 02:41:54.4940', '0', '2024-04-23 02:41:54.5020',
        '2024-04-23 02:41:54.5020'),
       (268, '936499355525984256', 10, 20.422, '2024-04-23 02:59:06.1330', '0', '2024-04-23 02:59:06.2260',
        '2024-04-23 02:59:06.2260'),
       (269, '936499355525984256', 20, 83.073, '2024-04-23 02:59:06.2590', '0', '2024-04-23 02:59:06.2670',
        '2024-04-23 02:59:06.2670'),
       (270, '936499355525984256', 10, 19.751, '2024-04-23 03:17:16.6700', '0', '2024-04-23 03:17:16.8210',
        '2024-04-23 03:17:16.8210'),
       (271, '936499355525984256', 20, 83.199, '2024-04-23 03:17:16.9320', '0', '2024-04-23 03:17:16.9620',
        '2024-04-23 03:17:16.9620'),
       (272, '936499355525984256', 10, 21.381, '2024-04-23 03:51:30.9370', '0', '2024-04-23 03:51:30.9780',
        '2024-04-23 03:51:30.9780'),
       (273, '936499355525984256', 20, 83.082, '2024-04-23 03:51:31.0150', '0', '2024-04-23 03:51:31.0220',
        '2024-04-23 03:51:31.0220'),
       (274, '936499355525984256', 10, 20.939, '2024-04-23 04:15:22.5200', '0', '2024-04-23 04:15:22.6710',
        '2024-04-23 04:15:22.6710'),
       (275, '936499355525984256', 20, 83.139, '2024-04-23 04:15:22.7420', '0', '2024-04-23 04:15:22.7760',
        '2024-04-23 04:15:22.7760'),
       (276, '936499355525984256', 10, 20.567, '2024-04-23 04:34:09.9630', '0', '2024-04-23 04:34:10.0570',
        '2024-04-23 04:34:10.0570'),
       (277, '936499355525984256', 20, 83.061, '2024-04-23 04:34:11.4180', '0', '2024-04-23 04:34:11.4290',
        '2024-04-23 04:34:11.4290'),
       (278, '936499355525984256', 10, 25.394, '2024-04-23 05:55:52.4520', '0', '2024-04-23 05:55:52.5810',
        '2024-04-23 05:55:52.5810'),
       (279, '936499355525984256', 20, 83.177, '2024-04-23 05:55:54.9530', '0', '2024-04-23 05:55:54.9710',
        '2024-04-23 05:55:54.9710'),
       (280, '936499355525984256', 10, 19.95, '2024-04-23 07:01:27.8830', '0', '2024-04-23 07:01:27.9420',
        '2024-04-23 07:01:27.9420'),
       (281, '936499355525984256', 20, 83.194, '2024-04-23 07:01:27.9970', '0', '2024-04-23 07:01:28.0030',
        '2024-04-23 07:01:28.0030'),
       (282, '936499355525984256', 10, 16.352, '2024-04-23 07:18:47.4710', '0', '2024-04-23 07:18:47.5780',
        '2024-04-23 07:18:47.5780'),
       (283, '936499355525984256', 20, 83.172, '2024-04-23 07:18:47.6640', '0', '2024-04-23 07:18:47.6750',
        '2024-04-23 07:18:47.6750'),
       (284, '936499355525984256', 10, 19.498, '2024-04-23 07:35:35.1740', '0', '2024-04-23 07:35:35.3220',
        '2024-04-23 07:35:35.3220'),
       (285, '936499355525984256', 20, 83.217, '2024-04-23 07:35:35.3790', '0', '2024-04-23 07:35:35.3890',
        '2024-04-23 07:35:35.3890'),
       (286, '936499355525984256', 10, 18.033, '2024-04-23 07:53:51.0030', '0', '2024-04-23 07:53:51.0550',
        '2024-04-23 07:53:51.0550'),
       (287, '936499355525984256', 20, 83.245, '2024-04-23 07:53:51.1150', '0', '2024-04-23 07:53:51.1410',
        '2024-04-23 07:53:51.1410'),
       (288, '936499355525984256', 10, 30.007, '2024-04-23 08:12:24.9840', '0', '2024-04-23 08:12:25.1800',
        '2024-04-23 08:12:25.1800'),
       (289, '936499355525984256', 20, 83.085, '2024-04-23 08:12:25.2880', '0', '2024-04-23 08:12:25.3120',
        '2024-04-23 08:12:25.3120'),
       (290, '936499355525984256', 10, 33.111, '2024-04-23 08:23:49.8970', '0', '2024-04-23 08:23:50.0040',
        '2024-04-23 08:23:50.0040'),
       (291, '936499355525984256', 20, 83.32, '2024-04-23 08:23:50.1030', '0', '2024-04-23 08:23:50.1150',
        '2024-04-23 08:23:50.1150'),
       (292, '936499355525984256', 10, 45.82, '2024-04-23 08:25:19.8830', '0', '2024-04-23 08:25:20.1320',
        '2024-04-23 08:25:20.1320'),
       (293, '936499355525984256', 20, 83.509, '2024-04-23 08:25:20.4270', '0', '2024-04-23 08:25:20.4800',
        '2024-04-23 08:25:20.4800'),
       (294, '936499355525984256', 10, 28.108, '2024-04-23 08:26:49.8780', '0', '2024-04-23 08:26:49.9630',
        '2024-04-23 08:26:49.9630'),
       (295, '936499355525984256', 20, 83.089, '2024-04-23 08:26:50.0610', '0', '2024-04-23 08:26:50.0860',
        '2024-04-23 08:26:50.0860'),
       (296, '936499355525984256', 10, 35.235, '2024-04-23 08:27:49.8780', '0', '2024-04-23 08:27:49.9740',
        '2024-04-23 08:27:49.9740'),
       (297, '936499355525984256', 20, 83.364, '2024-04-23 08:28:19.8700', '0', '2024-04-23 08:28:19.9270',
        '2024-04-23 08:28:19.9270'),
       (298, '936499355525984256', 10, 26.996, '2024-04-23 08:29:19.8780', '0', '2024-04-23 08:29:19.9850',
        '2024-04-23 08:29:19.9850'),
       (299, '936499355525984256', 20, 82.875, '2024-04-23 08:29:20.0930', '0', '2024-04-23 08:29:20.1180',
        '2024-04-23 08:29:20.1180'),
       (300, '936499355525984256', 10, 28.242, '2024-04-23 08:30:49.8730', '0', '2024-04-23 08:30:49.9640',
        '2024-04-23 08:30:49.9640'),
       (301, '936499355525984256', 20, 83.282, '2024-04-23 08:30:50.0190', '0', '2024-04-23 08:30:50.0300',
        '2024-04-23 08:30:50.0300'),
       (302, '936499355525984256', 10, 31.701, '2024-04-23 08:32:19.8660', '0', '2024-04-23 08:32:19.9270',
        '2024-04-23 08:32:19.9270'),
       (303, '936499355525984256', 20, 83.344, '2024-04-23 08:32:20.0240', '0', '2024-04-23 08:32:20.0320',
        '2024-04-23 08:32:20.0320'),
       (304, '936499355525984256', 10, 25.491, '2024-04-23 08:33:19.8730', '0', '2024-04-23 08:33:19.9400',
        '2024-04-23 08:33:19.9400'),
       (305, '936499355525984256', 20, 82.832, '2024-04-23 08:33:49.8720', '0', '2024-04-23 08:33:49.9520',
        '2024-04-23 08:33:49.9520'),
       (306, '936499355525984256', 10, 35.451, '2024-04-23 08:34:49.8670', '0', '2024-04-23 08:34:49.9760',
        '2024-04-23 08:34:49.9760'),
       (307, '936499355525984256', 20, 83.348, '2024-04-23 08:34:50.0650', '0', '2024-04-23 08:34:50.0890',
        '2024-04-23 08:34:50.0890'),
       (308, '936499355525984256', 10, 32.718, '2024-04-23 08:35:49.8700', '0', '2024-04-23 08:35:49.9380',
        '2024-04-23 08:35:49.9380'),
       (309, '936499355525984256', 20, 82.278, '2024-04-23 08:36:19.8700', '0', '2024-04-23 08:36:19.9350',
        '2024-04-23 08:36:19.9350'),
       (310, '936499355525984256', 10, 25.118, '2024-04-23 08:37:19.8640', '0', '2024-04-23 08:37:19.9150',
        '2024-04-23 08:37:19.9150'),
       (311, '936499355525984256', 20, 83.361, '2024-04-23 08:37:20.0470', '0', '2024-04-23 08:37:20.0520',
        '2024-04-23 08:37:20.0520'),
       (312, '936499355525984256', 10, 32.183, '2024-04-23 08:38:19.8660', '0', '2024-04-23 08:38:19.9220',
        '2024-04-23 08:38:19.9220'),
       (313, '936499355525984256', 20, 83.085, '2024-04-23 08:38:49.8590', '0', '2024-04-23 08:38:49.8890',
        '2024-04-23 08:38:49.8890'),
       (314, '936499355525984256', 10, 31.812, '2024-04-23 08:39:19.8730', '0', '2024-04-23 08:39:19.8990',
        '2024-04-23 08:39:19.8990'),
       (315, '936499355525984256', 20, 81.806, '2024-04-23 08:39:49.8710', '0', '2024-04-23 08:39:49.9040',
        '2024-04-23 08:39:49.9040'),
       (316, '936499355525984256', 10, 26.803, '2024-04-23 08:40:49.8650', '0', '2024-04-23 08:40:49.9410',
        '2024-04-23 08:40:49.9410'),
       (317, '936499355525984256', 20, 82.99, '2024-04-23 08:40:50.0540', '0', '2024-04-23 08:40:50.0620',
        '2024-04-23 08:40:50.0620'),
       (318, '936499355525984256', 10, 38.557, '2024-04-23 08:42:19.8640', '0', '2024-04-23 08:42:19.9530',
        '2024-04-23 08:42:19.9530'),
       (319, '936499355525984256', 20, 82.904, '2024-04-23 08:42:20.0150', '0', '2024-04-23 08:42:20.0220',
        '2024-04-23 08:42:20.0220'),
       (320, '936499355525984256', 10, 27.492, '2024-04-23 08:43:49.8620', '0', '2024-04-23 08:43:50.0090',
        '2024-04-23 08:43:50.0090'),
       (321, '936499355525984256', 20, 82.534, '2024-04-23 08:43:50.0950', '0', '2024-04-23 08:43:50.1010',
        '2024-04-23 08:43:50.1010'),
       (322, '936499355525984256', 10, 22.073, '2024-04-23 08:45:19.8670', '0', '2024-04-23 08:45:19.9590',
        '2024-04-23 08:45:19.9590'),
       (323, '936499355525984256', 20, 82.015, '2024-04-23 08:45:20.0000', '0', '2024-04-23 08:45:20.0040',
        '2024-04-23 08:45:20.0040'),
       (324, '936499355525984256', 10, 18.622, '2024-04-23 08:46:49.8640', '0', '2024-04-23 08:46:49.9530',
        '2024-04-23 08:46:49.9530'),
       (325, '936499355525984256', 20, 82.825, '2024-04-23 08:46:50.0320', '0', '2024-04-23 08:46:50.0410',
        '2024-04-23 08:46:50.0410'),
       (326, '936499355525984256', 10, 26.429, '2024-04-23 08:48:57.3570', '0', '2024-04-23 08:48:57.4270',
        '2024-04-23 08:48:57.4270'),
       (327, '936499355525984256', 20, 82.419, '2024-04-23 08:48:57.4630', '0', '2024-04-23 08:48:57.4790',
        '2024-04-23 08:48:57.4790'),
       (328, '936499355525984256', 10, 17.177, '2024-04-23 08:49:57.7310', '0', '2024-04-23 08:49:57.8190',
        '2024-04-23 08:49:57.8190'),
       (329, '936499355525984256', 20, 83.008, '2024-04-23 08:49:57.8710', '0', '2024-04-23 08:49:57.8760',
        '2024-04-23 08:49:57.8760'),
       (330, '936499355525984256', 10, 27.06, '2024-04-23 09:37:30.1510', '0', '2024-04-23 09:37:30.1930',
        '2024-04-23 09:37:30.1930'),
       (331, '936499355525984256', 20, 82.936, '2024-04-23 09:37:30.5260', '0', '2024-04-23 09:37:30.5330',
        '2024-04-23 09:37:30.5330'),
       (332, '936499355525984256', 10, 20.708, '2024-04-23 10:41:20.6660', '0', '2024-04-23 10:41:20.7650',
        '2024-04-23 10:41:20.7650'),
       (333, '936499355525984256', 20, 82.988, '2024-04-23 10:41:20.8820', '0', '2024-04-23 10:41:20.8880',
        '2024-04-23 10:41:20.8880'),
       (334, '936499355525984256', 10, 23.296, '2024-04-23 12:18:44.2940', '0', '2024-04-23 12:18:44.3660',
        '2024-04-23 12:18:44.3660'),
       (335, '936499355525984256', 20, 83.006, '2024-04-23 12:18:45.6650', '0', '2024-04-23 12:18:45.6880',
        '2024-04-23 12:18:45.6880'),
       (336, '936499355525984256', 10, 23.293, '2024-04-23 13:59:59.0950', '0', '2024-04-23 13:59:59.2290',
        '2024-04-23 13:59:59.2290'),
       (337, '936499355525984256', 20, 82.645, '2024-04-23 13:59:59.3830', '0', '2024-04-23 13:59:59.4000',
        '2024-04-23 13:59:59.4000'),
       (338, '936499355525984256', 10, 24.438, '2024-04-23 15:23:50.8920', '0', '2024-04-23 15:23:50.9490',
        '2024-04-23 15:23:50.9490'),
       (339, '936499355525984256', 20, 81.729, '2024-04-23 15:41:14.1830', '0', '2024-04-23 15:41:14.2250',
        '2024-04-23 15:41:14.2250'),
       (340, '936499355525984256', 10, 23.176, '2024-04-23 17:05:11.2920', '0', '2024-04-23 17:05:11.3120',
        '2024-04-23 17:05:11.3120'),
       (341, '936499355525984256', 20, 81.958, '2024-04-23 17:05:11.4010', '0', '2024-04-23 17:05:11.4240',
        '2024-04-23 17:05:11.4240'),
       (342, '936499355525984256', 10, 21.1, '2024-04-23 18:27:21.0070', '0', '2024-04-23 18:27:21.0510',
        '2024-04-23 18:27:21.0510'),
       (343, '936499355525984256', 20, 82.767, '2024-04-23 18:43:33.0010', '0', '2024-04-23 18:43:33.0440',
        '2024-04-23 18:43:33.0440'),
       (344, '936499355525984256', 10, 23.027, '2024-04-23 20:03:47.4360', '0', '2024-04-23 20:03:47.5660',
        '2024-04-23 20:03:47.5660'),
       (345, '936499355525984256', 20, 82.831, '2024-04-23 20:03:47.6300', '0', '2024-04-23 20:03:47.6360',
        '2024-04-23 20:03:47.6360'),
       (346, '936499355525984256', 10, 20.767, '2024-04-23 21:05:36.2640', '0', '2024-04-23 21:05:36.3080',
        '2024-04-23 21:05:36.3080'),
       (347, '936499355525984256', 20, 82.898, '2024-04-23 21:05:39.4560', '0', '2024-04-23 21:05:39.4660',
        '2024-04-23 21:05:39.4660'),
       (348, '936499355525984256', 10, 24.083, '2024-04-23 21:22:20.6120', '0', '2024-04-23 21:22:20.7460',
        '2024-04-23 21:22:20.7460'),
       (349, '936499355525984256', 20, 81.062, '2024-04-23 21:22:20.8380', '0', '2024-04-23 21:22:20.8440',
        '2024-04-23 21:22:20.8440'),
       (350, '936499355525984256', 10, 19.798, '2024-04-23 21:42:08.0320', '0', '2024-04-23 21:42:08.0910',
        '2024-04-23 21:42:08.0910'),
       (351, '936499355525984256', 20, 81.616, '2024-04-23 21:42:08.2050', '0', '2024-04-23 21:42:08.2110',
        '2024-04-23 21:42:08.2110'),
       (352, '936499355525984256', 10, 18.38, '2024-04-23 22:16:16.7710', '0', '2024-04-23 22:16:16.8070',
        '2024-04-23 22:16:16.8070'),
       (353, '936499355525984256', 20, 81.727, '2024-04-23 22:16:16.8870', '0', '2024-04-23 22:16:16.8960',
        '2024-04-23 22:16:16.8960'),
       (354, '936499355525984256', 10, 19.268, '2024-04-23 22:45:48.1370', '0', '2024-04-23 22:45:48.2210',
        '2024-04-23 22:45:48.2210'),
       (355, '936499355525984256', 20, 82.97, '2024-04-23 22:45:48.2770', '0', '2024-04-23 22:45:48.2810',
        '2024-04-23 22:45:48.2810'),
       (356, '936499355525984256', 10, 19.732, '2024-04-23 22:56:50.5150', '0', '2024-04-23 22:56:50.5630',
        '2024-04-23 22:56:50.5630'),
       (357, '936499355525984256', 20, 82.936, '2024-04-23 22:56:50.6160', '0', '2024-04-23 22:56:50.6210',
        '2024-04-23 22:56:50.6210'),
       (358, '936499355525984256', 10, 17.291, '2024-04-23 23:14:38.0910', '0', '2024-04-23 23:14:38.1170',
        '2024-04-23 23:14:38.1170'),
       (359, '936499355525984256', 20, 82.892, '2024-04-23 23:14:38.1650', '0', '2024-04-23 23:14:38.1710',
        '2024-04-23 23:14:38.1710'),
       (360, '936499355525984256', 10, 17.726, '2024-04-23 23:31:06.8480', '0', '2024-04-23 23:31:07.2630',
        '2024-04-23 23:31:07.2630'),
       (361, '936499355525984256', 20, 83.028, '2024-04-23 23:31:07.3550', '0', '2024-04-23 23:31:07.3660',
        '2024-04-23 23:31:07.3660'),
       (362, '936499355525984256', 10, 18.85, '2024-04-23 23:49:06.6300', '0', '2024-04-23 23:49:06.7020',
        '2024-04-23 23:49:06.7020'),
       (363, '936499355525984256', 20, 82.979, '2024-04-23 23:49:06.7840', '0', '2024-04-23 23:49:06.7870',
        '2024-04-23 23:49:06.7870'),
       (364, '936499355525984256', 10, 25.236, '2024-04-24 00:13:38.8400', '0', '2024-04-24 00:13:39.0430',
        '2024-04-24 00:13:39.0430'),
       (365, '936499355525984256', 20, 82.977, '2024-04-24 00:13:39.1100', '0', '2024-04-24 00:13:39.1210',
        '2024-04-24 00:13:39.1210'),
       (366, '936499355525984256', 10, 18.203, '2024-04-24 00:30:34.5170', '0', '2024-04-24 00:30:34.5450',
        '2024-04-24 00:30:34.5450'),
       (367, '936499355525984256', 20, 83.027, '2024-04-24 00:30:34.5920', '0', '2024-04-24 00:30:34.6080',
        '2024-04-24 00:30:34.6080'),
       (368, '936499355525984256', 10, 16.292, '2024-04-24 00:47:22.0300', '0', '2024-04-24 00:47:22.0740',
        '2024-04-24 00:47:22.0740'),
       (369, '936499355525984256', 20, 82.98, '2024-04-24 00:47:22.1100', '0', '2024-04-24 00:47:22.1160',
        '2024-04-24 00:47:22.1160'),
       (370, '936499355525984256', 10, 18.487, '2024-04-24 01:04:49.8920', '0', '2024-04-24 01:04:49.9320',
        '2024-04-24 01:04:49.9320'),
       (371, '936499355525984256', 20, 82.994, '2024-04-24 01:04:49.9910', '0', '2024-04-24 01:04:49.9980',
        '2024-04-24 01:04:49.9980'),
       (372, '936499355525984256', 10, 20.824, '2024-04-24 01:22:21.6440', '0', '2024-04-24 01:22:21.6900',
        '2024-04-24 01:22:21.6900'),
       (373, '936499355525984256', 20, 82.986, '2024-04-24 01:22:21.7500', '0', '2024-04-24 01:22:21.7540',
        '2024-04-24 01:22:21.7540'),
       (374, '936499355525984256', 10, 19.052, '2024-04-24 01:39:58.2550', '0', '2024-04-24 01:39:58.3600',
        '2024-04-24 01:39:58.3600'),
       (375, '936499355525984256', 20, 83.026, '2024-04-24 01:40:45.9860', '0', '2024-04-24 01:40:45.9960',
        '2024-04-24 01:40:45.9960'),
       (376, '936499355525984256', 10, 18.747, '2024-04-24 01:41:15.3090', '0', '2024-04-24 01:41:15.3450',
        '2024-04-24 01:41:15.3450'),
       (377, '936499355525984256', 10, 26.323, '2024-04-24 01:58:28.8290', '0', '2024-04-24 02:15:24.6750',
        '2024-04-24 02:15:24.6750'),
       (378, '936499355525984256', 20, 82.883, '2024-04-24 02:15:24.8620', '0', '2024-04-24 02:15:24.8790',
        '2024-04-24 02:15:24.8790'),
       (379, '936499355525984256', 10, 23.139, '2024-04-24 02:32:47.7310', '0', '2024-04-24 02:32:47.7910',
        '2024-04-24 02:32:47.7910'),
       (380, '936499355525984256', 20, 83.005, '2024-04-24 02:32:47.8390', '0', '2024-04-24 02:32:47.8490',
        '2024-04-24 02:32:47.8490'),
       (381, '936499355525984256', 10, 20.597, '2024-04-24 02:49:08.1380', '0', '2024-04-24 02:49:08.2290',
        '2024-04-24 02:49:08.2290'),
       (382, '936499355525984256', 20, 83.038, '2024-04-24 02:49:08.2910', '0', '2024-04-24 02:49:08.2970',
        '2024-04-24 02:49:08.2970'),
       (383, '936499355525984256', 10, 19.037, '2024-04-24 02:50:24.9260', '0', '2024-04-24 02:50:24.9950',
        '2024-04-24 02:50:24.9950'),
       (384, '936499355525984256', 20, 82.997, '2024-04-24 02:50:25.0430', '0', '2024-04-24 02:50:25.0480',
        '2024-04-24 02:50:25.0480'),
       (385, '936499355525984256', 10, 17.645, '2024-04-24 03:09:01.0950', '0', '2024-04-24 03:09:01.1850',
        '2024-04-24 03:09:01.1850'),
       (386, '936499355525984256', 20, 82.956, '2024-04-24 03:09:01.2850', '0', '2024-04-24 03:09:01.2910',
        '2024-04-24 03:09:01.2910'),
       (387, '936499355525984256', 10, 17.604, '2024-04-24 03:42:52.5640', '0', '2024-04-24 03:42:52.6100',
        '2024-04-24 03:42:52.6100'),
       (388, '936499355525984256', 20, 82.984, '2024-04-24 03:42:52.6450', '0', '2024-04-24 03:42:52.6510',
        '2024-04-24 03:42:52.6510'),
       (389, '936499355525984256', 10, 20.217, '2024-04-24 04:14:59.1000', '0', '2024-04-24 04:14:59.2340',
        '2024-04-24 04:14:59.2340'),
       (390, '936499355525984256', 20, 83.035, '2024-04-24 04:14:59.3630', '0', '2024-04-24 04:14:59.3760',
        '2024-04-24 04:14:59.3760'),
       (391, '936499355525984256', 10, 16.569, '2024-04-24 04:31:51.7850', '0', '2024-04-24 04:31:51.8140',
        '2024-04-24 04:31:51.8140'),
       (392, '936499355525984256', 20, 83.04, '2024-04-24 04:31:51.8540', '0', '2024-04-24 04:31:51.8600',
        '2024-04-24 04:31:51.8600'),
       (393, '936499355525984256', 10, 18.169, '2024-04-24 04:47:37.0810', '0', '2024-04-24 04:47:37.1110',
        '2024-04-24 04:47:37.1110'),
       (394, '936499355525984256', 20, 82.914, '2024-04-24 04:47:37.1480', '0', '2024-04-24 04:47:37.1520',
        '2024-04-24 04:47:37.1520'),
       (395, '936499355525984256', 10, 20.189, '2024-04-24 05:06:42.7720', '0', '2024-04-24 05:06:42.8090',
        '2024-04-24 05:06:42.8090'),
       (396, '936499355525984256', 20, 83.005, '2024-04-24 05:06:42.8540', '0', '2024-04-24 05:06:42.8620',
        '2024-04-24 05:06:42.8620'),
       (397, '936499355525984256', 10, 25.041, '2024-04-24 05:40:33.5210', '0', '2024-04-24 05:40:33.6310',
        '2024-04-24 05:40:33.6310'),
       (398, '936499355525984256', 20, 82.653, '2024-04-24 05:40:33.7640', '0', '2024-04-24 05:40:33.7790',
        '2024-04-24 05:40:33.7790'),
       (399, '936499355525984256', 10, 20.661, '2024-04-24 05:57:18.5360', '0', '2024-04-24 05:57:18.6000',
        '2024-04-24 05:57:18.6000'),
       (400, '936499355525984256', 20, 82.983, '2024-04-24 05:57:18.6470', '0', '2024-04-24 05:57:18.6610',
        '2024-04-24 05:57:18.6610'),
       (401, '936499355525984256', 10, 21.147, '2024-04-24 06:24:19.3450', '0', '2024-04-24 06:24:19.4640',
        '2024-04-24 06:24:19.4640'),
       (402, '936499355525984256', 20, 82.926, '2024-04-24 06:24:19.5440', '0', '2024-04-24 06:24:19.5520',
        '2024-04-24 06:24:19.5520'),
       (403, '936499355525984256', 10, 20.664, '2024-04-24 06:40:45.8290', '0', '2024-04-24 06:40:45.8640',
        '2024-04-24 06:40:45.8640'),
       (404, '936499355525984256', 20, 82.969, '2024-04-24 06:40:45.9240', '0', '2024-04-24 06:40:45.9300',
        '2024-04-24 06:40:45.9300'),
       (405, '936499355525984256', 10, 19.862, '2024-04-24 06:58:53.4130', '0', '2024-04-24 06:58:53.4630',
        '2024-04-24 06:58:53.4630'),
       (406, '936499355525984256', 20, 82.918, '2024-04-24 07:15:46.4250', '0', '2024-04-24 07:15:46.4450',
        '2024-04-24 07:15:46.4450'),
       (407, '936499355525984256', 10, 20.971, '2024-04-24 07:34:22.0310', '0', '2024-04-24 07:34:22.1560',
        '2024-04-24 07:34:22.1560'),
       (408, '936499355525984256', 20, 82.917, '2024-04-24 07:34:22.2170', '0', '2024-04-24 07:34:22.2500',
        '2024-04-24 07:34:22.2500'),
       (409, '936499355525984256', 10, 19.64, '2024-04-24 07:52:58.4350', '0', '2024-04-24 07:52:58.5240',
        '2024-04-24 07:52:58.5240'),
       (410, '936499355525984256', 20, 82.937, '2024-04-24 07:52:58.5720', '0', '2024-04-24 07:52:58.5800',
        '2024-04-24 07:52:58.5800'),
       (411, '936499355525984256', 10, 21.003, '2024-04-24 08:33:43.5620', '0', '2024-04-24 08:33:43.6480',
        '2024-04-24 08:33:43.6480'),
       (412, '936499355525984256', 20, 82.919, '2024-04-24 08:33:43.7740', '0', '2024-04-24 08:33:43.7890',
        '2024-04-24 08:33:43.7890'),
       (413, '936499355525984256', 10, 19.788, '2024-04-24 09:08:40.2610', '0', '2024-04-24 09:08:40.3800',
        '2024-04-24 09:08:40.3800'),
       (414, '936499355525984256', 20, 82.962, '2024-04-24 09:08:40.4460', '0', '2024-04-24 09:08:40.4650',
        '2024-04-24 09:08:40.4650'),
       (415, '936499355525984256', 10, 22.784, '2024-04-24 09:45:49.8240', '0', '2024-04-24 09:45:49.8690',
        '2024-04-24 09:45:49.8690'),
       (416, '936499355525984256', 20, 82.989, '2024-04-24 09:45:49.9770', '0', '2024-04-24 09:45:50.0070',
        '2024-04-24 09:45:50.0070'),
       (417, '936499355525984256', 10, 20.597, '2024-04-24 10:22:46.3940', '0', '2024-04-24 10:22:46.4680',
        '2024-04-24 10:22:46.4680'),
       (418, '936499355525984256', 20, 82.993, '2024-04-24 10:22:46.5150', '0', '2024-04-24 10:22:46.5340',
        '2024-04-24 10:22:46.5340'),
       (419, '936499355525984256', 10, 21.797, '2024-04-24 11:13:28.5590', '0', '2024-04-24 11:13:28.6340',
        '2024-04-24 11:13:28.6340'),
       (420, '936499355525984256', 20, 82.891, '2024-04-24 11:13:28.7190', '0', '2024-04-24 11:13:28.7300',
        '2024-04-24 11:13:28.7300'),
       (421, '936499355525984256', 10, 21.51, '2024-04-24 11:50:25.8600', '0', '2024-04-24 11:50:25.9240',
        '2024-04-24 11:50:25.9240'),
       (422, '936499355525984256', 20, 82.846, '2024-04-24 11:50:25.9710', '0', '2024-04-24 11:50:25.9740',
        '2024-04-24 11:50:25.9740'),
       (423, '936499355525984256', 10, 21.224, '2024-04-24 12:27:22.1780', '0', '2024-04-24 12:27:22.2180',
        '2024-04-24 12:27:22.2180'),
       (424, '936499355525984256', 20, 83.019, '2024-04-24 12:27:22.3030', '0', '2024-04-24 12:27:22.3270',
        '2024-04-24 12:27:22.3270'),
       (425, '936499355525984256', 10, 21.52, '2024-04-24 13:04:20.5830', '0', '2024-04-24 13:04:20.7150',
        '2024-04-24 13:04:20.7150'),
       (426, '936499355525984256', 20, 82.923, '2024-04-24 13:04:20.7910', '0', '2024-04-24 13:04:20.7990',
        '2024-04-24 13:04:20.7990'),
       (427, '936499355525984256', 10, 26.972, '2024-04-24 13:41:15.8010', '0', '2024-04-24 13:41:15.8490',
        '2024-04-24 13:41:15.8490'),
       (428, '936499355525984256', 20, 83.063, '2024-04-24 13:41:15.9220', '0', '2024-04-24 13:41:15.9360',
        '2024-04-24 13:41:15.9360'),
       (429, '936499355525984256', 10, 21.272, '2024-04-24 14:23:05.9810', '0', '2024-04-24 14:23:06.1220',
        '2024-04-24 14:23:06.1220'),
       (430, '936499355525984256', 20, 83.069, '2024-04-24 14:23:06.1980', '0', '2024-04-24 14:23:06.2030',
        '2024-04-24 14:23:06.2030'),
       (431, '936499355525984256', 10, 15.57, '2024-04-24 14:41:05.7720', '0', '2024-04-24 14:41:05.8260',
        '2024-04-24 14:41:05.8260'),
       (432, '936499355525984256', 20, 83.056, '2024-04-24 14:41:05.9070', '0', '2024-04-24 14:41:05.9180',
        '2024-04-24 14:41:05.9180'),
       (433, '936499355525984256', 10, 16.939, '2024-04-24 15:15:49.0710', '0', '2024-04-24 15:15:49.1260',
        '2024-04-24 15:15:49.1260'),
       (434, '936499355525984256', 20, 82.973, '2024-04-24 15:15:49.1720', '0', '2024-04-24 15:15:49.1790',
        '2024-04-24 15:15:49.1790'),
       (435, '936499355525984256', 10, 18.477, '2024-04-24 16:19:58.3730', '0', '2024-04-24 16:19:58.4200',
        '2024-04-24 16:19:58.4200'),
       (436, '936499355525984256', 20, 83, '2024-04-24 16:19:58.5200', '0', '2024-04-24 16:19:58.5340',
        '2024-04-24 16:19:58.5340'),
       (437, '936499355525984256', 10, 14.811, '2024-04-24 16:36:01.2260', '0', '2024-04-24 16:36:01.2870',
        '2024-04-24 16:36:01.2870'),
       (438, '936499355525984256', 20, 82.905, '2024-04-24 16:36:01.3390', '0', '2024-04-24 16:36:01.3470',
        '2024-04-24 16:36:01.3470'),
       (439, '936499355525984256', 10, 19.589, '2024-04-24 17:56:34.3950', '0', '2024-04-24 17:56:34.4760',
        '2024-04-24 17:56:34.4760'),
       (440, '936499355525984256', 20, 82.955, '2024-04-24 17:56:34.8500', '0', '2024-04-24 17:56:34.8620',
        '2024-04-24 17:56:34.8620'),
       (441, '936499355525984256', 10, 20.645, '2024-04-24 18:57:56.3020', '0', '2024-04-24 18:57:56.3620',
        '2024-04-24 18:57:56.3620'),
       (442, '936499355525984256', 20, 82.934, '2024-04-24 18:57:56.4090', '0', '2024-04-24 18:57:56.4130',
        '2024-04-24 18:57:56.4130'),
       (443, '936499355525984256', 10, 18.818, '2024-04-24 19:14:55.8870', '0', '2024-04-24 19:14:56.0370',
        '2024-04-24 19:14:56.0370'),
       (444, '936499355525984256', 20, 83.01, '2024-04-24 19:14:56.1250', '0', '2024-04-24 19:14:56.1310',
        '2024-04-24 19:14:56.1310'),
       (445, '936499355525984256', 10, 19.754, '2024-04-24 19:57:08.4180', '0', '2024-04-24 19:57:08.4640',
        '2024-04-24 19:57:08.4640'),
       (446, '936499355525984256', 20, 83.018, '2024-04-24 19:57:10.8060', '0', '2024-04-24 19:57:10.8130',
        '2024-04-24 19:57:10.8130'),
       (447, '936499355525984256', 10, 24.102, '2024-04-24 20:13:54.2060', '0', '2024-04-24 20:13:54.3210',
        '2024-04-24 20:13:54.3210'),
       (448, '936499355525984256', 20, 82.867, '2024-04-24 20:13:54.4030', '0', '2024-04-24 20:13:54.4090',
        '2024-04-24 20:13:54.4090'),
       (449, '936499355525984256', 10, 18.579, '2024-04-24 20:25:10.9280', '0', '2024-04-24 20:25:10.9620',
        '2024-04-24 20:25:10.9620'),
       (450, '936499355525984256', 20, 82.882, '2024-04-24 20:25:11.0110', '0', '2024-04-24 20:25:11.0190',
        '2024-04-24 20:25:11.0190'),
       (451, '936499355525984256', 10, 16.66, '2024-04-24 20:41:27.1030', '0', '2024-04-24 20:41:27.1680',
        '2024-04-24 20:41:27.1680'),
       (452, '936499355525984256', 20, 82.979, '2024-04-24 20:41:27.2320', '0', '2024-04-24 20:41:27.2460',
        '2024-04-24 20:41:27.2460'),
       (453, '936499355525984256', 10, 23.817, '2024-04-24 21:03:51.0660', '0', '2024-04-24 21:03:51.1910',
        '2024-04-24 21:03:51.1910'),
       (454, '936499355525984256', 20, 82.993, '2024-04-24 21:03:51.2520', '0', '2024-04-24 21:03:51.2570',
        '2024-04-24 21:03:51.2570'),
       (455, '936499355525984256', 10, 19.615, '2024-04-24 21:20:15.9790', '0', '2024-04-24 21:36:37.1820',
        '2024-04-24 21:36:37.1820'),
       (456, '936499355525984256', 20, 83.074, '2024-04-24 21:36:37.4040', '0', '2024-04-24 21:36:37.4190',
        '2024-04-24 21:36:37.4190'),
       (457, '936499355525984256', 10, 28.39, '2024-04-24 22:08:13.8610', '0', '2024-04-24 22:08:13.9440',
        '2024-04-24 22:08:13.9440'),
       (458, '936499355525984256', 20, 82.954, '2024-04-24 22:08:14.0270', '0', '2024-04-24 22:08:14.0350',
        '2024-04-24 22:08:14.0350'),
       (459, '936499355525984256', 10, 24.882, '2024-04-24 22:09:32.3530', '0', '2024-04-24 22:09:32.4410',
        '2024-04-24 22:09:32.4410'),
       (460, '936499355525984256', 20, 82.933, '2024-04-24 22:09:32.5270', '0', '2024-04-24 22:09:32.5330',
        '2024-04-24 22:09:32.5330'),
       (461, '936499355525984256', 10, 20.61, '2024-04-24 22:11:02.3550', '0', '2024-04-24 22:11:02.4440',
        '2024-04-24 22:11:02.4440'),
       (462, '936499355525984256', 20, 82.955, '2024-04-24 22:11:02.5060', '0', '2024-04-24 22:11:02.5100',
        '2024-04-24 22:11:02.5100'),
       (463, '936499355525984256', 10, 25.063, '2024-04-24 22:12:32.3420', '0', '2024-04-24 22:12:32.4180',
        '2024-04-24 22:12:32.4180'),
       (464, '936499355525984256', 20, 82.054, '2024-04-24 22:12:32.5570', '0', '2024-04-24 22:12:32.5640',
        '2024-04-24 22:12:32.5640'),
       (465, '936499355525984256', 10, 24.715, '2024-04-24 22:13:32.3520', '0', '2024-04-24 22:13:32.3890',
        '2024-04-24 22:13:32.3890'),
       (466, '936499355525984256', 20, 82.653, '2024-04-24 22:14:02.3430', '0', '2024-04-24 22:14:02.3700',
        '2024-04-24 22:14:02.3700'),
       (467, '936499355525984256', 10, 33.578, '2024-04-24 22:15:02.3410', '0', '2024-04-24 22:15:02.3690',
        '2024-04-24 22:15:02.3690'),
       (468, '936499355525984256', 20, 83.118, '2024-04-24 22:15:02.4510', '0', '2024-04-24 22:15:02.4560',
        '2024-04-24 22:15:02.4560'),
       (469, '936499355525984256', 10, 31.293, '2024-04-24 22:16:32.3350', '0', '2024-04-24 22:16:32.4610',
        '2024-04-24 22:16:32.4610'),
       (470, '936499355525984256', 20, 82.37, '2024-04-24 22:16:32.5110', '0', '2024-04-24 22:16:32.5150',
        '2024-04-24 22:16:32.5150'),
       (471, '936499355525984256', 10, 37.196, '2024-04-24 22:18:02.3350', '0', '2024-04-24 22:18:02.4590',
        '2024-04-24 22:18:02.4590'),
       (472, '936499355525984256', 20, 82.797, '2024-04-24 22:18:02.5530', '0', '2024-04-24 22:18:02.5640',
        '2024-04-24 22:18:02.5640'),
       (473, '936499355525984256', 10, 38.108, '2024-04-24 22:19:32.3330', '0', '2024-04-24 22:19:32.4290',
        '2024-04-24 22:19:32.4290'),
       (474, '936499355525984256', 20, 82.716, '2024-04-24 22:19:32.5300', '0', '2024-04-24 22:19:32.5400',
        '2024-04-24 22:19:32.5400'),
       (475, '936499355525984256', 10, 36.671, '2024-04-24 22:21:02.3580', '0', '2024-04-24 22:21:02.4390',
        '2024-04-24 22:21:02.4390'),
       (476, '936499355525984256', 20, 82.875, '2024-04-24 22:21:02.5230', '0', '2024-04-24 22:21:02.5290',
        '2024-04-24 22:21:02.5290'),
       (477, '936499355525984256', 10, 36.604, '2024-04-24 22:22:32.3320', '0', '2024-04-24 22:22:32.4040',
        '2024-04-24 22:22:32.4040'),
       (478, '936499355525984256', 20, 82.957, '2024-04-24 22:22:32.4940', '0', '2024-04-24 22:22:32.4980',
        '2024-04-24 22:22:32.4980'),
       (479, '936499355525984256', 10, 33.139, '2024-04-24 22:24:02.3250', '0', '2024-04-24 22:24:02.3920',
        '2024-04-24 22:24:02.3920'),
       (480, '936499355525984256', 20, 81.695, '2024-04-24 22:24:02.4960', '0', '2024-04-24 22:24:02.5030',
        '2024-04-24 22:24:02.5030'),
       (481, '936499355525984256', 10, 30.438, '2024-04-24 22:25:02.3390', '0', '2024-04-24 22:25:02.3920',
        '2024-04-24 22:25:02.3920'),
       (482, '936499355525984256', 20, 82.333, '2024-04-24 22:25:32.3220', '0', '2024-04-24 22:25:32.4380',
        '2024-04-24 22:25:32.4380'),
       (483, '936499355525984256', 10, 60.713, '2024-04-24 22:26:32.3210', '0', '2024-04-24 22:26:32.3690',
        '2024-04-24 22:26:32.3690'),
       (484, '936499355525984256', 20, 83.197, '2024-04-24 22:26:32.4720', '0', '2024-04-24 22:26:32.4800',
        '2024-04-24 22:26:32.4800'),
       (485, '936499355525984256', 10, 47.944, '2024-04-24 22:28:02.3310', '0', '2024-04-24 22:28:02.4190',
        '2024-04-24 22:28:02.4190'),
       (486, '936499355525984256', 20, 83.372, '2024-04-24 22:28:02.4800', '0', '2024-04-24 22:28:02.4830',
        '2024-04-24 22:28:02.4830'),
       (487, '936499355525984256', 10, 37.867, '2024-04-24 22:29:32.3230', '0', '2024-04-24 22:29:32.3830',
        '2024-04-24 22:29:32.3830'),
       (488, '936499355525984256', 20, 83.132, '2024-04-24 22:29:32.4730', '0', '2024-04-24 22:29:32.4780',
        '2024-04-24 22:29:32.4780'),
       (489, '936499355525984256', 10, 49.109, '2024-04-24 22:31:02.3370', '0', '2024-04-24 22:31:02.4130',
        '2024-04-24 22:31:02.4130'),
       (490, '936499355525984256', 20, 83.378, '2024-04-24 22:31:02.4880', '0', '2024-04-24 22:31:02.4940',
        '2024-04-24 22:31:02.4940'),
       (491, '936499355525984256', 10, 46.578, '2024-04-24 22:32:02.3950', '0', '2024-04-24 22:32:02.5150',
        '2024-04-24 22:32:02.5150'),
       (492, '936499355525984256', 20, 82.696, '2024-04-24 22:32:02.6150', '0', '2024-04-24 22:32:02.6270',
        '2024-04-24 22:32:02.6270'),
       (493, '936499355525984256', 10, 39.723, '2024-04-24 22:33:32.3910', '0', '2024-04-24 22:33:32.5130',
        '2024-04-24 22:33:32.5130'),
       (494, '936499355525984256', 20, 83.193, '2024-04-24 22:33:32.5860', '0', '2024-04-24 22:33:32.5900',
        '2024-04-24 22:33:32.5900'),
       (495, '936499355525984256', 10, 42.882, '2024-04-24 22:34:32.3970', '0', '2024-04-24 22:34:32.4420',
        '2024-04-24 22:34:32.4420'),
       (496, '936499355525984256', 20, 82.831, '2024-04-24 22:35:02.3950', '0', '2024-04-24 22:35:02.4280',
        '2024-04-24 22:35:02.4280'),
       (497, '936499355525984256', 10, 30.048, '2024-04-24 22:36:02.3900', '0', '2024-04-24 22:36:02.4650',
        '2024-04-24 22:36:02.4650'),
       (498, '936499355525984256', 20, 83.387, '2024-04-24 22:36:05.6250', '0', '2024-04-24 22:36:05.6340',
        '2024-04-24 22:36:05.6340'),
       (499, '936499355525984256', 10, 23.806, '2024-04-24 22:37:02.3970', '0', '2024-04-24 22:37:02.4430',
        '2024-04-24 22:37:02.4430'),
       (500, '936499355525984256', 20, 83.207, '2024-04-24 22:37:32.3920', '0', '2024-04-24 22:37:32.4170',
        '2024-04-24 22:37:32.4170'),
       (501, '936499355525984256', 10, 32.959, '2024-04-24 22:38:02.4060', '0', '2024-04-24 22:38:02.4510',
        '2024-04-24 22:38:02.4510'),
       (502, '936499355525984256', 20, 82.51, '2024-04-24 22:38:32.3960', '0', '2024-04-24 22:38:32.4480',
        '2024-04-24 22:38:32.4480'),
       (503, '936499355525984256', 10, 32.416, '2024-04-24 22:39:32.4200', '0', '2024-04-24 22:39:32.4690',
        '2024-04-24 22:39:32.4690'),
       (504, '936499355525984256', 20, 83.203, '2024-04-24 22:39:32.5820', '0', '2024-04-24 22:39:32.6100',
        '2024-04-24 22:39:32.6100'),
       (505, '936499355525984256', 10, 30.72, '2024-04-24 22:41:02.3880', '0', '2024-04-24 22:41:02.5100',
        '2024-04-24 22:41:02.5100'),
       (506, '936499355525984256', 20, 82.613, '2024-04-24 22:41:02.5760', '0', '2024-04-24 22:41:02.5810',
        '2024-04-24 22:41:02.5810'),
       (507, '936499355525984256', 10, 32.117, '2024-04-24 22:42:02.3970', '0', '2024-04-24 22:42:02.4740',
        '2024-04-24 22:42:02.4740'),
       (508, '936499355525984256', 20, 82.47, '2024-04-24 22:42:02.6400', '0', '2024-04-24 22:42:02.6470',
        '2024-04-24 22:42:02.6470'),
       (509, '936499355525984256', 10, 28.26, '2024-04-24 22:43:32.3940', '0', '2024-04-24 22:43:32.4650',
        '2024-04-24 22:43:32.4650'),
       (510, '936499355525984256', 20, 82.126, '2024-04-24 22:43:32.5220', '0', '2024-04-24 22:43:32.5310',
        '2024-04-24 22:43:32.5310'),
       (511, '936499355525984256', 10, 36.119, '2024-04-24 22:44:32.4050', '0', '2024-04-24 22:44:32.4680',
        '2024-04-24 22:44:32.4680'),
       (512, '936499355525984256', 20, 83.14, '2024-04-24 22:44:32.5510', '0', '2024-04-24 22:44:32.5560',
        '2024-04-24 22:44:32.5560'),
       (513, '936499355525984256', 10, 27.494, '2024-04-24 22:46:02.3940', '0', '2024-04-24 22:46:02.4790',
        '2024-04-24 22:46:02.4790'),
       (514, '936499355525984256', 20, 82.526, '2024-04-24 22:46:02.5580', '0', '2024-04-24 22:46:02.5610',
        '2024-04-24 22:46:02.5610'),
       (515, '936499355525984256', 10, 25.308, '2024-04-24 22:47:02.3950', '0', '2024-04-24 22:47:02.4300',
        '2024-04-24 22:47:02.4300'),
       (516, '936499355525984256', 20, 82.689, '2024-04-24 22:47:32.3950', '0', '2024-04-24 22:47:32.4540',
        '2024-04-24 22:47:32.4540'),
       (517, '936499355525984256', 10, 36.536, '2024-04-24 22:48:02.3960', '0', '2024-04-24 22:48:02.4330',
        '2024-04-24 22:48:02.4330'),
       (518, '936499355525984256', 20, 82.439, '2024-04-24 22:48:32.3980', '0', '2024-04-24 22:48:32.4270',
        '2024-04-24 22:48:32.4270'),
       (519, '936499355525984256', 10, 29.824, '2024-04-24 22:49:32.4010', '0', '2024-04-24 22:49:32.4810',
        '2024-04-24 22:49:32.4810'),
       (520, '936499355525984256', 20, 82.631, '2024-04-24 22:49:32.5560', '0', '2024-04-24 22:49:32.5590',
        '2024-04-24 22:49:32.5590'),
       (521, '936499355525984256', 10, 32.982, '2024-04-24 22:50:32.4160', '0', '2024-04-24 22:50:32.4900',
        '2024-04-24 22:50:32.4900'),
       (522, '936499355525984256', 20, 83.126, '2024-04-24 22:50:32.6280', '0', '2024-04-24 22:50:32.6330',
        '2024-04-24 22:50:32.6330'),
       (523, '936499355525984256', 10, 17.917, '2024-04-24 22:52:02.3910', '0', '2024-04-24 22:52:02.4890',
        '2024-04-24 22:52:02.4890'),
       (524, '936499355525984256', 20, 83.061, '2024-04-24 22:52:02.5400', '0', '2024-04-24 22:52:02.5430',
        '2024-04-24 22:52:02.5430'),
       (525, '936499355525984256', 10, 25.663, '2024-04-24 22:53:02.4070', '0', '2024-04-24 22:53:02.4370',
        '2024-04-24 22:53:02.4370'),
       (526, '936499355525984256', 20, 82.795, '2024-04-24 22:53:32.4160', '0', '2024-04-24 22:53:32.4580',
        '2024-04-24 22:53:32.4580'),
       (527, '936499355525984256', 10, 17.46, '2024-04-24 22:54:02.4070', '0', '2024-04-24 22:54:02.4450',
        '2024-04-24 22:54:02.4450'),
       (528, '936499355525984256', 20, 83.094, '2024-04-24 22:55:02.3960', '0', '2024-04-24 22:55:02.4410',
        '2024-04-24 22:55:02.4410'),
       (529, '936499355525984256', 10, 20.462, '2024-04-24 22:55:32.4030', '0', '2024-04-24 22:55:32.4610',
        '2024-04-24 22:55:32.4610'),
       (530, '936499355525984256', 20, 82.364, '2024-04-24 22:56:02.4030', '0', '2024-04-24 22:56:02.4480',
        '2024-04-24 22:56:02.4480'),
       (531, '936499355525984256', 10, 20.36, '2024-04-24 22:57:02.3960', '0', '2024-04-24 22:57:02.4640',
        '2024-04-24 22:57:02.4640'),
       (532, '936499355525984256', 20, 83, '2024-04-24 22:57:02.5420', '0', '2024-04-24 22:57:02.5500',
        '2024-04-24 22:57:02.5500'),
       (533, '936499355525984256', 10, 23.236, '2024-04-24 22:58:02.4000', '0', '2024-04-24 22:58:02.4630',
        '2024-04-24 22:58:02.4630'),
       (534, '936499355525984256', 20, 82.38, '2024-04-24 22:58:32.4030', '0', '2024-04-24 22:58:32.4640',
        '2024-04-24 22:58:32.4640'),
       (535, '936499355525984256', 10, 16.322, '2024-04-24 22:59:32.4010', '0', '2024-04-24 22:59:32.4480',
        '2024-04-24 22:59:32.4480'),
       (536, '936499355525984256', 20, 83.015, '2024-04-24 22:59:32.5460', '0', '2024-04-24 22:59:32.5510',
        '2024-04-24 22:59:32.5510'),
       (537, '936499355525984256', 10, 16.744, '2024-04-24 23:00:32.4150', '0', '2024-04-24 23:00:32.4810',
        '2024-04-24 23:00:32.4810'),
       (538, '936499355525984256', 20, 83.014, '2024-04-24 23:00:32.6020', '0', '2024-04-24 23:00:32.6080',
        '2024-04-24 23:00:32.6080'),
       (539, '936499355525984256', 10, 17.203, '2024-04-24 23:02:02.4080', '0', '2024-04-24 23:02:02.4850',
        '2024-04-24 23:02:02.4850'),
       (540, '936499355525984256', 20, 82.312, '2024-04-24 23:02:02.5430', '0', '2024-04-24 23:02:02.5480',
        '2024-04-24 23:02:02.5480'),
       (541, '936499355525984256', 10, 24.284, '2024-04-24 23:03:32.3700', '0', '2024-04-24 23:03:32.8840',
        '2024-04-24 23:03:32.8870'),
       (542, '936499355525984256', 20, 83.109, '2024-04-24 23:03:33.6890', '0', '2024-04-24 23:03:33.7140',
        '2024-04-24 23:03:33.7140'),
       (543, '936499355525984256', 10, 28.464, '2024-04-24 23:04:32.3750', '0', '2024-04-24 23:04:32.4400',
        '2024-04-24 23:04:32.4400'),
       (544, '936499355525984256', 20, 82.802, '2024-04-24 23:05:02.3610', '0', '2024-04-24 23:05:02.4060',
        '2024-04-24 23:05:02.4060'),
       (545, '936499355525984256', 10, 16.173, '2024-04-24 23:06:02.3610', '0', '2024-04-24 23:06:02.4380',
        '2024-04-24 23:06:02.4380'),
       (546, '936499355525984256', 20, 81.353, '2024-04-24 23:06:05.6590', '0', '2024-04-24 23:06:05.6740',
        '2024-04-24 23:06:05.6740'),
       (547, '936499355525984256', 10, 16.641, '2024-04-24 23:07:02.3630', '0', '2024-04-24 23:07:02.4480',
        '2024-04-24 23:07:02.4480'),
       (548, '936499355525984256', 20, 82.956, '2024-04-24 23:07:32.3600', '0', '2024-04-24 23:07:32.4020',
        '2024-04-24 23:07:32.4020'),
       (549, '936499355525984256', 10, 26.373, '2024-04-24 23:08:32.3570', '0', '2024-04-24 23:08:32.4310',
        '2024-04-24 23:08:32.4310'),
       (550, '936499355525984256', 20, 82.827, '2024-04-24 23:08:32.5250', '0', '2024-04-24 23:08:32.5290',
        '2024-04-24 23:08:32.5290'),
       (551, '936499355525984256', 10, 22.998, '2024-04-24 23:14:15.3700', '0', '2024-04-24 23:14:15.5200',
        '2024-04-24 23:14:15.5200'),
       (552, '936499355525984256', 20, 81.478, '2024-04-24 23:14:15.6130', '0', '2024-04-24 23:14:15.6190',
        '2024-04-24 23:14:15.6190'),
       (553, '936499355525984256', 10, 25.002, '2024-04-25 00:04:43.0870', '0', '2024-04-25 00:04:43.3150',
        '2024-04-25 00:04:43.3150'),
       (554, '936499355525984256', 20, 82.195, '2024-04-25 00:04:43.4690', '0', '2024-04-25 00:04:43.4820',
        '2024-04-25 00:04:43.4820'),
       (555, '936499355525984256', 10, 18.788, '2024-04-25 00:43:22.5870', '0', '2024-04-25 00:43:22.9450',
        '2024-04-25 00:43:22.9450'),
       (556, '936499355525984256', 20, 82.761, '2024-04-25 00:43:23.0730', '0', '2024-04-25 00:43:23.0870',
        '2024-04-25 00:43:23.0870'),
       (557, '936499355525984256', 10, 19.397, '2024-04-25 01:17:26.2900', '0', '2024-04-25 01:17:26.3700',
        '2024-04-25 01:17:26.3700'),
       (558, '936499355525984256', 20, 82.963, '2024-04-25 01:17:26.4750', '0', '2024-04-25 01:17:26.4910',
        '2024-04-25 01:17:26.4910'),
       (559, '936499355525984256', 10, 22.476, '2024-04-25 02:58:15.8840', '0', '2024-04-25 02:58:16.0170',
        '2024-04-25 02:58:16.0170'),
       (560, '936499355525984256', 20, 83.083, '2024-04-25 02:58:16.1200', '0', '2024-04-25 02:58:16.1270',
        '2024-04-25 02:58:16.1270'),
       (561, '936499355525984256', 10, 23.086, '2024-04-25 04:20:58.1320', '0', '2024-04-25 04:20:58.2120',
        '2024-04-25 04:20:58.2120'),
       (562, '936499355525984256', 20, 82.929, '2024-04-25 04:20:58.2670', '0', '2024-04-25 04:20:58.2750',
        '2024-04-25 04:20:58.2750'),
       (563, '936499355525984256', 10, 19.356, '2024-04-25 05:10:23.3220', '0', '2024-04-25 05:10:23.4680',
        '2024-04-25 05:10:23.4690'),
       (564, '936499355525984256', 20, 82.968, '2024-04-25 05:10:23.5870', '0', '2024-04-25 05:10:23.6010',
        '2024-04-25 05:10:23.6010'),
       (565, '936499355525984256', 10, 20.76, '2024-04-25 05:28:16.5870', '0', '2024-04-25 05:28:16.7820',
        '2024-04-25 05:28:16.7820'),
       (566, '936499355525984256', 20, 83.017, '2024-04-25 05:28:16.8960', '0', '2024-04-25 05:28:16.9180',
        '2024-04-25 05:28:16.9180'),
       (567, '936499355525984256', 10, 19.592, '2024-04-25 05:33:28.9520', '0', '2024-04-25 05:33:29.0240',
        '2024-04-25 05:33:29.0240'),
       (568, '936499355525984256', 20, 82.923, '2024-04-25 05:33:29.0780', '0', '2024-04-25 05:33:29.0990',
        '2024-04-25 05:33:29.0990'),
       (569, '936499355525984256', 10, 19.481, '2024-04-25 06:38:09.0000', '0', '2024-04-25 06:38:09.0810',
        '2024-04-25 06:38:09.0810'),
       (570, '936499355525984256', 20, 82.816, '2024-04-25 06:38:09.1730', '0', '2024-04-25 06:38:09.1870',
        '2024-04-25 06:38:09.1870'),
       (571, '936499355525984256', 10, 22.129, '2024-04-25 08:22:27.7310', '0', '2024-04-25 08:22:27.8310',
        '2024-04-25 08:22:27.8310'),
       (572, '936499355525984256', 20, 82.996, '2024-04-25 08:22:27.8970', '0', '2024-04-25 08:22:27.9040',
        '2024-04-25 08:22:27.9040'),
       (573, '936499355525984256', 10, 23.905, '2024-04-25 08:51:51.0080', '0', '2024-04-25 08:51:51.2000',
        '2024-04-25 08:51:51.2000'),
       (574, '936499355525984256', 20, 82.92, '2024-04-25 08:51:51.3630', '0', '2024-04-25 08:51:51.3730',
        '2024-04-25 08:51:51.3730'),
       (575, '936499355525984256', 10, 20.174, '2024-04-25 09:19:51.0770', '0', '2024-04-25 09:19:51.2460',
        '2024-04-25 09:19:51.2470'),
       (576, '936499355525984256', 20, 82.927, '2024-04-25 09:19:51.3970', '0', '2024-04-25 09:19:51.4180',
        '2024-04-25 09:19:51.4180'),
       (577, '936499355525984256', 10, 22.979, '2024-04-25 09:37:40.3740', '0', '2024-04-25 09:37:40.6880',
        '2024-04-25 09:37:40.6880'),
       (578, '936499355525984256', 20, 83.065, '2024-04-25 09:37:40.7520', '0', '2024-04-25 09:37:40.7700',
        '2024-04-25 09:37:40.7700'),
       (579, '936499355525984256', 10, 27.682, '2024-04-25 10:58:14.6450', '0', '2024-04-25 10:58:14.7070',
        '2024-04-25 10:58:14.7070'),
       (580, '936499355525984256', 20, 83.112, '2024-04-25 10:58:14.8220', '0', '2024-04-25 10:58:14.8590',
        '2024-04-25 10:58:14.8590'),
       (581, '936499355525984256', 10, 31.932, '2024-04-25 11:30:44.0880', '0', '2024-04-25 11:30:44.1770',
        '2024-04-25 11:30:44.1770'),
       (582, '936499355525984256', 20, 82.464, '2024-04-25 11:30:44.3220', '0', '2024-04-25 11:30:44.3400',
        '2024-04-25 11:30:44.3400'),
       (583, '936499355525984256', 10, 29.509, '2024-04-25 11:52:59.1280', '0', '2024-04-25 11:52:59.3490',
        '2024-04-25 11:52:59.3490'),
       (584, '936499355525984256', 20, 82.052, '2024-04-25 11:52:59.4580', '0', '2024-04-25 11:52:59.4660',
        '2024-04-25 11:52:59.4660'),
       (585, '936499355525984256', 10, 22.485, '2024-04-25 13:34:35.6450', '0', '2024-04-25 13:34:35.6970',
        '2024-04-25 13:34:35.6970'),
       (586, '936499355525984256', 20, 81.766, '2024-04-25 13:34:35.7880', '0', '2024-04-25 13:34:35.8050',
        '2024-04-25 13:34:35.8050'),
       (587, '936499355525984256', 10, 22.973, '2024-04-25 14:58:30.5910', '0', '2024-04-25 14:58:30.6370',
        '2024-04-25 14:58:30.6370'),
       (588, '936499355525984256', 20, 83.032, '2024-04-25 14:58:30.6660', '0', '2024-04-25 14:58:30.6830',
        '2024-04-25 14:58:30.6830'),
       (589, '936499355525984256', 10, 23.442, '2024-04-25 16:39:27.8150', '0', '2024-04-25 16:39:27.9260',
        '2024-04-25 16:39:27.9260'),
       (590, '936499355525984256', 20, 83.231, '2024-04-25 16:39:28.0660', '0', '2024-04-25 16:39:28.0810',
        '2024-04-25 16:39:28.0810'),
       (591, '936499355525984256', 10, 21.106, '2024-04-25 18:04:09.5960', '0', '2024-04-25 18:04:09.6280',
        '2024-04-25 18:04:09.6280'),
       (592, '936499355525984256', 20, 82.831, '2024-04-25 18:04:09.6780', '0', '2024-04-25 18:04:09.6930',
        '2024-04-25 18:04:09.6930'),
       (593, '936499355525984256', 10, 23.358, '2024-04-25 19:41:33.7150', '0', '2024-04-25 19:41:33.8040',
        '2024-04-25 19:41:33.8040'),
       (594, '936499355525984256', 20, 83.027, '2024-04-25 19:41:33.8860', '0', '2024-04-25 19:41:33.8960',
        '2024-04-25 19:41:33.8960'),
       (595, '936499355525984256', 10, 22.324, '2024-04-25 21:07:14.3580', '0', '2024-04-25 21:07:14.3810',
        '2024-04-25 21:07:14.3810'),
       (596, '936499355525984256', 20, 83.161, '2024-04-25 21:07:14.4420', '0', '2024-04-25 21:07:14.4650',
        '2024-04-25 21:07:14.4650'),
       (597, '936499355525984256', 10, 27.03, '2024-04-25 21:41:27.7450', '0', '2024-04-25 21:41:27.8390',
        '2024-04-25 21:41:27.8390'),
       (598, '936499355525984256', 20, 83.181, '2024-04-25 21:41:27.9840', '0', '2024-04-25 21:41:28.0030',
        '2024-04-25 21:41:28.0030'),
       (599, '936499355525984256', 10, 38.582, '2024-04-25 21:42:57.7170', '0', '2024-04-25 21:42:57.8420',
        '2024-04-25 21:42:57.8420'),
       (600, '936499355525984256', 20, 82.969, '2024-04-25 21:42:57.9080', '0', '2024-04-25 21:42:57.9120',
        '2024-04-25 21:42:57.9120'),
       (601, '936499355525984256', 10, 32.753, '2024-04-25 21:43:57.7260', '0', '2024-04-25 21:43:57.8480',
        '2024-04-25 21:43:57.8480'),
       (602, '936499355525984256', 20, 82.475, '2024-04-25 21:43:57.9220', '0', '2024-04-25 21:43:57.9310',
        '2024-04-25 21:43:57.9310'),
       (603, '936499355525984256', 10, 30.847, '2024-04-25 21:45:27.7220', '0', '2024-04-25 21:45:27.8450',
        '2024-04-25 21:45:27.8450'),
       (604, '936499355525984256', 20, 82.681, '2024-04-25 21:45:27.9170', '0', '2024-04-25 21:45:27.9330',
        '2024-04-25 21:45:27.9330'),
       (605, '936499355525984256', 10, 31.518, '2024-04-25 21:46:27.7300', '0', '2024-04-25 21:46:27.8460',
        '2024-04-25 21:46:27.8460'),
       (606, '936499355525984256', 20, 82.749, '2024-04-25 21:46:27.9960', '0', '2024-04-25 21:46:28.0020',
        '2024-04-25 21:46:28.0020'),
       (607, '936499355525984256', 10, 28.91, '2024-04-25 21:47:57.7210', '0', '2024-04-25 21:47:57.8290',
        '2024-04-25 21:47:57.8290'),
       (608, '936499355525984256', 20, 82.577, '2024-04-25 21:47:57.9000', '0', '2024-04-25 21:47:57.9130',
        '2024-04-25 21:47:57.9130'),
       (609, '936499355525984256', 10, 35.092, '2024-04-25 21:48:57.7450', '0', '2024-04-25 21:48:57.8420',
        '2024-04-25 21:48:57.8420'),
       (610, '936499355525984256', 20, 83.146, '2024-04-25 21:48:57.9330', '0', '2024-04-25 21:48:57.9460',
        '2024-04-25 21:48:57.9460'),
       (611, '936499355525984256', 10, 31.155, '2024-04-25 21:50:27.7260', '0', '2024-04-25 21:50:27.8230',
        '2024-04-25 21:50:27.8230'),
       (612, '936499355525984256', 20, 82.618, '2024-04-25 21:50:28.2010', '0', '2024-04-25 21:50:28.2230',
        '2024-04-25 21:50:28.2230'),
       (613, '936499355525984256', 10, 36.477, '2024-04-25 21:51:57.7350', '0', '2024-04-25 21:51:57.8930',
        '2024-04-25 21:51:57.8930'),
       (614, '936499355525984256', 20, 82.401, '2024-04-25 21:51:57.9800', '0', '2024-04-25 21:51:57.9870',
        '2024-04-25 21:51:57.9870'),
       (615, '936499355525984256', 10, 32.971, '2024-04-25 21:53:27.7310', '0', '2024-04-25 21:53:28.0210',
        '2024-04-25 21:53:28.0210'),
       (616, '936499355525984256', 20, 82.446, '2024-04-25 21:53:31.2070', '0', '2024-04-25 21:53:31.3070',
        '2024-04-25 21:53:31.3070'),
       (617, '936499355525984256', 10, 33.655, '2024-04-25 21:54:57.7260', '0', '2024-04-25 21:54:57.8600',
        '2024-04-25 21:54:57.8600'),
       (618, '936499355525984256', 20, 83.053, '2024-04-25 21:54:57.9200', '0', '2024-04-25 21:54:57.9300',
        '2024-04-25 21:54:57.9300'),
       (619, '936499355525984256', 10, 29.469, '2024-04-25 21:56:27.7300', '0', '2024-04-25 21:56:27.8670',
        '2024-04-25 21:56:27.8670'),
       (620, '936499355525984256', 20, 83.131, '2024-04-25 21:56:27.9560', '0', '2024-04-25 21:56:27.9690',
        '2024-04-25 21:56:27.9690'),
       (621, '936499355525984256', 10, 28.591, '2024-04-25 21:57:27.7370', '0', '2024-04-25 21:57:27.8090',
        '2024-04-25 21:57:27.8090'),
       (622, '936499355525984256', 20, 82.584, '2024-04-25 21:57:57.7410', '0', '2024-04-25 21:57:57.8200',
        '2024-04-25 21:57:57.8200'),
       (623, '936499355525984256', 10, 32.353, '2024-04-25 21:58:57.7300', '0', '2024-04-25 21:58:57.8180',
        '2024-04-25 21:58:57.8180'),
       (624, '936499355525984256', 20, 83.133, '2024-04-25 21:58:57.9260', '0', '2024-04-25 21:58:57.9780',
        '2024-04-25 21:58:57.9780'),
       (625, '936499355525984256', 10, 40.876, '2024-04-25 21:59:57.7420', '0', '2024-04-25 21:59:57.8880',
        '2024-04-25 21:59:57.8880'),
       (626, '936499355525984256', 20, 83.266, '2024-04-25 21:59:58.0390', '0', '2024-04-25 21:59:58.0600',
        '2024-04-25 21:59:58.0600'),
       (627, '936499355525984256', 10, 35.316, '2024-04-25 22:01:27.7310', '0', '2024-04-25 22:01:27.8820',
        '2024-04-25 22:01:27.8820'),
       (628, '936499355525984256', 20, 83.154, '2024-04-25 22:01:27.9680', '0', '2024-04-25 22:01:27.9890',
        '2024-04-25 22:01:27.9890'),
       (629, '936499355525984256', 10, 33.946, '2024-04-25 22:02:27.7400', '0', '2024-04-25 22:02:27.9150',
        '2024-04-25 22:02:27.9150'),
       (630, '936499355525984256', 20, 83.348, '2024-04-25 22:02:27.9950', '0', '2024-04-25 22:02:27.9990',
        '2024-04-25 22:02:27.9990'),
       (631, '936499355525984256', 10, 36.929, '2024-04-25 22:03:57.7290', '0', '2024-04-25 22:03:57.8880',
        '2024-04-25 22:03:57.8880'),
       (632, '936499355525984256', 20, 83.254, '2024-04-25 22:03:57.9940', '0', '2024-04-25 22:03:58.0020',
        '2024-04-25 22:03:58.0020'),
       (633, '936499355525984256', 10, 34.85, '2024-04-25 22:04:57.7490', '0', '2024-04-25 22:04:57.8120',
        '2024-04-25 22:04:57.8120'),
       (634, '936499355525984256', 20, 82.259, '2024-04-25 22:04:58.0390', '0', '2024-04-25 22:04:58.0620',
        '2024-04-25 22:04:58.0620'),
       (635, '936499355525984256', 10, 32.408, '2024-04-25 22:06:27.7530', '0', '2024-04-25 22:06:27.8830',
        '2024-04-25 22:06:27.8840'),
       (636, '936499355525984256', 20, 82.922, '2024-04-25 22:06:27.9950', '0', '2024-04-25 22:06:28.0040',
        '2024-04-25 22:06:28.0040'),
       (637, '936499355525984256', 10, 28.883, '2024-04-25 22:07:57.7620', '0', '2024-04-25 22:07:57.9010',
        '2024-04-25 22:07:57.9040'),
       (638, '936499355525984256', 20, 82.133, '2024-04-25 22:07:57.9770', '0', '2024-04-25 22:07:57.9800',
        '2024-04-25 22:07:57.9800'),
       (639, '936499355525984256', 10, 32.24, '2024-04-25 22:09:27.7620', '0', '2024-04-25 22:09:27.8710',
        '2024-04-25 22:09:27.8710'),
       (640, '936499355525984256', 20, 82.574, '2024-04-25 22:09:27.9610', '0', '2024-04-25 22:09:27.9670',
        '2024-04-25 22:09:27.9670'),
       (641, '936499355525984256', 10, 28.613, '2024-04-25 22:10:57.7460', '0', '2024-04-25 22:10:57.8930',
        '2024-04-25 22:10:57.8930'),
       (642, '936499355525984256', 20, 82.426, '2024-04-25 22:10:57.9610', '0', '2024-04-25 22:10:57.9730',
        '2024-04-25 22:10:57.9730'),
       (643, '936499355525984256', 10, 34.582, '2024-04-25 22:11:57.7540', '0', '2024-04-25 22:11:57.8130',
        '2024-04-25 22:11:57.8130'),
       (644, '936499355525984256', 20, 82.873, '2024-04-25 22:12:27.7550', '0', '2024-04-25 22:12:27.8300',
        '2024-04-25 22:12:27.8300'),
       (645, '936499355525984256', 10, 29.001, '2024-04-25 22:13:27.7540', '0', '2024-04-25 22:13:27.8010',
        '2024-04-25 22:13:27.8010'),
       (646, '936499355525984256', 20, 83.225, '2024-04-25 22:13:27.8630', '0', '2024-04-25 22:13:27.8690',
        '2024-04-25 22:13:27.8690'),
       (647, '936499355525984256', 10, 32.276, '2024-04-25 22:14:57.7480', '0', '2024-04-25 22:14:57.8460',
        '2024-04-25 22:14:57.8460'),
       (648, '936499355525984256', 20, 82.794, '2024-04-25 22:14:57.9280', '0', '2024-04-25 22:14:57.9530',
        '2024-04-25 22:14:57.9530'),
       (649, '936499355525984256', 10, 42.486, '2024-04-25 22:15:57.7530', '0', '2024-04-25 22:15:57.8270',
        '2024-04-25 22:15:57.8270'),
       (650, '936499355525984256', 20, 82.178, '2024-04-25 22:16:27.7530', '0', '2024-04-25 22:16:27.8120',
        '2024-04-25 22:16:27.8120'),
       (651, '936499355525984256', 10, 38.074, '2024-04-25 22:17:27.7510', '0', '2024-04-25 22:17:27.7880',
        '2024-04-25 22:17:27.7880'),
       (652, '936499355525984256', 20, 82.442, '2024-04-25 22:17:27.8530', '0', '2024-04-25 22:17:27.8790',
        '2024-04-25 22:17:27.8790'),
       (653, '936499355525984256', 10, 36.638, '2024-04-25 22:18:27.7650', '0', '2024-04-25 22:18:27.8520',
        '2024-04-25 22:18:27.8520'),
       (654, '936499355525984256', 20, 82.633, '2024-04-25 22:18:27.9140', '0', '2024-04-25 22:18:27.9290',
        '2024-04-25 22:18:27.9290'),
       (655, '936499355525984256', 10, 30.759, '2024-04-25 22:19:57.7540', '0', '2024-04-25 22:19:57.9100',
        '2024-04-25 22:19:57.9100'),
       (656, '936499355525984256', 20, 82.457, '2024-04-25 22:19:58.0500', '0', '2024-04-25 22:19:58.0790',
        '2024-04-25 22:19:58.0790'),
       (657, '936499355525984256', 10, 36.603, '2024-04-25 22:20:57.7540', '0', '2024-04-25 22:20:58.0100',
        '2024-04-25 22:20:58.0100'),
       (658, '936499355525984256', 20, 83.197, '2024-04-25 22:20:58.2930', '0', '2024-04-25 22:20:58.3220',
        '2024-04-25 22:20:58.3220'),
       (659, '936499355525984256', 10, 29.759, '2024-04-25 22:21:57.7550', '0', '2024-04-25 22:21:57.9010',
        '2024-04-25 22:21:57.9020'),
       (660, '936499355525984256', 20, 82.913, '2024-04-25 22:21:59.0650', '0', '2024-04-25 22:21:59.0760',
        '2024-04-25 22:21:59.0760'),
       (661, '936499355525984256', 10, 31.974, '2024-04-25 22:23:27.7550', '0', '2024-04-25 22:23:27.9480',
        '2024-04-25 22:23:27.9480'),
       (662, '936499355525984256', 20, 82.844, '2024-04-25 22:23:28.0750', '0', '2024-04-25 22:23:28.1070',
        '2024-04-25 22:23:28.1070'),
       (663, '936499355525984256', 10, 31.732, '2024-04-25 22:24:27.7570', '0', '2024-04-25 22:24:27.8380',
        '2024-04-25 22:24:27.8380'),
       (664, '936499355525984256', 20, 82.429, '2024-04-25 22:24:57.7560', '0', '2024-04-25 22:24:57.8710',
        '2024-04-25 22:24:57.8710'),
       (665, '936499355525984256', 10, 34.146, '2024-04-25 22:25:27.7570', '0', '2024-04-25 22:25:27.9010',
        '2024-04-25 22:25:27.9010'),
       (666, '936499355525984256', 20, 82.215, '2024-04-25 22:25:57.7570', '0', '2024-04-25 22:25:57.8450',
        '2024-04-25 22:25:57.8450'),
       (667, '936499355525984256', 10, 21.405, '2024-04-25 22:26:27.7610', '0', '2024-04-25 22:26:27.9040',
        '2024-04-25 22:26:27.9040'),
       (668, '936499355525984256', 20, 82.848, '2024-04-25 22:26:57.7740', '0', '2024-04-25 22:26:57.9320',
        '2024-04-25 22:26:57.9320'),
       (669, '936499355525984256', 10, 33.013, '2024-04-25 22:27:27.7830', '0', '2024-04-25 22:27:27.8960',
        '2024-04-25 22:27:27.8960'),
       (670, '936499355525984256', 20, 82.821, '2024-04-25 22:28:27.7540', '0', '2024-04-25 22:28:27.8530',
        '2024-04-25 22:28:27.8530'),
       (671, '936499355525984256', 10, 36.46, '2024-04-25 22:28:57.7650', '0', '2024-04-25 22:28:57.8150',
        '2024-04-25 22:28:57.8150'),
       (672, '936499355525984256', 20, 82.574, '2024-04-25 22:29:27.7590', '0', '2024-04-25 22:29:27.8250',
        '2024-04-25 22:29:27.8250'),
       (673, '936499355525984256', 10, 22.686, '2024-04-25 22:30:27.7580', '0', '2024-04-25 22:30:27.8270',
        '2024-04-25 22:30:27.8270'),
       (674, '936499355525984256', 20, 83.127, '2024-04-25 22:30:27.8980', '0', '2024-04-25 22:30:27.9020',
        '2024-04-25 22:30:27.9020'),
       (675, '936499355525984256', 10, 35.303, '2024-04-25 22:31:27.7600', '0', '2024-04-25 22:31:27.8420',
        '2024-04-25 22:31:27.8420'),
       (676, '936499355525984256', 20, 83.248, '2024-04-25 22:31:27.9000', '0', '2024-04-25 22:31:27.9050',
        '2024-04-25 22:31:27.9050'),
       (677, '936499355525984256', 10, 37.163, '2024-04-25 22:32:27.7620', '0', '2024-04-25 22:32:27.8560',
        '2024-04-25 22:32:27.8560'),
       (678, '936499355525984256', 20, 82.621, '2024-04-25 22:32:27.9270', '0', '2024-04-25 22:32:27.9320',
        '2024-04-25 22:32:27.9320'),
       (679, '936499355525984256', 10, 45.333, '2024-04-25 22:33:27.7790', '0', '2024-04-25 22:33:27.9190',
        '2024-04-25 22:33:27.9190'),
       (680, '936499355525984256', 20, 82.541, '2024-04-25 22:33:28.0230', '0', '2024-04-25 22:33:28.0550',
        '2024-04-25 22:33:28.0550'),
       (681, '936499355525984256', 10, 39.213, '2024-04-25 22:34:57.7660', '0', '2024-04-25 22:34:57.8500',
        '2024-04-25 22:34:57.8500'),
       (682, '936499355525984256', 20, 82.701, '2024-04-25 22:34:57.8990', '0', '2024-04-25 22:34:57.9050',
        '2024-04-25 22:34:57.9050'),
       (683, '936499355525984256', 10, 41.048, '2024-04-25 22:35:57.7980', '0', '2024-04-25 22:35:57.9450',
        '2024-04-25 22:35:57.9450'),
       (684, '936499355525984256', 20, 82.897, '2024-04-25 22:35:58.0340', '0', '2024-04-25 22:35:58.0380',
        '2024-04-25 22:35:58.0380'),
       (685, '936499355525984256', 10, 29.579, '2024-04-25 22:37:27.7710', '0', '2024-04-25 22:37:28.0000',
        '2024-04-25 22:37:28.0000'),
       (686, '936499355525984256', 20, 82.307, '2024-04-25 22:37:28.0940', '0', '2024-04-25 22:37:28.0970',
        '2024-04-25 22:37:28.0970'),
       (687, '936499355525984256', 10, 25.334, '2024-04-25 22:38:57.7650', '0', '2024-04-25 22:38:57.8500',
        '2024-04-25 22:38:57.8500'),
       (688, '936499355525984256', 20, 82.275, '2024-04-25 22:38:57.9140', '0', '2024-04-25 22:38:57.9190',
        '2024-04-25 22:38:57.9190'),
       (689, '936499355525984256', 10, 28.017, '2024-04-25 22:39:57.7760', '0', '2024-04-25 22:39:57.8390',
        '2024-04-25 22:39:57.8390'),
       (690, '936499355525984256', 20, 83.007, '2024-04-25 22:40:27.7650', '0', '2024-04-25 22:40:27.8030',
        '2024-04-25 22:40:27.8030'),
       (691, '936499355525984256', 10, 17.503, '2024-04-25 22:41:27.7510', '0', '2024-04-25 22:41:27.7880',
        '2024-04-25 22:41:27.7880'),
       (692, '936499355525984256', 20, 83.059, '2024-04-25 22:41:27.9370', '0', '2024-04-25 22:41:27.9410',
        '2024-04-25 22:41:27.9410'),
       (693, '936499355525984256', 10, 22.054, '2024-04-25 22:42:57.7620', '0', '2024-04-25 22:42:57.8130',
        '2024-04-25 22:42:57.8130'),
       (694, '936499355525984256', 20, 81.664, '2024-04-25 22:42:57.8430', '0', '2024-04-25 22:42:57.8510',
        '2024-04-25 22:42:57.8510'),
       (695, '936499355525984256', 10, 21.914, '2024-04-25 22:58:54.8600', '0', '2024-04-25 22:58:55.0350',
        '2024-04-25 22:58:55.0350'),
       (696, '936499355525984256', 20, 83.101, '2024-04-25 22:58:55.3020', '0', '2024-04-25 22:58:55.3170',
        '2024-04-25 22:58:55.3170'),
       (697, '936499355525984256', 10, 18.059, '2024-04-25 23:04:37.8960', '0', '2024-04-25 23:04:38.0010',
        '2024-04-25 23:04:38.0010'),
       (698, '936499355525984256', 20, 82.639, '2024-04-25 23:04:38.0810', '0', '2024-04-25 23:04:38.0860',
        '2024-04-25 23:04:38.0860'),
       (699, '936499355525984256', 10, 24.49, '2024-04-26 00:30:42.3360', '0', '2024-04-26 00:30:42.4020',
        '2024-04-26 00:30:42.4020'),
       (700, '936499355525984256', 20, 82.942, '2024-04-26 00:30:42.4680', '0', '2024-04-26 00:30:42.4970',
        '2024-04-26 00:30:42.4970'),
       (701, '936499355525984256', 10, 23.654, '2024-04-26 02:10:15.7820', '0', '2024-04-26 02:10:15.8380',
        '2024-04-26 02:10:15.8380'),
       (702, '936499355525984256', 20, 83.014, '2024-04-26 02:10:15.9040', '0', '2024-04-26 02:10:15.9130',
        '2024-04-26 02:10:15.9130'),
       (703, '936499355525984256', 10, 21.826, '2024-04-26 03:32:58.7320', '0', '2024-04-26 03:32:58.7550',
        '2024-04-26 03:32:58.7550'),
       (704, '936499355525984256', 20, 83.026, '2024-04-26 03:32:59.4110', '0', '2024-04-26 03:32:59.4550',
        '2024-04-26 03:32:59.4550'),
       (705, '936499355525984256', 10, 23.105, '2024-04-26 03:53:44.3940', '0', '2024-04-26 03:53:44.4410',
        '2024-04-26 03:53:44.4410'),
       (706, '936499355525984256', 20, 82.981, '2024-04-26 03:53:44.4960', '0', '2024-04-26 03:53:44.5040',
        '2024-04-26 03:53:44.5040'),
       (707, '936499355525984256', 10, 22.066, '2024-04-26 04:43:13.3280', '0', '2024-04-26 04:43:13.6320',
        '2024-04-26 04:43:13.6320'),
       (708, '936499355525984256', 20, 83.012, '2024-04-26 04:43:13.7100', '0', '2024-04-26 04:43:13.7160',
        '2024-04-26 04:43:13.7160'),
       (709, '936499355525984256', 10, 22.66, '2024-04-26 06:05:59.9360', '0', '2024-04-26 06:06:00.0500',
        '2024-04-26 06:06:00.0500'),
       (710, '936499355525984256', 20, 83.078, '2024-04-26 06:06:00.1730', '0', '2024-04-26 06:06:00.1950',
        '2024-04-26 06:06:00.1950'),
       (711, '936499355525984256', 10, 21.47, '2024-04-26 07:45:28.2640', '0', '2024-04-26 07:45:28.4740',
        '2024-04-26 07:45:28.4740'),
       (712, '936499355525984256', 20, 82.979, '2024-04-26 07:45:28.6660', '0', '2024-04-26 07:45:28.6720',
        '2024-04-26 07:45:28.6720'),
       (713, '936499355525984256', 10, 23.161, '2024-04-26 08:01:12.4290', '0', '2024-04-26 08:01:12.4790',
        '2024-04-26 08:01:12.4790'),
       (714, '936499355525984256', 20, 82.99, '2024-04-26 08:01:12.5300', '0', '2024-04-26 08:01:12.5370',
        '2024-04-26 08:01:12.5370'),
       (715, '936499355525984256', 10, 19.604, '2024-04-26 08:04:42.0140', '0', '2024-04-26 08:04:42.1120',
        '2024-04-26 08:04:42.1120'),
       (716, '936499355525984256', 20, 82.988, '2024-04-26 08:05:42.2850', '0', '2024-04-26 08:05:42.3010',
        '2024-04-26 08:05:42.3010'),
       (717, '936499355525984256', 10, 18.757, '2024-04-26 08:09:11.1050', '0', '2024-04-26 08:10:11.1980',
        '2024-04-26 08:10:11.1980'),
       (718, '936499355525984256', 20, 81.737, '2024-04-26 08:10:11.2590', '0', '2024-04-26 08:10:11.2640',
        '2024-04-26 08:10:11.2640'),
       (719, '936499355525984256', 10, 22.29, '2024-04-26 08:14:39.1210', '0', '2024-04-26 08:14:39.1990',
        '2024-04-26 08:14:39.1990'),
       (720, '936499355525984256', 20, 83.046, '2024-04-26 08:14:39.3100', '0', '2024-04-26 08:14:39.3330',
        '2024-04-26 08:14:39.3330'),
       (721, '936499355525984256', 10, 18.541, '2024-04-26 08:18:08.9600', '0', '2024-04-26 08:18:09.0690',
        '2024-04-26 08:18:09.0690'),
       (722, '936499355525984256', 20, 82.542, '2024-04-26 08:19:09.1610', '0', '2024-04-26 08:19:09.2210',
        '2024-04-26 08:19:09.2210'),
       (723, '936499355525984256', 10, 18.635, '2024-04-26 08:22:37.0270', '0', '2024-04-26 08:23:37.1690',
        '2024-04-26 08:23:37.1690'),
       (724, '936499355525984256', 20, 81.755, '2024-04-26 08:23:37.3630', '0', '2024-04-26 08:23:37.4160',
        '2024-04-26 08:23:37.4160'),
       (725, '936499355525984256', 10, 17.484, '2024-04-26 08:28:05.1650', '0', '2024-04-26 08:28:05.3000',
        '2024-04-26 08:28:05.3000'),
       (726, '936499355525984256', 20, 82.962, '2024-04-26 08:28:06.0770', '0', '2024-04-26 08:28:06.1150',
        '2024-04-26 08:28:06.1150'),
       (727, '936499355525984256', 10, 17.477, '2024-04-26 08:32:34.1600', '0', '2024-04-26 08:32:34.2560',
        '2024-04-26 08:32:34.2560'),
       (728, '936499355525984256', 20, 83.016, '2024-04-26 08:32:34.3340', '0', '2024-04-26 08:32:34.3400',
        '2024-04-26 08:32:34.3400'),
       (729, '936499355525984256', 10, 19.909, '2024-04-26 08:48:59.0600', '0', '2024-04-26 08:48:59.1610',
        '2024-04-26 08:48:59.1610'),
       (730, '936499355525984256', 20, 82.972, '2024-04-26 08:48:59.2110', '0', '2024-04-26 08:48:59.2230',
        '2024-04-26 08:48:59.2230'),
       (731, '936499355525984256', 10, 21.352, '2024-04-26 09:08:22.7340', '0', '2024-04-26 09:08:22.8120',
        '2024-04-26 09:08:22.8120'),
       (732, '936499355525984256', 20, 83.067, '2024-04-26 09:08:22.8830', '0', '2024-04-26 09:08:22.9140',
        '2024-04-26 09:08:22.9140'),
       (733, '936499355525984256', 10, 24.341, '2024-04-26 10:28:48.2040', '0', '2024-04-26 10:28:48.3060',
        '2024-04-26 10:28:48.3060'),
       (734, '936499355525984256', 20, 82.98, '2024-04-26 10:28:51.3920', '0', '2024-04-26 10:28:51.4050',
        '2024-04-26 10:28:51.4050'),
       (735, '936499355525984256', 10, 22.424, '2024-04-26 11:51:20.2030', '0', '2024-04-26 11:51:20.2470',
        '2024-04-26 11:51:20.2470'),
       (736, '936499355525984256', 20, 82.997, '2024-04-26 11:51:20.2860', '0', '2024-04-26 11:51:20.2910',
        '2024-04-26 11:51:20.2910'),
       (737, '936499355525984256', 10, 25.337, '2024-04-26 13:28:27.8130', '0', '2024-04-26 13:28:27.8950',
        '2024-04-26 13:28:27.8950'),
       (738, '936499355525984256', 20, 82.98, '2024-04-26 13:28:31.0500', '0', '2024-04-26 13:28:31.0750',
        '2024-04-26 13:28:31.0750'),
       (739, '936499355525984256', 10, 24.867, '2024-04-26 14:48:56.8480', '0', '2024-04-26 14:48:56.9310',
        '2024-04-26 14:48:56.9310'),
       (740, '936499355525984256', 20, 82.885, '2024-04-26 14:48:57.0040', '0', '2024-04-26 14:48:57.0090',
        '2024-04-26 14:48:57.0090'),
       (741, '936499355525984256', 10, 21.178, '2024-04-26 16:30:25.1730', '0', '2024-04-26 16:30:25.2840',
        '2024-04-26 16:30:25.2840'),
       (742, '936499355525984256', 20, 83.025, '2024-04-26 16:30:25.3400', '0', '2024-04-26 16:30:25.3520',
        '2024-04-26 16:30:25.3520'),
       (743, '936499355525984256', 10, 20.746, '2024-04-26 17:54:26.3570', '0', '2024-04-26 17:54:26.3980',
        '2024-04-26 17:54:26.3980'),
       (744, '936499355525984256', 20, 82.97, '2024-04-26 17:54:26.4470', '0', '2024-04-26 17:54:26.4590',
        '2024-04-26 17:54:26.4590'),
       (745, '936499355525984256', 10, 21.664, '2024-04-26 19:34:05.9890', '0', '2024-04-26 19:34:06.1090',
        '2024-04-26 19:34:06.1090'),
       (746, '936499355525984256', 20, 83.001, '2024-04-26 19:34:06.1610', '0', '2024-04-26 19:34:06.1660',
        '2024-04-26 19:34:06.1660'),
       (747, '936499355525984256', 10, 28.081, '2024-04-26 20:52:26.8220', '0', '2024-04-26 20:52:26.8740',
        '2024-04-26 20:52:26.8740'),
       (748, '936499355525984256', 20, 83.022, '2024-04-26 20:52:26.9320', '0', '2024-04-26 20:52:26.9390',
        '2024-04-26 20:52:26.9390'),
       (749, '936499355525984256', 10, 23.974, '2024-04-26 22:33:14.5940', '0', '2024-04-26 22:33:14.6720',
        '2024-04-26 22:33:14.6720'),
       (750, '936499355525984256', 20, 82.98, '2024-04-26 22:33:14.7530', '0', '2024-04-26 22:33:14.7580',
        '2024-04-26 22:33:14.7580'),
       (751, '936499355525984256', 10, 23.238, '2024-04-26 23:53:59.4880', '0', '2024-04-26 23:53:59.5220',
        '2024-04-26 23:53:59.5220'),
       (752, '936499355525984256', 20, 83.015, '2024-04-26 23:53:59.5950', '0', '2024-04-26 23:53:59.6000',
        '2024-04-26 23:53:59.6000'),
       (753, '936499355525984256', 10, 25.916, '2024-04-27 01:35:10.9710', '0', '2024-04-27 01:35:11.1070',
        '2024-04-27 01:35:11.1070'),
       (754, '936499355525984256', 20, 83.005, '2024-04-27 01:35:11.2070', '0', '2024-04-27 01:35:11.2180',
        '2024-04-27 01:35:11.2180'),
       (755, '936499355525984256', 10, 25.592, '2024-04-27 02:44:13.7490', '0', '2024-04-27 02:44:13.7940',
        '2024-04-27 02:44:13.7940'),
       (756, '936499355525984256', 20, 83.162, '2024-04-27 02:44:14.1500', '0', '2024-04-27 02:44:14.1580',
        '2024-04-27 02:44:14.1580'),
       (757, '936499355525984256', 10, 16.802, '2024-04-27 03:02:10.9900', '0', '2024-04-27 03:02:11.0850',
        '2024-04-27 03:02:11.0850'),
       (758, '936499355525984256', 20, 83.209, '2024-04-27 03:02:11.1620', '0', '2024-04-27 03:02:11.1800',
        '2024-04-27 03:02:11.1800'),
       (759, '936499355525984256', 10, 24.069, '2024-04-27 03:08:15.4320', '0', '2024-04-27 03:08:15.4890',
        '2024-04-27 03:08:15.4890'),
       (760, '936499355525984256', 20, 83.174, '2024-04-27 03:08:15.6270', '0', '2024-04-27 03:08:15.6350',
        '2024-04-27 03:08:15.6350'),
       (761, '936499355525984256', 10, 24.464, '2024-04-27 03:23:00.1070', '0', '2024-04-27 03:23:00.1910',
        '2024-04-27 03:23:00.1910'),
       (762, '936499355525984256', 20, 83.104, '2024-04-27 03:23:00.2470', '0', '2024-04-27 03:23:00.2510',
        '2024-04-27 03:23:00.2510'),
       (763, '936499355525984256', 10, 24.151, '2024-04-27 03:46:22.9220', '0', '2024-04-27 03:46:22.9980',
        '2024-04-27 03:46:22.9980'),
       (764, '936499355525984256', 20, 83.112, '2024-04-27 03:46:23.1100', '0', '2024-04-27 03:46:23.1210',
        '2024-04-27 03:46:23.1210'),
       (765, '936499355525984256', 10, 21.892, '2024-04-27 04:02:49.1890', '0', '2024-04-27 04:02:49.2730',
        '2024-04-27 04:02:49.2730'),
       (766, '936499355525984256', 20, 83.082, '2024-04-27 04:02:49.3760', '0', '2024-04-27 04:02:49.3870',
        '2024-04-27 04:02:49.3870'),
       (767, '936499355525984256', 10, 21.715, '2024-04-27 04:35:10.6290', '0', '2024-04-27 04:35:10.6860',
        '2024-04-27 04:35:10.6860'),
       (768, '936499355525984256', 20, 83.09, '2024-04-27 04:35:10.7360', '0', '2024-04-27 04:35:10.7520',
        '2024-04-27 04:35:10.7520'),
       (769, '936499355525984256', 10, 22.87, '2024-04-27 04:54:10.0110', '0', '2024-04-27 04:54:10.1220',
        '2024-04-27 04:54:10.1220'),
       (770, '936499355525984256', 20, 83.205, '2024-04-27 04:54:10.1790', '0', '2024-04-27 04:54:10.1860',
        '2024-04-27 04:54:10.1860'),
       (771, '936499355525984256', 10, 23.234, '2024-04-27 05:27:48.1950', '0', '2024-04-27 05:27:48.2800',
        '2024-04-27 05:27:48.2800'),
       (772, '936499355525984256', 20, 83.063, '2024-04-27 05:27:48.3440', '0', '2024-04-27 05:27:48.3490',
        '2024-04-27 05:27:48.3490'),
       (773, '936499355525984256', 10, 24.75, '2024-04-27 05:45:51.2590', '0', '2024-04-27 05:46:39.0430',
        '2024-04-27 05:46:39.0430'),
       (774, '936499355525984256', 20, 83.049, '2024-04-27 05:46:39.1060', '0', '2024-04-27 05:46:39.1140',
        '2024-04-27 05:46:39.1140'),
       (775, '936499355525984256', 10, 26.765, '2024-04-27 06:04:08.9910', '0', '2024-04-27 06:04:09.1250',
        '2024-04-27 06:04:09.1250'),
       (776, '936499355525984256', 20, 83.101, '2024-04-27 06:04:09.1900', '0', '2024-04-27 06:04:09.2000',
        '2024-04-27 06:04:09.2000'),
       (777, '936499355525984256', 10, 23.672, '2024-04-27 06:53:52.4750', '0', '2024-04-27 06:53:52.5720',
        '2024-04-27 06:53:52.5720'),
       (778, '936499355525984256', 20, 83.041, '2024-04-27 06:53:52.6320', '0', '2024-04-27 06:53:52.6360',
        '2024-04-27 06:53:52.6360'),
       (779, '936499355525984256', 10, 21.808, '2024-04-27 07:28:11.5830', '0', '2024-04-27 07:28:11.6840',
        '2024-04-27 07:28:11.6840'),
       (780, '936499355525984256', 20, 83.267, '2024-04-27 07:28:11.7320', '0', '2024-04-27 07:28:11.7370',
        '2024-04-27 07:28:11.7370'),
       (781, '936499355525984256', 10, 22.007, '2024-04-27 07:46:18.6250', '0', '2024-04-27 07:46:18.6890',
        '2024-04-27 07:46:18.6890'),
       (782, '936499355525984256', 20, 83.129, '2024-04-27 07:46:18.7640', '0', '2024-04-27 07:46:18.7750',
        '2024-04-27 07:46:18.7750'),
       (783, '936499355525984256', 10, 22.858, '2024-04-27 08:05:23.1080', '0', '2024-04-27 08:05:23.1630',
        '2024-04-27 08:05:23.1630'),
       (784, '936499355525984256', 20, 83.179, '2024-04-27 08:05:23.2250', '0', '2024-04-27 08:05:23.2370',
        '2024-04-27 08:05:23.2370'),
       (785, '936499355525984256', 10, 28.064, '2024-04-27 08:37:55.4310', '0', '2024-04-27 08:37:55.5420',
        '2024-04-27 08:37:55.5420'),
       (786, '936499355525984256', 20, 83.222, '2024-04-27 08:37:55.6050', '0', '2024-04-27 08:37:55.6140',
        '2024-04-27 08:37:55.6140'),
       (787, '936499355525984256', 10, 23.445, '2024-04-27 09:26:58.4160', '0', '2024-04-27 09:26:58.5240',
        '2024-04-27 09:26:58.5240'),
       (788, '936499355525984256', 20, 83.155, '2024-04-27 09:26:58.5810', '0', '2024-04-27 09:26:58.5890',
        '2024-04-27 09:26:58.5890'),
       (789, '936499355525984256', 10, 23.774, '2024-04-27 09:45:06.4660', '0', '2024-04-27 09:45:06.5530',
        '2024-04-27 09:45:06.5530'),
       (790, '936499355525984256', 20, 83.197, '2024-04-27 09:45:06.6360', '0', '2024-04-27 09:45:06.6530',
        '2024-04-27 09:45:06.6530'),
       (791, '936499355525984256', 10, 23.431, '2024-04-27 10:17:59.8060', '0', '2024-04-27 10:17:59.9050',
        '2024-04-27 10:17:59.9050'),
       (792, '936499355525984256', 20, 83.157, '2024-04-27 10:17:59.9610', '0', '2024-04-27 10:17:59.9770',
        '2024-04-27 10:17:59.9770'),
       (793, '936499355525984256', 10, 20.453, '2024-04-27 10:35:47.7580', '0', '2024-04-27 10:35:47.8320',
        '2024-04-27 10:35:47.8320'),
       (794, '936499355525984256', 20, 83.193, '2024-04-27 10:35:47.9270', '0', '2024-04-27 10:35:47.9500',
        '2024-04-27 10:35:47.9500'),
       (795, '936499355525984256', 10, 26.902, '2024-04-27 10:52:52.0260', '0', '2024-04-27 10:52:52.2170',
        '2024-04-27 10:52:52.2170'),
       (796, '936499355525984256', 20, 83.204, '2024-04-27 10:52:52.3680', '0', '2024-04-27 10:52:52.3990',
        '2024-04-27 10:52:52.3990'),
       (797, '936499355525984256', 10, 30.802, '2024-04-27 11:43:31.2670', '0', '2024-04-27 11:43:31.3100',
        '2024-04-27 11:43:31.3100'),
       (798, '936499355525984256', 20, 83.189, '2024-04-27 11:43:31.3940', '0', '2024-04-27 11:43:31.4010',
        '2024-04-27 11:43:31.4010'),
       (799, '936499355525984256', 10, 20.929, '2024-04-27 12:02:18.8450', '0', '2024-04-27 12:02:18.8660',
        '2024-04-27 12:02:18.8660'),
       (800, '936499355525984256', 20, 83.088, '2024-04-27 12:02:18.9080', '0', '2024-04-27 12:02:18.9130',
        '2024-04-27 12:02:18.9130'),
       (801, '936499355525984256', 10, 19.474, '2024-04-27 12:19:56.0510', '0', '2024-04-27 12:19:56.1520',
        '2024-04-27 12:19:56.1520'),
       (802, '936499355525984256', 20, 83.131, '2024-04-27 12:19:56.2020', '0', '2024-04-27 12:19:56.2060',
        '2024-04-27 12:19:56.2060'),
       (803, '936499355525984256', 10, 19.483, '2024-04-27 12:53:32.0950', '0', '2024-04-27 12:53:32.1110',
        '2024-04-27 12:53:32.1110'),
       (804, '936499355525984256', 20, 83.088, '2024-04-27 12:53:32.1620', '0', '2024-04-27 12:53:32.1680',
        '2024-04-27 12:53:32.1680'),
       (805, '936499355525984256', 10, 18.643, '2024-04-27 13:12:02.4910', '0', '2024-04-27 13:12:02.5310',
        '2024-04-27 13:12:02.5310'),
       (806, '936499355525984256', 20, 83.125, '2024-04-27 13:12:02.5790', '0', '2024-04-27 13:12:02.5830',
        '2024-04-27 13:12:02.5830'),
       (807, '936499355525984256', 10, 24.582, '2024-04-27 13:48:42.5390', '0', '2024-04-27 13:48:42.5700',
        '2024-04-27 13:48:42.5700'),
       (808, '936499355525984256', 20, 83.109, '2024-04-27 13:48:42.6120', '0', '2024-04-27 13:48:42.6180',
        '2024-04-27 13:48:42.6180'),
       (809, '936499355525984256', 10, 32.388, '2024-04-27 14:03:46.6490', '0', '2024-04-27 14:03:46.7270',
        '2024-04-27 14:03:46.7270'),
       (810, '936499355525984256', 20, 83.632, '2024-04-27 14:03:46.8510', '0', '2024-04-27 14:03:46.8590',
        '2024-04-27 14:03:46.8590'),
       (811, '936499355525984256', 10, 24.297, '2024-04-27 14:04:46.6510', '0', '2024-04-27 14:04:46.7280',
        '2024-04-27 14:04:46.7280'),
       (812, '936499355525984256', 20, 83.368, '2024-04-27 14:04:46.8680', '0', '2024-04-27 14:04:46.8790',
        '2024-04-27 14:04:46.8790'),
       (813, '936499355525984256', 10, 27.779, '2024-04-27 14:06:16.6420', '0', '2024-04-27 14:06:16.7330',
        '2024-04-27 14:06:16.7330'),
       (814, '936499355525984256', 20, 83.377, '2024-04-27 14:06:16.7800', '0', '2024-04-27 14:06:16.7890',
        '2024-04-27 14:06:16.7890'),
       (815, '936499355525984256', 10, 34.635, '2024-04-27 14:07:46.6440', '0', '2024-04-27 14:07:46.7250',
        '2024-04-27 14:07:46.7250'),
       (816, '936499355525984256', 20, 83.299, '2024-04-27 14:07:46.9130', '0', '2024-04-27 14:07:46.9180',
        '2024-04-27 14:07:46.9180'),
       (817, '936499355525984256', 10, 29.765, '2024-04-27 14:09:16.6810', '0', '2024-04-27 14:09:16.7550',
        '2024-04-27 14:09:16.7550'),
       (818, '936499355525984256', 20, 83.147, '2024-04-27 14:09:16.8250', '0', '2024-04-27 14:09:16.8300',
        '2024-04-27 14:09:16.8300'),
       (819, '936499355525984256', 10, 21.821, '2024-04-27 14:12:51.9490', '0', '2024-04-27 14:12:52.2990',
        '2024-04-27 14:12:52.2990'),
       (820, '936499355525984256', 20, 83.103, '2024-04-27 14:12:52.4610', '0', '2024-04-27 14:12:52.4670',
        '2024-04-27 14:12:52.4670'),
       (821, '936499355525984256', 10, 29.82, '2024-04-27 14:14:21.7790', '0', '2024-04-27 14:14:21.9290',
        '2024-04-27 14:14:21.9290'),
       (822, '936499355525984256', 20, 83.151, '2024-04-27 14:14:22.1240', '0', '2024-04-27 14:14:22.1300',
        '2024-04-27 14:14:22.1300'),
       (823, '936499355525984256', 10, 29.888, '2024-04-27 14:15:21.7820', '0', '2024-04-27 14:15:21.8320',
        '2024-04-27 14:15:21.8320'),
       (824, '936499355525984256', 20, 83.123, '2024-04-27 14:15:23.0710', '0', '2024-04-27 14:15:23.0820',
        '2024-04-27 14:15:23.0820'),
       (825, '936499355525984256', 10, 36.829, '2024-04-27 14:16:51.8200', '0', '2024-04-27 14:16:51.8850',
        '2024-04-27 14:16:51.8850'),
       (826, '936499355525984256', 20, 81.873, '2024-04-27 14:16:51.9370', '0', '2024-04-27 14:16:51.9400',
        '2024-04-27 14:16:51.9400'),
       (827, '936499355525984256', 10, 31.865, '2024-04-27 14:18:21.7770', '0', '2024-04-27 14:18:21.8480',
        '2024-04-27 14:18:21.8480'),
       (828, '936499355525984256', 20, 83.266, '2024-04-27 14:18:25.0320', '0', '2024-04-27 14:18:25.0430',
        '2024-04-27 14:18:25.0430'),
       (829, '936499355525984256', 10, 32.508, '2024-04-27 14:19:21.7810', '0', '2024-04-27 14:19:21.8420',
        '2024-04-27 14:19:21.8420'),
       (830, '936499355525984256', 20, 83.159, '2024-04-27 14:19:51.7860', '0', '2024-04-27 14:19:51.8090',
        '2024-04-27 14:19:51.8090'),
       (831, '936499355525984256', 10, 34.024, '2024-04-27 14:20:21.7810', '0', '2024-04-27 14:20:21.7990',
        '2024-04-27 14:20:21.7990'),
       (832, '936499355525984256', 20, 83.181, '2024-04-27 14:20:51.7890', '0', '2024-04-27 14:20:51.8830',
        '2024-04-27 14:20:51.8830'),
       (833, '936499355525984256', 10, 27.536, '2024-04-27 14:21:51.7840', '0', '2024-04-27 14:21:51.8780',
        '2024-04-27 14:21:51.8780'),
       (834, '936499355525984256', 20, 82.331, '2024-04-27 14:21:51.9470', '0', '2024-04-27 14:21:51.9530',
        '2024-04-27 14:21:51.9530'),
       (835, '936499355525984256', 10, 26.23, '2024-04-27 14:23:21.7800', '0', '2024-04-27 14:23:21.9140',
        '2024-04-27 14:23:21.9140'),
       (836, '936499355525984256', 20, 83.194, '2024-04-27 14:23:21.9910', '0', '2024-04-27 14:23:21.9950',
        '2024-04-27 14:23:21.9950'),
       (837, '936499355525984256', 10, 26.745, '2024-04-27 14:24:51.7850', '0', '2024-04-27 14:24:51.9150',
        '2024-04-27 14:24:51.9150'),
       (838, '936499355525984256', 20, 83.176, '2024-04-27 14:24:51.9990', '0', '2024-04-27 14:24:52.0040',
        '2024-04-27 14:24:52.0040'),
       (839, '936499355525984256', 10, 26.083, '2024-04-27 14:26:21.7870', '0', '2024-04-27 14:26:21.8990',
        '2024-04-27 14:26:21.8990'),
       (840, '936499355525984256', 20, 83.112, '2024-04-27 14:26:22.0130', '0', '2024-04-27 14:26:22.0320',
        '2024-04-27 14:26:22.0320'),
       (841, '936499355525984256', 10, 29.115, '2024-04-27 14:27:51.7830', '0', '2024-04-27 14:27:51.9280',
        '2024-04-27 14:27:51.9280'),
       (842, '936499355525984256', 20, 83.176, '2024-04-27 14:27:52.0110', '0', '2024-04-27 14:27:52.0240',
        '2024-04-27 14:27:52.0240'),
       (843, '936499355525984256', 10, 25.668, '2024-04-27 14:29:21.7780', '0', '2024-04-27 14:29:21.8660',
        '2024-04-27 14:29:21.8660'),
       (844, '936499355525984256', 20, 82.936, '2024-04-27 14:29:21.9400', '0', '2024-04-27 14:29:21.9440',
        '2024-04-27 14:29:21.9440'),
       (845, '936499355525984256', 10, 25.95, '2024-04-27 14:30:51.7750', '0', '2024-04-27 14:30:51.9140',
        '2024-04-27 14:30:51.9140'),
       (846, '936499355525984256', 20, 83.064, '2024-04-27 14:30:52.0070', '0', '2024-04-27 14:30:52.0200',
        '2024-04-27 14:30:52.0200'),
       (847, '936499355525984256', 10, 25.692, '2024-04-27 14:31:51.7760', '0', '2024-04-27 14:31:51.8050',
        '2024-04-27 14:31:51.8050'),
       (848, '936499355525984256', 20, 83.039, '2024-04-27 14:32:21.7770', '0', '2024-04-27 14:32:21.8180',
        '2024-04-27 14:32:21.8180'),
       (849, '936499355525984256', 10, 26.217, '2024-04-27 14:32:51.7800', '0', '2024-04-27 14:32:51.8080',
        '2024-04-27 14:32:51.8080'),
       (850, '936499355525984256', 20, 83.015, '2024-04-27 14:33:21.7820', '0', '2024-04-27 14:33:21.8320',
        '2024-04-27 14:33:21.8320'),
       (851, '936499355525984256', 10, 25.916, '2024-04-27 14:34:21.7730', '0', '2024-04-27 14:34:21.8150',
        '2024-04-27 14:34:21.8150'),
       (852, '936499355525984256', 20, 83.004, '2024-04-27 14:34:21.8820', '0', '2024-04-27 14:34:21.8860',
        '2024-04-27 14:34:21.8860'),
       (853, '936499355525984256', 10, 25.388, '2024-04-27 14:35:51.7730', '0', '2024-04-27 14:35:51.8250',
        '2024-04-27 14:35:51.8250'),
       (854, '936499355525984256', 20, 82.969, '2024-04-27 14:35:51.8700', '0', '2024-04-27 14:35:51.8790',
        '2024-04-27 14:35:51.8790'),
       (855, '936499355525984256', 10, 25.843, '2024-04-27 14:36:51.7820', '0', '2024-04-27 14:36:51.8510',
        '2024-04-27 14:36:51.8510'),
       (856, '936499355525984256', 20, 83.055, '2024-04-27 14:36:52.0080', '0', '2024-04-27 14:36:52.0190',
        '2024-04-27 14:36:52.0190'),
       (857, '936499355525984256', 10, 25.637, '2024-04-27 14:37:51.7820', '0', '2024-04-27 14:37:51.8320',
        '2024-04-27 14:37:51.8320'),
       (858, '936499355525984256', 20, 82.977, '2024-04-27 14:38:21.7750', '0', '2024-04-27 14:38:21.8370',
        '2024-04-27 14:38:21.8370'),
       (859, '936499355525984256', 10, 25.581, '2024-04-27 14:39:21.7720', '0', '2024-04-27 14:39:21.8390',
        '2024-04-27 14:39:21.8390'),
       (860, '936499355525984256', 20, 82.984, '2024-04-27 14:39:21.9200', '0', '2024-04-27 14:39:21.9290',
        '2024-04-27 14:39:21.9290'),
       (861, '936499355525984256', 10, 26.826, '2024-04-27 14:40:51.7150', '0', '2024-04-27 14:40:51.8110',
        '2024-04-27 14:40:51.8110'),
       (862, '936499355525984256', 20, 82.922, '2024-04-27 14:40:51.8660', '0', '2024-04-27 14:40:51.8700',
        '2024-04-27 14:40:51.8700'),
       (863, '936499355525984256', 10, 26.344, '2024-04-27 14:41:51.7160', '0', '2024-04-27 14:41:51.7450',
        '2024-04-27 14:41:51.7450'),
       (864, '936499355525984256', 20, 82.985, '2024-04-27 14:42:21.7170', '0', '2024-04-27 14:42:21.7720',
        '2024-04-27 14:42:21.7720'),
       (865, '936499355525984256', 10, 26.598, '2024-04-27 14:42:51.7310', '0', '2024-04-27 14:42:51.7540',
        '2024-04-27 14:42:51.7540'),
       (866, '936499355525984256', 20, 82.971, '2024-04-27 14:43:21.7290', '0', '2024-04-27 14:43:21.7460',
        '2024-04-27 14:43:21.7460'),
       (867, '936499355525984256', 10, 25.548, '2024-04-27 14:44:21.7160', '0', '2024-04-27 14:44:21.7630',
        '2024-04-27 14:44:21.7630'),
       (868, '936499355525984256', 20, 82.9, '2024-04-27 14:44:21.8640', '0', '2024-04-27 14:44:21.8700',
        '2024-04-27 14:44:21.8700'),
       (869, '936499355525984256', 10, 26.583, '2024-04-27 14:45:51.7090', '0', '2024-04-27 14:45:51.8240',
        '2024-04-27 14:45:51.8240'),
       (870, '936499355525984256', 20, 82.611, '2024-04-27 14:45:52.9780', '0', '2024-04-27 14:45:52.9830',
        '2024-04-27 14:45:52.9830'),
       (871, '936499355525984256', 10, 26.214, '2024-04-27 14:47:21.7060', '0', '2024-04-27 14:47:21.8590',
        '2024-04-27 14:47:21.8590'),
       (872, '936499355525984256', 20, 82.932, '2024-04-27 14:47:21.9120', '0', '2024-04-27 14:47:21.9170',
        '2024-04-27 14:47:21.9170'),
       (873, '936499355525984256', 10, 26.339, '2024-04-27 14:48:21.7060', '0', '2024-04-27 14:48:21.7770',
        '2024-04-27 14:48:21.7770'),
       (874, '936499355525984256', 20, 82.99, '2024-04-27 14:48:51.7020', '0', '2024-04-27 14:48:51.7200',
        '2024-04-27 14:48:51.7200'),
       (875, '936499355525984256', 10, 26.829, '2024-04-27 14:49:51.7020', '0', '2024-04-27 14:49:51.8430',
        '2024-04-27 14:49:51.8430'),
       (876, '936499355525984256', 20, 82.988, '2024-04-27 14:49:51.9060', '0', '2024-04-27 14:49:51.9230',
        '2024-04-27 14:49:51.9230'),
       (877, '936499355525984256', 10, 26.543, '2024-04-27 14:50:51.7060', '0', '2024-04-27 14:50:51.7340',
        '2024-04-27 14:50:51.7340'),
       (878, '936499355525984256', 20, 82.975, '2024-04-27 14:51:21.6970', '0', '2024-04-27 14:51:21.7200',
        '2024-04-27 14:51:21.7200'),
       (879, '936499355525984256', 10, 27.717, '2024-04-27 14:52:21.7020', '0', '2024-04-27 14:52:21.7420',
        '2024-04-27 14:52:21.7420'),
       (880, '936499355525984256', 20, 83.01, '2024-04-27 14:52:21.8350', '0', '2024-04-27 14:52:21.8460',
        '2024-04-27 14:52:21.8460'),
       (881, '936499355525984256', 10, 47.957, '2024-05-06 22:36:16.9450', '0', '2024-05-06 22:36:17.2950',
        '2024-05-06 22:36:17.2960'),
       (882, '936499355525984256', 20, 83.545, '2024-05-06 22:36:18.0490', '0', '2024-05-06 22:36:18.0990',
        '2024-05-06 22:36:18.0990'),
       (883, '936499355525984256', 10, 30.255, '2024-05-06 22:38:11.6970', '0', '2024-05-06 22:38:11.9440',
        '2024-05-06 22:38:11.9440'),
       (884, '936499355525984256', 20, 83.428, '2024-05-06 22:38:12.1030', '0', '2024-05-06 22:38:12.1130',
        '2024-05-06 22:38:12.1130'),
       (885, '936499355525984256', 10, 32.217, '2024-05-06 22:55:22.1740', '0', '2024-05-06 22:55:22.3960',
        '2024-05-06 22:55:22.3960'),
       (886, '936499355525984256', 20, 83.498, '2024-05-06 22:55:22.5330', '0', '2024-05-06 22:55:22.5420',
        '2024-05-06 22:55:22.5420'),
       (887, '936499355525984256', 10, 39.611, '2024-05-06 23:32:52.6530', '0', '2024-05-06 23:32:52.8280',
        '2024-05-06 23:32:52.8280'),
       (888, '936499355525984256', 20, 83.392, '2024-05-06 23:32:52.9770', '0', '2024-05-06 23:32:53.0050',
        '2024-05-06 23:32:53.0050'),
       (889, '936499355525984256', 10, 24.867, '2024-05-06 23:33:57.2280', '0', '2024-05-06 23:33:57.2710',
        '2024-05-06 23:33:57.2710'),
       (890, '936499355525984256', 20, 83.526, '2024-05-06 23:33:57.3260', '0', '2024-05-06 23:33:57.3330',
        '2024-05-06 23:33:57.3330'),
       (891, '936499355525984256', 10, 20.345, '2024-05-06 23:52:23.4420', '0', '2024-05-06 23:52:23.6180',
        '2024-05-06 23:52:23.6180'),
       (892, '936499355525984256', 20, 83.362, '2024-05-06 23:52:23.7860', '0', '2024-05-06 23:52:23.7960',
        '2024-05-06 23:52:23.7960'),
       (893, '936499355525984256', 10, 20.851, '2024-05-07 00:10:19.4650', '0', '2024-05-07 00:10:19.6000',
        '2024-05-07 00:10:19.6000'),
       (894, '936499355525984256', 20, 83.356, '2024-05-07 00:10:19.6910', '0', '2024-05-07 00:10:19.7030',
        '2024-05-07 00:10:19.7030'),
       (895, '936499355525984256', 10, 24.783, '2024-05-07 01:31:25.1180', '0', '2024-05-07 01:31:25.1740',
        '2024-05-07 01:31:25.1740'),
       (896, '936499355525984256', 20, 83.366, '2024-05-07 01:31:25.2270', '0', '2024-05-07 01:31:25.2360',
        '2024-05-07 01:31:25.2360'),
       (897, '936499355525984256', 10, 22.078, '2024-05-07 02:50:12.1580', '0', '2024-05-07 02:50:12.3290',
        '2024-05-07 02:50:12.3300'),
       (898, '936499355525984256', 20, 83.385, '2024-05-07 02:50:12.4570', '0', '2024-05-07 02:50:12.4640',
        '2024-05-07 02:50:12.4640'),
       (899, '936499355525984256', 10, 22.731, '2024-05-07 04:15:31.9830', '0', '2024-05-07 04:15:32.0260',
        '2024-05-07 04:15:32.0260'),
       (900, '936499355525984256', 20, 83.2, '2024-05-07 04:15:32.1020', '0', '2024-05-07 04:15:32.1290',
        '2024-05-07 04:15:32.1290'),
       (901, '936499355525984256', 10, 22.286, '2024-05-07 05:44:27.7870', '0', '2024-05-07 05:44:27.9920',
        '2024-05-07 05:44:27.9920'),
       (902, '936499355525984256', 20, 83.378, '2024-05-07 05:44:28.1380', '0', '2024-05-07 05:44:28.1500',
        '2024-05-07 05:44:28.1500'),
       (903, '936499355525984256', 10, 25.796, '2024-05-07 05:50:09.6860', '0', '2024-05-07 05:50:09.8100',
        '2024-05-07 05:50:09.8100'),
       (904, '936499355525984256', 20, 83.324, '2024-05-07 05:50:10.0050', '0', '2024-05-07 05:50:10.0110',
        '2024-05-07 05:50:10.0110'),
       (905, '936499355525984256', 10, 21.432, '2024-05-07 05:55:22.6730', '0', '2024-05-07 05:55:22.7610',
        '2024-05-07 05:55:22.7610'),
       (906, '936499355525984256', 20, 83.381, '2024-05-07 05:55:22.8360', '0', '2024-05-07 05:55:22.8430',
        '2024-05-07 05:55:22.8430'),
       (907, '936499355525984256', 10, 31.66, '2024-05-07 06:29:36.5110', '0', '2024-05-07 06:29:36.6610',
        '2024-05-07 06:29:36.6620'),
       (908, '936499355525984256', 20, 83.56, '2024-05-07 06:29:36.8180', '0', '2024-05-07 06:29:36.8370',
        '2024-05-07 06:29:36.8370'),
       (909, '936499355525984256', 10, 25.045, '2024-05-07 06:34:50.5000', '0', '2024-05-07 06:34:50.7900',
        '2024-05-07 06:34:50.7900'),
       (910, '936499355525984256', 20, 83.549, '2024-05-07 06:34:50.9070', '0', '2024-05-07 06:34:50.9170',
        '2024-05-07 06:34:50.9170'),
       (911, '936499355525984256', 10, 24.373, '2024-05-07 06:51:03.4300', '0', '2024-05-07 07:07:30.3240',
        '2024-05-07 07:07:30.3240'),
       (912, '936499355525984256', 20, 83.491, '2024-05-07 07:07:30.6940', '0', '2024-05-07 07:07:30.7380',
        '2024-05-07 07:07:30.7380'),
       (913, '936499355525984256', 10, 25.096, '2024-05-07 07:23:16.0530', '0', '2024-05-07 07:23:16.2770',
        '2024-05-07 07:23:16.2770'),
       (914, '936499355525984256', 20, 83.646, '2024-05-07 07:23:16.4200', '0', '2024-05-07 07:23:16.4570',
        '2024-05-07 07:23:16.4570'),
       (915, '936499355525984256', 10, 26.17, '2024-05-07 07:57:00.7800', '0', '2024-05-07 07:57:00.8610',
        '2024-05-07 07:57:00.8610'),
       (916, '936499355525984256', 20, 83.695, '2024-05-07 07:57:00.9240', '0', '2024-05-07 07:57:00.9580',
        '2024-05-07 07:57:00.9580'),
       (917, '936499355525984256', 10, 23.026, '2024-05-07 08:17:05.4450', '0', '2024-05-07 08:17:05.6440',
        '2024-05-07 08:17:05.6440'),
       (918, '936499355525984256', 20, 82.614, '2024-05-07 08:27:15.5810', '0', '2024-05-07 08:27:15.6880',
        '2024-05-07 08:27:15.6880'),
       (919, '936499355525984256', 10, 22.885, '2024-05-07 08:27:44.6710', '0', '2024-05-07 08:27:44.8010',
        '2024-05-07 08:27:44.8010'),
       (920, '936499355525984256', 20, 82.267, '2024-05-07 08:44:01.2810', '0', '2024-05-07 08:44:01.3630',
        '2024-05-07 08:44:01.3630'),
       (921, '936499355525984256', 10, 25.787, '2024-05-07 09:02:05.6150', '0', '2024-05-07 09:02:05.9420',
        '2024-05-07 09:02:05.9420'),
       (922, '936499355525984256', 20, 83.546, '2024-05-07 09:02:06.0970', '0', '2024-05-07 09:02:06.1220',
        '2024-05-07 09:02:06.1220'),
       (923, '936499355525984256', 10, 24.612, '2024-05-07 09:34:58.3540', '0', '2024-05-07 09:34:58.6060',
        '2024-05-07 09:34:58.6060'),
       (924, '936499355525984256', 20, 83.526, '2024-05-07 09:34:58.8280', '0', '2024-05-07 09:34:58.8930',
        '2024-05-07 09:34:58.8930'),
       (925, '936499355525984256', 10, 23.854, '2024-05-07 09:40:41.3460', '0', '2024-05-07 09:40:41.4900',
        '2024-05-07 09:40:41.4900'),
       (926, '936499355525984256', 20, 83.543, '2024-05-07 09:40:41.6400', '0', '2024-05-07 09:40:41.6470',
        '2024-05-07 09:40:41.6470'),
       (927, '936499355525984256', 10, 21.481, '2024-05-07 09:45:54.2660', '0', '2024-05-07 09:45:54.3310',
        '2024-05-07 09:45:54.3310'),
       (928, '936499355525984256', 20, 83.537, '2024-05-07 09:45:54.3880', '0', '2024-05-07 09:45:54.4000',
        '2024-05-07 09:45:54.4000'),
       (929, '936499355525984256', 10, 23.885, '2024-05-07 09:51:06.6790', '0', '2024-05-07 09:51:06.7850',
        '2024-05-07 09:51:06.7850'),
       (930, '936499355525984256', 20, 83.668, '2024-05-07 09:51:06.9740', '0', '2024-05-07 09:51:06.9820',
        '2024-05-07 09:51:06.9820'),
       (931, '936499355525984256', 10, 21.09, '2024-05-07 09:56:19.5860', '0', '2024-05-07 09:56:19.6630',
        '2024-05-07 09:56:19.6630'),
       (932, '936499355525984256', 20, 83.524, '2024-05-07 09:56:19.7720', '0', '2024-05-07 09:56:19.7760',
        '2024-05-07 09:56:19.7760'),
       (933, '936499355525984256', 10, 31.162, '2024-05-07 10:02:02.4710', '0', '2024-05-07 10:02:02.6640',
        '2024-05-07 10:02:02.6640'),
       (934, '936499355525984256', 20, 83.486, '2024-05-07 10:02:02.8620', '0', '2024-05-07 10:02:02.8680',
        '2024-05-07 10:02:02.8680'),
       (935, '936499355525984256', 10, 25.74, '2024-05-07 10:07:15.1750', '0', '2024-05-07 10:07:15.3950',
        '2024-05-07 10:07:15.3950'),
       (936, '936499355525984256', 20, 83.545, '2024-05-07 10:07:15.5140', '0', '2024-05-07 10:07:15.5190',
        '2024-05-07 10:07:15.5190'),
       (937, '936499355525984256', 10, 18.807, '2024-05-07 10:12:29.0740', '0', '2024-05-07 10:12:29.1550',
        '2024-05-07 10:12:29.1550'),
       (938, '936499355525984256', 20, 83.537, '2024-05-07 10:12:29.2370', '0', '2024-05-07 10:12:29.2430',
        '2024-05-07 10:12:29.2430'),
       (939, '936499355525984256', 10, 19.001, '2024-05-07 10:22:24.9520', '0', '2024-05-07 10:22:25.2090',
        '2024-05-07 10:22:25.2090'),
       (940, '936499355525984256', 20, 83.586, '2024-05-07 10:22:25.3540', '0', '2024-05-07 10:22:25.3760',
        '2024-05-07 10:22:25.3760'),
       (941, '936499355525984256', 10, 27.404, '2024-05-07 10:28:44.9380', '0', '2024-05-07 10:28:45.0780',
        '2024-05-07 10:28:45.0780'),
       (942, '936499355525984256', 20, 83.563, '2024-05-07 10:28:45.1880', '0', '2024-05-07 10:28:45.1990',
        '2024-05-07 10:28:45.1990'),
       (943, '936499355525984256', 10, 17.825, '2024-05-07 10:33:57.9260', '0', '2024-05-07 10:33:57.9720',
        '2024-05-07 10:33:57.9720'),
       (944, '936499355525984256', 20, 83.571, '2024-05-07 10:33:58.1340', '0', '2024-05-07 10:33:58.1430',
        '2024-05-07 10:33:58.1430'),
       (945, '936499355525984256', 10, 16.894, '2024-05-07 10:39:40.9730', '0', '2024-05-07 10:39:41.1790',
        '2024-05-07 10:39:41.1790'),
       (946, '936499355525984256', 20, 83.615, '2024-05-07 10:39:41.3180', '0', '2024-05-07 10:39:41.3340',
        '2024-05-07 10:39:41.3340'),
       (947, '936499355525984256', 10, 21.145, '2024-05-07 11:29:37.2750', '0', '2024-05-07 11:29:37.3680',
        '2024-05-07 11:29:37.3680'),
       (948, '936499355525984256', 20, 83.579, '2024-05-07 11:29:37.5010', '0', '2024-05-07 11:29:37.5060',
        '2024-05-07 11:29:37.5060'),
       (949, '936499355525984256', 10, 18.648, '2024-05-07 11:52:46.2540', '0', '2024-05-07 11:52:46.5410',
        '2024-05-07 11:52:46.5410'),
       (950, '936499355525984256', 20, 83.579, '2024-05-07 11:52:46.6910', '0', '2024-05-07 11:52:46.7010',
        '2024-05-07 11:52:46.7010'),
       (951, '936499355525984256', 10, 24.3, '2024-05-07 13:16:40.0690', '0', '2024-05-07 13:16:40.1510',
        '2024-05-07 13:16:40.1510'),
       (952, '936499355525984256', 20, 83.418, '2024-05-07 13:34:09.4280', '0', '2024-05-07 13:34:09.5130',
        '2024-05-07 13:34:09.5130'),
       (953, '936499355525984256', 10, 25.614, '2024-05-07 14:53:00.5640', '0', '2024-05-07 14:53:00.8760',
        '2024-05-07 14:53:00.8760'),
       (954, '936499355525984256', 20, 83.583, '2024-05-07 14:53:01.0780', '0', '2024-05-07 14:53:01.1010',
        '2024-05-07 14:53:01.1010'),
       (955, '936499355525984256', 10, 44.459, '2024-05-07 16:16:40.3080', '0', '2024-05-07 16:16:40.7030',
        '2024-05-07 16:16:40.7030'),
       (956, '936499355525984256', 20, 81.777, '2024-05-07 16:32:17.3750', '0', '2024-05-07 16:32:17.4110',
        '2024-05-07 16:32:17.4110'),
       (957, '936499355525984256', 10, 38.668, '2024-05-07 17:55:30.6500', '0', '2024-05-07 17:55:30.7590',
        '2024-05-07 17:55:30.7590'),
       (958, '936499355525984256', 20, 83.529, '2024-05-07 17:55:30.9330', '0', '2024-05-07 17:55:30.9390',
        '2024-05-07 17:55:30.9390'),
       (959, '936499355525984256', 10, 24.497, '2024-05-07 19:18:08.7960', '0', '2024-05-07 19:18:08.8880',
        '2024-05-07 19:18:08.8880'),
       (960, '936499355525984256', 20, 83.489, '2024-05-07 19:33:33.4120', '0', '2024-05-07 19:33:33.5300',
        '2024-05-07 19:33:33.5300'),
       (961, '936499355525984256', 10, 23.998, '2024-05-07 20:56:59.2440', '0', '2024-05-07 20:56:59.2960',
        '2024-05-07 20:56:59.2960'),
       (962, '936499355525984256', 20, 83.357, '2024-05-07 20:56:59.3920', '0', '2024-05-07 20:56:59.4050',
        '2024-05-07 20:56:59.4050'),
       (963, '936499355525984256', 10, 25.513, '2024-05-07 22:08:11.9130', '0', '2024-05-07 22:08:11.9920',
        '2024-05-07 22:08:11.9920'),
       (964, '936499355525984256', 20, 81.89, '2024-05-07 22:08:15.1820', '0', '2024-05-07 22:08:15.2060',
        '2024-05-07 22:08:15.2060'),
       (965, '936499355525984256', 10, 44.824, '2024-05-07 22:09:41.8770', '0', '2024-05-07 22:09:42.0520',
        '2024-05-07 22:09:42.0520'),
       (966, '936499355525984256', 20, 83.672, '2024-05-07 22:09:42.3110', '0', '2024-05-07 22:09:42.3460',
        '2024-05-07 22:09:42.3460'),
       (967, '936499355525984256', 10, 34.296, '2024-05-07 22:11:11.8710', '0', '2024-05-07 22:11:12.0680',
        '2024-05-07 22:11:12.0680'),
       (968, '936499355525984256', 20, 83.418, '2024-05-07 22:11:12.1630', '0', '2024-05-07 22:11:12.1870',
        '2024-05-07 22:11:12.1870'),
       (969, '936499355525984256', 10, 37.346, '2024-05-07 22:12:41.8770', '0', '2024-05-07 22:12:41.9450',
        '2024-05-07 22:12:41.9450'),
       (970, '936499355525984256', 20, 83.209, '2024-05-07 22:12:42.1070', '0', '2024-05-07 22:12:42.1200',
        '2024-05-07 22:12:42.1200'),
       (971, '936499355525984256', 10, 51.431, '2024-05-07 22:13:42.7190', '0', '2024-05-07 22:13:42.8760',
        '2024-05-07 22:13:42.8760'),
       (972, '936499355525984256', 20, 83.633, '2024-05-07 22:13:42.9680', '0', '2024-05-07 22:13:42.9730',
        '2024-05-07 22:13:42.9730'),
       (973, '936499355525984256', 10, 39.63, '2024-05-07 22:15:11.8720', '0', '2024-05-07 22:15:11.9540',
        '2024-05-07 22:15:11.9540'),
       (974, '936499355525984256', 20, 83.759, '2024-05-07 22:15:12.1200', '0', '2024-05-07 22:15:12.1320',
        '2024-05-07 22:15:12.1320'),
       (975, '936499355525984256', 10, 39.8, '2024-05-07 22:16:41.9410', '0', '2024-05-07 22:16:42.0600',
        '2024-05-07 22:16:42.0600'),
       (976, '936499355525984256', 20, 83.242, '2024-05-07 22:16:42.1470', '0', '2024-05-07 22:16:42.1560',
        '2024-05-07 22:16:42.1560'),
       (977, '936499355525984256', 10, 51.009, '2024-05-07 22:18:11.8710', '0', '2024-05-07 22:18:11.9840',
        '2024-05-07 22:18:11.9840'),
       (978, '936499355525984256', 20, 83.551, '2024-05-07 22:18:12.1360', '0', '2024-05-07 22:18:12.1470',
        '2024-05-07 22:18:12.1470'),
       (979, '936499355525984256', 10, 53.083, '2024-05-07 22:19:11.8780', '0', '2024-05-07 22:19:11.9560',
        '2024-05-07 22:19:11.9560'),
       (980, '936499355525984256', 20, 83.376, '2024-05-07 22:19:41.8950', '0', '2024-05-07 22:19:41.9730',
        '2024-05-07 22:19:41.9730'),
       (981, '936499355525984256', 10, 38.397, '2024-05-07 22:20:11.8850', '0', '2024-05-07 22:20:11.9950',
        '2024-05-07 22:20:11.9950'),
       (982, '936499355525984256', 20, 82.836, '2024-05-07 22:21:11.8830', '0', '2024-05-07 22:21:11.9470',
        '2024-05-07 22:21:11.9470'),
       (983, '936499355525984256', 10, 44.888, '2024-05-07 22:21:41.8730', '0', '2024-05-07 22:21:41.9410',
        '2024-05-07 22:21:41.9410'),
       (984, '936499355525984256', 20, 83.221, '2024-05-07 22:22:11.9070', '0', '2024-05-07 22:22:11.9490',
        '2024-05-07 22:22:11.9490'),
       (985, '936499355525984256', 10, 52.296, '2024-05-07 22:22:41.8760', '0', '2024-05-07 22:22:42.0210',
        '2024-05-07 22:22:42.0220');
/*!40000 ALTER TABLE `host_alarm_history` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `host_env`
--

DROP TABLE IF EXISTS `host_env`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `host_env`
(
    `id`           bigint       NOT NULL AUTO_INCREMENT,
    `host_id`      varchar(64)  NOT NULL,
    `attr_key`     varchar(64)  NOT NULL,
    `attr_value`   varchar(256) NOT NULL,
    `is_deleted`   char(4)      DEFAULT '0',
    `gmt_create`   datetime     DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `creator`      varchar(255) DEFAULT NULL,
    `modifier`     varchar(255) DEFAULT NULL,
    `description`  varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `host_env`
--

LOCK
TABLES `host_env` WRITE;
/*!40000 ALTER TABLE `host_env` DISABLE KEYS */;
INSERT INTO `host_env`
VALUES (93, '954318785865723904', 'sftp_charset', 'UTF-8', '0', '2024-05-25 20:08:59', '2024-05-25 20:08:59',
        '768460662077796352', NULL, 'SFTP 文件名称编码格式'),
       (94, '954318785865723904', 'tail_offset', '300', '0', '2024-05-25 20:08:59', '2024-05-25 20:08:59',
        '768460662077796352', NULL, '文件追踪偏移量(行)'),
       (95, '954318785865723904', 'tail_charset', 'UTF-8', '0', '2024-05-25 20:08:59', '2024-05-25 20:08:59',
        '768460662077796352', NULL, '文件追踪编码格式'),
       (96, '954318785865723904', 'tail_default_command', 'tail -f -n @{offset} \'@{file}\'', '0',
        '2024-05-25 20:08:59', '2024-05-25 20:08:59', '768460662077796352', NULL, '文件追踪默认命令'),
       (97, '954318785865723904', 'connect_timeout', '30000', '0', '2024-05-25 20:08:59', '2024-05-25 20:08:59',
        '768460662077796352', NULL, '连接超时时间 (ms)'),
       (98, '954318785865723904', 'connect_retry_times', '1', '0', '2024-05-25 20:08:59', '2024-05-25 20:08:59',
        '768460662077796352', NULL, '连接失败重试次数');
/*!40000 ALTER TABLE `host_env` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `host_group`
--

DROP TABLE IF EXISTS `host_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `host_group`
(
    `id`            bigint       NOT NULL AUTO_INCREMENT,
    `host_group_id` varchar(64)  NOT NULL,
    `name`          varchar(255) NOT NULL,
    `parent_id`     varchar(64)  DEFAULT NULL,
    `sort`          int          NOT NULL,
    `is_deleted`    char(4)      DEFAULT '0',
    `gmt_create`    datetime     DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified`  datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `creator`       varchar(255) DEFAULT NULL,
    `modifier`      varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `host_group`
--

LOCK
TABLES `host_group` WRITE;
/*!40000 ALTER TABLE `host_group` DISABLE KEYS */;
INSERT INTO `host_group`
VALUES (1, '1', 'package', '0', 1, '0', '2024-04-06 15:53:33', '2024-04-06 15:58:45', NULL, NULL);
/*!40000 ALTER TABLE `host_group` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `host_monitor`
--

DROP TABLE IF EXISTS `host_monitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `host_monitor`
(
    `id`             bigint NOT NULL AUTO_INCREMENT,
    `host_id`        varchar(64)  DEFAULT NULL COMMENT '机器id',
    `monitor_status` int          DEFAULT '1' COMMENT '监控状态 1未启动 2启动中 3运行中',
    `monitor_url`    varchar(512) DEFAULT NULL COMMENT '请求 api url',
    `access_token`   varchar(512) DEFAULT NULL COMMENT '请求 api accessToken',
    `agent_version`  varchar(16)  DEFAULT NULL COMMENT '插件版本',
    `is_deleted`     char(4)      DEFAULT '0',
    `gmt_create`     datetime     DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified`   datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `creator`        varchar(255) DEFAULT NULL,
    `modifier`       varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='机器监控配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `host_monitor`
--

LOCK
TABLES `host_monitor` WRITE;
/*!40000 ALTER TABLE `host_monitor` DISABLE KEYS */;
INSERT INTO `host_monitor`
VALUES (1, '936499355525984256', 1, 'http://127.0.0.1:9220', 'agent_access', '1.0.0', '1', NULL, '2024-05-25 20:08:24',
        NULL, '768460662077796352'),
       (13, '954318785865723904', 1, 'http://127.0.0.1:9220', 'agent_access', NULL, '0', '2024-05-25 20:08:59',
        '2024-05-25 20:08:59', '768460662077796352', NULL);
/*!40000 ALTER TABLE `host_monitor` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `host_terminal_config`
--

DROP TABLE IF EXISTS `host_terminal_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `host_terminal_config`
(
    `id`               bigint                          NOT NULL AUTO_INCREMENT COMMENT 'id',
    `host_id`          varchar(64) COLLATE utf8mb3_bin NOT NULL COMMENT '机器ID',
    `terminal_type`    varchar(5) COLLATE utf8mb3_bin  NOT NULL,
    `background_color` varchar(64) COLLATE utf8mb3_bin NOT NULL,
    `font_color`       varchar(64) COLLATE utf8mb3_bin NOT NULL,
    `font_size`        int                             NOT NULL,
    `font_family`      varchar(64) COLLATE utf8mb3_bin NOT NULL,
    `enable_web_link`  int                             NOT NULL,
    `is_deleted`       varchar(5) COLLATE utf8mb3_bin   DEFAULT '1' COMMENT '是否删除 0未删除 1已删除',
    `gmt_create`       datetime(4) DEFAULT CURRENT_TIMESTAMP (4) COMMENT '创建时间',
    `gmt_modified`     datetime(4) DEFAULT CURRENT_TIMESTAMP (4) ON UPDATE CURRENT_TIMESTAMP (4) COMMENT '修改时间',
    `creator`          varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
    `modifier`         varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC COMMENT='机器终端配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `host_terminal_config`
--

LOCK
TABLES `host_terminal_config` WRITE;
/*!40000 ALTER TABLE `host_terminal_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `host_terminal_config` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `host_terminal_log`
--

DROP TABLE IF EXISTS `host_terminal_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `host_terminal_log`
(
    `id`                bigint                          NOT NULL AUTO_INCREMENT COMMENT 'id',
    `host_id`           varchar(64) COLLATE utf8mb3_bin NOT NULL COMMENT '机器ID',
    `user_id`           varchar(64) COLLATE utf8mb3_bin  DEFAULT NULL COMMENT 'userid',
    `username`          varchar(32) COLLATE utf8mb3_bin  DEFAULT NULL COMMENT 'username',
    `access_token`      varchar(512) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'token',
    `connected_time`    datetime(4) DEFAULT NULL COMMENT '建立连接时间',
    `disconnected_time` datetime(4) DEFAULT NULL COMMENT '断开连接时间',
    `close_code`        int                              DEFAULT NULL COMMENT 'close code',
    `screen_path`       varchar(512) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '录屏文件路径',
    `is_deleted`        varchar(5) COLLATE utf8mb3_bin   DEFAULT '1' COMMENT '是否删除 0未删除 1已删除',
    `gmt_create`        datetime(4) DEFAULT CURRENT_TIMESTAMP (4) COMMENT '创建时间',
    `gmt_modified`      datetime(4) DEFAULT CURRENT_TIMESTAMP (4) ON UPDATE CURRENT_TIMESTAMP (4) COMMENT '修改时间',
    `creator`           varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
    `modifier`          varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC COMMENT='机器终端日志配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `host_terminal_log`
--

LOCK
TABLES `host_terminal_log` WRITE;
/*!40000 ALTER TABLE `host_terminal_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `host_terminal_log` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `namespace`
--

DROP TABLE IF EXISTS `namespace`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `namespace`
(
    `id`             bigint                                                        NOT NULL,
    `namespace_id`   varchar(64)                                                   NOT NULL,
    `cluster_id`     varchar(64)                                                   NOT NULL,
    `name`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
    `request_cpu`    decimal(12, 4) DEFAULT NULL,
    `request_memory` decimal(12, 4) DEFAULT '0.0000',
    `limit_cpu`      decimal(12, 4) DEFAULT NULL,
    `limit_memory`   decimal(12, 4) DEFAULT NULL,
    `max_pods`       int            DEFAULT NULL,
    `max_gpu`        decimal(12, 4) DEFAULT NULL,
    `max_services`   int            DEFAULT NULL,
    `status`         char(4)        DEFAULT NULL,
    `is_deleted`     char(4)        DEFAULT '0',
    `gmt_create`     datetime       DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified`   datetime       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `creator`        varchar(255)   DEFAULT NULL,
    `modifier`       varchar(255)   DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `namespace`
--

LOCK
TABLES `namespace` WRITE;
/*!40000 ALTER TABLE `namespace` DISABLE KEYS */;
INSERT INTO `namespace`
VALUES (1, '1', '1', 'cake-devops-base', 20.0000, 20.0000, 40.0000, 60.0000, 200, 0.0000, 200, '1', '0',
        '2023-11-23 20:49:37', '2024-01-16 21:22:14', NULL, NULL);
/*!40000 ALTER TABLE `namespace` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `release_no`
--

DROP TABLE IF EXISTS `release_no`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `release_no`
(
    `id`                bigint       NOT NULL AUTO_INCREMENT,
    `release_id`        varchar(64)  NOT NULL,
    `app_id`            varchar(64)  NOT NULL,
    `release_no`        varchar(64)  DEFAULT NULL,
    `approval_id`       varchar(64)  DEFAULT NULL,
    `release_date`      datetime     NOT NULL,
    `release_branch`    varchar(255) NOT NULL,
    `release_commit_id` varchar(255) DEFAULT NULL,
    `release_version`   varchar(255) DEFAULT NULL,
    `env_id`            varchar(64)  NOT NULL,
    `release_status`    varchar(255) NOT NULL,
    `rollback`          varchar(64)  DEFAULT NULL,
    `rollback_id`       varchar(64)  DEFAULT NULL,
    `is_deleted`        char(4)      DEFAULT '0',
    `gmt_create`        datetime     DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified`      datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `creator`           varchar(255) DEFAULT NULL,
    `modifier`          varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `release_no`
--

LOCK
TABLES `release_no` WRITE;
/*!40000 ALTER TABLE `release_no` DISABLE KEYS */;
INSERT INTO `release_no`
VALUES (2, '906359016366682112', '1', 'R202401141153590004', '906359020359659520', '2024-01-14 11:53:47', 'main', NULL,
        'v1.0.0', '897615291213819903', 'FAILED', NULL, NULL, '0', '2024-01-14 11:54:00', '2024-01-21 15:52:16', NULL,
        NULL),
       (3, '906669481965793280', '1', 'R202401150827390005', '906669494347378688', '2024-01-31 08:27:26', 'main', NULL,
        'v1.1.0', '897615291213819903', 'CLOSED', NULL, NULL, '0', '2024-01-15 08:27:43', '2024-01-20 11:02:44', NULL,
        NULL),
       (4, '908309875333935104', '1', 'R202401192106000006', '908309878756487168', '2024-01-31 21:05:37', 'develop',
        NULL, 'v2.0.0-beta', '897615291213819903', 'CLOSED', NULL, NULL, '0', '2024-01-19 21:06:01',
        '2024-01-20 10:46:01', NULL, NULL),
       (5, '908309900264878080', '1', 'R202401192106060007', '908309900323598336', '2024-01-31 21:05:37', 'develop',
        NULL, 'v2.0.0-beta', '897615291213819903', 'CLOSED', NULL, NULL, '0', '2024-01-19 21:06:06',
        '2024-01-20 10:45:06', NULL, NULL),
       (6, '937674256597266432', '1', 'R202404092149340008', '937674263555616768', '2024-04-09 21:49:26', 'origin/main',
        NULL, 'v2.0.0', '897615291213819904', 'READY', NULL, NULL, '0', '2024-04-09 21:49:36', '2024-04-09 21:49:36',
        NULL, NULL);
/*!40000 ALTER TABLE `release_no` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `script_template`
--

DROP TABLE IF EXISTS `script_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `script_template`
(
    `id`             bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `template_name`  varchar(32) COLLATE utf8mb3_bin   DEFAULT NULL COMMENT '模板名称',
    `template_value` varchar(2048) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '模板命令',
    `description`    varchar(64) COLLATE utf8mb3_bin   DEFAULT NULL COMMENT '命令描述',
    `is_deleted`     char(4) COLLATE utf8mb3_bin       DEFAULT '0',
    `gmt_create`     datetime                          DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified`   datetime                          DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `creator`        varchar(255) COLLATE utf8mb3_bin  DEFAULT NULL,
    `modifier`       varchar(255) COLLATE utf8mb3_bin  DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC COMMENT='命令模板表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `script_template`
--

LOCK
TABLES `script_template` WRITE;
/*!40000 ALTER TABLE `script_template` DISABLE KEYS */;
INSERT INTO `script_template`
VALUES (1, '水电费水电费的',
        'DEL_PATH=\nif [ -d \"$DEL_PATH\" ]; then\n rm -rf $DEL_PATH\n echo \'删除文件夹\' $DEL_PATH\nfi',
        '水电费说的都是', '0', '2024-04-10 22:38:11', '2024-04-10 22:46:07', '768460662077796352',
        '768460662077796352'),
       (2, '使用测试', '使用测试', '使用测试', '0', '2024-04-10 23:02:51', '2024-04-10 23:02:51', '768460662077796352',
        NULL);
/*!40000 ALTER TABLE `script_template` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `server_key`
--

DROP TABLE IF EXISTS `server_key`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `server_key`
(
    `id`           bigint NOT NULL AUTO_INCREMENT,
    `display_name` varchar(255) DEFAULT NULL,
    `account_type` varchar(32)  DEFAULT NULL COMMENT '账号类型0普通，1管理员',
    `protocol`     varchar(32)  DEFAULT NULL COMMENT '协议，当前支持ssh',
    `active`       char(4)      DEFAULT '1',
    `credential`   varchar(255) DEFAULT NULL,
    `public_key`   varchar(255) DEFAULT NULL,
    `passphrase`   varchar(255) DEFAULT NULL,
    `is_deleted`   char(4)      DEFAULT '0',
    `gmt_create`   datetime     DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified` datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `creator`      varchar(255) DEFAULT NULL,
    `modifier`     varchar(255) DEFAULT NULL,
    `key_path`     varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='机器秘钥';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `server_key`
--

LOCK
TABLES `server_key` WRITE;
/*!40000 ALTER TABLE `server_key` DISABLE KEYS */;
/*!40000 ALTER TABLE `server_key` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `server_load_monitor`
--

DROP TABLE IF EXISTS `server_load_monitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `server_load_monitor`
(
    `id`                bigint      NOT NULL AUTO_INCREMENT,
    `host_id`           varchar(64) NOT NULL,
    `cpu_rate`          decimal(10, 2) DEFAULT NULL,
    `mem_rate`          decimal(10, 2) DEFAULT NULL,
    `one_min_load_avg`  decimal(10, 2) DEFAULT NULL,
    `five_min_load_avg` decimal(10, 2) DEFAULT NULL,
    `fif_min_load_avg`  decimal(10, 2) DEFAULT NULL,
    `is_deleted`        char(4)        DEFAULT '0',
    `gmt_create`        datetime       DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified`      datetime       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `creator`           varchar(255)   DEFAULT NULL,
    `modifier`          varchar(255)   DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `server_load_monitor`
--

LOCK
TABLES `server_load_monitor` WRITE;
/*!40000 ALTER TABLE `server_load_monitor` DISABLE KEYS */;
/*!40000 ALTER TABLE `server_load_monitor` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `server_proxy`
--

DROP TABLE IF EXISTS `server_proxy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `server_proxy`
(
    `id`             bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `proxy_host`     varchar(128) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '代理主机',
    `proxy_port`     int                              DEFAULT NULL COMMENT '代理端口',
    `proxy_username` varchar(128) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '代理用户名',
    `proxy_password` varchar(512) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '代理密码',
    `proxy_type`     int                              DEFAULT NULL COMMENT '代理类型 1http代理 2socket4代理 3socket5代理',
    `description`    varchar(64) COLLATE utf8mb3_bin  DEFAULT NULL COMMENT '描述',
    `is_deleted`     varchar(5) COLLATE utf8mb3_bin   DEFAULT '0' COMMENT '是否删除 1未删除 2已删除',
    `gmt_create`     datetime(4) DEFAULT CURRENT_TIMESTAMP (4) COMMENT '创建时间',
    `gmt_modified`   datetime(4) DEFAULT CURRENT_TIMESTAMP (4) ON UPDATE CURRENT_TIMESTAMP (4) COMMENT '修改时间',
    `creator`        varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
    `modifier`       varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC COMMENT='机器代理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `server_proxy`
--

LOCK
TABLES `server_proxy` WRITE;
/*!40000 ALTER TABLE `server_proxy` DISABLE KEYS */;
/*!40000 ALTER TABLE `server_proxy` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `system_env`
--

DROP TABLE IF EXISTS `system_env`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_env`
(
    `id`           bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `attr_key`     varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  DEFAULT NULL COMMENT 'key',
    `attr_value`   varchar(2048) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'value',
    `system_env`   int                                                            DEFAULT NULL COMMENT '是否为系统变量 1是 2否',
    `description`  varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci   DEFAULT NULL COMMENT '描述',
    `is_deleted`   varchar(5)                                                     DEFAULT '1' COMMENT '是否删除',
    `gmt_create`   datetime(4) DEFAULT CURRENT_TIMESTAMP (4) COMMENT '创建时间',
    `gmt_modified` datetime(4) DEFAULT CURRENT_TIMESTAMP (4) ON UPDATE CURRENT_TIMESTAMP (4) COMMENT '修改时间',
    `creator`      varchar(255)                                                   DEFAULT NULL,
    `modifier`     varchar(255)                                                   DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY            `key_idx` (`attr_key`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统环境变量';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_env`
--

LOCK
TABLES `system_env` WRITE;
/*!40000 ALTER TABLE `system_env` DISABLE KEYS */;
INSERT INTO `system_env`
VALUES (1, 'key_path', '/Users/yuanjinxiu/cake-ops/.keys', 2, '秘钥存放目录', NULL, NULL, NULL, NULL, NULL),
       (2, 'pic_path', '/Users/yuanjinxiu/cake-ops/pic', 2, '图片存放目录', NULL, NULL, NULL, NULL, NULL),
       (3, 'swap_path', '/Users/yuanjinxiu/cake-ops/swap', 2, '交换分区目录', NULL, NULL, NULL, NULL, NULL),
       (4, 'screen_path', '/Users/yuanjinxiu/cake-ops/screen', 2, '录屏存放目录', NULL, NULL, NULL, NULL, NULL),
       (5, 'log_path', '/Users/yuanjinxiu/cake-ops/logs', 2, '日志存放目录', NULL, NULL, NULL, NULL, NULL),
       (6, 'temp_path', '/Users/yuanjinxiu/cake-ops/temp', 2, '临时文件目录', NULL, NULL, NULL, NULL, NULL),
       (7, 'repo_path', '/Users/yuanjinxiu/cake-ops/repo', 2, '应用版本仓库目录', NULL, NULL, NULL, NULL, NULL),
       (8, 'dist_path', '/Users/yuanjinxiu/cake-ops/dist', 2, '构建产物目录', NULL, NULL, NULL, NULL, NULL),
       (9, 'machine_monitor_agent_path',
        '/Users/yuanjinxiu/cake-ops/lib/cake-devops-base-plugin-agent-0.0.1-SNAPSHOT.jar', 2, '机器监控插件绝对路径',
        NULL, NULL, '2024-05-25 15:03:15.3308', NULL, NULL),
       (10, 'tail_file_upload_path', '/Users/yuanjinxiu/cake-ops/tail', 2, '日志文件上传目录', NULL, NULL, NULL, NULL,
        NULL),
       (11, 'tail_mode', 'tracker', 2, '日志文件追踪模式 (tracker/tail)', NULL, NULL, NULL, NULL, NULL),
       (12, 'tracker_delay_time', '250', 2, '文件追踪器等待时间 (ms)', NULL, NULL, NULL, NULL, NULL),
       (13, 'white_ip_list', NULL, 1, 'ip 白名单', NULL, NULL, NULL, NULL, NULL),
       (14, 'black_ip_list', NULL, 1, 'ip 黑名单', NULL, NULL, NULL, NULL, NULL),
       (15, 'enable_ip_filter', 'disabled', 1, '是否启用IP过滤', NULL, NULL, NULL, NULL, NULL),
       (16, 'enable_white_ip_list', 'disabled', 1, '是否启用IP白名单', NULL, NULL, NULL, NULL, NULL),
       (17, 'file_clean_threshold', '60', 1, '文件清理阈值 (天)', NULL, NULL, NULL, NULL, NULL),
       (18, 'enable_auto_clean_file', 'disabled', 1, '是否启用自动清理', NULL, NULL, NULL, NULL, NULL),
       (19, 'allow_multiple_login', 'disabled', 1, '允许多端登陆', NULL, NULL, NULL, NULL, NULL),
       (20, 'login_failure_lock', 'enabled', 1, '是否启用登陆失败锁定', NULL, NULL, NULL, NULL, NULL),
       (21, 'login_ip_bind', 'disabled', 1, '是否启用登陆IP绑定', NULL, NULL, NULL, NULL, NULL),
       (22, 'login_token_auto_renew', 'enabled', 1, '是否启用凭证自动续签', NULL, NULL, NULL, NULL, NULL),
       (23, 'login_token_expire', '48', 1, '登陆凭证有效期 (时)', NULL, NULL, NULL, NULL, NULL),
       (24, 'login_failure_lock_threshold', '5', 1, '登陆失败锁定阈值', NULL, NULL, NULL, NULL, NULL),
       (25, 'login_token_auto_renew_threshold', '2', 1, '登陆自动续签阈值 (时)', NULL, NULL, NULL, NULL, NULL),
       (26, 'resume_enable_scheduler_task', 'disabled', 1, '自动恢复启用的调度任务', NULL, NULL, NULL, NULL, NULL),
       (27, 'terminal_active_push_heartbeat', 'disabled', 1, '终端后台主动推送心跳', NULL, NULL, NULL, NULL, NULL),
       (28, 'sftp_upload_threshold', '512', 1, 'sftp 上传文件最大阈值 (MB)', NULL, NULL, NULL, NULL, NULL),
       (29, 'statistics_cache_expire', '5', 1, '统计缓存有效时间 (分)', NULL, NULL, NULL, NULL, NULL);
/*!40000 ALTER TABLE `system_env` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `terminal_session`
--

DROP TABLE IF EXISTS `terminal_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `terminal_session`
(
    `id`              bigint       NOT NULL AUTO_INCREMENT,
    `session_id`      varchar(255) NOT NULL,
    `account_id`      bigint       DEFAULT NULL,
    `remote_addr`     varchar(255) DEFAULT NULL,
    `session_closed`  tinyint(1) DEFAULT NULL,
    `close_time`      datetime     DEFAULT NULL,
    `server_hostname` varchar(255) DEFAULT NULL,
    `server_addr`     varchar(255) DEFAULT NULL,
    `session_type`    varchar(10)  DEFAULT NULL,
    `is_deleted`      char(4)      DEFAULT '0',
    `gmt_create`      datetime     DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified`    datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `creator`         varchar(255) DEFAULT NULL,
    `modifier`        varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `terminal_session`
--

LOCK
TABLES `terminal_session` WRITE;
/*!40000 ALTER TABLE `terminal_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `terminal_session` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `terminal_session_instance`
--

DROP TABLE IF EXISTS `terminal_session_instance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `terminal_session_instance`
(
    `id`                    bigint       NOT NULL AUTO_INCREMENT,
    `session_id`            varchar(255) NOT NULL,
    `instance_id`           varchar(255) DEFAULT NULL,
    `duplicate_instance_id` varchar(255) DEFAULT NULL,
    `instance_session_type` varchar(10)  DEFAULT NULL,
    `login_user`            varchar(255) DEFAULT NULL,
    `host_ip`               varchar(255) DEFAULT NULL,
    `output_size`           bigint       DEFAULT NULL,
    `instance_closed`       tinyint      DEFAULT NULL,
    `open_time`             datetime     DEFAULT NULL,
    `close_time`            datetime     DEFAULT NULL,
    `is_deleted`            char(4)      DEFAULT '0',
    `gmt_create`            datetime     DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified`          datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `creator`               varchar(255) DEFAULT NULL,
    `modifier`              varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `terminal_session_instance`
--

LOCK
TABLES `terminal_session_instance` WRITE;
/*!40000 ALTER TABLE `terminal_session_instance` DISABLE KEYS */;
/*!40000 ALTER TABLE `terminal_session_instance` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `terminal_session_instance_command`
--

DROP TABLE IF EXISTS `terminal_session_instance_command`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `terminal_session_instance_command`
(
    `id`                           bigint       NOT NULL AUTO_INCREMENT,
    `terminal_session_instance_id` varchar(255) NOT NULL,
    `prompt`                       varchar(255) DEFAULT NULL,
    `is_formatted`                 varchar(10)  DEFAULT '0',
    `input`                        text,
    `input_formatted`              text,
    `output`                       text,
    `is_deleted`                   char(4)      DEFAULT '0',
    `gmt_create`                   datetime     DEFAULT CURRENT_TIMESTAMP,
    `gmt_modified`                 datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `creator`                      varchar(255) DEFAULT NULL,
    `modifier`                     varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `terminal_session_instance_command`
--

LOCK
TABLES `terminal_session_instance_command` WRITE;
/*!40000 ALTER TABLE `terminal_session_instance_command` DISABLE KEYS */;
/*!40000 ALTER TABLE `terminal_session_instance_command` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `web_side_message`
--

DROP TABLE IF EXISTS `web_side_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `web_side_message`
(
    `id`               bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `message_classify` tinyint                         DEFAULT NULL COMMENT '消息分类',
    `message_type`     int                             DEFAULT NULL COMMENT '消息类型',
    `read_status`      tinyint                         DEFAULT '1' COMMENT '是否已读 1未读 2已读',
    `to_user_id`       bigint                          DEFAULT NULL COMMENT '收信人id',
    `to_user_name`     varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '收信人名称',
    `send_message`     text COLLATE utf8mb3_bin COMMENT '消息',
    `rel_id`           bigint                          DEFAULT NULL COMMENT '消息关联id',
    `is_deleted`       varchar(5) COLLATE utf8mb3_bin  DEFAULT '1' COMMENT '是否删除 0未删除 1已删除',
    `gmt_create`       datetime(4) DEFAULT CURRENT_TIMESTAMP (4) COMMENT '创建时间',
    `gmt_modified`     datetime(4) DEFAULT CURRENT_TIMESTAMP (4) ON UPDATE CURRENT_TIMESTAMP (4) COMMENT '修改时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=172 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC COMMENT='站内信';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_side_message`
--

LOCK
TABLES `web_side_message` WRITE;
/*!40000 ALTER TABLE `web_side_message` DISABLE KEYS */;
INSERT INTO `web_side_message`
VALUES (1, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:46:17 <sb>CPU使用率</sb>达到<sr><b>19.28%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (2, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:46:17 <sb>CPU使用率</sb>达到<sr><b>19.28%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (3, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:46:17 <sb>CPU使用率</sb>达到<sr><b>19.28%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (4, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:46:20 <sb>内存使用率</sb>达到<sr><b>83.03%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (5, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:46:20 <sb>内存使用率</sb>达到<sr><b>83.03%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (6, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:46:20 <sb>内存使用率</sb>达到<sr><b>83.03%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (7, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:47:47 <sb>CPU使用率</sb>达到<sr><b>24.64%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (8, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:47:47 <sb>CPU使用率</sb>达到<sr><b>24.64%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (9, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:47:47 <sb>CPU使用率</sb>达到<sr><b>24.64%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (10, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:47:48 <sb>内存使用率</sb>达到<sr><b>83.34%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (11, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:47:48 <sb>内存使用率</sb>达到<sr><b>83.34%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (12, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:47:48 <sb>内存使用率</sb>达到<sr><b>83.34%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (13, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:49:17 <sb>CPU使用率</sb>达到<sr><b>22.91%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (14, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:49:17 <sb>CPU使用率</sb>达到<sr><b>22.91%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (15, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:49:17 <sb>CPU使用率</sb>达到<sr><b>22.91%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (16, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:49:18 <sb>内存使用率</sb>达到<sr><b>83.1%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (17, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:49:18 <sb>内存使用率</sb>达到<sr><b>83.1%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (18, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:49:18 <sb>内存使用率</sb>达到<sr><b>83.1%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (19, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:50:47 <sb>CPU使用率</sb>达到<sr><b>30.52%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (20, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:50:47 <sb>CPU使用率</sb>达到<sr><b>30.52%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (21, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:50:47 <sb>CPU使用率</sb>达到<sr><b>30.52%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (22, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:50:48 <sb>内存使用率</sb>达到<sr><b>83.15%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (23, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:50:48 <sb>内存使用率</sb>达到<sr><b>83.15%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (24, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:50:48 <sb>内存使用率</sb>达到<sr><b>83.15%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (25, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:51:47 <sb>CPU使用率</sb>达到<sr><b>24.99%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (26, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:51:47 <sb>CPU使用率</sb>达到<sr><b>24.99%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (27, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:51:47 <sb>CPU使用率</sb>达到<sr><b>24.99%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (28, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:51:48 <sb>内存使用率</sb>达到<sr><b>82.86%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (29, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:51:48 <sb>内存使用率</sb>达到<sr><b>82.86%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (30, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:51:48 <sb>内存使用率</sb>达到<sr><b>82.86%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (31, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:53:17 <sb>CPU使用率</sb>达到<sr><b>27.36%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (32, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:53:17 <sb>CPU使用率</sb>达到<sr><b>27.36%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (33, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:53:17 <sb>CPU使用率</sb>达到<sr><b>27.36%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (34, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:53:18 <sb>内存使用率</sb>达到<sr><b>82.73%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (35, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:53:18 <sb>内存使用率</sb>达到<sr><b>82.73%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (36, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:53:18 <sb>内存使用率</sb>达到<sr><b>82.73%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (37, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:54:17 <sb>CPU使用率</sb>达到<sr><b>30.63%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (38, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:54:17 <sb>CPU使用率</sb>达到<sr><b>30.63%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (39, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:54:17 <sb>CPU使用率</sb>达到<sr><b>30.63%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (40, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:54:18 <sb>内存使用率</sb>达到<sr><b>82.75%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (41, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:54:18 <sb>内存使用率</sb>达到<sr><b>82.75%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (42, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:54:18 <sb>内存使用率</sb>达到<sr><b>82.75%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (43, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:55:17 <sb>CPU使用率</sb>达到<sr><b>27.77%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (44, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:55:17 <sb>CPU使用率</sb>达到<sr><b>27.77%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (45, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:55:17 <sb>CPU使用率</sb>达到<sr><b>27.77%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (46, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:55:18 <sb>内存使用率</sb>达到<sr><b>83.12%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (47, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:55:18 <sb>内存使用率</sb>达到<sr><b>83.12%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (48, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:55:18 <sb>内存使用率</sb>达到<sr><b>83.12%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (49, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:56:17 <sb>CPU使用率</sb>达到<sr><b>16.54%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (50, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:56:17 <sb>CPU使用率</sb>达到<sr><b>16.54%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (51, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:56:17 <sb>CPU使用率</sb>达到<sr><b>16.54%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (52, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:56:47 <sb>内存使用率</sb>达到<sr><b>83.08%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (53, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:56:47 <sb>内存使用率</sb>达到<sr><b>83.08%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (54, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:56:47 <sb>内存使用率</sb>达到<sr><b>83.08%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (55, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:57:47 <sb>CPU使用率</sb>达到<sr><b>32.75%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (56, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:57:47 <sb>CPU使用率</sb>达到<sr><b>32.75%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (57, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:57:47 <sb>CPU使用率</sb>达到<sr><b>32.75%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (58, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:57:48 <sb>内存使用率</sb>达到<sr><b>82.51%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (59, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:57:48 <sb>内存使用率</sb>达到<sr><b>82.51%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (60, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:57:48 <sb>内存使用率</sb>达到<sr><b>82.51%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (61, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:58:47 <sb>CPU使用率</sb>达到<sr><b>24.14%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (62, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:58:47 <sb>CPU使用率</sb>达到<sr><b>24.14%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (63, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:58:47 <sb>CPU使用率</sb>达到<sr><b>24.14%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (64, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:58:48 <sb>内存使用率</sb>达到<sr><b>83.02%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (65, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:58:48 <sb>内存使用率</sb>达到<sr><b>83.02%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (66, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 20:58:48 <sb>内存使用率</sb>达到<sr><b>83.02%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (67, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 21:00:17 <sb>CPU使用率</sb>达到<sr><b>24.94%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (68, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 21:00:17 <sb>CPU使用率</sb>达到<sr><b>24.94%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (69, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 21:00:17 <sb>CPU使用率</sb>达到<sr><b>24.94%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (70, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 21:00:19 <sb>内存使用率</sb>达到<sr><b>82.09%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (71, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 21:00:19 <sb>内存使用率</sb>达到<sr><b>82.09%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (72, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 21:00:19 <sb>内存使用率</sb>达到<sr><b>82.09%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (73, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 21:01:17 <sb>CPU使用率</sb>达到<sr><b>30.84%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (74, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 21:01:17 <sb>CPU使用率</sb>达到<sr><b>30.84%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (75, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 21:01:17 <sb>CPU使用率</sb>达到<sr><b>30.84%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (76, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 21:01:47 <sb>内存使用率</sb>达到<sr><b>83.05%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (77, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 21:01:47 <sb>内存使用率</sb>达到<sr><b>83.05%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (78, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-14 21:01:47 <sb>内存使用率</sb>达到<sr><b>83.05%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (79, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:47:10 <sb>CPU使用率</sb>达到<sr><b>42.97%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (80, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:47:10 <sb>CPU使用率</sb>达到<sr><b>42.97%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (81, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:47:10 <sb>CPU使用率</sb>达到<sr><b>42.97%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (82, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:47:14 <sb>内存使用率</sb>达到<sr><b>83.16%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (83, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:47:14 <sb>内存使用率</sb>达到<sr><b>83.16%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (84, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:47:14 <sb>内存使用率</sb>达到<sr><b>83.16%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (85, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:48:40 <sb>CPU使用率</sb>达到<sr><b>28.54%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (86, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:48:40 <sb>CPU使用率</sb>达到<sr><b>28.54%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (87, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:48:40 <sb>CPU使用率</sb>达到<sr><b>28.54%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (88, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:48:42 <sb>内存使用率</sb>达到<sr><b>83.28%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (89, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:48:42 <sb>内存使用率</sb>达到<sr><b>83.28%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (90, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:48:42 <sb>内存使用率</sb>达到<sr><b>83.28%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (91, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:49:40 <sb>CPU使用率</sb>达到<sr><b>27.17%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (92, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:49:40 <sb>CPU使用率</sb>达到<sr><b>27.17%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (93, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:49:40 <sb>CPU使用率</sb>达到<sr><b>27.17%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (94, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:50:10 <sb>内存使用率</sb>达到<sr><b>83.24%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (95, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:50:10 <sb>内存使用率</sb>达到<sr><b>83.24%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (96, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:50:10 <sb>内存使用率</sb>达到<sr><b>83.24%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (97, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:50:40 <sb>CPU使用率</sb>达到<sr><b>32.77%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (98, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:50:40 <sb>CPU使用率</sb>达到<sr><b>32.77%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (99, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:50:40 <sb>CPU使用率</sb>达到<sr><b>32.77%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (100, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:52:10 <sb>CPU使用率</sb>达到<sr><b>33.47%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (101, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:52:10 <sb>CPU使用率</sb>达到<sr><b>33.47%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (102, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:52:10 <sb>CPU使用率</sb>达到<sr><b>33.47%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (103, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:52:12 <sb>内存使用率</sb>达到<sr><b>83.21%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (104, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:52:12 <sb>内存使用率</sb>达到<sr><b>83.21%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (105, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:52:12 <sb>内存使用率</sb>达到<sr><b>83.21%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (106, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:53:40 <sb>CPU使用率</sb>达到<sr><b>37.58%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (107, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:53:40 <sb>CPU使用率</sb>达到<sr><b>37.58%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (108, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:53:40 <sb>CPU使用率</sb>达到<sr><b>37.58%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (109, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:53:41 <sb>内存使用率</sb>达到<sr><b>83.4%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (110, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:53:41 <sb>内存使用率</sb>达到<sr><b>83.4%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (111, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:53:41 <sb>内存使用率</sb>达到<sr><b>83.4%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (112, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:55:10 <sb>CPU使用率</sb>达到<sr><b>34.57%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (113, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:55:10 <sb>CPU使用率</sb>达到<sr><b>34.57%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (114, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:55:10 <sb>CPU使用率</sb>达到<sr><b>34.57%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (115, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:55:11 <sb>内存使用率</sb>达到<sr><b>83.24%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (116, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:55:11 <sb>内存使用率</sb>达到<sr><b>83.24%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (117, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:55:11 <sb>内存使用率</sb>达到<sr><b>83.24%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (118, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:56:10 <sb>CPU使用率</sb>达到<sr><b>69.7%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (119, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:56:10 <sb>CPU使用率</sb>达到<sr><b>69.7%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (120, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:56:10 <sb>CPU使用率</sb>达到<sr><b>69.7%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (121, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:56:11 <sb>内存使用率</sb>达到<sr><b>83.15%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (122, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:56:11 <sb>内存使用率</sb>达到<sr><b>83.15%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (123, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:56:11 <sb>内存使用率</sb>达到<sr><b>83.15%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (124, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:57:40 <sb>CPU使用率</sb>达到<sr><b>20.63%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (125, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:57:40 <sb>CPU使用率</sb>达到<sr><b>20.63%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (126, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:57:40 <sb>CPU使用率</sb>达到<sr><b>20.63%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (127, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:57:41 <sb>内存使用率</sb>达到<sr><b>83.17%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (128, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:57:41 <sb>内存使用率</sb>达到<sr><b>83.17%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (129, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:57:41 <sb>内存使用率</sb>达到<sr><b>83.17%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (130, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:59:10 <sb>CPU使用率</sb>达到<sr><b>21.96%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (131, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:59:10 <sb>CPU使用率</sb>达到<sr><b>21.96%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (132, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:59:10 <sb>CPU使用率</sb>达到<sr><b>21.96%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (133, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:59:11 <sb>内存使用率</sb>达到<sr><b>83.03%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (134, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:59:11 <sb>内存使用率</sb>达到<sr><b>83.03%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (135, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 20:59:11 <sb>内存使用率</sb>达到<sr><b>83.03%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (136, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:00:10 <sb>CPU使用率</sb>达到<sr><b>25.06%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (137, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:00:10 <sb>CPU使用率</sb>达到<sr><b>25.06%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (138, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:00:10 <sb>CPU使用率</sb>达到<sr><b>25.06%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (139, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:00:11 <sb>内存使用率</sb>达到<sr><b>83.19%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (140, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:00:11 <sb>内存使用率</sb>达到<sr><b>83.19%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (141, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:00:11 <sb>内存使用率</sb>达到<sr><b>83.19%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (142, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:01:10 <sb>CPU使用率</sb>达到<sr><b>25.05%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (143, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:01:10 <sb>CPU使用率</sb>达到<sr><b>25.05%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (144, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:01:10 <sb>CPU使用率</sb>达到<sr><b>25.05%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (145, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:01:11 <sb>内存使用率</sb>达到<sr><b>83.08%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (146, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:01:11 <sb>内存使用率</sb>达到<sr><b>83.08%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (147, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:01:11 <sb>内存使用率</sb>达到<sr><b>83.08%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (148, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:02:10 <sb>CPU使用率</sb>达到<sr><b>25.33%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (149, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:02:10 <sb>CPU使用率</sb>达到<sr><b>25.33%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (150, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:02:10 <sb>CPU使用率</sb>达到<sr><b>25.33%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (151, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:02:40 <sb>内存使用率</sb>达到<sr><b>82.82%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (152, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:02:40 <sb>内存使用率</sb>达到<sr><b>82.82%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (153, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:02:40 <sb>内存使用率</sb>达到<sr><b>82.82%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (154, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:03:40 <sb>CPU使用率</sb>达到<sr><b>18.78%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (155, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:03:40 <sb>CPU使用率</sb>达到<sr><b>18.78%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (156, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:03:40 <sb>CPU使用率</sb>达到<sr><b>18.78%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (157, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:04:20 <sb>内存使用率</sb>达到<sr><b>82.73%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (158, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:04:20 <sb>内存使用率</sb>达到<sr><b>82.73%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (159, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:04:20 <sb>内存使用率</sb>达到<sr><b>82.73%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (160, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:04:35 <sb>CPU使用率</sb>达到<sr><b>24.28%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (161, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:04:35 <sb>CPU使用率</sb>达到<sr><b>24.28%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (162, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:04:35 <sb>CPU使用率</sb>达到<sr><b>24.28%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (163, 30, 3010, 1, 781488231601549312, '测试管理员01580',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:20:47 <sb>内存使用率</sb>达到<sr><b>83.02%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (164, 30, 3010, 1, 768821468053254144, '7tqss9cf租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:20:47 <sb>内存使用率</sb>达到<sr><b>83.02%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (165, 30, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>localhost</b></sb>(<sb 0>127.0.0.1</sb>) 2024-04-20 21:20:47 <sb>内存使用率</sb>达到<sr><b>83.02%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (166, 10, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>本地</b></sb>(<sb 0>${host}</sb>) ${time} <sb>${type}</sb>达到<sr><b>${value}%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (167, 10, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>本地</b></sb>(<sb 0>${host}</sb>) ${time} <sb>${type}</sb>达到<sr><b>${value}%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (168, 10, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>本地</b></sb>(<sb 0>${host}</sb>) ${time} <sb>${type}</sb>达到<sr><b>${value}%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (169, 10, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>本地</b></sb>(<sb 0>${host}</sb>) ${time} <sb>${type}</sb>达到<sr><b>${value}%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (170, 10, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>本地</b></sb>(<sb 0>${host}</sb>) ${time} <sb>${type}</sb>达到<sr><b>${value}%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL),
       (171, 10, 3010, 1, 768460662077796352, 'hzrany租户管理员',
        '机器 <sb 0><b>本地</b></sb>(<sb 0>${host}</sb>) ${time} <sb>${type}</sb>达到<sr><b>${value}%</b></sr>, 请及时排查!',
        936499355525984256, '0', NULL, NULL);
/*!40000 ALTER TABLE `web_side_message` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `webhook_config`
--

DROP TABLE IF EXISTS `webhook_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `webhook_config`
(
    `id`             bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `webhook_name`   varchar(64) COLLATE utf8mb3_bin   DEFAULT NULL COMMENT '名称',
    `webhook_url`    varchar(2048) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'url',
    `webhook_type`   int                               DEFAULT NULL COMMENT '类型 10: 钉钉机器人',
    `webhook_config` varchar(2048) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '配置项 json',
    `is_deleted`     varchar(5) COLLATE utf8mb3_bin    DEFAULT '0' COMMENT '是否删除 1未删除 2已删除',
    `gmt_create`     datetime(4) DEFAULT CURRENT_TIMESTAMP (4) COMMENT '创建时间',
    `gmt_modified`   datetime(4) DEFAULT CURRENT_TIMESTAMP (4) ON UPDATE CURRENT_TIMESTAMP (4) COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC COMMENT='webhook 配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `webhook_config`
--

LOCK
TABLES `webhook_config` WRITE;
/*!40000 ALTER TABLE `webhook_config` DISABLE KEYS */;
INSERT INTO `webhook_config`
VALUES (1, 'name', 'https://oapi.dingtalk.com/robot/send?access_token=xxx', 10, 'sadasdagsdfgs', '1',
        '2024-04-04 20:06:10.7720', '2024-04-04 20:06:10.7720'),
       (2, 'dfasdfasdf', 'https://oapi.dingtalk.com/robot/send?access_token=xxx', 10, 'sadasdafasfads', '1',
        '2024-04-06 15:52:02.0000', '2024-04-06 15:52:08.6408'),
       (3, 'Cova', 'https://oapi.dingtalk.com/robot/send?access_token=xxx', 10, '', '0', '2024-04-06 15:52:03.0000',
        '2024-04-14 20:37:35.6420'),
       (4, 'asdasfsa', 'https://oapi.dingtalk.com/robot/send?access_token=xxx', 10, 'sadasdas', '1',
        '2024-04-06 12:34:43.0940', '2024-04-06 12:34:43.0960'),
       (5, '小尼', 'https://oapi.dingtalk.com/robot/send?access_token=xxx', 10, NULL, '0', '2024-04-14 20:36:41.6310',
        '2024-04-14 20:36:41.6310');
/*!40000 ALTER TABLE `webhook_config` ENABLE KEYS */;
UNLOCK
TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-25 20:13:09
