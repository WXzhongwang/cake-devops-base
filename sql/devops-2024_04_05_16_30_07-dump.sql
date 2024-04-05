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
CREATE TABLE `alarm_group` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `group_name` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '报警组名称',
  `group_description` varchar(128) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '报警组描述',
  `is_deleted` varchar(5) COLLATE utf8mb3_bin DEFAULT '1' COMMENT '是否删除 0未删除 1已删除',
  `gmt_create` datetime(4) DEFAULT CURRENT_TIMESTAMP(4) COMMENT '创建时间',
  `gmt_modified` datetime(4) DEFAULT CURRENT_TIMESTAMP(4) ON UPDATE CURRENT_TIMESTAMP(4) COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC COMMENT='报警组';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarm_group`
--

LOCK TABLES `alarm_group` WRITE;
/*!40000 ALTER TABLE `alarm_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `alarm_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alarm_group_notify`
--

DROP TABLE IF EXISTS `alarm_group_notify`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alarm_group_notify` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `group_id` bigint DEFAULT NULL COMMENT '报警组id',
  `notify_id` bigint DEFAULT NULL COMMENT '通知id',
  `notify_type` int DEFAULT NULL COMMENT '通知类型 10 webhook',
  `is_deleted` varchar(5) COLLATE utf8mb3_bin DEFAULT '0' COMMENT '是否删除 1未删除 2已删除',
  `gmt_create` datetime(4) DEFAULT CURRENT_TIMESTAMP(4) COMMENT '创建时间',
  `gmt_modified` datetime(4) DEFAULT CURRENT_TIMESTAMP(4) ON UPDATE CURRENT_TIMESTAMP(4) COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `group_idx` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC COMMENT='报警组通知方式';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarm_group_notify`
--

