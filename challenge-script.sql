DROP schema if exists devsu;
CREATE schema devsu;

USE devsu;

-- DESCRIBE clientes;

CREATE table clientes (Persona_id VARCHAR(50) NOT NULL PRIMARY KEY, apellido VARCHAR(25) NOT NULL, 
DIRECCION VARCHAR(255), DNI INT NOT NULL, EDAD INT, GENERO VARCHAR(10), NOMBRE VARCHAR(25), 
TELEFONO VARCHAR(25),CREATION_TIMESTAMP DATETIME, PASSWORD VARCHAR(4) NOT NULL, SOFT_DELETE BOOLEAN );

INSERT INTO clientes VALUES( '5f684706-cfdd-471a-9f61-e6e0199ec63f', 'Garcia', 
'Rodriguez 3221, Rosario, Santa Fe, Argentina', 24895643, 25, 'MUJER', 'Inti', '+543413029855',
'2021-11-21 09:31:22', "1234", 0) , ('d1de1bb4-dbad-4fbe-ad5e-ff39a5b4e2f1', 'Li', 
'Pasco 3221, Rosario, Santa Fe, Argentina', 24321643, 35, 'HOMBRE', 'Juan', '+543413032155',
'2021-10-20 09:31:22', "4321", 0); 
 -- describe cuentas;
 
CREATE TABLE CUENTAS (CUENTA_ID varchar(50) NOT NULL PRIMARY KEY, CREATION_TIMESTAMP DATETIME, 
DISPONIBLE DECIMAL(19,2),NUMERO_CUENTA VARCHAR(6), SALDO DECIMAL(19,2), 
SALDO_INICIAL DECIMAL(19,2), SOFT_DELETE BOOLEAN, TIPO VARCHAR(25), ULTIMA_TRANSACCION datetime,
CLIENTE_ID VARCHAR(50) NOT NULL);
ALTER TABLE CUENTAS MODIFY COLUMN CLIENTE_ID VARCHAR(50) NOT NULL, ADD KEY (CLIENTE_ID);

INSERT INTO cuentas VALUES ('8643af21-eea2-450d-a650-8ce5814f6a90', '2021-11-21 09:31:22', 1000.00,  
'123456', 80000.00, 80000.00, 0, 'CAJA DE AHORROS','2021-11-21 09:31:22', 
'5f684706-cfdd-471a-9f61-e6e0199ec63f'), ('fa495ca7-a6e1-41c9-bb27-66f2bc836b0d', '2021-10-20 09:31:22', 1000.00,  
'654321', 70000.00, 70000.00, 0, 'CUENTA CORRIENTE','2021-10-20 09:31:22', 'd1de1bb4-dbad-4fbe-ad5e-ff39a5b4e2f1');

SELECT * FROM devsu.cuentas;
Select * FROM devsu.clientes;