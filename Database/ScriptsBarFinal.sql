














-- Creación de la base de datos
CREATE DATABASE BarUnibosque;
USE BarUnibosque;

CREATE TABLE pubs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    entrytime VARCHAR(10),
    closingtime VARCHAR(10),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL
);

-- Tabla de clientes
CREATE TABLE customers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    location VARCHAR(255),
    phone VARCHAR(20),
    email VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL
);

-- Creación de la tabla de tipos de bebida
CREATE TABLE drink_types(
    id INT AUTO_INCREMENT PRIMARY KEY,
    types VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL
    
);

-- Tabla de bebidas
CREATE TABLE drinks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    types INT,
    price DECIMAL(10, 2),
   availability INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    FOREIGN KEY (types) REFERENCES drink_types(id)
);




-- Tabla de usuarios
CREATE TABLE users(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
   phone VARCHAR(20),
    id_role INT,
    hash_password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL
);

-- Tabla de pedidos
CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_customer INT,
    id_pub INT,
    dates VARCHAR(10),
    id_user INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    FOREIGN KEY (id_customer) REFERENCES customers(id),
    FOREIGN KEY (id_pub) REFERENCES pubs(id),
    FOREIGN KEY (id_user) REFERENCES users(id)
);

-- Tabla de detalles de pedidos
CREATE TABLE order_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_order INT,
    id_drink INT,
    amount INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    FOREIGN KEY (id_order) REFERENCES orders(id),
    FOREIGN KEY (id_drink) REFERENCES drinks(id)
);


-- Tabla de facturas
CREATE TABLE invoices(
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_order INT,
    total DECIMAL(10, 2),
    dates VARCHAR(16) ,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    deleted_at TIMESTAMP NULL,
    FOREIGN KEY (id_order) REFERENCES orders(id)
);


-- Creación de la tabla de logs
CREATE TABLE logs (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tables VARCHAR(50) NOT NULL,
    operation VARCHAR(50) NOT NULL,
    users VARCHAR(255),
    detail TEXT,
    dates TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);





CREATE VIEW top_selling_drinks AS
SELECT d.name AS drink_name, COUNT(od.id) AS total_orders
FROM drinks d
JOIN order_details od ON d.id = od.id_drink
WHERE d.deleted_at IS NULL AND od.deleted_at IS NULL
GROUP BY d.name
ORDER BY total_orders DESC
LIMIT 5;




CREATE VIEW pub_revenue AS
SELECT p.id, p.name, COALESCE(SUM(i.total), 0) AS total_revenue
FROM pubs p
LEFT JOIN orders o ON p.id = o.id_pub
LEFT JOIN invoices i ON o.id = i.id_order
WHERE p.deleted_at IS NULL
GROUP BY p.id, p.name;

CREATE VIEW frequent_customers AS
SELECT c.id, c.name, c.email, COUNT(*) AS total_orders
FROM customers c
JOIN orders o ON c.id = o.id_customer
WHERE c.deleted_at IS NULL AND o.deleted_at IS NULL
GROUP BY c.id
ORDER BY total_orders DESC;



Select * from logs

-- Creación del trigger para la tabla pubs
CREATE TRIGGER logs_trigger_pubs
AFTER INSERT ON pubs
FOR EACH ROW
BEGIN
    INSERT INTO logs (tables, operation, users, detail)
    VALUES ('pubs', 'INSERT', NEW.name, CONCAT('New pub inserted: ', NEW.name));
END;

-- Creación del trigger para la tabla drinks
CREATE TRIGGER logs_trigger_drinks
AFTER INSERT ON drinks
FOR EACH ROW
BEGIN
    INSERT INTO logs (tables, operation, users, detail)
    VALUES ('drinks', 'INSERT', NEW.name, CONCAT('New drink inserted: ', NEW.name));
END;

-- Creación del trigger para la tabla users
CREATE TRIGGER logs_trigger_users
AFTER INSERT ON users
FOR EACH ROW
BEGIN
    INSERT INTO logs (tables, operation, users, detail)
    VALUES ('users', 'INSERT', NEW.name, CONCAT('New user inserted: ', NEW.name));
END;

-- Creación del trigger para la tabla drink_types
CREATE TRIGGER logs_trigger_drink_types
AFTER INSERT ON drink_types
FOR EACH ROW
BEGIN
    INSERT INTO logs (tables, operation, users, detail)
    VALUES ('drink_types', 'INSERT', NEW.types, CONCAT('New drink type inserted: ', NEW.types));
END;

