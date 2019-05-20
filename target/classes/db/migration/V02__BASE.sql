CREATE TABLE school (
	id MEDIUMINT AUTO_INCREMENT PRIMARY KEY,
	name_school VARCHAR(100),
	name_social VARCHAR(45),,
	cnpj VARCHAR(14),
	ie VARCHAR(14),
	im VARCHAR(14),
	complement_home VARCHAR(45),
	number_home VARCHAR(10),
	
	id_street MEDIUMINT,
	
	FOREIGN KEY (id_street) REFERENCES street(id)	
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE classroom (
	id TINYINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(20)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE school_year (
	id MEDIUMINT AUTO_INCREMENT PRIMARY KEY,
	current_year YEAR(4),
	date_start DATE,
	date_finish DATE,
	score_averange TINYINT,
	generate_score BIT(1),
	active BIT(1),
	
	id_school MEDIUMINT,
	FOREIGN KEY (id_school) REFERENCES school(id)	
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE classroom_school (
	id MEDIUMINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(20),
	active BIT(1),
	
	id_classroom TINYINT,	
	id_school_year MEDIUMINT,
	id_school MEDIUMINT,

	FOREIGN KEY (id_classroom) REFERENCES classroom(id),
	FOREIGN KEY (id_school_year) REFERENCES school_year(id),
	FOREIGN KEY (id_school) REFERENCES school(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE student (
	id MEDIUMINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(75),
	shortname VARCHAR(45),
	cpf VARCHAR(11),
	rg VARCHAR(15),
	birthday DATE,
	email VARCHAR(45),
	email_notification BOOLEAN,
	phone_1 VARCHAR(11),
	phone_2 VARCHAR(11),
	note VARCHAR(255),
	is_note BIT(1),
	number_home VARCHAR(10),
	complement_home VARCHAR(80),
	active BIT(1),
	number_register VARCHAR(45),
	
	id_city SMALLINT,
	id_street MEDIUMINT,
	id_school MEDIUMINT,
	id_classroom_school MEDIUMINT,
	
	FOREIGN KEY (id_city) REFERENCES city(id),
	FOREIGN KEY (id_street) REFERENCES street(id),
	FOREIGN KEY (id_school) REFERENCES school(id),
	FOREIGN KEY (id_classroom_school) REFERENCES classroom_school(id)
	
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE teacher (
	id MEDIUMINT AUTO_INCREMENT PRIMARY KEY,
	image VARCHAR(255),
	name VARCHAR(45),
	shortname VARCHAR(15),
	cpf VARCHAR(11),
	rg VARCHAR(15),
	email VARCHAR(45),
	phone_1 VARCHAR(11),
	phone_2 VARCHAR(11),
	note VARCHAR(255),
	number_home VARCHAR(10),
	complement_home VARCHAR(80),
	
	id_street MEDIUMINT,
	
	FOREIGN KEY (id_street) REFERENCES street(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE users (
	id MEDIUMINT AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(15),
	pwd VARCHAR(255)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE user_permition (
	id TINYINT AUTO_INCREMENT PRIMARY KEY,
	descrition VARCHAR(80)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE lesson (
	id TINYINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(20)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE presence (
	id MEDIUMINT AUTO_INCREMENT PRIMARY KEY,
	date_event DATE,
	situation VARCHAR(15),
	
	id_student MEDIUMINT,
	
	FOREIGN KEY (id_student) REFERENCES student(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;