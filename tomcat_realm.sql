-- MySQL dump 10.13  Distrib 5.7.19, for osx10.12 (x86_64)
--
-- Host: localhost    Database: tomcat_realm
-- ------------------------------------------------------
-- Server version	5.7.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tomcat_roles`
--

DROP TABLE IF EXISTS `tomcat_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tomcat_roles` (
  `role_name` varchar(20) NOT NULL,
  PRIMARY KEY (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tomcat_roles`
--

LOCK TABLES `tomcat_roles` WRITE;
/*!40000 ALTER TABLE `tomcat_roles` DISABLE KEYS */;
INSERT INTO `tomcat_roles` VALUES ('admin'),('user');
/*!40000 ALTER TABLE `tomcat_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tomcat_users`
--

DROP TABLE IF EXISTS `tomcat_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tomcat_users` (
  `user_name` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tomcat_users`
--

LOCK TABLES `tomcat_users` WRITE;
/*!40000 ALTER TABLE `tomcat_users` DISABLE KEYS */;
INSERT INTO `tomcat_users` VALUES ('admin','7BBWDþ¨¶]Øký»;¿~°/å{àÈ°5T.¢~Ã'),('user','ÄýôR\ZrþÒM\n¶¥Xÿµø÷A9');
/*!40000 ALTER TABLE `tomcat_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tomcat_users_roles`
--

DROP TABLE IF EXISTS `tomcat_users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tomcat_users_roles` (
  `user_name` varchar(20) NOT NULL,
  `role_name` varchar(20) NOT NULL,
  PRIMARY KEY (`user_name`,`role_name`),
  KEY `tomcat_users_roles_foreign_key_2` (`role_name`),
  CONSTRAINT `tomcat_users_roles_foreign_key_1` FOREIGN KEY (`user_name`) REFERENCES `tomcat_users` (`user_name`),
  CONSTRAINT `tomcat_users_roles_foreign_key_2` FOREIGN KEY (`role_name`) REFERENCES `tomcat_roles` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tomcat_users_roles`
--

LOCK TABLES `tomcat_users_roles` WRITE;
/*!40000 ALTER TABLE `tomcat_users_roles` DISABLE KEYS */;
INSERT INTO `tomcat_users_roles` VALUES ('admin','admin'),('admin','user'),('user','user');
/*!40000 ALTER TABLE `tomcat_users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-27 18:30:10
