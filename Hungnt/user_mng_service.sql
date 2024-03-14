DROP DATABASE IF EXISTS user_management_service;
CREATE DATABASE IF NOT EXISTS user_management_service;

USE user_management_service;

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user`(
	userId		INT PRIMARY KEY AUTO_INCREMENT,
    username	VARCHAR(50)		NOT NULL UNIQUE,
    `password`	VARCHAR(500)	NOT NULL,
    email		VARCHAR(50)		NOT NULL UNIQUE,
    fullname	VARCHAR(50)		NOT NULL,
    created_at	DATE		DEFAULT(now())
);

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role`(
	roleId		INT PRIMARY KEY AUTO_INCREMENT,
    roleName	ENUM('ADMIN', 'USER')
);

-- DROP TABLE IF EXISTS `user_role`;
-- CREATE TABLE IF NOT EXISTS `user_role`(
-- 	user_role_id INT AUTO_INCREMENT PRIMARY KEY,
--     user_id INT,
--     role_id INT,
--     FOREIGN KEY (user_id) REFERENCES `user`(user_id),
--     FOREIGN KEY (role_id) REFERENCES `role`(role_id)	
-- );

-- INSERT INTO `user` (username, `password`, email, fullname) 
-- VALUES	('admin', '123456', 'user1@example.com', 'User One'),
-- 		('user2', '123456', 'user2@example.com', 'User Two'),
-- 		('user3', '123456', 'user3@example.com', 'User Three'),
-- 		('user4', '123456', 'user4@example.com', 'User Four'),
-- 		('user5', '123456', 'user5@example.com', 'User Five');

-- INSERT INTO `role` (role_name) VALUES ('ADMIN'), ('USER');

-- INSERT INTO `user_role` (user_id, role_id)
-- VALUES	(1, 1),
-- 		(1, 2),
-- 		(2, 2),
--         (3, 2),
--         (4, 2),
--         (5, 2);
