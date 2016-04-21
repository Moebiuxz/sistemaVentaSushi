CREATE DATABASE bd_sushi;
USE bd_sushi;

CREATE TABLE tipoUsuario (
	tipoUsuario_id INT AUTO_INCREMENT,
	tipoUsuario_nombre VARCHAR(100),
	tipoUsuario_estado BIT, /*1.Activo 2.Inactivo*/
	PRIMARY KEY(tipoUsuario_id)
);

/*INSERT por defecto*/
INSERT INTO tipoUsuario VALUES (NULL, 'ADMIN', 1);
INSERT INTO tipoUsuario VALUES (NULL, 'NORMAL', 1);

CREATE TABLE usuario(
	usuario_id INT AUTO_INCREMENT,
	usuario_nombre VARCHAR(100),
	usuario_clave BLOB,
	usuario_tipo INT,
	usuario_estado BIT, /*1.Activo 2.Inactivo*/
	PRIMARY KEY(usuario_id),
	FOREIGN KEY(usuario_tipo) REFERENCES tipoUsuario(tipoUsuario_id)
);

/*INSERT por defecto*/
INSERT INTO usuario VALUES (NULL, 'admin', AES_ENCRYPT('admin',666), 1, 1);

select * from usuario;

CREATE TABLE tipoPersonal (
	tipoPersonal_id INT AUTO_INCREMENT,
	tipoPersonal_nombre VARCHAR(100),
	tipoPersonal_estado BIT, /*1.Activo 2.Inactivo*/
	PRIMARY KEY(tipoPersonal_id)
);

/*INSERT por defecto*/
INSERT INTO tipoPersonal VALUES (NULL, 'Cocinero', 1);
INSERT INTO tipoPersonal VALUES (NULL, 'Vendedor', 1);
INSERT INTO tipoPersonal VALUES (NULL, 'Repartidor', 1);

CREATE TABLE personal (
	personal_id INT AUTO_INCREMENT,
	personal_rut VARCHAR(12),
	personal_nombre VARCHAR(100),
	personal_apellidos VARCHAR(100),
	personal_tipo INT,
	personal_usuario INT,
	personal_estado BIT, /*1.Activo 2.Inactivo*/
	PRIMARY KEY(personal_id),
	FOREIGN KEY(personal_tipo) REFERENCES tipoPersonal(tipoPersonal_id),
	FOREIGN KEY(personal_usuario) REFERENCES usuario(usuario_id)
);

/*Esta tabla almacena a los clientes que decidan registrarse para recibir promociones.*/
CREATE TABLE cliente (
	cliente_fono VARCHAR(15),
	cliente_nombre VARCHAR(50),
	cliente_apellido VARCHAR(100),
	cliente_nacimiento DATE,
	cliente_estado BIT, /*1.Activo 2.Inactivo*/
	PRIMARY KEY(cliente_fono)
);

/*Categoría a la cual pertenece el producto, brebaje, postre, etc.*/
CREATE TABLE tipoProducto (
	tipoProducto_id INT AUTO_INCREMENT,
	tipoProducto_nombre VARCHAR(100),
	tipoProducto_estado BIT, /*1.Activo 2.Inactivo*/
	PRIMARY KEY(tipoProducto_id)
);

/*Estos vendrían siendo los productos a la venta, por ejemplo sushi apanado, normal, 
o bebida, etc.*/
CREATE TABLE producto (
	producto_id INT AUTO_INCREMENT,
	producto_nombre VARCHAR(100),
	producto_precio INT,
	producto_tipo INT,
	producto_estado BIT, /*1.Activo 2.Inactivo*/
	PRIMARY KEY(producto_id),
	FOREIGN KEY(producto_tipo) REFERENCES tipoProducto(tipoProducto_id)
);

/*Contiene el nombre de la promoción y el descuento que tiene la promo.*/
CREATE TABLE promocion (
	promocion_id INT AUTO_INCREMENT,
	promocion_nombre VARCHAR(100),
	promocion_descueto INT,
	promocion_estado BIT, /*1.Activo 2.Inactivo*/
	PRIMARY KEY(promocion_id)
);

/*Tabla generada por relacion muchos a muchos entre producto y promocion.*/
CREATE TABLE productoPromocion (
	productoPromocion_id INT AUTO_INCREMENT,
	productoPromocion_idPromocion INT,
	productoPromocion_idProducto INT,
	productoPromocion_estado BIT, /*1.Activo 2.Inactivo*/
	PRIMARY KEY(productoPromocion_id),
	FOREIGN KEY(productoPromocion_idPromocion) REFERENCES promocion(promocion_id),
	FOREIGN KEY(productoPromocion_idProducto) REFERENCES producto(producto_id)
);

/*Esta almacena la fecha de la venta, el personal que la hizo y 
al cliente(si se encuentra registrado).*/
CREATE TABLE venta(
	venta_id INT AUTO_INCREMENT,
	venta_fecha DATETIME,
	venta_personal INT,
	venta_cliente VARCHAR(15),
	venta_estado BIT, /*1.Activo 2.Inactivo*/
	PRIMARY KEY(venta_id),
	FOREIGN KEY(venta_personal) REFERENCES personal(personal_id),
	FOREIGN KEY(venta_cliente) REFERENCES cliente(cliente_fono)
);

/*Esta tabla almacena los productos que contiene la venta.*/
CREATE TABLE productoVenta (
	productoVenta_id INT AUTO_INCREMENT,
	productoVenta_producto INT,
	productoVenta_venta INT,
	productoVenta_estado BIT, /*1.Activo 2.Inactivo*/
	PRIMARY KEY(productoVenta_id),
	FOREIGN KEY(productoVenta_producto) REFERENCES producto(producto_id),
	FOREIGN KEY(productoVenta_venta) REFERENCES venta(venta_id)
);