-- Creación del trigger para la tabla invoices
CREATE TRIGGER logs_trigger_invoices
AFTER INSERT ON invoices
FOR EACH ROW
BEGIN
    INSERT INTO logs (tables, operation, users, detail)
    VALUES ('invoices', 'INSERT', NEW.total, CONCAT('New invoice inserted: ', NEW.total));
END;

-- Creación del trigger para la tabla order_details
CREATE TRIGGER logs_trigger_order_details
AFTER INSERT ON order_details
FOR EACH ROW
BEGIN
    INSERT INTO logs (tables, operation, users, detail)
    VALUES ('order_details', 'INSERT', NEW.amount, CONCAT('New order detail inserted: ', NEW.amount));
END;

-- Creación del trigger para la tabla orders
CREATE TRIGGER logs_trigger_orders
AFTER INSERT ON orders
FOR EACH ROW
BEGIN
    INSERT INTO logs (tables, operation, users, detail)
    VALUES ('orders', 'INSERT', NEW.dates, CONCAT('New order inserted: ', NEW.dates));
END;

-- Creación del trigger para la tabla customers
CREATE TRIGGER logs_trigger_customers
AFTER INSERT ON customers
FOR EACH ROW
BEGIN
    INSERT INTO logs (tables, operation, users, detail)
    VALUES ('customers', 'INSERT', NEW.name, CONCAT('New customer inserted: ', NEW.name));
END;

-- Trigger para actualizacion 

-- Trigger para la tabla pubs
CREATE TRIGGER logs_update_trigger_pubs
AFTER UPDATE ON pubs
FOR EACH ROW
BEGIN
    IF OLD.deleted_at = NEW.deleted_at THEN
        INSERT INTO logs (tables, operation, users, detail)
        VALUES ('pubs', 'UPDATE', NEW.name, CONCAT('Pub updated: ', NEW.name));
    END IF;
END;



-- Trigger para la tabla drinks
CREATE TRIGGER logs_update_trigger_drinks
AFTER UPDATE ON drinks
FOR EACH ROW
BEGIN
    IF OLD.deleted_at = NEW.deleted_at THEN
        INSERT INTO logs (tables, operation, users, detail)
        VALUES ('drinks', 'UPDATE', NEW.name, CONCAT('Drink updated: ', NEW.name));
    END IF;
END;

-- Trigger para la tabla users
CREATE TRIGGER logs_update_trigger_users
AFTER UPDATE ON users
FOR EACH ROW
BEGIN
    IF OLD.deleted_at = NEW.deleted_at THEN
        INSERT INTO logs (tables, operation, users, detail)
        VALUES ('users', 'UPDATE', NEW.name, CONCAT('User updated: ', NEW.name));
    END IF;
END;

-- Trigger para la tabla drink_types
CREATE TRIGGER logs_update_trigger_drink_types
AFTER UPDATE ON drink_types
FOR EACH ROW
BEGIN
    IF OLD.deleted_at = NEW.deleted_at THEN
        INSERT INTO logs (tables, operation, users, detail)
        VALUES ('drink_types', 'UPDATE', NEW.types, CONCAT('Drink type updated: ', NEW.types));
    END IF;
END;

-- Trigger para la tabla invoices
CREATE TRIGGER logs_update_trigger_invoices
AFTER UPDATE ON invoices
FOR EACH ROW
BEGIN
    IF OLD.deleted_at = NEW.deleted_at THEN
        INSERT INTO logs (tables, operation, users, detail)
        VALUES ('invoices', 'UPDATE', NEW.total, CONCAT('Invoice updated: ', NEW.total));
    END IF;
END;

-- Trigger para la tabla order_details
CREATE TRIGGER logs_update_trigger_order_details
AFTER UPDATE ON order_details
FOR EACH ROW
BEGIN
    IF OLD.deleted_at = NEW.deleted_at THEN
        INSERT INTO logs (tables, operation, users, detail)
        VALUES ('order_details', 'UPDATE', NEW.amount, CONCAT('Order detail updated: ', NEW.amount));
    END IF;
END;

-- Trigger para la tabla orders
CREATE TRIGGER logs_update_trigger_orders
AFTER UPDATE ON orders
FOR EACH ROW
BEGIN
    IF OLD.deleted_at = NEW.deleted_at THEN
        INSERT INTO logs (tables, operation, users, detail)
        VALUES ('orders', 'UPDATE', NEW.dates, CONCAT('Order updated: ', NEW.dates));
    END IF;
END;

-- Trigger para la tabla customers
CREATE TRIGGER logs_update_trigger_customers
AFTER UPDATE ON customers
FOR EACH ROW
BEGIN
    IF OLD.deleted_at = NEW.deleted_at THEN
        INSERT INTO logs (tables, operation, users, detail)
        VALUES ('customers', 'UPDATE', NEW.name, CONCAT('Customer updated: ', NEW.name));
    END IF;
