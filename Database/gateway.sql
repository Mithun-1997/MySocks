-- ================================
-- Create Gateway Schema (For Filters, Service URLs, and Permissions)
-- ================================
CREATE DATABASE IF NOT EXISTS gateway;
USE gateway;

-- Table for managing filters
CREATE TABLE IF NOT EXISTS filters (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    filter_name VARCHAR(100) NOT NULL UNIQUE,
    filter_value TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table for managing service URLs
CREATE TABLE IF NOT EXISTS service_urls (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    service_name VARCHAR(100) NOT NULL UNIQUE,
    url VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Table for managing endpoint permissions (Permit All, Authenticated, etc.)
CREATE TABLE IF NOT EXISTS endpoint_permissions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    endpoint VARCHAR(255) NOT NULL UNIQUE,
    permission ENUM('PERMIT_ALL', 'AUTHENTICATED', 'ADMIN_ONLY') NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
-- ================================
-- Insert Initial Data for Gateway Schema
-- ================================
USE gateway;

-- Insert filter configurations
INSERT INTO filters (filter_name, filter_value) 
VALUES ('auth_filter', 'enabled');

-- Insert service URLs
INSERT INTO service_urls (service_name, url) 
VALUES 
    ('UserService', 'http://localhost:8080/api/users'),
    ('AdminService', 'http://localhost:8080/api/admin');

-- Insert endpoint permissions (Define access rules for different endpoints)
INSERT INTO endpoint_permissions (endpoint, permission) 
VALUES 
    ('/api/auth/login', 'PERMIT_ALL'),
    ('/api/auth/signup', 'PERMIT_ALL'),
    ('/api/users/**', 'AUTHENTICATED'),
    ('/api/admin/**', 'ADMIN_ONLY');

