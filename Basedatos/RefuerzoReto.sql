--Tabla para manejo de estados.
CREATE TABLE estado_prestamo (
	id_estado SERIAL PRIMARY KEY,
    descripcion VARCHAR(20) 
);
 
--Tabla para manejo de clientes.
CREATE TABLE cliente (
	id_cliente BIGINT PRIMARY KEY, 
    nombre VARCHAR(255),
	email VARCHAR(80),
	telefono VARCHAR(20),
    direccion VARCHAR(255)
);
 
--Tabla para manejo de prestamos.
CREATE TABLE prestamo (
	id_prestamo SERIAL PRIMARY KEY,
    monto NUMERIC(15, 2),
	interes NUMERIC(8, 5),
	plazo int,
	estado int REFERENCES estado_prestamo(id_estado),
	cliente BIGINT REFERENCES cliente(id_cliente)
);
 
--Tabla para historia de transacciones de cada prestamo.
CREATE TABLE transaccion (
    id_transaccion SERIAL PRIMARY KEY,
    prestamo_asociado BIGINT REFERENCES prestamo(id_prestamo),
    tipo_novedad VARCHAR(100),
    valor NUMERIC(15, 2),
    fecha DATE,
    hora TIME
);
 
SELECT * FROM estado_prestamo;
SELECT * FROM cliente;
SELECT * FROM prestamo;
SELECT * FROM transaccion;
tiene menú contextual