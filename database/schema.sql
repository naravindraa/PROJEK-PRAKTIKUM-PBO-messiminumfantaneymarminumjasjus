CREATE DATABASE IF NOT EXISTS brocode_queue;
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
