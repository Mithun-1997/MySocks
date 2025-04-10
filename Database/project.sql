-- ================================
-- Drop Existing Database and Create New One
-- ================================
DROP DATABASE IF EXISTS project;
CREATE DATABASE project;
USE project;

-- ================================
-- Drop Table if Exists and Create New One
-- ================================
DROP TABLE IF EXISTS userlist;

CREATE TABLE userlist (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    mobile_number VARCHAR(20) NOT NULL UNIQUE, -- Renamed to match entity field 'mobileNumber'
    password VARCHAR(255) NOT NULL,
    role INT NOT NULL CHECK (role IN (1, 2, 4)), -- 1 = User, 2 = Admin, 4 = SuperAdmin
    gender INT NOT NULL CHECK (gender IN (1, 2, 3)), -- 1 = Male, 2 = Female, 3 = Other
    creation_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- Renamed to match entity
    valid_till TIMESTAMP NULL DEFAULT NULL -- Renamed to match entity
);


-- ================================
-- Insert Initial Data for Project Schema
-- ================================
INSERT INTO userlist (username, email, mobile_number, password, role, gender) 
VALUES ('admin', 'admin@example.com', '1234567890', '$2a$10$xyzhashedpassword', 4, 1);

-- Verify data insertion
SELECT * FROM userlist;