END;



-- Creacion triggers para eliminacion

-- Trigger para la tabla pubs
CREATE TRIGGER logs_update_deleted_at_trigger_pubs
AFTER UPDATE ON pubs
FOR EACH ROW
BEGIN
    IF OLD.deleted_at != NEW.deleted_at THEN
        INSERT INTO logs (tables, operation, users, detail)
        VALUES ('pubs', 'DELETED', NEW.name, CONCAT('Pub deleted_at updated: ', NEW.deleted_at));
    END IF;
END;

-- Trigger para la tabla drinks
CREATE TRIGGER logs_update_deleted_at_trigger_drinks
AFTER UPDATE ON drinks
FOR EACH ROW
BEGIN
    IF OLD.deleted_at != NEW.deleted_at THEN
        INSERT INTO logs (tables, operation, users, detail)
        VALUES ('drinks', 'DELETED', NEW.name, CONCAT('Drink deleted_at updated: ', NEW.deleted_at));
    END IF;
END;

-- Trigger para la tabla users
CREATE TRIGGER logs_update_deleted_at_trigger_users
AFTER UPDATE ON users
FOR EACH ROW
BEGIN
    IF OLD.deleted_at != NEW.deleted_at THEN
        INSERT INTO logs (tables, operation, users, detail)
        VALUES ('users', 'DELETED', NEW.name, CONCAT('User deleted_at updated: ', NEW.deleted_at));
    END IF;
END;

-- Trigger para la tabla drink_types
CREATE TRIGGER logs_update_deleted_at_trigger_drink_types
AFTER UPDATE ON drink_types
FOR EACH ROW
BEGIN
    IF OLD.deleted_at != NEW.deleted_at THEN
        INSERT INTO logs (tables, operation, users, detail)
        VALUES ('drink_types', 'DELETED', NEW.types, CONCAT('Drink type deleted_at updated: ', NEW.deleted_at));
    END IF;
END;

-- Trigger para la tabla invoices
CREATE TRIGGER logs_update_deleted_at_trigger_invoices
AFTER UPDATE ON invoices
FOR EACH ROW
BEGIN
    IF OLD.deleted_at != NEW.deleted_at THEN
        INSERT INTO logs (tables, operation, users, detail)
        VALUES ('invoices', 'DELETED', NEW.total, CONCAT('Invoice deleted_at updated: ', NEW.deleted_at));
    END IF;
END;

-- Trigger para la tabla order_details
CREATE TRIGGER logs_update_deleted_at_trigger_order_details
AFTER UPDATE ON order_details
FOR EACH ROW
BEGIN
    IF OLD.deleted_at != NEW.deleted_at THEN
        INSERT INTO logs (tables, operation, users, detail)
        VALUES ('order_details', 'DELETED', NEW.amount, CONCAT('Order detail deleted_at updated: ', NEW.deleted_at));
    END IF;
END;

-- Trigger para la tabla orders
CREATE TRIGGER logs_update_deleted_at_trigger_orders
AFTER UPDATE ON orders
FOR EACH ROW
BEGIN
    IF OLD.deleted_at != NEW.deleted_at THEN
        INSERT INTO logs (tables, operation, users, detail)
        VALUES ('orders', 'DELETED', NEW.dates, CONCAT('Order deleted_at updated: ', NEW.deleted_at));
    END IF;
END;

-- Trigger para la tabla customers
CREATE TRIGGER logs_update_deleted_at_trigger_customers
AFTER UPDATE ON customers
FOR EACH ROW
BEGIN
    IF OLD.deleted_at != NEW.deleted_at THEN
        INSERT INTO logs (tables, operation, users, detail)
        VALUES ('customers', 'DELETED', NEW.name, CONCAT('Customer deleted_at updated: ', NEW.deleted_at));
    END IF;
END;


-- Creación del índice para la tabla pubs
CREATE INDEX idx_pubs_name ON pubs(name);

-- Creación del índice para la tabla drinks
CREATE INDEX idx_drinks_name ON drinks(name);

-- Creación del índice para la tabla users
CREATE INDEX idx_users_name ON users(name);

-- Creación del índice para la tabla drink_types
CREATE INDEX idx_drink_types_types ON drink_types(types);

-- Creación del índice para la tabla invoices
CREATE INDEX idx_invoices_total ON invoices(total);

-- Creación del índice para la tabla order_details
CREATE INDEX idx_order_details_amount ON order_details(amount);

-- Creación del índice para la tabla orders
CREATE INDEX idx_orders_dates ON orders(dates);

-- Creación del índice para la tabla customers
CREATE INDEX idx_customers_name ON customers(name);