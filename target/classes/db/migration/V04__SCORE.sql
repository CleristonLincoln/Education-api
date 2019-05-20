CREATE TABLE score_semester (
	id MEDIUMINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(20),
	active BIT(1),
	generate_score BIT(1),
	date_generate_score DATE,
	
	id_school_year MEDIUMINT,
	
	FOREIGN KEY (id_school_year) REFERENCES school_year(id)	
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE type_score (
	id MEDIUMINT AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(75),
	sum_or_finally BIT(1),
	active BIT(1),
	
	id_score_semester MEDIUMINT,

	FOREIGN KEY (id_score_semester) REFERENCES score_semester(id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8mb4;

CREATE TABLE score (
	id MEDIUMINT AUTO_INCREMENT PRIMARY KEY,
	initial_score TINYINT(3),
	points TINYINT(3),
	final_score TINYINT(3),
	situation BIT(1),
	date_score DATE,
	active BIT(1),
	
	id_student MEDIUMINT,
	id_classroom_school MEDIUMINT,
	id_lesson TINYINT,
	id_teacher MEDIUMINT,
	id_score_semester MEDIUMINT,
	id_school MEDIUMINT,
	id_type_score MEDIUMINT,
	
	FOREIGN KEY (id_student) REFERENCES student(id),
	FOREIGN KEY (id_classroom_school) REFERENCES classroom_school(id),
	FOREIGN KEY (id_teacher) REFERENCES teacher(id),
	FOREIGN KEY (id_score_semester) REFERENCES score_semester(id),
	FOREIGN KEY (id_school) REFERENCES school(id),
	FOREIGN KEY (id_type_score) REFERENCES type_score(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE score_semester_result (
	id MEDIUMINT AUTO_INCREMENT PRIMARY KEY,
	score_avarenge TINYINT(3),
	situation BIT(1),
	active BIT(1),
	
	id_lesson TINYINT,
	id_school MEDIUMINT,
	id_student MEDIUMINT,
	id_score_semester MEDIUMINT,

	FOREIGN KEY (id_lesson) REFERENCES lesson(id),
	FOREIGN KEY (id_school) REFERENCES school(id),
	FOREIGN KEY (id_student) REFERENCES student(id),
	FOREIGN KEY (id_score_semester) REFERENCES score_semester(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE score_additional (
	id MEDIUMINT AUTO_INCREMENT PRIMARY KEY,
	score TINYNT UNSIGNED,
	date_score DATE,
	
	id_lesson TINYINT,
	id_school MEDIUMINT,
	id_student MEDIUMINT,
	id_teacher MEDIUMINT,
	id_type_score MEDIUMINT,

	FOREIGN KEY (id_lesson) REFERENCES lesson(id),
	FOREIGN KEY (id_school) REFERENCES school(id),
	FOREIGN KEY (id_student) REFERENCES student(id),
	FOREIGN KEY (id_teacher) REFERENCES teacher(id),
	FOREIGN KEY (id_type_score) REFERENCES type_score(id)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8mb4;
