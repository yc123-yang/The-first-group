CREATE DATABASE
IF NOT EXISTS scientific_research CHARACTER
SET utf8;

USE scientific_research;

#创建用户表 user
DROP TABLE
IF EXISTS `user`;

CREATE TABLE
IF NOT EXISTS `user` (
	user_id INT PRIMARY KEY auto_increment,
	user_act VARCHAR (255) UNIQUE NOT NULL,
	user_pwd VARCHAR (255) NOT NULL,
	user_name CHAR (20) NOT NULL,
	user_email CHAR (30) UNIQUE NOT NULL,
	user_number CHAR (30) UNIQUE NOT NULL,
	user_id_number CHAR (30) UNIQUE,
	user_state TINYINT NOT NULL,
	department_id INT NOT NULL,
	role_id INT NOT NULL,
	salt VARCHAR (255) NOT NULL
);

#创建缓存表 cache_user
DROP TABLE
IF EXISTS cache_user;

CREATE TABLE
IF NOT EXISTS cache_user (
	cid INT PRIMARY KEY auto_increment,
	user_id INT NOT NULL,
	user_act VARCHAR (255) NOT NULL,
	user_name CHAR (20) NOT NULL,
	user_email CHAR (30) NOT NULL,
	user_state TINYINT NOT NULL,
	token VARCHAR (255) NOT NULL
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
	auth_name CHAR (20) NOT NULL,
	auth_pid INT,
	auth_resource CHAR (30) NOT NULL
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
	sc_id CHAR (2) NOT NULL,
	subject_id CHAR (4) NOT NULL,
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
	user_role CHAR (20) NOT NULL,
	PRIMARY KEY (project_id, user_id)
);

#创建学科门类表 subject_category
DROP TABLE
IF EXISTS subject_category;

CREATE TABLE
IF NOT EXISTS subject_category (
	sc_id CHAR (2) PRIMARY KEY,
	sc_name CHAR (30) NOT NULL
);

#创建一级学科表 subject
DROP TABLE
IF EXISTS `subject`;

