CREATE TABLE diary_teacher (
	id BIGINT(11) PRIMARY KEY AUTO_INCREMENT,
	date_start DATE,
	date_finish DATE,
	active  VARCHAR(10),
	
	id_classroom_school BIGINT(11),
	id_teacher BIGINT(11),
	id_lesson BIGINT(11),
	
	FOREIGN KEY (id_classroom_school) REFERENCES classroom_school(id),
	FOREIGN KEY (id_teacher) REFERENCES teacher(id),
	FOREIGN KEY (id_lesson) REFERENCES lesson(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE diary_student (
	id BIGINT(11) PRIMARY KEY AUTO_INCREMENT,
	topic VARCHAR(255),
	date_diary DATE,
	
	id_student BIGINT(11),
	
	FOREIGN KEY (id_student) REFERENCES student(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE diary_classroom_school (
	id BIGINT(11) PRIMARY KEY AUTO_INCREMENT,
	date_diary DATE,
	
	id_classroom_school BIGINT(11),
	
	FOREIGN KEY (id_classroom_school) REFERENCES classroom_school(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE diary_classroom_school_topic (
	id BIGINT(11) PRIMARY KEY AUTO_INCREMENT,
	topic VARCHAR(255),
	
	id_diary_classroom_school BIGINT(11),
	id_teacher BIGINT(11),
	id_lesson BIGINT(11),
	
	FOREIGN KEY (id_diary_classroom_school) REFERENCES diary_classroom_school(id),
	FOREIGN KEY (id_teacher) REFERENCES teacher(id),
	FOREIGN KEY (id_lesson) REFERENCES lesson(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE school_calendar (
	id BIGINT(11) PRIMARY KEY AUTO_INCREMENT,
	date_start DATE,
	date_finish DATE,
	topic VARCHAR(255),
	
	id_school INT,
	
	FOREIGN KEY (id_school) REFERENCES school(id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8mb4;



