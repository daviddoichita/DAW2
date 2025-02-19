-- MySQL dump 10.13  Distrib 8.0.36, for Linux (x86_64)
--
-- Host: localhost    Database: clientesbd
-- ------------------------------------------------------
-- Server version	8.4.3

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



DROP DATABASE IF EXISTS clientesbd;
CREATE DATABASE clientesbd;

CREATE USER IF NOT EXISTS 'usuariopresencial'@'localhost' IDENTIFIED WITH sha256_password BY 'IesCampMorvedre01%';
GRANT ALL ON clientesbd.* TO 'usuariopresencial'@'localhost';

USE clientesbd;

--
-- Table structure for table `agente`
--

DROP TABLE IF EXISTS `agente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `agente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(32) NOT NULL,
  `apellido` varchar(32) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agente`
--

LOCK TABLES `agente` WRITE;
/*!40000 ALTER TABLE `agente` DISABLE KEYS */;
INSERT INTO `agente` VALUES (1,'Juan','Perez'),(2,'Mario','Gonzalez'),(3,'Pedro','Rodriguez');
/*!40000 ALTER TABLE `agente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clienteagente`
--

DROP TABLE IF EXISTS `clienteagente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clienteagente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idcliente` int NOT NULL,
  `idagente` int NOT NULL,
  `fechaasignacion` datetime NOT NULL,
  `tiporelacion` varchar(32) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idcliente` (`idcliente`),
  KEY `idagente` (`idagente`),
  CONSTRAINT `clienteagente_ibfk_1` FOREIGN KEY (`idcliente`) REFERENCES `clientes` (`id`),
  CONSTRAINT `clienteagente_ibfk_2` FOREIGN KEY (`idagente`) REFERENCES `agente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clienteagente`
--

