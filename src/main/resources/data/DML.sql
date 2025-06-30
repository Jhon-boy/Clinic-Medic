-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: localhost    Database: mediapp
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Dumping data for table `tcitcitas`
--

LOCK TABLES `tcitcitas` WRITE;
/*!40000 ALTER TABLE `tcitcitas` DISABLE KEYS */;
/*!40000 ALTER TABLE `tcitcitas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tempempresa`
--

LOCK TABLES `tempempresa` WRITE;
/*!40000 ALTER TABLE `tempempresa` DISABLE KEYS */;
/*!40000 ALTER TABLE `tempempresa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tempempresadetalle`
--

LOCK TABLES `tempempresadetalle` WRITE;
/*!40000 ALTER TABLE `tempempresadetalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `tempempresadetalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tpagfactura`
--

LOCK TABLES `tpagfactura` WRITE;
/*!40000 ALTER TABLE `tpagfactura` DISABLE KEYS */;
INSERT INTO `tpagfactura` VALUES (0,1,1,0,100,100.00,'2025-05-25 23:18:51.000000','Compra de productos varios');
/*!40000 ALTER TABLE `tpagfactura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tpagformapago`
--

LOCK TABLES `tpagformapago` WRITE;
/*!40000 ALTER TABLE `tpagformapago` DISABLE KEYS */;
INSERT INTO `tpagformapago` VALUES (_binary '',1,'2025-05-16 06:44:25.000000','2025-05-16 06:26:29.000000','TRANSFERENCIA','PAGO POR MEDIO DE TRANSFERENCIA BANCARIA'),(_binary '',3,NULL,'2025-05-16 06:27:18.000000','EFECTIVO','PAGO CON DINERO FISICO'),(_binary '\0',7,'2025-05-16 06:47:46.000000','2025-05-16 06:46:43.000000','TARJETA','PAGO A TRAVEZ DE TARJETA EN LINEA');
/*!40000 ALTER TABLE `tpagformapago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tpagpago`
--

LOCK TABLES `tpagpago` WRITE;
/*!40000 ALTER TABLE `tpagpago` DISABLE KEYS */;
INSERT INTO `tpagpago` VALUES (1,3,151,1,'2025-05-25 23:31:04.000000',2);
/*!40000 ALTER TABLE `tpagpago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tperempcategoria`
--

LOCK TABLES `tperempcategoria` WRITE;
/*!40000 ALTER TABLE `tperempcategoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `tperempcategoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tperempleado`
--

LOCK TABLES `tperempleado` WRITE;
/*!40000 ALTER TABLE `tperempleado` DISABLE KEYS */;
/*!40000 ALTER TABLE `tperempleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tperpersona`
--

LOCK TABLES `tperpersona` WRITE;
/*!40000 ALTER TABLE `tperpersona` DISABLE KEYS */;
INSERT INTO `tperpersona` VALUES (1,'2025-04-21 00:00:00.000000','2023-01-01 00:00:00.000000','1990-05-15 00:00:00.000000','0102030405','0991234567','admin@mediapp.com','Administrador','Admin'),(2,NULL,'2025-04-22 01:02:46.000000','1987-05-20 22:41:42.280000','6458774903','6231630357','Graham_Wyman@gmailcom','Kub','Narciso'),(3,NULL,'2025-05-01 23:16:07.000000','2008-02-21 15:09:46.036000','7765553471','1072186872','Madaline25@hotmailcom','Harris','Adah'),(4,NULL,'2025-05-01 23:21:21.000000','2019-09-29 07:10:47.524000','4594514771','6425458446','KariWill55@gmailcom','Yundt','Lori'),(5,NULL,'2025-05-25 23:33:47.000000','2020-11-02 08:35:42.845000','8524437186','4278953469','Katelin_Jacobs70@hotmailcom','Tromp','Chloe'),(6,NULL,'2025-05-25 23:36:02.000000','2018-12-09 04:03:40.430000','5972182280','4541887349','Maria_Bogan@gmailcom','Rosenbaum','Isadore');
/*!40000 ALTER TABLE `tperpersona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tperprofecion`
--

LOCK TABLES `tperprofecion` WRITE;
/*!40000 ALTER TABLE `tperprofecion` DISABLE KEYS */;
INSERT INTO `tperprofecion` VALUES (1,'ADMIN','ADMINISTRA LA APLICACION');
/*!40000 ALTER TABLE `tperprofecion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tsegrol`
--

LOCK TABLES `tsegrol` WRITE;
/*!40000 ALTER TABLE `tsegrol` DISABLE KEYS */;
INSERT INTO `tsegrol` VALUES (_binary '',1,'ADMIN','ADMINISTRADOR GENERAL DE LA APLICACION'),(_binary '',2,'USER','MODIFICADO A PROPOSITO XD'),(_binary '',3,'GENERICO','USUARIO USADO PARA ACCEDER AL SISTEMA DESDE CANALES EXTERNOS');
/*!40000 ALTER TABLE `tsegrol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tsegrol_seq`
--

LOCK TABLES `tsegrol_seq` WRITE;
/*!40000 ALTER TABLE `tsegrol_seq` DISABLE KEYS */;
INSERT INTO `tsegrol_seq` VALUES (101);
/*!40000 ALTER TABLE `tsegrol_seq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tsegusuario`
--

LOCK TABLES `tsegusuario` WRITE;
/*!40000 ALTER TABLE `tsegusuario` DISABLE KEYS */;
INSERT INTO `tsegusuario` VALUES (_binary '',1,1,1,_binary '\0',NULL,'2025-04-21 00:00:00.000000','admin','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92'),(_binary '',2,2,1,_binary '\0',NULL,'2025-04-22 01:02:46.000000','Fae_Leannon','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92'),(_binary '',3,3,1,_binary '\0',NULL,'2025-05-01 23:16:07.000000','Marian_Tillman','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92'),(_binary '',4,4,2,_binary '\0',NULL,'2025-05-01 23:21:21.000000','AndyTowne52','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92'),(_binary '',5,5,1,_binary '\0',NULL,'2025-05-25 23:33:47.000000','ElvisCruickshank67','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92'),(_binary '',6,6,1,_binary '\0',NULL,'2025-05-25 23:36:02.000000','CollinMcDermott','8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92');
/*!40000 ALTER TABLE `tsegusuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tsegusuariorol`
--

LOCK TABLES `tsegusuariorol` WRITE;
/*!40000 ALTER TABLE `tsegusuariorol` DISABLE KEYS */;
/*!40000 ALTER TABLE `tsegusuariorol` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-29 23:43:04
