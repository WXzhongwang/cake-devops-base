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
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
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
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `env_id` varchar(64) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `app_env`
--

LOCK TABLES `app_env` WRITE;
/*!40000 ALTER TABLE `app_env` DISABLE KEYS */;
INSERT INTO `app_env` VALUES (1,'1','1','cake-devops.rany.com','开发环境','TEST',1,'1C','1C','500M','1024M','0','0','1','0','2023-11-23 20:51:15','2023-12-21 22:38:19',NULL,NULL,'897615291213819903'),(2,'1','1',NULL,'测试环境新增001','TEST',2,'0.5C','1C','500M','1024M','0','0','1','0','2023-12-21 22:38:04','2023-12-21 22:38:08',NULL,NULL,'897615291213819904'),(3,'1','1','pre.cake.com','预发','PRE',2,'1C','1C','1024M','1C','0','0','0','0',NULL,NULL,NULL,NULL,'898888447652147200');
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
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
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
  `is_deleted` varchar(255) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `approval`
--

LOCK TABLES `approval` WRITE;
/*!40000 ALTER TABLE `approval` DISABLE KEYS */;
INSERT INTO `approval` VALUES (1,'906357378688102400','http://yuque.com','2024-01-14 11:47:16','APPROVED','备注',NULL,NULL,'2024-01-16 21:31:00',NULL,NULL),(2,'906357551837360128','http://yuque.com','2024-01-14 11:47:47','APPROVED','备注',NULL,NULL,'2024-01-16 21:31:06',NULL,NULL),(3,'906359020359659520','http://yuque.com','2024-01-14 11:53:47','APPROVED','备注内容','0','2024-01-14 11:54:00','2024-01-16 21:31:12',NULL,NULL),(4,'906669494347378688','http://yuque.com','2024-01-31 08:27:26','APPROVED','备注','0','2024-01-15 08:27:43','2024-01-16 21:31:17',NULL,NULL);
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
  `status` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
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
  `is_deleted` varchar(255) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `group_host`
--

LOCK TABLES `group_host` WRITE;
/*!40000 ALTER TABLE `group_host` DISABLE KEYS */;
INSERT INTO `group_host` VALUES (1,'1','1','0','2023-11-23 21:41:10','2023-11-23 21:41:10',NULL,NULL),(2,'3','903926807307366400','0',NULL,NULL,NULL,NULL),(3,'2','903926807307366400','0',NULL,NULL,NULL,NULL);
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
  `port` int NOT NULL,
  `username` varchar(255) NOT NULL,
  `status` varchar(255) DEFAULT '0',
  `is_deleted` varchar(255) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `desc` varchar(255) DEFAULT NULL,
  `verified` char(2) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  `pkey` varchar(2000) NOT NULL,
  `server_addr` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `host`
--

LOCK TABLES `host` WRITE;
/*!40000 ALTER TABLE `host` DISABLE KEYS */;
INSERT INTO `host` VALUES (1,'1','测试机器1','v-local-test01',22,'yuanjinxiu','0','0','2023-11-23 21:40:54','2024-01-08 08:56:22','本机','1',NULL,NULL,'131400','127.0.0.1'),(2,'2','测试机器2','v-local-test02',22,'yuanjinxiu','0','0','2023-11-23 21:40:54','2024-01-08 08:56:22','本机','1',NULL,NULL,'131400','127.0.0.1'),(3,'3','测试机器3','v-local-test03',22,'yuanjinxiu','0','0','2023-11-23 21:40:54','2024-01-08 08:56:22','本机','1',NULL,NULL,'131400','127.0.0.1'),(4,'4','测试机器4','v-local-test04',22,'yuanjinxiu','0','0','2023-11-23 21:40:54','2024-01-08 08:56:22','本机','1',NULL,NULL,'131400','127.0.0.1'),(5,'5','测试机器5','v-local-test05',22,'yuanjinxiu','0','0','2023-11-23 21:40:54','2024-01-08 08:56:22','本机','1',NULL,NULL,'131400','127.0.0.1'),(6,'6','测试机器6','v-local-test06',22,'yuanjinxiu','0','0','2023-11-23 21:40:54','2024-01-08 08:56:22','本机','1',NULL,NULL,'131400','127.0.0.1'),(7,'7','测试机器7','v-local-test07',22,'yuanjinxiu','0','0','2023-11-23 21:40:54','2024-01-08 08:56:22','本机','1',NULL,NULL,'131400','127.0.0.1'),(8,'8','测试机器8','v-local-test08',22,'yuanjinxiu','0','0','2023-11-23 21:40:54','2024-01-08 08:56:22','本机','1',NULL,NULL,'131400','127.0.0.1'),(9,'9','测试机器9','v-local-test09',22,'yuanjinxiu','0','0','2023-11-23 21:40:54','2024-01-08 08:56:22','本机','1',NULL,NULL,'131400','127.0.0.1'),(10,'10','测试机器10','v-local-test10',22,'yuanjinxiu','0','0','2023-11-23 21:40:54','2024-01-08 08:56:22','本机','1',NULL,NULL,'131400','127.0.0.1'),(11,'11','测试机器11','v-local-test11',22,'yuanjinxiu','0','0','2023-11-23 21:40:54','2024-01-08 08:56:22','本机','1',NULL,NULL,'131400','127.0.0.1'),(12,'12','测试机器12','v-local-test12',22,'yuanjinxiu','0','0','2023-11-23 21:40:54','2024-01-08 08:56:22','本机','1',NULL,NULL,'131400','127.0.0.1'),(13,'13','测试机器13','v-local-test13',22,'yuanjinxiu','0','0','2023-11-23 21:40:54','2024-01-08 08:56:22','本机','1',NULL,NULL,'131400','127.0.0.1'),(14,'14','测试机器14','v-local-test14',22,'yuanjinxiu','0','0','2023-11-23 21:40:54','2024-01-08 08:56:22','本机','1',NULL,NULL,'131400','127.0.0.1'),(15,'903920328227565568','1','1',1,'1','0','0','2024-01-07 18:23:31','2024-01-07 18:24:09','本机','1',NULL,NULL,'1','1'),(16,'903926807307366400','2','2',2,'2','0','0','2024-01-07 18:49:15','2024-01-07 18:49:15',NULL,NULL,NULL,NULL,'2','2222');
/*!40000 ALTER TABLE `host` ENABLE KEYS */;
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
  `is_deleted` varchar(255) DEFAULT '0',
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
  `status` char(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `is_deleted` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '0',
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
  `is_deleted` varchar(255) DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `creator` varchar(255) DEFAULT NULL,
  `modifier` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `release_no`
--

LOCK TABLES `release_no` WRITE;
/*!40000 ALTER TABLE `release_no` DISABLE KEYS */;
INSERT INTO `release_no` VALUES (2,'906359016366682112','1','R202401141153590004','906359020359659520','2024-01-14 11:53:47','main',NULL,'v1.0.0','897615291213819903','READY',NULL,NULL,'0','2024-01-14 11:54:00','2024-01-16 22:33:45',NULL,NULL),(3,'906669481965793280','1','R202401150827390005','906669494347378688','2024-01-31 08:27:26','main',NULL,NULL,'897615291213819903','READY',NULL,NULL,'0','2024-01-15 08:27:43','2024-01-16 22:33:45',NULL,NULL);
/*!40000 ALTER TABLE `release_no` ENABLE KEYS */;
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
  `is_deleted` varchar(255) DEFAULT '0',
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
  `is_deleted` varchar(255) DEFAULT '0',
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
  `is_deleted` varchar(255) DEFAULT '0',
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-17 20:41:13
