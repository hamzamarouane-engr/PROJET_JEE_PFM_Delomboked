-- ============================================
-- Database Creation Script for gestion_commercial
-- ============================================

-- Create database
CREATE DATABASE IF NOT EXISTS G_commercial
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE G_commercial;

-- ============================================
-- Table: produits_prix
-- Stores product information and prices
-- ============================================
CREATE TABLE IF NOT EXISTS produits_prix (
    codepdt INT PRIMARY KEY,
    nompdt VARCHAR(20) NOT NULL,
    descpdt VARCHAR(200),
    prixpdt INT(11) NOT NULL,
    CONSTRAINT chk_prixpdt_positive CHECK (prixpdt > 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- Table: tous_commandes
-- Stores all orders from all points of sale
-- ============================================
CREATE TABLE IF NOT EXISTS tous_commandes (
    codetouscmd INT PRIMARY KEY AUTO_INCREMENT,
    codecmd INT NOT NULL,
    client VARCHAR(20) NOT NULL,
    codepdt INT NOT NULL,
    qtecmd INT NOT NULL,
    datecmd DATE NOT NULL,
    CONSTRAINT chk_qtecmd_positive CHECK (qtecmd > 0)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- Sample Data for Testing
-- ============================================

-- Insert sample products
INSERT INTO produits_prix (codepdt, nompdt, descpdt, prixpdt) VALUES
(1, 'Laptop HP', 'HP Pavilion 15-inch, Intel Core i5, 8GB RAM, 256GB SSD', 6500),
(2, 'Mouse Logitech', 'Logitech MX Master 3 Wireless Mouse', 850),
(3, 'Keyboard Mechanical', 'Mechanical Gaming Keyboard RGB', 1200),
(4, 'Monitor Samsung', 'Samsung 27-inch 4K UHD Monitor', 3500),
(5, 'USB Flash Drive', 'SanDisk 64GB USB 3.0 Flash Drive', 150),
(6, 'External HDD', 'Seagate 1TB External Hard Drive', 650),
(7, 'Webcam Logitech', 'Logitech C920 HD Pro Webcam', 750),
(8, 'Headset Gaming', 'Gaming Headset with Microphone', 450),
(9, 'Router WiFi', 'TP-Link AC1750 Wireless Router', 550),
(10, 'Printer HP', 'HP DeskJet 2710 All-in-One Printer', 950);

-- Insert sample orders
INSERT INTO tous_commandes (codecmd, client, codepdt, qtecmd, datecmd) VALUES
(101, 'Mohamed Ali', 1, 2, '2025-12-01'),
(102, 'Fatima Zahra', 2, 5, '2025-12-02'),
(103, 'Ahmed Hassan', 3, 3, '2025-12-03'),
(104, 'Sara Ben', 4, 1, '2025-12-04'),
(105, 'Youssef Idrissi', 5, 10, '2025-12-05');

-- ============================================
-- Verify data insertion
-- ============================================
SELECT 'Products Count:' AS Info, COUNT(*) AS Count FROM produits_prix;
SELECT 'Orders Count:' AS Info, COUNT(*) AS Count FROM tous_commandes;
