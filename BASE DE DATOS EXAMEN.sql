CREATE DATABASE proyectos;
USE proyectos;

CREATE TABLE vehiculos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    marca VARCHAR(50),
    modelo VARCHAR(50),
    año INT,
    precio DECIMAL(10, 2)
);
-- Insertar datos en la tabla vehiculos
INSERT INTO vehiculos (marca, modelo, año, precio) VALUES
('Ford', 'Focus', 2019, 15000),
('Chevrolet', 'Camaro', 2020, 40000);
