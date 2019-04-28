/*A tabela  SCHOOL_YEAR está na linha 85 em CLASS_BASE*/

CREATE TABLE score_semester (
	id BIGINT(11) AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(20),
	active BOOLEAN,
	generate_score BOOLEAN,
	date_generate_score DATE,
	
	id_school_year BIGINT(11),
	
	FOREIGN KEY (id_school_year) REFERENCES school_year(id)	
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE score (
	id BIGINT(11) AUTO_INCREMENT PRIMARY KEY,
	initialScore TINYINT(3),
	points TINYINT(3),
	final_score TINYINT(3),
	situation BOOLEAN,
	date_score DATE,
	active BOOLEAN,
	
	id_student BIGINT(11),
	id_classroom_school BIGINT(11),
	id_lesson BIGINT(11),
	id_teacher BIGINT(11),
	id_score_semester BIGINT(11),
	id_school BIGINT(11),
	
	FOREIGN KEY (id_student) REFERENCES student(id),
	FOREIGN KEY (id_classroom_school) REFERENCES classroom_school(id),
	FOREIGN KEY (id_teacher) REFERENCES teacher(id),
	FOREIGN KEY (id_score_semester) REFERENCES score_semester(id),
	FOREIGN KEY (id_school) REFERENCES school(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE score_semester_result (
	id BIGINT(11) AUTO_INCREMENT PRIMARY KEY,
	score_avarenge TINYINT(3),
	situation BOOLEAN,
	active BOOLEAN,
	
	id_lesson BIGINT(11),
	id_school BIGINT(11),
	id_student BIGINT(11),
	id_score_semester BIGINT(11),

	FOREIGN KEY (id_lesson) REFERENCES lesson(id),
	FOREIGN KEY (id_school) REFERENCES school(id),
	FOREIGN KEY (id_student) REFERENCES student(id),
	FOREIGN KEY (id_score_semester) REFERENCES score_semester(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE score_additional (
	id BIGINT(11) AUTO_INCREMENT PRIMARY KEY,
	date_score DATE,

	id_lesson BIGINT(11),
	id_school BIGINT(11),
	id_student BIGINT(11),
	id_teacher BIGINT(11),

	FOREIGN KEY (id_lesson) REFERENCES lesson(id),
	FOREIGN KEY (id_school) REFERENCES school(id),
	FOREIGN KEY (id_student) REFERENCES student(id),
	FOREIGN KEY (id_teacher) REFERENCES teacher(id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8mb4;

CREATE TABLE type_score(
	id BIGINT(11),
	name VARCHAR(75),
	
	id_score_semester BIGINT(11),

	FOREIGN KEY (id_score_semester) REFERENCES score_semester(id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8mb4;

