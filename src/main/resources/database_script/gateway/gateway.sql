
USE gateway;
DROP TABLE IF EXISTS `filters`;
CREATE TABLE `filters` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `filter_name` VARCHAR(100) NOT NULL,
  `filter_value` TEXT NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `filter_name` (`filter_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `service_urls`;
CREATE TABLE `service_urls` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `service_name` VARCHAR(100) NOT NULL,
  `url` VARCHAR(255) NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `service_name` (`service_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



DROP TABLE IF EXISTS `endpoint_permissions`;
CREATE TABLE `endpoint_permissions` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `endpoint` VARCHAR(255) NOT NULL,
  `permission` ENUM('READ','WRITE','DELETE','EXECUTE') NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `endpoint` (`endpoint`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `endpoint_permissions` WRITE;
UNLOCK TABLES;

DROP TABLE IF EXISTS `server_url`;
CREATE TABLE `server_url` (
  `id` VARCHAR(250) NOT NULL,
  `description` VARCHAR(250) DEFAULT NULL,
  `requestUrl` VARCHAR(250) NOT NULL,
  `targetUrl` VARCHAR(250) NOT NULL,
  `method` VARCHAR(8) DEFAULT NULL,
  `service` VARCHAR(50) NOT NULL,
  `requestEncrypted` INT DEFAULT 0 NOT NULL,
  `allowUserEdit` INT DEFAULT 0 NOT NULL,
  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `permitAll` INT DEFAULT 0 NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `unique_requestUrl` UNIQUE (`requestUrl`)
);

