-- MySQL dump 10.16  Distrib 10.1.13-MariaDB, for Win32 (AMD64)
--
-- Host: localhost    Database: bd_sushi
-- ------------------------------------------------------
-- Server version	10.1.13-MariaDB

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
-- Current Database: `bd_sushi`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `bd_sushi` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `bd_sushi`;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `cliente_fono` varchar(50) NOT NULL,
  `cliente_nombre` varchar(50) DEFAULT NULL,
  `cliente_apellido` varchar(100) DEFAULT NULL,
  `cliente_estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`cliente_fono`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('555','555','555','');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personal`
--

DROP TABLE IF EXISTS `personal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal` (
  `personal_id` int(11) NOT NULL AUTO_INCREMENT,
  `personal_rut` varchar(12) DEFAULT NULL,
  `personal_nombre` varchar(100) DEFAULT NULL,
  `personal_apellidos` varchar(100) DEFAULT NULL,
  `personal_tipo` int(11) DEFAULT NULL,
  `personal_usuario` int(11) DEFAULT NULL,
  `personal_estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`personal_id`),
  KEY `personal_tipo` (`personal_tipo`),
  KEY `personal_usuario` (`personal_usuario`),
  CONSTRAINT `personal_ibfk_1` FOREIGN KEY (`personal_tipo`) REFERENCES `tipopersonal` (`tipoPersonal_id`),
  CONSTRAINT `personal_ibfk_2` FOREIGN KEY (`personal_usuario`) REFERENCES `usuario` (`usuario_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personal`
--

LOCK TABLES `personal` WRITE;
/*!40000 ALTER TABLE `personal` DISABLE KEYS */;
INSERT INTO `personal` VALUES (1,'admin','Admin','Admin',2,1,''),(2,'11-1','Luis','Martinez',2,2,'');
/*!40000 ALTER TABLE `personal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `producto` (
  `producto_id` int(11) NOT NULL AUTO_INCREMENT,
  `producto_nombre` varchar(100) DEFAULT NULL,
  `producto_precio` int(11) DEFAULT NULL,
  `producto_tipo` int(11) DEFAULT NULL,
  `producto_estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`producto_id`),
  KEY `producto_tipo` (`producto_tipo`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`producto_tipo`) REFERENCES `tipoproducto` (`tipoProducto_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,'nada',0,1,'');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productopromocion`
--

DROP TABLE IF EXISTS `productopromocion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productopromocion` (
  `productoPromocion_id` int(11) NOT NULL AUTO_INCREMENT,
  `productoPromocion_idPromocion` int(11) DEFAULT NULL,
  `productoPromocion_idProducto` int(11) DEFAULT NULL,
  `productoPromocion_estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`productoPromocion_id`),
  KEY `productoPromocion_idPromocion` (`productoPromocion_idPromocion`),
  KEY `productoPromocion_idProducto` (`productoPromocion_idProducto`),
  CONSTRAINT `productopromocion_ibfk_1` FOREIGN KEY (`productoPromocion_idPromocion`) REFERENCES `promocion` (`promocion_id`),
  CONSTRAINT `productopromocion_ibfk_2` FOREIGN KEY (`productoPromocion_idProducto`) REFERENCES `producto` (`producto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productopromocion`
--

LOCK TABLES `productopromocion` WRITE;
/*!40000 ALTER TABLE `productopromocion` DISABLE KEYS */;
/*!40000 ALTER TABLE `productopromocion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productoventa`
--

DROP TABLE IF EXISTS `productoventa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productoventa` (
  `productoVenta_id` int(11) NOT NULL AUTO_INCREMENT,
  `productoVenta_producto` int(11) DEFAULT NULL,
  `productoVenta_venta` int(11) DEFAULT NULL,
  `productoVenta_estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`productoVenta_id`),
  KEY `productoVenta_producto` (`productoVenta_producto`),
  KEY `productoVenta_venta` (`productoVenta_venta`),
  CONSTRAINT `productoventa_ibfk_1` FOREIGN KEY (`productoVenta_producto`) REFERENCES `producto` (`producto_id`),
  CONSTRAINT `productoventa_ibfk_2` FOREIGN KEY (`productoVenta_venta`) REFERENCES `venta` (`venta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productoventa`
--

LOCK TABLES `productoventa` WRITE;
/*!40000 ALTER TABLE `productoventa` DISABLE KEYS */;
/*!40000 ALTER TABLE `productoventa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promocion`
--

DROP TABLE IF EXISTS `promocion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promocion` (
  `promocion_id` int(11) NOT NULL AUTO_INCREMENT,
  `promocion_nombre` varchar(100) DEFAULT NULL,
  `promocion_descueto` int(11) DEFAULT NULL,
  `promocion_estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`promocion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promocion`
--

LOCK TABLES `promocion` WRITE;
/*!40000 ALTER TABLE `promocion` DISABLE KEYS */;
INSERT INTO `promocion` VALUES (1,'nada',0,'');
/*!40000 ALTER TABLE `promocion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promocionventa`
--

DROP TABLE IF EXISTS `promocionventa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `promocionventa` (
  `productoVenta_id` int(11) NOT NULL AUTO_INCREMENT,
  `productoVenta_promocion` int(11) DEFAULT NULL,
  `productoVenta_venta` int(11) DEFAULT NULL,
  `productoVenta_estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`productoVenta_id`),
  KEY `productoVenta_promocion` (`productoVenta_promocion`),
  KEY `productoVenta_venta` (`productoVenta_venta`),
  CONSTRAINT `promocionventa_ibfk_1` FOREIGN KEY (`productoVenta_promocion`) REFERENCES `promocion` (`promocion_id`),
  CONSTRAINT `promocionventa_ibfk_2` FOREIGN KEY (`productoVenta_venta`) REFERENCES `venta` (`venta_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promocionventa`
--

LOCK TABLES `promocionventa` WRITE;
/*!40000 ALTER TABLE `promocionventa` DISABLE KEYS */;
/*!40000 ALTER TABLE `promocionventa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `respaldo`
--

DROP TABLE IF EXISTS `respaldo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `respaldo` (
  `respaldo_id` int(11) NOT NULL AUTO_INCREMENT,
  `respaldo_fecha` date DEFAULT NULL,
  `respaldo_hora` time DEFAULT NULL,
  `respaldo_tipo` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`respaldo_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `respaldo`
--

LOCK TABLES `respaldo` WRITE;
/*!40000 ALTER TABLE `respaldo` DISABLE KEYS */;
INSERT INTO `respaldo` VALUES (1,'2016-05-05','01:27:27','0');
/*!40000 ALTER TABLE `respaldo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipopersonal`
--

DROP TABLE IF EXISTS `tipopersonal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipopersonal` (
  `tipoPersonal_id` int(11) NOT NULL AUTO_INCREMENT,
  `tipoPersonal_nombre` varchar(100) DEFAULT NULL,
  `tipoPersonal_estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`tipoPersonal_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipopersonal`
--

LOCK TABLES `tipopersonal` WRITE;
/*!40000 ALTER TABLE `tipopersonal` DISABLE KEYS */;
INSERT INTO `tipopersonal` VALUES (1,'Cocinero',''),(2,'Vendedor',''),(3,'Repartidor','');
/*!40000 ALTER TABLE `tipopersonal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipoproducto`
--

DROP TABLE IF EXISTS `tipoproducto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipoproducto` (
  `tipoProducto_id` int(11) NOT NULL AUTO_INCREMENT,
  `tipoProducto_nombre` varchar(100) DEFAULT NULL,
  `tipoProducto_estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`tipoProducto_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipoproducto`
--

LOCK TABLES `tipoproducto` WRITE;
/*!40000 ALTER TABLE `tipoproducto` DISABLE KEYS */;
INSERT INTO `tipoproducto` VALUES (1,'Sushi',''),(2,'Bebestible',''),(3,'Postre','');
/*!40000 ALTER TABLE `tipoproducto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipousuario`
--

DROP TABLE IF EXISTS `tipousuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipousuario` (
  `tipoUsuario_id` int(11) NOT NULL AUTO_INCREMENT,
  `tipoUsuario_nombre` varchar(100) DEFAULT NULL,
  `tipoUsuario_estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`tipoUsuario_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipousuario`
--

LOCK TABLES `tipousuario` WRITE;
/*!40000 ALTER TABLE `tipousuario` DISABLE KEYS */;
INSERT INTO `tipousuario` VALUES (1,'ADMIN',''),(2,'NORMAL','');
/*!40000 ALTER TABLE `tipousuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `usuario_id` int(11) NOT NULL AUTO_INCREMENT,
  `usuario_nombre` varchar(100) DEFAULT NULL,
  `usuario_clave` blob,
  `usuario_tipo` int(11) DEFAULT NULL,
  `usuario_estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`usuario_id`),
  KEY `usuario_tipo` (`usuario_tipo`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`usuario_tipo`) REFERENCES `tipousuario` (`tipoUsuario_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'admin','º0U’î …ôwænw×ï',1,''),(2,'Luis',';2ÿŒG \0Ý~–Ï5J',2,'');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `venta` (
  `venta_id` int(11) NOT NULL AUTO_INCREMENT,
  `venta_fecha` datetime DEFAULT NULL,
  `venta_personal` int(11) DEFAULT NULL,
  `venta_cliente` varchar(15) DEFAULT NULL,
  `venta_total` int(11) DEFAULT NULL,
  `venta_estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`venta_id`),
  KEY `venta_personal` (`venta_personal`),
  KEY `venta_cliente` (`venta_cliente`),
  CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`venta_personal`) REFERENCES `personal` (`personal_id`),
  CONSTRAINT `venta_ibfk_2` FOREIGN KEY (`venta_cliente`) REFERENCES `cliente` (`cliente_fono`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-05-05  1:27:53
