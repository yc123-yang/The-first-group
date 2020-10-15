CREATE DATABASE
IF NOT EXISTS scientific_research CHARACTER
SET utf8;

USE scientific_research;

DROP TABLE
IF EXISTS USER;

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

DROP TABLE
IF EXISTS check_email;

CREATE TABLE
IF NOT EXISTS `check_email` (
	email CHAR (30) PRIMARY KEY,
	check_code INT NOT NULL,
	overtime TIMESTAMP NOT NULL
);

DROP TABLE
IF EXISTS project;

CREATE TABLE
IF NOT EXISTS project (
	project_id INT PRIMARY KEY auto_increment,
	project_stage CHAR (10) NOT NULL,
	project_type CHAR (10),
	leader_id INT NOT NULL,
	project_name CHAR (127) UNIQUE NOT NULL,
	abstract text NOT NULL,
	declaration CHAR (255) NOT NULL,
	apply_time TIMESTAMP NOT NULL
);

DROP TABLE
IF EXISTS project_team;

CREATE TABLE
IF NOT EXISTS project_team (
	project_id INT,
	user_id INT,
	PRIMARY KEY (project_id, user_id)
)