CREATE TABLE
IF NOT EXISTS `subject` (
	subject_id CHAR (4) PRIMARY KEY,
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

#创建论文类型表 paper_type
DROP TABLE
IF EXISTS paper_type;

CREATE TABLE
IF NOT EXISTS paper_type (
	pt_id INT PRIMARY KEY auto_increment,
	pt_name CHAR (20) NOT NULL
);

#创建期刊表 periodical
DROP TABLE
IF EXISTS periodical;

CREATE TABLE
IF NOT EXISTS periodical (
	periodical_id INT PRIMARY KEY auto_increment,
	periodical_name CHAR (30) NOT NULL
);

#创建论文表 paper
DROP TABLE
IF EXISTS paper;

CREATE TABLE
IF NOT EXISTS paper (
	paper_id INT PRIMARY KEY auto_increment,
	paper_name CHAR (40) NOT NULL,
	pt_id INT NOT NULL,
	periodical_id INT NOT NULL,
	publish_time TIMESTAMP NOT NULL,
	include_number CHAR (30) NOT NULL,
	sc_id CHAR (2) NOT NULL,
	subject_id CHAR (4) NOT NULL,
	aod_id INT NOT NULL,
	sd_id INT NOT NULL,
	remark CHAR (50)
);

#创建论文团队表 paper_team
DROP TABLE
IF EXISTS paper_team;

CREATE TABLE
IF NOT EXISTS paper_team (
	paper_id INT,
	user_id INT,
	user_role CHAR (20) NOT NULL,
	ranking INT NOT NULL,
	contribution DEC (3, 2) NOT NULL,
	PRIMARY KEY (paper_id, user_id)
);

#创建著作类型表 book_type
DROP TABLE
IF EXISTS book_type;

CREATE TABLE
IF NOT EXISTS book_type (
	bt_id INT PRIMARY KEY auto_increment,
	bt_name CHAR (20) NOT NULL
);

#创建出版社等级表 press_level
DROP TABLE
IF EXISTS press_level;

CREATE TABLE
IF NOT EXISTS press_level (
	pl_id INT PRIMARY KEY auto_increment,
	pl_name CHAR (20) NOT NULL
);

#创建出版地表 publication_place
DROP TABLE
IF EXISTS publication_place;

CREATE TABLE
IF NOT EXISTS publication_place (
	pp_id INT PRIMARY KEY auto_increment,
	pp_name CHAR (20) NOT NULL
);

#创建翻译语种表 language
DROP TABLE
IF EXISTS `language`;

CREATE TABLE
IF NOT EXISTS `language` (
	language_id INT PRIMARY KEY auto_increment,
	language_name CHAR (20) NOT NULL
);

#创建研究类型表 research_type
DROP TABLE
IF EXISTS research_type;

CREATE TABLE
IF NOT EXISTS research_type (
	rt_id INT PRIMARY KEY auto_increment,
	rt_name CHAR (30) NOT NULL
);

#创建著作表 book
DROP TABLE
IF EXISTS book;

CREATE TABLE
IF NOT EXISTS book (
	book_id INT PRIMARY KEY auto_increment,
	book_name CHAR (30) NOT NULL,
	press CHAR (30) NOT NULL,
	pl_id INT NOT NULL,
	bt_id INT NOT NULL,
	pp_id INT NOT NULL,
	isbn CHAR (30) NOT NULL,
	word_number CHAR (20) NOT NULL,
	translate CHAR (10) NOT NULL,
	language_id INT,
	sc_id CHAR (2) NOT NULL,
	subject_id CHAR (4) NOT NULL,
	publish_time TIMESTAMP NOT NULL,
	aod_id INT NOT NULL,
	sd_id INT NOT NULL,
	rt_id INT NOT NULL
);

#创建著作团队表 book_team
DROP TABLE
IF EXISTS book_team;

CREATE TABLE
IF NOT EXISTS book_team (
	book_id INT,
	user_id INT,
	USEr_role CHAR (20) NOT NULL,
	ranking INT NOT NULL,
	contribution DEC (3, 2) NOT NULL,
	PRIMARY KEY (book_id, user_id)
);

#创建单位排名表 department_rank
DROP TABLE
IF EXISTS department_rank;

CREATE TABLE
IF NOT EXISTS department_rank (
	dr_id INT PRIMARY KEY auto_increment,
	dr_name CHAR (20) NOT NULL
);

#创建获奖级别表 award_rank
DROP TABLE
IF EXISTS award_rank;

CREATE TABLE
IF NOT EXISTS award_rank (
	ar_id INT PRIMARY KEY auto_increment,
	ar_name CHAR (20) NOT NULL
);

#创建获奖等级表 award_level
DROP TABLE
IF EXISTS award_level;

CREATE TABLE
IF NOT EXISTS award_level (
	al_id INT PRIMARY KEY auto_increment,
	al_name CHAR (20) NOT NULL
);

#创建获奖表 award
DROP TABLE
IF EXISTS award;

CREATE TABLE
IF NOT EXISTS award (
	award_id INT PRIMARY KEY auto_increment,
	achievement_name CHAR (30) NOT NULL,
	award_name CHAR (20) NOT NULL,
	issuing_authority CHAR (30) NOT NULL,
	approval_number CHAR (30) NOT NULL,
	aod_id INT NOT NULL,
	dr_id INT NOT NULL,
	ar_id INT NOT NULL,
	al_id INT NOT NULL,
	sc_id CHAR (2) NOT NULL,
	subject_id CHAR (4) NOT NULL,
	sd_id INT NOT NULL,
	at_id INT NOT NULL
);

#创建获奖团队表 award_team
DROP TABLE
IF EXISTS award_team;

CREATE TABLE
IF NOT EXISTS award_team (
	award_id INT,
	user_id INT,
	user_role CHAR (20) NOT NULL,
	ranking INT NOT NULL,
	contribution DEC (3, 2) NOT NULL,
	PRIMARY KEY (award_id, user_id)
);

#创建专利类型表 patent_type
DROP TABLE
IF EXISTS patent_type;

CREATE TABLE
IF NOT EXISTS patent_type (
	pt_id INT PRIMARY KEY auto_increment,
	pt_name CHAR (20) NOT NULL
);

#创建专利范围表 patent_range
DROP TABLE
IF EXISTS patent_range;

CREATE TABLE
IF NOT EXISTS patent_range (
	pr_id INT PRIMARY KEY auto_increment,
	pr_name CHAR (20) NOT NULL
);

#创建专利状态表 patent_status
DROP TABLE
IF EXISTS patent_status;

CREATE TABLE
IF NOT EXISTS patent_status (
	ps_id INT PRIMARY KEY auto_increment,
	ps_name CHAR (20) NOT NULL
);

#创建专利表 patent
DROP TABLE
IF EXISTS patent;

CREATE TABLE
IF NOT EXISTS patent (
	patent_id INT PRIMARY KEY auto_increment,
	patent_name CHAR (30) NOT NULL,
	pt_id INT NOT NULL,
	pr_id INT NOT NULL,
	ps_id INT NOT NULL,
	aod_id INT NOT NULL,
	application_number CHAR (30) NOT NULL,
	application_time TIMESTAMP NOT NULL,
	public_number CHAR (30) NOT NULL,
	public_time TIMESTAMP NOT NULL,
	authorization_number CHAR (30) NOT NULL,
	authorization_time TIMESTAMP NOT NULL
);

#创建专利团队表 patent_team
DROP TABLE
IF EXISTS patent_team;

CREATE TABLE
IF NOT EXISTS patent_team (
	patent_id INT,
	user_id INT,
	user_role CHAR (20) NOT NULL,
	ranking INT NOT NULL,
	contribution DEC (3, 2) NOT NULL,
	PRIMARY KEY (patent_id, user_id)
);