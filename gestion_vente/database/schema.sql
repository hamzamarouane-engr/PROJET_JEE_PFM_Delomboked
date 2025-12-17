-- ============================================
-- Database Schema for Gestion Vente
-- Database: G_Vente
-- Spring Boot Application: gestion_vente
-- Port: 8080
-- ============================================

-- Create database (if not exists)
CREATE DATABASE IF NOT EXISTS G_Vente
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE G_Vente;

-- ============================================
-- Table: users
-- Description: User accounts for authentication
-- ============================================
CREATE TABLE IF NOT EXISTS users (
    userid BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'ROLE_USER',
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    fullname VARCHAR(100),
    email VARCHAR(100),
    createdat DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_username (username),
    INDEX idx_role (role)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- Table: commandes
-- Description: Orders placed through the system
-- ============================================
CREATE TABLE IF NOT EXISTS commandes (
    codecmd BIGINT PRIMARY KEY AUTO_INCREMENT,
    client VARCHAR(100) NOT NULL,
    codepdt INT NOT NULL,
    nompdt VARCHAR(100),
    qtecmd INT NOT NULL CHECK (qtecmd > 0),
    prixunitaire INT,
    montanttotal INT,
    datecmd DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) DEFAULT 'PENDING',
    createdby VARCHAR(50),
    INDEX idx_client (client),
    INDEX idx_codepdt (codepdt),
    INDEX idx_status (status),
    INDEX idx_datecmd (datecmd)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- Sample Data - Users
-- ============================================
-- Password for all users: "password123" (BCrypt encoded)
-- Use Spring Boot to properly encode passwords in production
INSERT INTO users (username, password, role, enabled, fullname, email) VALUES
('admin', '$2a$10$YJF.MqL6wQfJOe8G8EKuMO2E4tJp8hJgLKo3V7sQ.PxPzKLnZL3kO', 'ROLE_ADMIN', TRUE, 'Administrator', 'admin@asustec.com'),
('user1', '$2a$10$YJF.MqL6wQfJOe8G8EKuMO2E4tJp8hJgLKo3V7sQ.PxPzKLnZL3kO', 'ROLE_USER', TRUE, 'John Doe', 'john@asustec.com'),
('user2', '$2a$10$YJF.MqL6wQfJOe8G8EKuMO2E4tJp8hJgLKo3V7sQ.PxPzKLnZL3kO', 'ROLE_USER', TRUE, 'Jane Smith', 'jane@asustec.com')
ON DUPLICATE KEY UPDATE username=username;

-- ============================================
-- Sample Data - Commandes (Optional)
-- ============================================
-- Uncomment to insert sample orders
-- INSERT INTO commandes (client, codepdt, nompdt, qtecmd, prixunitaire, montanttotal, status, createdby) VALUES
-- ('ACME Corp', 1, 'ASUS ROG Strix', 2, 15000, 30000, 'COMPLETED', 'admin'),
-- ('TechStore', 2, 'ASUS ZenBook', 1, 8500, 8500, 'COMPLETED', 'user1'),
-- ('ElectroShop', 3, 'ASUS TUF Gaming', 3, 12000, 36000, 'PENDING', 'user1');

-- ============================================
-- Notes
-- ============================================
-- 1. The application uses spring.jpa.hibernate.ddl-auto=update
--    Tables will be created automatically if they don't exist
-- 2. This schema file is provided for reference and manual database setup
-- 3. Password encoding must be done through BCryptPasswordEncoder
-- 4. Default credentials:
--    - Username: admin, Password: password123 (ROLE_ADMIN)
--    - Username: user1, Password: password123 (ROLE_USER)
--    - Username: user2, Password: password123 (ROLE_USER)