LOCK TABLES `clienteagente` WRITE;
/*!40000 ALTER TABLE `clienteagente` DISABLE KEYS */;
INSERT INTO `clienteagente` VALUES (4,1,1,'2025-01-11 18:45:16','Venta'),(6,7,3,'2025-01-11 18:45:16','Relaciones publicas');
/*!40000 ALTER TABLE `clienteagente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientemetodopago`
--

DROP TABLE IF EXISTS `clientemetodopago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientemetodopago` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idcliente` int NOT NULL,
  `idmetodopago` int NOT NULL,
  `fechaalta` datetime DEFAULT CURRENT_TIMESTAMP,
  `predeterminado` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idcliente` (`idcliente`),
  KEY `idmetodopago` (`idmetodopago`),
  CONSTRAINT `clientemetodopago_ibfk_1` FOREIGN KEY (`idcliente`) REFERENCES `clientes` (`id`),
  CONSTRAINT `clientemetodopago_ibfk_2` FOREIGN KEY (`idmetodopago`) REFERENCES `metodopago` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientemetodopago`
--

LOCK TABLES `clientemetodopago` WRITE;
/*!40000 ALTER TABLE `clientemetodopago` DISABLE KEYS */;
INSERT INTO `clientemetodopago` VALUES (2,1,2,'2025-01-11 17:43:08',0);
/*!40000 ALTER TABLE `clientemetodopago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nif` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `claveseguridad` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fechanacimiento` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'44999594K','Keiko','Meadows','12312312','meadowskeiko6933@icloud.net','2025-01-17'),(7,'82568848H','Chava','Floyd','3011','cfloyd@hotmail.couk','2025-01-10'),(8,'03768520L','Tatum','Walsh','2463','walsh_tatum2491@outlook.couk','2025-01-10'),(9,'51976869H','Ezekiel','Woodard','2707','e-woodard@icloud.couk','2025-01-10'),(10,'55172493N','Coby','Lester','9856','c-lester7681@hotmail.couk','2025-01-10'),(11,'48037186R','Brielle','Howard','7848','b_howard@aol.com','2025-01-10'),(12,'71227610F','Candace','Bates','4990','c-bates@google.org','2025-01-10'),(13,'41124626J','Eagan','Carpenter','2060','e_carpenter9768@outlook.org','2025-01-10'),(14,'86926685Y','Yvette','Slater','6646','yslater@google.edu','2025-01-10'),(15,'18829162J','Heather','Abbott','9983','abbottheather@hotmail.org','2025-01-10'),(16,'10159358C','Kessie','Rich','4285','k_rich804@google.net','2025-01-10'),(17,'61816073X','Keelie','Forbes','1844','k-forbes1767@hotmail.com','2025-01-10'),(20,'21865746E','Graham','Casey','6223','g.casey3482@google.com','2025-01-10');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientesdirecciones`
--

DROP TABLE IF EXISTS `clientesdirecciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientesdirecciones` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `idcliente` int DEFAULT NULL,
  `iddireccion` int DEFAULT NULL,
  `fechaalta` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idcliente` (`idcliente`),
  KEY `iddireccion` (`iddireccion`),
  CONSTRAINT `clientesdirecciones_ibfk_1` FOREIGN KEY (`idcliente`) REFERENCES `clientes` (`id`),
  CONSTRAINT `clientesdirecciones_ibfk_2` FOREIGN KEY (`iddireccion`) REFERENCES `direcciones` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=169 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientesdirecciones`
--

LOCK TABLES `clientesdirecciones` WRITE;
/*!40000 ALTER TABLE `clientesdirecciones` DISABLE KEYS */;
INSERT INTO `clientesdirecciones` VALUES (1,17,44,'2025-01-10 17:50:52'),(3,9,26,'2025-01-10 17:50:52'),(5,9,6,'2025-01-10 17:50:52'),(8,14,61,'2025-01-10 17:50:52'),(11,13,28,'2025-01-10 17:50:52'),(13,14,18,'2025-01-10 17:50:52'),(15,12,8,'2025-01-10 17:50:52'),(16,9,64,'2025-01-10 17:50:52'),(18,14,12,'2025-01-10 17:50:52'),(19,17,75,'2025-01-10 17:50:52'),(20,17,14,'2025-01-10 17:50:52'),(22,10,22,'2025-01-10 17:50:52'),(23,13,51,'2025-01-10 17:50:52'),(25,16,13,'2025-01-10 17:50:52'),(26,17,36,'2025-01-10 17:50:52'),(27,20,33,'2025-01-10 17:50:52'),(28,11,47,'2025-01-10 17:50:52'),(29,14,29,'2025-01-10 17:50:52'),(30,12,21,'2025-01-10 17:50:52'),(31,12,57,'2025-01-10 17:50:52'),(32,17,10,'2025-01-10 17:50:52'),(33,15,5,'2025-01-10 17:50:52'),(34,10,16,'2025-01-10 17:50:52'),(36,11,74,'2025-01-10 17:50:52'),(38,12,56,'2025-01-10 17:50:52'),(39,16,23,'2025-01-10 17:50:52'),(41,10,14,'2025-01-10 17:50:52'),(42,9,27,'2025-01-10 17:50:52'),(44,17,29,'2025-01-10 17:50:52'),(45,15,37,'2025-01-10 17:50:52'),(46,17,63,'2025-01-10 17:50:52'),(48,15,24,'2025-01-10 17:50:52'),(49,12,31,'2025-01-10 17:50:52'),(51,10,31,'2025-01-10 17:50:52'),(52,10,45,'2025-01-10 17:50:52'),(53,12,22,'2025-01-10 17:50:52'),(60,16,9,'2025-01-10 17:50:52'),(61,12,11,'2025-01-10 17:50:52'),(64,7,76,'2025-01-10 17:50:52'),(65,12,42,'2025-01-10 17:50:52'),(68,15,44,'2025-01-10 17:50:52'),(69,16,21,'2025-01-10 17:50:52'),(71,12,52,'2025-01-10 17:50:52'),(72,15,57,'2025-01-10 17:50:52'),(73,9,66,'2025-01-10 17:50:52'),(75,12,30,'2025-01-10 17:50:52'),(78,11,51,'2025-01-10 17:50:52'),(80,10,70,'2025-01-10 17:50:52'),(82,11,22,'2025-01-10 17:50:52'),(83,13,76,'2025-01-10 17:50:52'),(84,16,6,'2025-01-10 17:50:52'),(85,10,11,'2025-01-10 17:50:52'),(86,12,9,'2025-01-10 17:50:52'),(87,9,8,'2025-01-10 17:50:52'),(90,17,11,'2025-01-10 17:50:52'),(91,16,61,'2025-01-10 17:50:52'),(92,14,62,'2025-01-10 17:50:52'),(93,13,30,'2025-01-10 17:50:52'),(96,15,8,'2025-01-10 17:50:52'),(99,9,50,'2025-01-10 17:50:52'),(103,20,40,'2025-01-10 17:50:52'),(104,13,10,'2025-01-10 17:50:52'),(105,8,57,'2025-01-10 17:50:52'),(106,17,47,'2025-01-10 17:50:52'),(108,12,6,'2025-01-10 17:50:52'),(109,10,12,'2025-01-10 17:50:52'),(110,9,40,'2025-01-10 17:50:52'),(113,17,62,'2025-01-10 17:50:52'),(114,13,48,'2025-01-10 17:50:52'),(115,8,56,'2025-01-10 17:50:52'),(116,10,6,'2025-01-10 17:50:52'),(121,20,55,'2025-01-10 17:50:52'),(122,15,68,'2025-01-10 17:50:52'),(123,7,56,'2025-01-10 17:50:52'),(124,17,54,'2025-01-10 17:50:52'),(126,7,3,'2025-01-10 17:50:52'),(127,16,73,'2025-01-10 17:50:52'),(129,11,53,'2025-01-10 17:50:52'),(130,8,25,'2025-01-10 17:50:52'),(134,10,5,'2025-01-10 17:50:52'),(135,8,30,'2025-01-10 17:50:52'),(136,10,69,'2025-01-10 17:50:52'),(137,17,17,'2025-01-10 17:50:52'),(138,15,78,'2025-01-10 17:50:52'),(162,1,98,'2025-01-10 18:32:37'),(163,1,99,'2025-01-10 18:49:09'),(164,1,1,'2025-01-10 19:36:30'),(165,1,4,'2025-01-10 19:40:31'),(166,1,100,'2025-01-12 11:10:34');
/*!40000 ALTER TABLE `clientesdirecciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuentas`
--

DROP TABLE IF EXISTS `cuentas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuentas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `banco` varchar(255) DEFAULT NULL,
  `sucursal` varchar(255) DEFAULT NULL,
  `dc` varchar(255) DEFAULT NULL,
  `numerocuenta` varchar(255) DEFAULT NULL,
  `saldoactual` float DEFAULT NULL,
  `idcliente` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idcliente` (`idcliente`),
  CONSTRAINT `cuentas_ibfk_1` FOREIGN KEY (`idcliente`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=86 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuentas`
--

LOCK TABLES `cuentas` WRITE;
/*!40000 ALTER TABLE `cuentas` DISABLE KEYS */;
INSERT INTO `cuentas` VALUES (11,'6789','3715','63','2199081317',5000,1),(20,'3018','4318','35','5284872370',277.81,1),(21,'6801','2438','17','5428117294',1015.1,9),(22,'8858','9133','12','7188054849',3989.94,9),(24,'2900','4122','86','7540817458',8057.13,10),(25,'2146','6447','44','5413826986',818.82,10),(26,'8622','3652','35','8477533415',1918.74,8),(27,'2385','3476','47','4862155636',35291.9,7),(29,'4056','6608','61','6162280121',16974.6,9),(30,'4654','7458','63','3781616582',3825.93,8),(31,'6715','2614','46','6344426358',8973.2,8),(32,'5551','5269','86','5474846384',5732.95,8),(33,'4221','9447','34','4517758671',18268.4,9),(34,'4734','2038','44','1112026302',2318.58,9),(36,'4513','6589','17','9202881622',26846.6,9),(38,'9645','2578','76','6920843777',32272.3,10),(39,'1868','7713','76','1115841464',1622.8,9),(40,'4826','6888','37','2054668818',7121.95,10),(41,'6801','2438','17','5428117294',1015.1,15),(42,'8858','9133','12','7188054849',3989.94,12),(43,'5832','9724','83','6513413315',5699.01,15),(44,'2900','4122','86','7540817458',8057.13,14),(45,'2146','6447','44','5413826986',818.82,12),(46,'8622','3652','35','8477533415',1918.74,13),(47,'2385','3476','47','4862155636',35291.9,11),(48,'6046','1995','44','8045327684',3317.29,11),(49,'4056','6608','61','6162280121',16974.6,14),(50,'4654','7458','63','3781616582',3825.93,13),(51,'6715','2614','46','6344426358',8973.2,14),(52,'5551','5269','86','5474846384',5732.95,12),(53,'4221','9447','34','4517758671',18268.4,12),(54,'4734','2038','44','1112026302',2318.58,14),(55,'7311','8161','74','6287165832',2360.29,12),(56,'4513','6589','17','9202881622',26846.6,13),(57,'6412','7043','50','5913644254',11826.6,15),(58,'9645','2578','76','6920843777',32272.3,14),(59,'1868','7713','76','1115841464',1622.8,11),(60,'4826','6888','37','2054668818',7121.95,13),(62,'4555','9345','67','3563339472',975.88,16),(63,'7795','3185','81','7154365519',5004.85,16),(65,'2616','6328','26','6671866375',8199.72,20),(66,'2881','1372','71','9818316121',355.46,17),(68,'4871','3346','38','7866448990',18163.4,16),(69,'2553','0205','88','3415671625',4731.88,17),(70,'8454','5325','28','1276682987',7372.49,17),(71,'1887','5971','44','6676384451',5967.51,17),(72,'0762','2946','74','5533736273',5896.77,20),(74,'6519','4911','06','9021237656',12692.6,17),(77,'9237','3724','62','6456388138',12303.8,16),(78,'8238','1435','86','4001284223',7925.9,17),(79,'4125','1721','05','0045452157',32134.8,17),(80,'8834','0640','83','8077554878',6674.63,17);
/*!40000 ALTER TABLE `cuentas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `direcciones`
--

DROP TABLE IF EXISTS `direcciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `direcciones` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `pais` varchar(255) DEFAULT NULL,
  `cp` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=102 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `direcciones`
--

LOCK TABLES `direcciones` WRITE;
/*!40000 ALTER TABLE `direcciones` DISABLE KEYS */;
INSERT INTO `direcciones` VALUES (1,'Ap #838-4348 Eros. St.','Nigeria','92977'),(2,'333-9796 Sem, Rd.','Ireland','17221'),(3,'Ap #282-2375 Donec Street','Austria','44444'),(4,'324-493 Elit, Ave','Netherlands','49569'),(5,'Ap #263-7963 Suspendisse Av.','Nigeria','11982'),(6,'204-4479 Nullam Rd.','Germany','84777'),(7,'7013 Adipiscing St.','Nigeria','14219'),(8,'6194 In Rd.','Austria','54458'),(9,'309-9174 Sed Rd.','Vietnam','16664'),(10,'600-7639 Ut, Road','Philippines','41663'),(11,'Ap #250-4150 Donec St.','Indonesia','55825'),(12,'9297 Duis Rd.','United States','61856'),(13,'511-9481 Interdum. St.','Ireland','58771'),(14,'Ap #796-3301 Molestie Av.','Mexico','77274'),(15,'Ap #327-6294 Integer Av.','Spain','51172'),(16,'Ap #939-819 Vestibulum Road','Ireland','82605'),(17,'Ap #607-5810 Erat Avenue','France','14143'),(18,'P.O. Box 670, 5307 Praesent Ave','Norway','61948'),(19,'739 Netus Avenue','Costa Rica','86318'),(20,'347-5338 Phasellus St.','Germany','22612'),(21,'906-9945 Laoreet Avenue','Sweden','28139'),(22,'508-8419 Sit Avenue','Spain','46636'),(23,'Ap #954-2658 Consequat, Rd.','Germany','14564'),(24,'Ap #268-2308 Quisque St.','Brazil','29247'),(25,'815-2232 Nullam Ave','South Korea','46310'),(26,'Ap #506-3026 Velit Av.','Norway','81861'),(27,'Ap #185-7332 Dui Av.','Germany','65782'),(28,'P.O. Box 501, 2438 Sodales Av.','China','73726'),(29,'Ap #775-2327 Fusce Rd.','South Korea','22575'),(30,'291-7517 Lobortis Street','Italy','50085'),(31,'P.O. Box 371, 3363 Suspendisse Avenue','Singapore','33421'),(32,'900-5693 Pellentesque St.','Germany','21414'),(33,'P.O. Box 419, 6772 Sagittis Street','Costa Rica','73828'),(34,'998-8166 Vel, Street','Colombia','49677'),(35,'894-6620 Odio. Av.','New Zealand','83819'),(36,'4511 Lorem Rd.','Germany','78465'),(37,'Ap #176-9738 Phasellus Rd.','Poland','22001'),(38,'9890 Nec Av.','Austria','83496'),(39,'4631 Pellentesque. Ave','South Korea','95732'),(40,'103-5022 Donec St.','Russian Federation','05878'),(41,'238-733 Aenean Road','Turkey','33333'),(42,'3258 Ullamcorper Avenue','Indonesia','53851'),(43,'Ap #270-8384 Mollis St.','India','17133'),(44,'909-3129 Nulla Street','Singapore','61244'),(45,'1399 Non, Av.','United States','47167'),(46,'Ap #127-8719 Quis Avenue','Turkey','87532'),(47,'2479 Sapien. St.','Sweden','91561'),(48,'P.O. Box 548, 7423 Vel, Av.','Philippines','09759'),(49,'873-770 Gravida. Av.','Nigeria','73466'),(50,'Ap #824-7037 Fames St.','Pakistan','77714'),(51,'Ap #676-3278 Metus. St.','Chile','80276'),(52,'P.O. Box 875, 2659 Donec St.','Mexico','88382'),(53,'676-3884 Leo. St.','South Korea','65421'),(54,'105-7385 Volutpat. Street','Turkey','83339'),(55,'Ap #293-8191 Donec Avenue','Spain','54586'),(56,'5602 Aliquet Av.','Brazil','47216'),(57,'Ap #608-6296 Scelerisque Ave','Germany','66585'),(58,'528-148 Consectetuer Avenue','Norway','33285'),(59,'Ap #443-9053 Nulla. Rd.','Pakistan','71042'),(60,'212-4072 Cursus Rd.','Chile','11687'),(61,'582-1013 Magna Road','China','27752'),(62,'928-6933 Eleifend, St.','South Korea','65717'),(63,'8407 Natoque Av.','Poland','34444'),(64,'418-9258 Nec St.','South Africa','34213'),(65,'Ap #750-8336 Arcu Rd.','Brazil','76747'),(66,'613-6503 Mauris Road','Poland','58861'),(67,'Ap #414-8760 Nibh Av.','Spain','23878'),(68,'956-974 Erat. Avenue','Singapore','58331'),(69,'509-5131 Feugiat Rd.','Spain','65593'),(70,'Ap #382-5932 Gravida. Avenue','Turkey','79545'),(71,'4818 Sapien Street','Netherlands','12121'),(72,'Ap #359-9989 Tortor. Road','Italy','82767'),(73,'P.O. Box 955, 1773 Neque Av.','Canada','22793'),(74,'5150 Eros Avenue','Peru','72955'),(75,'P.O. Box 223, 8796 Magna. St.','Indonesia','22661'),(76,'P.O. Box 489, 4632 Nulla. Avenue','Ukraine','38571'),(77,'530-9117 Metus Avenue','Peru','74509'),(78,'Ap #248-3123 Amet, Avenue','Norway','32732'),(79,'Ap #817-7092 Dolor. Av.','Spain','13198'),(80,'Ap #709-4277 Interdum St.','Colombia','72762'),(94,'Mi calle','Mi pais','22612'),(95,'Phasellus St.','Turkey','22612'),(96,'Mi calle','Turkey','22612'),(97,'Mi calle','Mi pais','22612'),(98,'Phasellus St.','Mi pais','80900'),(99,'Mi calle','Mi pais','12122'),(100,'4818 Sapien Street','Turkey','22612'),(101,'Mi calle','Mi pais','22612');
/*!40000 ALTER TABLE `direcciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metodopago`
--

DROP TABLE IF EXISTS `metodopago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `metodopago` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metodopago`
--

LOCK TABLES `metodopago` WRITE;
/*!40000 ALTER TABLE `metodopago` DISABLE KEYS */;
INSERT INTO `metodopago` VALUES (2,'Bizum'),(5,'PayPal');
/*!40000 ALTER TABLE `metodopago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimientos`
--

DROP TABLE IF EXISTS `movimientos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimientos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `tipo` varchar(255) DEFAULT NULL,
  `fechaoperacion` datetime DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `importe` float DEFAULT NULL,
  `idcuentaorigen` int DEFAULT NULL,
  `idcuentadestino` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idcuentaorigen` (`idcuentaorigen`),
  KEY `idcuentadestino` (`idcuentadestino`),
  CONSTRAINT `movimientos_ibfk_1` FOREIGN KEY (`idcuentaorigen`) REFERENCES `cuentas` (`id`),
  CONSTRAINT `movimientos_ibfk_2` FOREIGN KEY (`idcuentadestino`) REFERENCES `cuentas` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimientos`
--

LOCK TABLES `movimientos` WRITE;
/*!40000 ALTER TABLE `movimientos` DISABLE KEYS */;
INSERT INTO `movimientos` VALUES (1,'Bizum','2025-01-11 11:52:22','lorem ipsum',12,11,20),(2,'Bizum','2025-01-11 11:52:22','lorem ipsum',30,11,20),(4,'Bizum','2025-01-11 11:52:22','lorem ipsum',50,20,11),(9,'bizum','2025-01-12 11:21:52','hola',2,11,20);
/*!40000 ALTER TABLE `movimientos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recomendaciones`
--

DROP TABLE IF EXISTS `recomendaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recomendaciones` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `observaciones` varchar(255) DEFAULT NULL,
  `idcliente` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idcliente` (`idcliente`),
  CONSTRAINT `recomendaciones_ibfk_1` FOREIGN KEY (`idcliente`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recomendaciones`
--

LOCK TABLES `recomendaciones` WRITE;
/*!40000 ALTER TABLE `recomendaciones` DISABLE KEYS */;
INSERT INTO `recomendaciones` VALUES (7,'ac nulla. In tincidunt congue turpis. In condimentum. Donec at arcu. Vestibulum ante',7),(8,'ligula tortor, dictum eu, placerat eget, venenatis a, magna. Lorem ipsum dolor sit amet,',8),(9,'molestie pharetra nibh. Aliquam ornare, libero at auctor ullamcorper, nisl arcu iaculis enim,',9),(10,'sem eget massa. Suspendisse eleifend. Cras sed leo.',10),(11,'velit. Sed malesuada augue ut lacus. Nulla tincidunt,',11),(12,'netus et malesuada fames ac turpis egestas. Fusce',12),(13,'vitae, sodales at, velit. Pellentesque ultricies dignissim lacus. Aliquam rutrum',13),(14,'vitae sodales nisi magna sed dui. Fusce aliquam,',14),(15,'hendrerit id, ante. Nunc mauris sapien, cursus in, hendrerit',15),(16,'at, nisi. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Proin',16),(17,'nisl arcu iaculis enim, sit amet ornare lectus justo eu arcu. Morbi sit amet',17),(20,'ornare placerat, orci lacus vestibulum lorem,',20),(37,'Quisque nonummy ipsum non arcu. Vivamus sit amet risus. Donec egestas. Aliquam nec',1);
/*!40000 ALTER TABLE `recomendaciones` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-01-16 19:41:31
