USE gateway;

-- Lock the filters table and insert values
LOCK TABLES `filters` WRITE;
INSERT INTO `filters` (id, filter_name, filter_value, created_at) 
VALUES 
(1, 'auth_filter', 'enabled', CURRENT_TIMESTAMP);
UNLOCK TABLES;

-- Lock the service_urls table and insert values
LOCK TABLES `service_urls` WRITE;
INSERT INTO `service_urls` (id, service_name, url, created_at) 
VALUES 
(1, 'UserService', 'http://localhost:8080/api/users', CURRENT_TIMESTAMP),
(2, 'AdminService', 'http://localhost:8080/api/admin', CURRENT_TIMESTAMP);
UNLOCK TABLES;

-- Lock the server_url table and insert values
LOCK TABLES `server_url` WRITE;
INSERT INTO `server_url` (description, requestUrl, targetUrl, method, service, requestEncrypted, allowUserEdit, permitAll, created_at)
VALUES 
('User registration API', '/server/user/register', '/service/user/register', 'POST', 'user', 1, 0, 1, CURRENT_TIMESTAMP),
('User login API', '/server/user/login', '/service/user/login', 'POST', 'user', 1, 0, 1, CURRENT_TIMESTAMP),
('Get user details by ID', '/server/user/getuser/{id}', '/service/user/getuser/{id}', 'GET', 'user', 0, 0, 0, CURRENT_TIMESTAMP),
('Get all users', '/server/user/getUserAll', '/service/user/getUserAll', 'GET', 'user', 0, 0, 0, CURRENT_TIMESTAMP);
UNLOCK TABLES;
