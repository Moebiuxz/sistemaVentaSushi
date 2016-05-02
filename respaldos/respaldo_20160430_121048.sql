/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE cliente (
  cliente_fono varchar(15) NOT NULL,
  cliente_nombre varchar(50) DEFAULT NULL,
  cliente_apellido varchar(100) DEFAULT NULL,
  cliente_nacimiento date DEFAULT NULL,
  cliente_estado bit(1) DEFAULT NULL,
  PRIMARY KEY (cliente_fono)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE personal (
  personal_id int(11) NOT NULL AUTO_INCREMENT,
  personal_rut varchar(12) DEFAULT NULL,
  personal_nombre varchar(100) DEFAULT NULL,
  personal_apellidos varchar(100) DEFAULT NULL,
  personal_tipo int(11) DEFAULT NULL,
  personal_usuario int(11) DEFAULT NULL,
  personal_estado bit(1) DEFAULT NULL,
  PRIMARY KEY (personal_id),
  KEY personal_tipo (personal_tipo),
  KEY personal_usuario (personal_usuario)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO personal (personal_id, personal_rut, personal_nombre, personal_apellidos, personal_tipo, personal_usuario, personal_estado) VALUES (1,'11-1','Luis','Martinez',2,2,'\0');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE producto (
  producto_id int(11) NOT NULL AUTO_INCREMENT,
  producto_nombre varchar(100) DEFAULT NULL,
  producto_precio int(11) DEFAULT NULL,
  producto_tipo int(11) DEFAULT NULL,
  producto_estado bit(1) DEFAULT NULL,
  PRIMARY KEY (producto_id),
  KEY producto_tipo (producto_tipo)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE productopromocion (
  productoPromocion_id int(11) NOT NULL AUTO_INCREMENT,
  productoPromocion_idPromocion int(11) DEFAULT NULL,
  productoPromocion_idProducto int(11) DEFAULT NULL,
  productoPromocion_estado bit(1) DEFAULT NULL,
  PRIMARY KEY (productoPromocion_id),
  KEY productoPromocion_idPromocion (productoPromocion_idPromocion),
  KEY productoPromocion_idProducto (productoPromocion_idProducto)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE productoventa (
  productoVenta_id int(11) NOT NULL AUTO_INCREMENT,
  productoVenta_producto int(11) DEFAULT NULL,
  productoVenta_venta int(11) DEFAULT NULL,
  productoVenta_estado bit(1) DEFAULT NULL,
  PRIMARY KEY (productoVenta_id),
  KEY productoVenta_producto (productoVenta_producto),
  KEY productoVenta_venta (productoVenta_venta)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE promocion (
  promocion_id int(11) NOT NULL AUTO_INCREMENT,
  promocion_nombre varchar(100) DEFAULT NULL,
  promocion_descueto int(11) DEFAULT NULL,
  promocion_estado bit(1) DEFAULT NULL,
  PRIMARY KEY (promocion_id)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE respaldo (
  respaldo_id int(11) NOT NULL AUTO_INCREMENT,
  respaldo_fecha date DEFAULT NULL,
  respaldo_hora time DEFAULT NULL,
  respaldo_tipo varchar(100) DEFAULT NULL,
  PRIMARY KEY (respaldo_id)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO respaldo (respaldo_id, respaldo_fecha, respaldo_hora, respaldo_tipo) VALUES (1,'2016-04-30','01:01:28','0'),(2,'2016-04-30','01:55:13','0'),(3,'2016-04-30','12:10:45','0'),(4,'2016-04-30','12:10:46','0'),(5,'2016-04-30','12:10:47','0');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE tipopersonal (
  tipoPersonal_id int(11) NOT NULL AUTO_INCREMENT,
  tipoPersonal_nombre varchar(100) DEFAULT NULL,
  tipoPersonal_estado bit(1) DEFAULT NULL,
  PRIMARY KEY (tipoPersonal_id)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO tipopersonal (tipoPersonal_id, tipoPersonal_nombre, tipoPersonal_estado) VALUES (1,'Cocinero',''),(2,'Vendedor',''),(3,'Repartidor','');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE tipoproducto (
  tipoProducto_id int(11) NOT NULL AUTO_INCREMENT,
  tipoProducto_nombre varchar(100) DEFAULT NULL,
  tipoProducto_estado bit(1) DEFAULT NULL,
  PRIMARY KEY (tipoProducto_id)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO tipoproducto (tipoProducto_id, tipoProducto_nombre, tipoProducto_estado) VALUES (1,'Sushi',''),(2,'Bebestible',''),(3,'Postre','');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE tipousuario (
  tipoUsuario_id int(11) NOT NULL AUTO_INCREMENT,
  tipoUsuario_nombre varchar(100) DEFAULT NULL,
  tipoUsuario_estado bit(1) DEFAULT NULL,
  PRIMARY KEY (tipoUsuario_id)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO tipousuario (tipoUsuario_id, tipoUsuario_nombre, tipoUsuario_estado) VALUES (1,'ADMIN',''),(2,'NORMAL','');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE usuario (
  usuario_id int(11) NOT NULL AUTO_INCREMENT,
  usuario_nombre varchar(100) DEFAULT NULL,
  usuario_clave blob,
  usuario_tipo int(11) DEFAULT NULL,
  usuario_estado bit(1) DEFAULT NULL,
  PRIMARY KEY (usuario_id),
  KEY usuario_tipo (usuario_tipo)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
INSERT INTO usuario (usuario_id, usuario_nombre, usuario_clave, usuario_tipo, usuario_estado) VALUES (1,'admin','º0U’\î …ôw\ænw\×\ï',1,''),(2,'Vendedor1','º0U’\î …ôw\ænw\×\ï',2,'\0');
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE venta (
  venta_id int(11) NOT NULL AUTO_INCREMENT,
  venta_fecha datetime DEFAULT NULL,
  venta_personal int(11) DEFAULT NULL,
  venta_cliente varchar(15) DEFAULT NULL,
  venta_estado bit(1) DEFAULT NULL,
  PRIMARY KEY (venta_id),
  KEY venta_personal (venta_personal),
  KEY venta_cliente (venta_cliente)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

