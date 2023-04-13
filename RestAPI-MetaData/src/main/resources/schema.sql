-- MySQL dump 10.13  Distrib 8.0.29, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: newdb
-- ------------------------------------------------------
-- Server version	8.0.32-0ubuntu0.22.04.2

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `track`
--

DROP TABLE IF EXISTS `track`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `track` (
  `id` int NOT NULL AUTO_INCREMENT,
  `isrc` varchar(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `duration_ms` int NOT NULL,
  `explicit` tinyint(1) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `artists` text,
  PRIMARY KEY (`id`),
  UNIQUE KEY `isrc` (`isrc`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `track`
--

LOCK TABLES `track` WRITE;
/*!40000 ALTER TABLE `track` DISABLE KEYS */;
INSERT INTO `track` VALUES (1,'TEST1234560000','Some new track!',12000000,1,'2023-04-07 11:14:28','Artist 1, Artist 2'),(2,'TEST1234560001','Another track',18000000,0,'2023-04-07 11:14:28',NULL),(3,'TEST1234560002','Third track',15000000,1,'2023-04-07 11:14:28',NULL),(4,'TEST1234567815','My Track',240000,1,'2023-04-07 14:05:27',NULL),(6,'TEST1234567816','My Track',240000,1,'2023-04-07 15:33:38',NULL),(8,'TEST1234567844','My Track',240000,1,'2023-04-11 15:09:08',NULL),(10,'TEST1234545454','My Track',240000,1,'2023-04-12 06:55:15','Artist 1, Artist 2');
/*!40000 ALTER TABLE `track` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `secret` varchar(255) DEFAULT NULL,
  `verification_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'john@example.com','password1',1,'4J32Q5LRNBSW6BNZ','123456'),(2,'j','$2a$12$9l7hZKUzmazV9kAV/4Jc4OcRPcAmUAfWkYIP8O1FAxjyxIxB9DXR2',1,'SJ6RGRF3DTHLEWVB','217133'),(3,'brendan@ballysheen.net','$2a$12$ZP8Ri9l.TVnXoAb4bcN7Veo3Ft53Vx2IaLdOpZacLEBqFcaFBySgW',0,NULL,'212709'),(4,'sdfs','$2a$12$8ax0.D5jmUnvgxRE8NgS5u.s13CstFAu2iGfklkb931/w8qFjC5DS',1,'4J32Q5LRNBSW6BNZ','435986'),(5,'Luminous.sofia@gmail.com','$2a$10$GL/6p2EbNm7yH2jEulTwVe939Wb74NiAA6RXgoCJvApmREt6yHy/y',1,'W7DIN7ANR55BGLEG',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-04-13  8:24:24