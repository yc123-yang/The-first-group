CREATE DATABASE
IF NOT EXISTS scientific_research CHARACTER
SET utf8;

USE scientific_research;

CREATE TABLE
IF NOT EXISTS `USER` (
	user_id INT PRIMARY KEY auto_increment,
	user_act CHAR (20) UNIQUE NOT NULL,
	user_pwd CHAR (20) NOT NULL,
	user_name CHAR (20) NOT NULL,
	user_email CHAR (30) UNIQUE NOT NULL,
	user_number CHAR (30) UNIQUE NOT NULL,
	user_id_number CHAR (30) UNIQUE,
	user_state TINYINT NOT NULL,
	user_power INT NOT NULL
);

CREATE TABLE
IF NOT EXISTS `check_email` (
	email CHAR (30) PRIMARY KEY,
	check_code SMALLINT NOT NULL,
	overtime TIMESTAMP NOT NULL
);

