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
-- Table structure for table `tcitcitas`
--

DROP TABLE IF EXISTS `tcitcitas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tcitcitas` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `IDPERSONA` int NOT NULL,
  `IDPROFECIONAL` int NOT NULL,
  `FECHAACTUALIZACION` datetime(6) DEFAULT NULL,
  `FECHADESDE` datetime(6) DEFAULT NULL,
  `FECHAHASTA` datetime(6) DEFAULT NULL,
  `FECHARESERVA` datetime(6) DEFAULT NULL,
  `MOTIVO` varchar(100) DEFAULT NULL,
  `ESTADO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKr627464mqx4xr3dtsumhtm13f` (`IDPERSONA`),
  KEY `FKqsbgwbi27as2c3yyihn0uqknj` (`IDPROFECIONAL`),
  CONSTRAINT `FKqsbgwbi27as2c3yyihn0uqknj` FOREIGN KEY (`IDPROFECIONAL`) REFERENCES `tperempleado` (`ID`),
  CONSTRAINT `FKr627464mqx4xr3dtsumhtm13f` FOREIGN KEY (`IDPERSONA`) REFERENCES `tperpersona` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tempempresa`
--

DROP TABLE IF EXISTS `tempempresa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tempempresa` (
  `RUC` varchar(15) NOT NULL,
  `NOMBRE` varchar(50) DEFAULT NULL,
  `ACTIVIDADECONOMICA` varchar(100) DEFAULT NULL,
  `DESCRIPCION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`RUC`),
  UNIQUE KEY `UK_o5lanpa4yh6v380m0f7q41amj` (`NOMBRE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tempempresadetalle`
--

DROP TABLE IF EXISTS `tempempresadetalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tempempresadetalle` (
  `ID` int NOT NULL,
  `x` double NOT NULL,
  `y` double NOT NULL,
  `IDEMPRESA` varchar(15) DEFAULT NULL,
  `calles` varchar(255) DEFAULT NULL,
  `canton` varchar(255) DEFAULT NULL,
  `cuidad` varchar(255) DEFAULT NULL,
  `numeroResidencia` varchar(255) DEFAULT NULL,
  `parroquia` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_ctbeojawx9l3rlioagyjo7qft` (`IDEMPRESA`),
  CONSTRAINT `FKs4538a6ycbe8p6dqr5phrpepc` FOREIGN KEY (`IDEMPRESA`) REFERENCES `tempempresa` (`RUC`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tpagfactura`
--

DROP TABLE IF EXISTS `tpagfactura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tpagfactura` (
  `DESCUENTO` int NOT NULL,
  `ID` int NOT NULL AUTO_INCREMENT,
  `IDFORMAGO` int DEFAULT NULL,
  `IVA` int NOT NULL,
  `MONTOORIGINAL` decimal(3,0) NOT NULL,
  `MONTOTOTAL` decimal(38,2) NOT NULL,
  `FCREACION` datetime(6) NOT NULL,
  `DESCRIPCION` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKmbysmpt6y3ndjy9aalu9jwg7e` (`IDFORMAGO`),
  CONSTRAINT `FKmbysmpt6y3ndjy9aalu9jwg7e` FOREIGN KEY (`IDFORMAGO`) REFERENCES `tpagformapago` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tpagformapago`
--

DROP TABLE IF EXISTS `tpagformapago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tpagformapago` (
  `ACTIVO` bit(1) DEFAULT NULL,
  `ID` int NOT NULL AUTO_INCREMENT,
  `FACTUALIZACION` datetime(6) DEFAULT NULL,
  `FCREACION` datetime(6) DEFAULT NULL,
  `NOMBRE` varchar(30) DEFAULT NULL,
  `DESCRIPCION` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_4qoweg846pso33qu083evjgsk` (`NOMBRE`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tpagpago`
--

DROP TABLE IF EXISTS `tpagpago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tpagpago` (
  `IDFACTURA` int DEFAULT NULL,
  `IDFORMAPAGO` int DEFAULT NULL,
  `MONTO` decimal(3,0) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `FCREACION` datetime(6) DEFAULT NULL,
  `IDUSUARIO` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kejv1fwd0hd1y1afj05q3jea1` (`IDUSUARIO`),
  KEY `FKnxu2a8lwd8qas7a1exmkcb4o8` (`IDFACTURA`),
  KEY `FKiu8okxx0v1leb6e522mpmdvgv` (`IDFORMAPAGO`),
  CONSTRAINT `FKiu8okxx0v1leb6e522mpmdvgv` FOREIGN KEY (`IDFORMAPAGO`) REFERENCES `tpagformapago` (`ID`),
  CONSTRAINT `FKljb9624qqg30ndwmmo52xkq4k` FOREIGN KEY (`IDUSUARIO`) REFERENCES `tperpersona` (`ID`),
  CONSTRAINT `FKnxu2a8lwd8qas7a1exmkcb4o8` FOREIGN KEY (`IDFACTURA`) REFERENCES `tpagfactura` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tperempcategoria`
--

DROP TABLE IF EXISTS `tperempcategoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tperempcategoria` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(30) NOT NULL,
  `DESCRIPCION` varchar(200) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_6s4nmwggxv3ar1s42bbf2gtnr` (`NOMBRE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tperempleado`
--

DROP TABLE IF EXISTS `tperempleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tperempleado` (
  `EXPERIENCIA` float NOT NULL,
  `ID` int NOT NULL AUTO_INCREMENT,
  `IDPERSONA` int NOT NULL,
  `IDPROFECION` int NOT NULL,
  `SLOGAN` varchar(250) NOT NULL,
  `IMAGEN` longblob,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_lrlsydod9tbfp63van40djhyl` (`IDPERSONA`),
  UNIQUE KEY `UK_3r7n7iy5lt685bo7ik8n8h9ds` (`IDPROFECION`),
  CONSTRAINT `FKluqnqwa2jja9yr30nwpc0uhk7` FOREIGN KEY (`IDPROFECION`) REFERENCES `tperprofecion` (`ID`),
  CONSTRAINT `FKtnmyd2cud0cgjfr7707w3qea4` FOREIGN KEY (`IDPERSONA`) REFERENCES `tperpersona` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tperpersona`
--

DROP TABLE IF EXISTS `tperpersona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tperpersona` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `FECHAACTUALIZACION` datetime(6) DEFAULT NULL,
  `FECHAINGRESO` datetime(6) NOT NULL,
  `FNACIMIENTO` datetime(6) DEFAULT NULL,
  `IDENTIFICACION` varchar(10) DEFAULT NULL,
  `TELEFONO` varchar(10) DEFAULT NULL,
  `EMAIL` varchar(30) DEFAULT NULL,
  `APELLIDO` varchar(100) DEFAULT NULL,
  `NOMBRE` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_36bge744eyj8q98ame258pls6` (`IDENTIFICACION`),
  UNIQUE KEY `UK_escptqmrxxoxj7q3ww3ie2e1j` (`EMAIL`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tperprofecion`
--

DROP TABLE IF EXISTS `tperprofecion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tperprofecion` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `NOMBRE` varchar(100) NOT NULL,
  `DESCRIPCION` varchar(250) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_chxtai3uoxqaksv4fagcwo0oj` (`NOMBRE`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsegrol`
--

DROP TABLE IF EXISTS `tsegrol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tsegrol` (
  `ACTIVO` bit(1) DEFAULT NULL,
  `ID` int NOT NULL,
  `NOMBRE` varchar(30) NOT NULL,
  `DESCRIPCION` varchar(250) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_by0p0vclk3i4n1f94fv2byi8y` (`NOMBRE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsegrol_seq`
--

DROP TABLE IF EXISTS `tsegrol_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tsegrol_seq` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsegusuario`
--

DROP TABLE IF EXISTS `tsegusuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tsegusuario` (
  `ACTIVO` bit(1) DEFAULT NULL,
  `ID` int NOT NULL AUTO_INCREMENT,
  `IDPERSONA` int NOT NULL,
  `IDROL` int NOT NULL,
  `SESSION` bit(1) DEFAULT NULL,
  `FECHAACTUALIZACION` datetime(6) DEFAULT NULL,
  `FECHAINGRESO` datetime(6) NOT NULL,
  `USUARIO` varchar(20) NOT NULL,
  `CLAVE` varchar(100) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_1qttx0kb34q8f7sel60wxep3g` (`IDPERSONA`),
  UNIQUE KEY `UK_rgbft99x6labcmuglvf79c4hn` (`USUARIO`),
  KEY `FKqtmm6btw7nlnrkb38608pd5bq` (`IDROL`),
  CONSTRAINT `FKbc2qij9hx30og448qi5plv5bf` FOREIGN KEY (`IDPERSONA`) REFERENCES `tperpersona` (`ID`),
  CONSTRAINT `FKqtmm6btw7nlnrkb38608pd5bq` FOREIGN KEY (`IDROL`) REFERENCES `tsegrol` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tsegusuariorol`
--

DROP TABLE IF EXISTS `tsegusuariorol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tsegusuariorol` (
  `IDROL` int NOT NULL,
  `IDUSUARIO` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `FK54erucfofyuylxosrmuwiffkd` (`IDUSUARIO`),
  KEY `FK9p6b88xhf1gyk80ujobhy2jll` (`IDROL`),
  CONSTRAINT `FK54erucfofyuylxosrmuwiffkd` FOREIGN KEY (`IDUSUARIO`) REFERENCES `tsegusuario` (`ID`),
  CONSTRAINT `FK9p6b88xhf1gyk80ujobhy2jll` FOREIGN KEY (`IDROL`) REFERENCES `tsegrol` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-29 23:41:49
