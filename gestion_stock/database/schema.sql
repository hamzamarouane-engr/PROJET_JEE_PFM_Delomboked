-- ============================================
-- Database Creation Script for gestion_stock
-- ============================================

-- Create database
CREATE DATABASE IF NOT EXISTS G_Stock
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE G_Stock;

-- ============================================
-- Table: produits_stock
-- Stores product stock quantities
-- ============================================
CREATE TABLE IF NOT EXISTS produits_stock (
    codestock INT PRIMARY KEY AUTO_INCREMENT,
    codepdt INT NOT NULL,
    qtepdt INT NOT NULL DEFAULT 0,
    CONSTRAINT chk_qtepdt_positive CHECK (qtepdt >= 0),
    UNIQUE KEY idx_codepdt (codepdt)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- ============================================
-- Sample Data for Testing
-- ============================================

-- Insert sample stock
INSERT INTO produits_stock (codepdt, qtepdt) VALUES
(1, 50),   -- Laptop HP: 50 units
(2, 100),  -- Mouse Logitech: 100 units
(3, 75),   -- Keyboard Mechanical: 75 units
(4, 30),   -- Monitor Samsung: 30 units
(5, 200),  -- USB Flash Drive: 200 units
(6, 60),   -- External HDD: 60 units
(7, 45),   -- Webcam Logitech: 45 units
(8, 80),   -- Headset Gaming: 80 units
(9, 40),   -- Router WiFi: 40 units
(10, 25);  -- Printer HP: 25 units

-- ============================================
-- Verify data insertion
-- ============================================
SELECT 'Stock Count:' AS Info, COUNT(*) AS Count FROM produits_stock;
SELECT 'Total Units:' AS Info, SUM(qtepdt) AS Total FROM produits_stock;

-- ============================================
-- Sample Queries
-- ============================================

-- Get all stock
SELECT * FROM produits_stock;

-- Get stock for specific product
SELECT * FROM produits_stock WHERE codepdt = 1;

-- Update stock (subtract 5 units from product 1)
UPDATE produits_stock SET qtepdt = qtepdt - 5 WHERE codepdt = 1;

-- Check stock level
SELECT codepdt, qtepdt,
       CASE
           WHEN qtepdt = 0 THEN 'Out of Stock'
           WHEN qtepdt < 10 THEN 'Low Stock'
           WHEN qtepdt < 50 THEN 'Medium Stock'
           ELSE 'High Stock'
       END AS stock_status
FROM produits_stock;
