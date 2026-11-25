DROP DATABASE IF EXISTS brocode_queue;
CREATE DATABASE brocode_queue;
USE brocode_queue;

CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL UNIQUE,
  password_hash VARCHAR(255) NOT NULL
);

CREATE TABLE customers (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  phone VARCHAR(25)
);

CREATE TABLE services (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  duration_min INT NOT NULL,
  price DECIMAL(12,2) NOT NULL DEFAULT 0
);

CREATE TABLE barbers (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  is_active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE queues (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  queue_number VARCHAR(20) NOT NULL UNIQUE,
  customer_id BIGINT NOT NULL,
  service_id BIGINT NOT NULL,
  barber_id BIGINT NULL,
  duration_snapshot INT NOT NULL,
  status ENUM('Waiting','In Progress','Completed','Cancelled') NOT NULL DEFAULT 'Waiting',
  registered_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  started_at DATETIME NULL,
  finished_at DATETIME NULL,
  CONSTRAINT fk_queue_customer FOREIGN KEY (customer_id) REFERENCES customers(id),
  CONSTRAINT fk_queue_service FOREIGN KEY (service_id) REFERENCES services(id),
  CONSTRAINT fk_queue_barber FOREIGN KEY (barber_id) REFERENCES barbers(id)
);

CREATE TABLE queue_history (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  queue_id BIGINT NOT NULL,
  old_status VARCHAR(30),
  new_status VARCHAR(30) NOT NULL,
  changed_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_history_queue FOREIGN KEY (queue_id) REFERENCES queues(id)
);

CREATE INDEX idx_queues_registered_at ON queues(registered_at);
CREATE INDEX idx_queues_status ON queues(status);


SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE queue_history;
TRUNCATE TABLE queues;
TRUNCATE TABLE barbers;
TRUNCATE TABLE services;
TRUNCATE TABLE customers;
TRUNCATE TABLE users;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO users (username, password_hash) VALUES
  ('admin', 'admin123');

INSERT INTO customers (name, phone) VALUES
  ('Farrel', '081234567801'),
  ('Rizky', '081234567802'),
  ('Dimas', '081234567803'),
  ('Naufal', '081234567804'),
  ('Bagas', '081234567805'),
  ('Reza', '081234567806'),
  ('Alif', '081234567807'),
  ('Gilang', '081234567808');

INSERT INTO services (name, duration_min, price) VALUES
  ('Haircut', 30, 35000),
  ('Wash', 15, 15000),
  ('Haircut + Wash', 45, 45000),
  ('Beard Trim', 20, 25000),
  ('Full Grooming', 60, 65000);

INSERT INTO barbers (name, is_active) VALUES
  ('Andi', TRUE),
  ('Budi', TRUE),
  ('Raka', TRUE),
  ('Fajar', TRUE),
  ('Ilham', FALSE);

INSERT INTO queues (queue_number, customer_id, service_id, barber_id, duration_snapshot, status, registered_at, started_at, finished_at) VALUES
  (CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),'-A001'), 1, 1, 1, 30, 'In Progress', NOW() - INTERVAL 15 MINUTE, NOW() - INTERVAL 15 MINUTE, NULL),
  (CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),'-A002'), 2, 3, 2, 45, 'In Progress', NOW() - INTERVAL 10 MINUTE, NOW() - INTERVAL 10 MINUTE, NULL),
  (CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),'-A003'), 3, 2, 3, 15, 'Waiting', NOW() - INTERVAL 6 MINUTE, NULL, NULL),
  (CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),'-A004'), 4, 1, 4, 30, 'Waiting', NOW() - INTERVAL 3 MINUTE, NULL, NULL),
  (CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),'-A005'), 5, 3, 3, 45, 'Completed', NOW() - INTERVAL 2 HOUR, NOW() - INTERVAL 2 HOUR, NOW() - INTERVAL 75 MINUTE),
  (CONCAT(DATE_FORMAT(NOW(),'%Y%m%d'),'-A006'), 6, 1, 2, 30, 'Cancelled', NOW() - INTERVAL 1 HOUR, NULL, NOW() - INTERVAL 50 MINUTE);

INSERT INTO queue_history (queue_id, old_status, new_status, changed_at)
SELECT id, NULL, status, registered_at FROM queues;