LOCK TABLES `alarm_group_notify` WRITE;
/*!40000 ALTER TABLE `alarm_group_notify` DISABLE KEYS */;
/*!40000 ALTER TABLE `alarm_group_notify` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alarm_group_user`
--

DROP TABLE IF EXISTS `alarm_group_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alarm_group_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `group_id` bigint DEFAULT NULL COMMENT '报警组id',
  `account_id` bigint DEFAULT NULL COMMENT '报警组成员id',
  `username` varchar(32) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '报警组成员用户名',
  `is_deleted` varchar(5) COLLATE utf8mb3_bin DEFAULT '0' COMMENT '是否删除 0未删除 1已删除',
  `gmt_create` datetime(4) DEFAULT CURRENT_TIMESTAMP(4) COMMENT '创建时间',
  `gmt_modified` datetime(4) DEFAULT CURRENT_TIMESTAMP(4) ON UPDATE CURRENT_TIMESTAMP(4) COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `group_id_idx` (`group_id`,`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC COMMENT='报警组成员';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alarm_group_user`
--

LOCK TABLES `alarm_group_user` WRITE;
/*!40000 ALTER TABLE `alarm_group_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `alarm_group_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app`
--

DROP TABLE IF EXISTS `app`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `app_id` varchar(64) NOT NULL,
  `app_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `repo` varchar(255) DEFAULT NULL,
  `default_branch` varchar(255) DEFAULT NULL,
  `language` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `develop_mode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `owner` bigint DEFAULT NULL,
  `health_check` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` char(4) DEFAULT '0',
  `is_deleted` char(4) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `department_abbreviation` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `webhook` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=781513981771788289 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app`
--

LOCK TABLES `app` WRITE;
/*!40000 ALTER TABLE `app` DISABLE KEYS */;
INSERT INTO `app` VALUES (1,'1','cake-devops-base','测试应用001','https://github.com/WXzhongwang/cake-devops-base.git','origin/main','JAVA','FREEDOM',768821468053254144,'/ok','0','0','2023-02-03 23:44:31','2024-01-15 21:27:57','Honda','cake-honda',NULL,NULL,'https://oapi.dingtalk.com/robot/send?access_token=89ca235dbe9f617f4ca045a1f24b0e61a32e9f845771752416377089c36470b7');
/*!40000 ALTER TABLE `app` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_env`
--

DROP TABLE IF EXISTS `app_env`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_env` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cluster_id` varchar(64) NOT NULL,
  `app_id` varchar(64) NOT NULL,
  `domains` varchar(255) DEFAULT NULL,
  `env_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `env` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `replicas` int DEFAULT NULL,
  `cpu` varchar(64) DEFAULT NULL,
  `max_cpu` varchar(64) DEFAULT NULL,
  `memory` varchar(64) DEFAULT NULL,
  `max_memory` varchar(64) DEFAULT NULL,
  `auto_scaling` char(1) DEFAULT NULL,
  `need_approval` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` char(4) DEFAULT '0',
  `is_deleted` char(4) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `env_id` varchar(64) NOT NULL,
  `deploy_status` varchar(4) NOT NULL,
  `progress` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_env`
--

LOCK TABLES `app_env` WRITE;
/*!40000 ALTER TABLE `app_env` DISABLE KEYS */;
INSERT INTO `app_env` VALUES (1,'1','1','cake-devops.rany.com','开发环境','TEST',1,'1C','1C','500M','1024M','0','0','1','0','2023-11-23 20:51:15','2024-01-21 15:52:16',NULL,NULL,'897615291213819903','0','{\"current\":9,\"endDate\":1705823536458,\"pipeKey\":\"202401211548290008\",\"startDate\":1705823311955,\"status\":\"error\",\"steps\":[{\"cost\":0,\"description\":\"EXECUTED\",\"endDate\":1705823312238,\"startDate\":1705823312222,\"title\":\"发布审批校验\"},{\"cost\":0,\"description\":\"EXECUTED\",\"endDate\":1705823312261,\"startDate\":1705823312261,\"title\":\"封网校验\"},{\"cost\":0,\"description\":\"EXECUTED\",\"endDate\":1705823312387,\"startDate\":1705823312287,\"title\":\"工作机选择\"},{\"cost\":2,\"description\":\"EXECUTED\",\"endDate\":1705823315081,\"startDate\":1705823312493,\"title\":\"创建工作空间\"},{\"cost\":71,\"description\":\"EXECUTED\",\"endDate\":1705823386261,\"startDate\":1705823315213,\"title\":\"拉取代码\"},{\"cost\":48,\"description\":\"EXECUTED\",\"endDate\":1705823435426,\"startDate\":1705823386433,\"title\":\"Maven构建\"},{\"cost\":61,\"description\":\"EXECUTED\",\"endDate\":1705823496579,\"startDate\":1705823435478,\"title\":\"sonar代码扫描\"},{\"cost\":22,\"description\":\"EXECUTED\",\"endDate\":1705823519117,\"startDate\":1705823496708,\"title\":\"镜像构建\"},{\"cost\":17,\"description\":\"EXECUTED\",\"endDate\":1705823536394,\"startDate\":1705823519168,\"title\":\"推送镜像到阿里云\"}]}'),(2,'1','1',NULL,'测试环境新增001','TEST',2,'0.5C','1C','500M','1024M','0','0','1','0','2023-12-21 22:38:04','2024-01-18 22:47:17',NULL,NULL,'897615291213819904','0',NULL),(3,'1','1','pre.cake.com','预发','PRE',2,'1C','1C','1024M','1C','0','0','0','0',NULL,'2024-01-18 22:47:17',NULL,NULL,'898888447652147200','0',NULL);
/*!40000 ALTER TABLE `app_env` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `app_member`
--

DROP TABLE IF EXISTS `app_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_member` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `member_id` varchar(64) NOT NULL,
  `account_id` varchar(64) NOT NULL,
  `app_id` varchar(64) DEFAULT NULL,
  `roles` varchar(512) DEFAULT NULL,
  `status` char(4) DEFAULT '0',
  `is_deleted` char(4) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=781514013132599297 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_member`
--

LOCK TABLES `app_member` WRITE;
/*!40000 ALTER TABLE `app_member` DISABLE KEYS */;
INSERT INTO `app_member` VALUES (781514010909618176,'1','768821468053254144','1','OWNER,DEVELOPER,TESTER,OPERATOR,ARCHITECT','0','0','2023-02-03 23:44:31','2024-01-08 23:46:09',NULL,NULL),(781514013132599296,'2','781488231601549312','1','DEVELOPER,CHECKER,REPORTER','1','1','2023-02-03 23:44:31','2024-01-08 23:47:30',NULL,NULL);
/*!40000 ALTER TABLE `app_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `approval`
--

DROP TABLE IF EXISTS `approval`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `approval` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `approval_id` varchar(64) NOT NULL,
  `doc_address` varchar(500) NOT NULL,
  `change_date` datetime NOT NULL,
  `approval_status` varchar(64) NOT NULL,
  `comment` varchar(64) NOT NULL,
  `is_deleted` char(4) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `approval`
--

LOCK TABLES `approval` WRITE;
/*!40000 ALTER TABLE `approval` DISABLE KEYS */;
INSERT INTO `approval` VALUES (1,'906357378688102400','http://yuque.com','2024-01-14 11:47:16','APPROVED','备注',NULL,NULL,'2024-01-16 21:31:00',NULL,NULL),(2,'906357551837360128','http://yuque.com','2024-01-14 11:47:47','APPROVED','备注',NULL,NULL,'2024-01-16 21:31:06',NULL,NULL),(3,'906359020359659520','http://yuque.com','2024-01-14 11:53:47','APPROVED','备注内容','0','2024-01-14 11:54:00','2024-01-16 21:31:12',NULL,NULL),(4,'906669494347378688','http://yuque.com','2024-01-31 08:27:26','APPROVED','备注','0','2024-01-15 08:27:43','2024-01-16 21:31:17',NULL,NULL),(5,'908309878756487168','http://yuque.com','2024-01-31 21:05:37','AUTO_APPROVED','v2.0.0-beta','0','2024-01-19 21:06:01','2024-01-19 21:06:01',NULL,NULL),(6,'908309900323598336','http://yuque.com','2024-01-31 21:05:37','AUTO_APPROVED','v2.0.0-beta','0','2024-01-19 21:06:06','2024-01-19 21:06:06',NULL,NULL);
/*!40000 ALTER TABLE `approval` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cluster`
--

DROP TABLE IF EXISTS `cluster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cluster` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cluster_id` varchar(64) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
  `version` varchar(255) DEFAULT NULL,
  `cluster_type` char(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `status` char(4) DEFAULT NULL,
  `is_deleted` char(4) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `connection_string` varchar(200) DEFAULT NULL,
  `token` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cluster`
--

LOCK TABLES `cluster` WRITE;
/*!40000 ALTER TABLE `cluster` DISABLE KEYS */;
INSERT INTO `cluster` VALUES (1,'1','test-clueter-01','test,dev','1.19','K8S','0','0','2023-11-23 08:47:32','2024-01-07 20:03:06',NULL,NULL,'127.0.0.1:8080','token123');
/*!40000 ALTER TABLE `cluster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `group_host`
--

DROP TABLE IF EXISTS `group_host`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `group_host` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `group_id` varchar(64) NOT NULL,
  `host_id` varchar(64) NOT NULL,
  `is_deleted` char(4) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_host`
--

LOCK TABLES `group_host` WRITE;
/*!40000 ALTER TABLE `group_host` DISABLE KEYS */;
INSERT INTO `group_host` VALUES (1,'1','1','0','2023-11-23 21:41:10','2023-11-23 21:41:10',NULL,NULL),(2,'3','903926807307366400','0',NULL,NULL,NULL,NULL),(3,'2','903926807307366400','0',NULL,NULL,NULL,NULL),(4,'1','936105566084935680','0','2023-11-23 21:41:10','2023-11-23 21:41:10',NULL,NULL),(5,'5','936106267217375232','0','2024-04-05 13:58:56','2024-04-05 13:58:56','768460662077796352',NULL),(6,'5','936107600582422528','0','2024-04-05 14:04:14','2024-04-05 14:04:14','768460662077796352',NULL),(7,'4','936107600582422528','0','2024-04-05 14:04:14','2024-04-05 14:04:14','768460662077796352',NULL),(8,'1','936143894498914304','0','2024-04-05 16:28:27','2024-04-05 16:28:27','768460662077796352',NULL);
/*!40000 ALTER TABLE `group_host` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `host`
--

DROP TABLE IF EXISTS `host`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `host` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `host_id` varchar(64) NOT NULL,
  `name` varchar(255) NOT NULL,
  `host_name` varchar(255) NOT NULL,
  `server_addr` varchar(255) DEFAULT NULL,
  `port` int NOT NULL,
  `auth_type` int DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `proxy_id` bigint DEFAULT NULL COMMENT '代理ID',
  `key_id` bigint DEFAULT NULL COMMENT '秘钥ID',
  `status` char(4) DEFAULT '0',
  `is_deleted` char(4) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `desc` varchar(255) DEFAULT NULL,
  `verified` char(2) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `host`
--

LOCK TABLES `host` WRITE;
/*!40000 ALTER TABLE `host` DISABLE KEYS */;
INSERT INTO `host` VALUES (1,'1','测试机器1','v-local-test01','127.0.0.1',22,1,'yuanjinxiu','131400',NULL,NULL,'0','1','2023-11-23 21:40:54','2024-04-05 15:51:44','本机','1',NULL,'768460662077796352'),(2,'2','测试机器2','v-local-test02','127.0.0.1',22,1,'yuanjinxiu','131400',NULL,NULL,'0','1','2023-11-23 21:40:54','2024-04-05 15:51:46','本机','1',NULL,'768460662077796352'),(3,'3','测试机器3','v-local-test03','127.0.0.1',22,1,'yuanjinxiu','131400',NULL,NULL,'0','1','2023-11-23 21:40:54','2024-04-05 15:51:48','本机','1',NULL,'768460662077796352'),(4,'4','测试机器4','v-local-test04','127.0.0.1',22,1,'yuanjinxiu','131400',NULL,NULL,'0','1','2023-11-23 21:40:54','2024-04-05 15:51:50','本机','1',NULL,'768460662077796352'),(5,'5','测试机器5','v-local-test05','127.0.0.1',22,1,'yuanjinxiu','131400',NULL,NULL,'0','1','2023-11-23 21:40:54','2024-04-05 15:51:53','本机','1',NULL,'768460662077796352'),(6,'6','测试机器6','v-local-test06','127.0.0.1',22,1,'yuanjinxiu','131400',NULL,NULL,'0','1','2023-11-23 21:40:54','2024-04-05 15:51:55','本机','1',NULL,'768460662077796352'),(7,'7','测试机器7','v-local-test07','127.0.0.1',22,1,'yuanjinxiu','131400',NULL,NULL,'0','1','2023-11-23 21:40:54','2024-04-05 15:51:57','本机','1',NULL,'768460662077796352'),(8,'8','测试机器8','v-local-test08','127.0.0.1',22,1,'yuanjinxiu','131400',NULL,NULL,'0','1','2023-11-23 21:40:54','2024-04-05 15:51:59','本机','1',NULL,'768460662077796352'),(9,'9','测试机器9','v-local-test09','127.0.0.1',22,1,'yuanjinxiu','131400',NULL,NULL,'0','1','2023-11-23 21:40:54','2024-04-05 15:52:01','本机','1',NULL,'768460662077796352'),(10,'10','测试机器10','v-local-test10','127.0.0.1',22,1,'yuanjinxiu','131400',NULL,NULL,'0','1','2023-11-23 21:40:54','2024-04-05 15:52:04','本机','1',NULL,'768460662077796352'),(11,'11','测试机器11','v-local-test11','127.0.0.1',22,1,'yuanjinxiu','131400',NULL,NULL,'0','1','2023-11-23 21:40:54','2024-04-05 15:52:07','本机','1',NULL,'768460662077796352'),(12,'12','测试机器12','v-local-test12','127.0.0.1',22,1,'yuanjinxiu','131400',NULL,NULL,'0','1','2023-11-23 21:40:54','2024-04-05 15:52:10','本机','1',NULL,'768460662077796352'),(13,'13','测试机器13','v-local-test13','127.0.0.1',22,1,'yuanjinxiu','131400',NULL,NULL,'0','1','2023-11-23 21:40:54','2024-04-05 15:52:15','本机','1',NULL,'768460662077796352'),(14,'14','测试机器14','v-local-test14','127.0.0.1',22,1,'yuanjinxiu','131400',NULL,NULL,'0','1','2023-11-23 21:40:54','2024-04-05 15:52:12','本机','1',NULL,'768460662077796352'),(15,'903920328227565568','1','1','1',1,1,'1','1',NULL,NULL,'0','1','2024-01-07 18:23:31','2024-04-05 15:33:42','本机','1',NULL,'768460662077796352'),(16,'903926807307366400','2','2','2222',2,1,'2','2',NULL,NULL,'0','1','2024-01-07 18:49:15','2024-04-05 14:04:31',NULL,NULL,NULL,'768460662077796352'),(17,'936105566084935680','测试机器1','v-local-test01','127.0.0.1',22,1,'yuanjinxiu','131400',NULL,NULL,'0','1','2024-04-05 13:56:09','2024-04-05 15:38:29','本机','1',NULL,'768460662077796352'),(18,'936106267217375232','测试机器1','v-local-test0333333','127.0.0.1',22,NULL,'yuanjinxiu','131400',NULL,NULL,'0','1','2024-04-05 13:58:56','2024-04-05 15:51:42',NULL,NULL,'768460662077796352','768460662077796352'),(19,'936106386234945536','2','2','2222',2,1,'2','2',NULL,NULL,'0','1','2024-04-05 13:59:25','2024-04-05 15:38:26',NULL,NULL,NULL,'768460662077796352'),(20,'936106405394526208','2','2','2222',2,1,'2','2',NULL,NULL,'0','1','2024-04-05 13:59:29','2024-04-05 15:38:24',NULL,NULL,NULL,'768460662077796352'),(21,'936107600582422528','测试机器1','v-local-test0333333','127.0.0.1',22,NULL,'yuanjinxiu','131400',NULL,NULL,'0','1','2024-04-05 14:04:14','2024-04-05 14:15:40',NULL,NULL,'768460662077796352','768460662077796352'),(22,'936130145763340288','测试机器1的副本','v-local-test0333333','127.0.0.1',22,NULL,'yuanjinxiu','131400',NULL,NULL,'0','0','2024-04-05 15:33:49','2024-04-05 15:38:14',NULL,NULL,NULL,NULL),(23,'936130858279120896','测试机器1的副本','v-local-test0333333','127.0.0.1',22,NULL,'yuanjinxiu','131400',NULL,NULL,'0','0','2024-04-05 15:36:39','2024-04-05 15:38:14',NULL,NULL,NULL,NULL),(24,'936130912259813376','测试机器1的副本','v-local-test0333333','127.0.0.1',22,NULL,'yuanjinxiu','131400',NULL,NULL,'0','0','2024-04-05 15:36:52','2024-04-05 15:38:14',NULL,NULL,NULL,NULL),(25,'936131727267606528','测试机器1的副本的副本','v-local-test0333333','127.0.0.1',22,NULL,'yuanjinxiu','131400',NULL,NULL,'0',NULL,'2024-04-05 15:40:06','2024-04-05 15:40:06',NULL,NULL,NULL,NULL),(26,'936132018520076288','测试机器1的副本的副本','v-local-test0333333','127.0.0.1',22,NULL,'yuanjinxiu','131400',NULL,NULL,'0',NULL,'2024-04-05 15:41:16','2024-04-05 15:41:16',NULL,NULL,NULL,NULL),(27,'936132187063988224','测试机器1的副本的副本','v-local-test0333333','127.0.0.1',22,NULL,'yuanjinxiu','131400',NULL,NULL,'0',NULL,'2024-04-05 15:41:56','2024-04-05 15:41:56',NULL,NULL,NULL,NULL),(28,'936134522712502272','测试机器1的副本的副本','v-local-test0333333','127.0.0.1',22,NULL,'yuanjinxiu','131400',NULL,NULL,'0','0','2024-04-05 15:51:13','2024-04-05 15:51:13',NULL,NULL,NULL,NULL),(29,'936139687184183296','测试机器1的副本的副本的副本','v-local-test0333333','127.0.0.1',22,NULL,'yuanjinxiu','131400',NULL,NULL,'0','0','2024-04-05 16:11:44','2024-04-05 16:11:44',NULL,NULL,NULL,NULL),(30,'936143894498914304','sfasdfasdf','127.0.0.2','127.0.0.2',22,NULL,'kkk','asdasdas',NULL,NULL,'0','0','2024-04-05 16:28:27','2024-04-05 16:28:27',NULL,NULL,'768460662077796352',NULL);
/*!40000 ALTER TABLE `host` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `host_alarm_config`
--

DROP TABLE IF EXISTS `host_alarm_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `host_alarm_config` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `host_id` varchar(64) COLLATE utf8mb3_bin NOT NULL COMMENT '机器ID',
  `alarm_type` int DEFAULT NULL COMMENT '报警类型 10: cpu使用率 20: 内存使用率',
  `alarm_threshold` double DEFAULT NULL COMMENT '报警阈值',
  `trigger_threshold` int DEFAULT NULL COMMENT '触发报警阈值 次',
  `notify_silence` int DEFAULT NULL COMMENT '报警通知沉默时间 分',
  `is_deleted` varchar(5) COLLATE utf8mb3_bin DEFAULT '1' COMMENT '是否删除 0未删除 1已删除',
  `gmt_create` datetime(4) DEFAULT CURRENT_TIMESTAMP(4) COMMENT '创建时间',
  `gmt_modified` datetime(4) DEFAULT CURRENT_TIMESTAMP(4) ON UPDATE CURRENT_TIMESTAMP(4) COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC COMMENT='机器报警配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `host_alarm_config`
--

LOCK TABLES `host_alarm_config` WRITE;
/*!40000 ALTER TABLE `host_alarm_config` DISABLE KEYS */;
/*!40000 ALTER TABLE `host_alarm_config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `host_alarm_group`
--

DROP TABLE IF EXISTS `host_alarm_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `host_alarm_group` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `host_id` varchar(64) NOT NULL,
  `alarm_group_id` bigint NOT NULL,
  `is_deleted` char(4) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `host_alarm_group`
--

LOCK TABLES `host_alarm_group` WRITE;
/*!40000 ALTER TABLE `host_alarm_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `host_alarm_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `host_alarm_history`
--

DROP TABLE IF EXISTS `host_alarm_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `host_alarm_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `host_id` varchar(64) COLLATE utf8mb3_bin NOT NULL COMMENT '机器ID',
  `alarm_type` int DEFAULT NULL COMMENT '报警类型 10: cpu使用率 20: 内存使用率',
  `alarm_value` double DEFAULT NULL COMMENT '报警值',
  `alarm_time` datetime(4) DEFAULT NULL COMMENT '报警时间',
  `is_deleted` varchar(5) COLLATE utf8mb3_bin DEFAULT '1' COMMENT '是否删除 0未删除 1已删除',
  `gmt_create` datetime(4) DEFAULT CURRENT_TIMESTAMP(4) COMMENT '创建时间',
  `gmt_modified` datetime(4) DEFAULT CURRENT_TIMESTAMP(4) ON UPDATE CURRENT_TIMESTAMP(4) COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC COMMENT='机器报警历史值';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `host_alarm_history`
--

LOCK TABLES `host_alarm_history` WRITE;
/*!40000 ALTER TABLE `host_alarm_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `host_alarm_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `host_env`
--

DROP TABLE IF EXISTS `host_env`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `host_env` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `host_id` varchar(64) NOT NULL,
  `attr_key` varchar(64) NOT NULL,
  `attr_value` varchar(256) NOT NULL,
  `is_deleted` char(4) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `host_env`
--

LOCK TABLES `host_env` WRITE;
/*!40000 ALTER TABLE `host_env` DISABLE KEYS */;
/*!40000 ALTER TABLE `host_env` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `host_group`
--

DROP TABLE IF EXISTS `host_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `host_group` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `host_group_id` varchar(64) NOT NULL,
  `name` varchar(255) NOT NULL,
  `parent_id` varchar(64) DEFAULT NULL,
  `sort` int NOT NULL,
  `is_deleted` char(4) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `host_group`
--

LOCK TABLES `host_group` WRITE;
/*!40000 ALTER TABLE `host_group` DISABLE KEYS */;
INSERT INTO `host_group` VALUES (1,'1','package',NULL,0,'0','2023-11-23 21:39:52','2024-01-05 19:55:39',NULL,NULL),(2,'2','测试环境机器组','1',1,'0','2024-01-05 20:12:32','2024-01-06 16:27:25',NULL,NULL),(5,'3','预发环境机器组',NULL,1,'0','2024-01-06 16:26:42','2024-01-06 16:27:25',NULL,NULL),(6,'4','线上环境机器组',NULL,1,'0','2024-01-06 16:26:42','2024-01-06 16:27:25',NULL,NULL),(7,'5','日常测试机器组',NULL,1,'0','2024-01-06 16:26:42','2024-01-06 16:27:25',NULL,NULL);
/*!40000 ALTER TABLE `host_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `host_monitor`
--

DROP TABLE IF EXISTS `host_monitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `host_monitor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `host_id` varchar(64) DEFAULT NULL COMMENT '机器id',
  `monitor_status` int DEFAULT '1' COMMENT '监控状态 1未启动 2启动中 3运行中',
  `monitor_url` varchar(512) DEFAULT NULL COMMENT '请求 api url',
  `access_token` varchar(512) DEFAULT NULL COMMENT '请求 api accessToken',
  `agent_version` varchar(16) DEFAULT NULL COMMENT '插件版本',
  `is_deleted` char(4) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='机器监控配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `host_monitor`
--

LOCK TABLES `host_monitor` WRITE;
/*!40000 ALTER TABLE `host_monitor` DISABLE KEYS */;
/*!40000 ALTER TABLE `host_monitor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `namespace`
--

DROP TABLE IF EXISTS `namespace`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `namespace` (
  `id` bigint NOT NULL,
  `namespace_id` varchar(64) NOT NULL,
  `cluster_id` varchar(64) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `request_cpu` decimal(12,4) DEFAULT NULL,
  `request_memory` decimal(12,4) DEFAULT '0.0000',
  `limit_cpu` decimal(12,4) DEFAULT NULL,
  `limit_memory` decimal(12,4) DEFAULT NULL,
  `max_pods` int DEFAULT NULL,
  `max_gpu` decimal(12,4) DEFAULT NULL,
  `max_services` int DEFAULT NULL,
  `status` char(4) DEFAULT NULL,
  `is_deleted` char(4) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `namespace`
--

LOCK TABLES `namespace` WRITE;
/*!40000 ALTER TABLE `namespace` DISABLE KEYS */;
INSERT INTO `namespace` VALUES (1,'1','1','cake-devops-base',20.0000,20.0000,40.0000,60.0000,200,0.0000,200,'1','0','2023-11-23 20:49:37','2024-01-16 21:22:14',NULL,NULL);
/*!40000 ALTER TABLE `namespace` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `release_no`
--

DROP TABLE IF EXISTS `release_no`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `release_no` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `release_id` varchar(64) NOT NULL,
  `app_id` varchar(64) NOT NULL,
  `release_no` varchar(64) DEFAULT NULL,
  `approval_id` varchar(64) DEFAULT NULL,
  `release_date` datetime NOT NULL,
  `release_branch` varchar(255) NOT NULL,
  `release_commit_id` varchar(255) DEFAULT NULL,
  `release_version` varchar(255) DEFAULT NULL,
  `env_id` varchar(64) NOT NULL,
  `release_status` varchar(255) NOT NULL,
  `rollback` varchar(64) DEFAULT NULL,
  `rollback_id` varchar(64) DEFAULT NULL,
  `is_deleted` char(4) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `release_no`
--

LOCK TABLES `release_no` WRITE;
/*!40000 ALTER TABLE `release_no` DISABLE KEYS */;
INSERT INTO `release_no` VALUES (2,'906359016366682112','1','R202401141153590004','906359020359659520','2024-01-14 11:53:47','main',NULL,'v1.0.0','897615291213819903','FAILED',NULL,NULL,'0','2024-01-14 11:54:00','2024-01-21 15:52:16',NULL,NULL),(3,'906669481965793280','1','R202401150827390005','906669494347378688','2024-01-31 08:27:26','main',NULL,'v1.1.0','897615291213819903','CLOSED',NULL,NULL,'0','2024-01-15 08:27:43','2024-01-20 11:02:44',NULL,NULL),(4,'908309875333935104','1','R202401192106000006','908309878756487168','2024-01-31 21:05:37','develop',NULL,'v2.0.0-beta','897615291213819903','CLOSED',NULL,NULL,'0','2024-01-19 21:06:01','2024-01-20 10:46:01',NULL,NULL),(5,'908309900264878080','1','R202401192106060007','908309900323598336','2024-01-31 21:05:37','develop',NULL,'v2.0.0-beta','897615291213819903','CLOSED',NULL,NULL,'0','2024-01-19 21:06:06','2024-01-20 10:45:06',NULL,NULL);
/*!40000 ALTER TABLE `release_no` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `script_template`
--

DROP TABLE IF EXISTS `script_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `script_template` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `template_name` varchar(32) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '模板名称',
  `template_value` varchar(2048) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '模板命令',
  `description` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '命令描述',
  `is_deleted` char(4) COLLATE utf8mb3_bin DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `modifier` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC COMMENT='命令模板表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `script_template`
--

LOCK TABLES `script_template` WRITE;
/*!40000 ALTER TABLE `script_template` DISABLE KEYS */;
/*!40000 ALTER TABLE `script_template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `server_key`
--

DROP TABLE IF EXISTS `server_key`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `server_key` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `display_name` varchar(255) DEFAULT NULL,
  `account_type` varchar(32) DEFAULT NULL COMMENT '账号类型0普通，1管理员',
  `protocol` varchar(32) DEFAULT NULL COMMENT '协议，当前支持ssh',
  `active` char(4) DEFAULT '1',
  `credential` varchar(255) DEFAULT NULL,
  `public_key` varchar(255) DEFAULT NULL,
  `passphrase` varchar(255) DEFAULT NULL,
  `is_deleted` char(4) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `key_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='机器秘钥';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `server_key`
--

LOCK TABLES `server_key` WRITE;
/*!40000 ALTER TABLE `server_key` DISABLE KEYS */;
INSERT INTO `server_key` VALUES (5,'kkkk','0','SSH','1','bvvv',NULL,'nnnn','1','2024-04-04 14:34:56','2024-04-04 16:16:02',NULL,'768460662077796352','/660e49f61fb6000064e76f59_id_rsa'),(6,'aasdasdas','1','SSH','1',NULL,NULL,'7t3ozVJWiq+CVx+bMG31Lg==','0','2024-04-04 15:09:29','2024-04-04 19:35:58',NULL,'768460662077796352','/660e52211fb6000015b26bc8_id_rsa'),(7,'cccc','0','SSH','1','xcxc',NULL,'cccc','0','2024-04-04 15:29:27','2024-04-04 15:29:27',NULL,NULL,'/660e56d61fb6000072441d0f_id_rsa'),(8,'tals','0','SSH','1','ccc',NULL,'sda','0','2024-04-04 15:32:01','2024-04-04 15:32:01',NULL,NULL,'/660e57711fb6000072441d10_id_rsa'),(9,'s da s da','0','SSH','1','cccc',NULL,'asdadsa','0','2024-04-04 15:35:00','2024-04-04 15:35:00',NULL,NULL,'/660e58231fb6000072441d11_id_rsa'),(10,'xdsfsdc','0','SSH','true','cccc',NULL,'cccc','1','2024-04-04 15:49:06','2024-04-04 19:36:15',NULL,'768460662077796352','/660e5b711fb6000059b5de16_id_rsa'),(11,'777','0','SSH','true','kkk',NULL,'kk','0','2024-04-04 15:53:19','2024-04-04 15:53:19',NULL,NULL,'/660e5c6e1fb6000059b5de17_id_rsa'),(12,'cccc','0','SSH','true','cccc',NULL,'cccc','0','2024-04-04 16:00:54','2024-04-04 16:00:54','768460662077796352',NULL,'/660e5e351fb600004a996541_id_rsa'),(13,'sfadsf','1','SSH','1',NULL,NULL,'asfsad','0','2024-04-04 19:23:15','2024-04-04 19:23:15','768460662077796352',NULL,'/660e8da21fb60000724f8fcc_id_rsa');
/*!40000 ALTER TABLE `server_key` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `server_load_monitor`
--

DROP TABLE IF EXISTS `server_load_monitor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `server_load_monitor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `host_id` varchar(64) NOT NULL,
  `cpu_rate` decimal(10,2) DEFAULT NULL,
  `mem_rate` decimal(10,2) DEFAULT NULL,
  `one_min_load_avg` decimal(10,2) DEFAULT NULL,
  `five_min_load_avg` decimal(10,2) DEFAULT NULL,
  `fif_min_load_avg` decimal(10,2) DEFAULT NULL,
  `is_deleted` char(4) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `server_load_monitor`
--

LOCK TABLES `server_load_monitor` WRITE;
/*!40000 ALTER TABLE `server_load_monitor` DISABLE KEYS */;
/*!40000 ALTER TABLE `server_load_monitor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `server_proxy`
--

DROP TABLE IF EXISTS `server_proxy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `server_proxy` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `proxy_host` varchar(128) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '代理主机',
  `proxy_port` int DEFAULT NULL COMMENT '代理端口',
  `proxy_username` varchar(128) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '代理用户名',
  `proxy_password` varchar(512) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '代理密码',
  `proxy_type` int DEFAULT NULL COMMENT '代理类型 1http代理 2socket4代理 3socket5代理',
  `description` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '描述',
  `is_deleted` varchar(5) COLLATE utf8mb3_bin DEFAULT '0' COMMENT '是否删除 1未删除 2已删除',
  `gmt_create` datetime(4) DEFAULT CURRENT_TIMESTAMP(4) COMMENT '创建时间',
  `gmt_modified` datetime(4) DEFAULT CURRENT_TIMESTAMP(4) ON UPDATE CURRENT_TIMESTAMP(4) COMMENT '修改时间',
  `creator` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  `modifier` varchar(255) COLLATE utf8mb3_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC COMMENT='机器代理';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `server_proxy`
--

LOCK TABLES `server_proxy` WRITE;
/*!40000 ALTER TABLE `server_proxy` DISABLE KEYS */;
INSERT INTO `server_proxy` VALUES (1,'127.0.0.1',5432,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL),(2,'127.0.0.1',23,'kkk','kkk',1,NULL,NULL,NULL,NULL,NULL,NULL),(3,'127.0.0.1',22,'kkk','kkk',1,NULL,'1','2024-04-04 16:56:26.6490','2024-04-04 16:56:26.6510','768460662077796352',NULL),(4,'127.0.0.1',22,'kkk','ccc',1,NULL,'1','2024-04-04 16:56:52.3180','2024-04-04 16:56:52.3180','768460662077796352',NULL),(5,'127.0.0.1',22,'kkk','kkk',1,'dsadasd','0','2024-04-04 17:01:47.2280','2024-04-04 17:01:47.2300','768460662077796352',NULL),(6,'127.0.0.2',34,'kkk','kkksadas',2,'asdasdsa','0','2024-04-04 19:03:51.4940','2024-04-04 19:03:51.4970','768460662077796352',NULL);
/*!40000 ALTER TABLE `server_proxy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system_env`
--

DROP TABLE IF EXISTS `system_env`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_env` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `attr_key` varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'key',
  `attr_value` varchar(2048) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT 'value',
  `system_env` int DEFAULT NULL COMMENT '是否为系统变量 1是 2否',
  `description` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '描述',
  `is_deleted` varchar(5) DEFAULT '1' COMMENT '是否删除',
  `gmt_create` datetime(4) DEFAULT CURRENT_TIMESTAMP(4) COMMENT '创建时间',
  `gmt_modified` datetime(4) DEFAULT CURRENT_TIMESTAMP(4) ON UPDATE CURRENT_TIMESTAMP(4) COMMENT '修改时间',
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `key_idx` (`attr_key`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统环境变量';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system_env`
--

LOCK TABLES `system_env` WRITE;
/*!40000 ALTER TABLE `system_env` DISABLE KEYS */;
INSERT INTO `system_env` VALUES (1,'key_path','/Users/yuanjinxiu/cake-ops/.keys',2,'秘钥存放目录',NULL,NULL,NULL,NULL,NULL),(2,'pic_path','/Users/yuanjinxiu/cake-ops/pic',2,'图片存放目录',NULL,NULL,NULL,NULL,NULL),(3,'swap_path','/Users/yuanjinxiu/cake-ops/swap',2,'交换分区目录',NULL,NULL,NULL,NULL,NULL),(4,'screen_path','/Users/yuanjinxiu/cake-ops/screen',2,'录屏存放目录',NULL,NULL,NULL,NULL,NULL),(5,'log_path','/Users/yuanjinxiu/cake-ops/logs',2,'日志存放目录',NULL,NULL,NULL,NULL,NULL),(6,'temp_path','/Users/yuanjinxiu/cake-ops/temp',2,'临时文件目录',NULL,NULL,NULL,NULL,NULL),(7,'repo_path','/Users/yuanjinxiu/cake-ops/repo',2,'应用版本仓库目录',NULL,NULL,NULL,NULL,NULL),(8,'dist_path','/Users/yuanjinxiu/cake-ops/dist',2,'构建产物目录',NULL,NULL,NULL,NULL,NULL),(9,'machine_monitor_agent_path','/Users/yuanjinxiu/cake-ops/lib/machine-monitor-agent-latest.jar',2,'机器监控插件绝对路径',NULL,NULL,NULL,NULL,NULL),(10,'tail_file_upload_path','/Users/yuanjinxiu/cake-ops/tail',2,'日志文件上传目录',NULL,NULL,NULL,NULL,NULL),(11,'tail_mode','tracker',2,'日志文件追踪模式 (tracker/tail)',NULL,NULL,NULL,NULL,NULL),(12,'tracker_delay_time','250',2,'文件追踪器等待时间 (ms)',NULL,NULL,NULL,NULL,NULL),(13,'white_ip_list',NULL,1,'ip 白名单',NULL,NULL,NULL,NULL,NULL),(14,'black_ip_list',NULL,1,'ip 黑名单',NULL,NULL,NULL,NULL,NULL),(15,'enable_ip_filter','disabled',1,'是否启用IP过滤',NULL,NULL,NULL,NULL,NULL),(16,'enable_white_ip_list','disabled',1,'是否启用IP白名单',NULL,NULL,NULL,NULL,NULL),(17,'file_clean_threshold','60',1,'文件清理阈值 (天)',NULL,NULL,NULL,NULL,NULL),(18,'enable_auto_clean_file','disabled',1,'是否启用自动清理',NULL,NULL,NULL,NULL,NULL),(19,'allow_multiple_login','disabled',1,'允许多端登陆',NULL,NULL,NULL,NULL,NULL),(20,'login_failure_lock','enabled',1,'是否启用登陆失败锁定',NULL,NULL,NULL,NULL,NULL),(21,'login_ip_bind','disabled',1,'是否启用登陆IP绑定',NULL,NULL,NULL,NULL,NULL),(22,'login_token_auto_renew','enabled',1,'是否启用凭证自动续签',NULL,NULL,NULL,NULL,NULL),(23,'login_token_expire','48',1,'登陆凭证有效期 (时)',NULL,NULL,NULL,NULL,NULL),(24,'login_failure_lock_threshold','5',1,'登陆失败锁定阈值',NULL,NULL,NULL,NULL,NULL),(25,'login_token_auto_renew_threshold','2',1,'登陆自动续签阈值 (时)',NULL,NULL,NULL,NULL,NULL),(26,'resume_enable_scheduler_task','disabled',1,'自动恢复启用的调度任务',NULL,NULL,NULL,NULL,NULL),(27,'terminal_active_push_heartbeat','disabled',1,'终端后台主动推送心跳',NULL,NULL,NULL,NULL,NULL),(28,'sftp_upload_threshold','512',1,'sftp 上传文件最大阈值 (MB)',NULL,NULL,NULL,NULL,NULL),(29,'statistics_cache_expire','5',1,'统计缓存有效时间 (分)',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `system_env` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `terminal_session`
--

DROP TABLE IF EXISTS `terminal_session`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `terminal_session` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `session_id` varchar(255) NOT NULL,
  `account_id` bigint DEFAULT NULL,
  `remote_addr` varchar(255) DEFAULT NULL,
  `session_closed` tinyint(1) DEFAULT NULL,
  `close_time` datetime DEFAULT NULL,
  `server_hostname` varchar(255) DEFAULT NULL,
  `server_addr` varchar(255) DEFAULT NULL,
  `session_type` varchar(10) DEFAULT NULL,
  `is_deleted` char(4) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `terminal_session`
--

LOCK TABLES `terminal_session` WRITE;
/*!40000 ALTER TABLE `terminal_session` DISABLE KEYS */;
/*!40000 ALTER TABLE `terminal_session` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `terminal_session_instance`
--

DROP TABLE IF EXISTS `terminal_session_instance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `terminal_session_instance` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `session_id` varchar(255) NOT NULL,
  `instance_id` varchar(255) DEFAULT NULL,
  `duplicate_instance_id` varchar(255) DEFAULT NULL,
  `instance_session_type` varchar(10) DEFAULT NULL,
  `login_user` varchar(255) DEFAULT NULL,
  `host_ip` varchar(255) DEFAULT NULL,
  `output_size` bigint DEFAULT NULL,
  `instance_closed` tinyint DEFAULT NULL,
  `open_time` datetime DEFAULT NULL,
  `close_time` datetime DEFAULT NULL,
  `is_deleted` char(4) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `terminal_session_instance`
--

LOCK TABLES `terminal_session_instance` WRITE;
/*!40000 ALTER TABLE `terminal_session_instance` DISABLE KEYS */;
/*!40000 ALTER TABLE `terminal_session_instance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `terminal_session_instance_command`
--

DROP TABLE IF EXISTS `terminal_session_instance_command`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `terminal_session_instance_command` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `terminal_session_instance_id` varchar(255) NOT NULL,
  `prompt` varchar(255) DEFAULT NULL,
  `is_formatted` varchar(10) DEFAULT '0',
  `input` text,
  `input_formatted` text,
  `output` text,
  `is_deleted` char(4) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `terminal_session_instance_command`
--

LOCK TABLES `terminal_session_instance_command` WRITE;
/*!40000 ALTER TABLE `terminal_session_instance_command` DISABLE KEYS */;
/*!40000 ALTER TABLE `terminal_session_instance_command` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `web_side_message`
--

DROP TABLE IF EXISTS `web_side_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `web_side_message` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `message_classify` tinyint DEFAULT NULL COMMENT '消息分类',
  `message_type` int DEFAULT NULL COMMENT '消息类型',
  `read_status` tinyint DEFAULT '1' COMMENT '是否已读 1未读 2已读',
  `to_user_id` bigint DEFAULT NULL COMMENT '收信人id',
  `to_user_name` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '收信人名称',
  `send_message` text COLLATE utf8mb3_bin COMMENT '消息',
  `rel_id` bigint DEFAULT NULL COMMENT '消息关联id',
  `is_deleted` varchar(5) COLLATE utf8mb3_bin DEFAULT '1' COMMENT '是否删除 0未删除 1已删除',
  `gmt_create` datetime(4) DEFAULT CURRENT_TIMESTAMP(4) COMMENT '创建时间',
  `gmt_modified` datetime(4) DEFAULT CURRENT_TIMESTAMP(4) ON UPDATE CURRENT_TIMESTAMP(4) COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC COMMENT='站内信';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `web_side_message`
--

LOCK TABLES `web_side_message` WRITE;
/*!40000 ALTER TABLE `web_side_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `web_side_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `webhook_config`
--

DROP TABLE IF EXISTS `webhook_config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `webhook_config` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `webhook_name` varchar(64) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '名称',
  `webhook_url` varchar(2048) COLLATE utf8mb3_bin DEFAULT NULL COMMENT 'url',
  `webhook_type` int DEFAULT NULL COMMENT '类型 10: 钉钉机器人',
  `webhook_config` varchar(2048) COLLATE utf8mb3_bin DEFAULT NULL COMMENT '配置项 json',
  `is_deleted` varchar(5) COLLATE utf8mb3_bin DEFAULT '0' COMMENT '是否删除 1未删除 2已删除',
  `gmt_create` datetime(4) DEFAULT CURRENT_TIMESTAMP(4) COMMENT '创建时间',
  `gmt_modified` datetime(4) DEFAULT CURRENT_TIMESTAMP(4) ON UPDATE CURRENT_TIMESTAMP(4) COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin ROW_FORMAT=DYNAMIC COMMENT='webhook 配置';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `webhook_config`
--

LOCK TABLES `webhook_config` WRITE;
/*!40000 ALTER TABLE `webhook_config` DISABLE KEYS */;
INSERT INTO `webhook_config` VALUES (1,'name','https://oapi.dingtalk.com/robot/send?access_token=xxx',10,'sadasdagsdfgs','1','2024-04-04 20:06:10.7720','2024-04-04 20:06:10.7720'),(2,'dfasdfasdf','https://oapi.dingtalk.com/robot/send?access_token=xxx',10,'sadasdafasfads','1',NULL,'2024-04-04 20:38:19.3730'),(3,'sdasfd','https://oapi.dingtalk.com/robot/send?access_token=xxx',10,'sdfasdfadfasd','0',NULL,'2024-04-04 20:38:19.3536');
/*!40000 ALTER TABLE `webhook_config` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-05 16:30:09
