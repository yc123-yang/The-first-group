CREATE DATABASE
IF NOT EXISTS scientific_research CHARACTER
SET utf8;

USE scientific_research;

#创建用户表 user
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
	department_id INT NOT NULL,
	role_id INT NOT NULL
);

#创建角色表 role
DROP TABLE
IF EXISTS role;

CREATE TABLE
IF NOT EXISTS role (
	role_id INT PRIMARY KEY auto_increment,
	role_name CHAR (20) NOT NULL
);

#创建权限表 auth
DROP TABLE
IF EXISTS auth;

CREATE TABLE
IF NOT EXISTS auth (
	auth_id INT PRIMARY KEY auto_increment,
	auth_name CHAR (20) NOT NULL
);

#创建角色权限表 role_auth
DROP TABLE
IF EXISTS role_auth;

CREATE TABLE
IF NOT EXISTS role_auth (
	role_id INT,
	auth_id INT,
	PRIMARY KEY (role_id, auth_id)
);

#创建邮箱验证表 check_email
DROP TABLE
IF EXISTS check_email;

CREATE TABLE
IF NOT EXISTS `check_email` (
	email CHAR (30) PRIMARY KEY,
	check_code INT NOT NULL,
	overtime TIMESTAMP NOT NULL
);

#创建项目表 project
DROP TABLE
IF EXISTS project;

CREATE TABLE
IF NOT EXISTS project (
	project_id INT PRIMARY KEY auto_increment,
	project_name CHAR (30) UNIQUE NOT NULL,
	leader_id INT NOT NULL,
	department_id INT NOT NULL,
	aod_id INT NOT NULL,
	sc_id INT NOT NULL,
	subject_id INT NOT NULL,
	nature_id INT NOT NULL,
	level_id INT NOT NULL,
	status_id INT NOT NULL,
	sd_id INT NOT NULL,
	at_id INT NOT NULL,
	approval_number CHAR (30) NOT NULL,
	start_time TIMESTAMP,
	plan_time TIMESTAMP,
	complete_time TIMESTAMP,
	outlay INT NOT NULL,
	ct_id INT NOT NULL
);

#创建项目团队表 project_team
DROP TABLE
IF EXISTS project_team;

CREATE TABLE
IF NOT EXISTS project_team (
	project_id INT,
	user_id INT,
	join_time TIMESTAMP NOT NULL,
	PRIMARY KEY (project_id, user_id)
);

#创建学科门类表 subject_category
DROP TABLE
IF EXISTS subject_category;

CREATE TABLE
IF NOT EXISTS subject_category (
	sc_id INT PRIMARY KEY auto_increment,
	sc_name CHAR (30) NOT NULL
);

#创建一级学科表 subject
DROP TABLE
IF EXISTS `subject`;

CREATE TABLE
IF NOT EXISTS `subject` (
	subject_id INT PRIMARY KEY auto_increment,
	subject_name CHAR (30) NOT NULL,
	sc_id INT NOT NULL
);

#创建项目性质表 nature
DROP TABLE
IF EXISTS nature;

CREATE TABLE
IF NOT EXISTS nature (
	nature_id INT PRIMARY KEY auto_increment,
	nature_name CHAR (30) NOT NULL
);

#创建项目级别表 level
DROP TABLE
IF EXISTS `level`;

CREATE TABLE
IF NOT EXISTS `level` (
	level_id INT PRIMARY KEY auto_increment,
	level_name CHAR (30) NOT NULL
);

#创建项目状态表 status
DROP TABLE
IF EXISTS `status`;

CREATE TABLE
IF NOT EXISTS `status` (
	status_id INT PRIMARY KEY auto_increment,
	status_name CHAR (30) NOT NULL
);

#创建成果类型表 achievement_type
DROP TABLE
IF EXISTS achievement_type;

CREATE TABLE
IF NOT EXISTS achievement_type (
	at_id INT PRIMARY KEY auto_increment,
	at_name CHAR (30) NOT NULL
);

#创建结题形式表 conclusion_type
DROP TABLE
IF EXISTS conclusion_type;

CREATE TABLE
IF NOT EXISTS conclusion_type (
	ct_id INT PRIMARY KEY auto_increment,
	ct_name CHAR (30) NOT NULL
);

#创建单位表 department
DROP TABLE
IF EXISTS department;

CREATE TABLE
IF NOT EXISTS department (
	department_id INT PRIMARY KEY auto_increment,
	department_name CHAR (30) NOT NULL
);

