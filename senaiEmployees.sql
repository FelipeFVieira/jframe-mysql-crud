CREATE DATABASE SENAI;

USE SENAI;

CREATE TABLE employees (
	id int AUTO_INCREMENT,
    name VARCHAR(120),
    cpf VARCHAR(14),
    role VARCHAR(100),
    CONSTRAINT pk_employees PRIMARY KEY (id)
    );
    
INSERT INTO employees (name, cpf, role) VALUES
('Alexsandro Bittencourt', '415.123.356-39', 'Technical Instructor'),
('Lucas Correa', '436.614.242-41', 'Technical Instructor'),
('Julio Freitas', '534.124.324-40', 'Technical Instructor'),
('José Chile', '532.354.324-12', 'Technical Coordinator'),
('Atila Olivi', '233.135.652-41', 'Technical Instructor'),
('Mathias Viana', '643.334.323-45', 'Technical Instructor'),
('Welington Alves', '235.355.346-32', 'Technical Instructor')
;

