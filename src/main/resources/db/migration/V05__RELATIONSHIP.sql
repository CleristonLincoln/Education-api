
CREATE TABLE school_x_teacher (
	id_school BIGINT(11),
	id_teacher BIGINT(11),
	
	FOREIGN KEY (id_school) REFERENCES school(id),
	FOREIGN KEY (id_teacher) REFERENCES teacher(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
             
CREATE TABLE teacher_x_lesson (
	id_teacher BIGINT(11),
	id_lesson BIGINT(11),
	
	FOREIGN KEY (id_teacher) REFERENCES teacher(id),
	FOREIGN KEY (id_lesson) REFERENCES lesson(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE users_x_user_permition (
	id_users BIGINT(11),
	id_user_permition BIGINT(11),
	
	FOREIGN KEY (id_users) REFERENCES users(id),
	FOREIGN KEY (id_user_permition) REFERENCES user_permition(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE score_x_score_semester_result(
	id_score_semester_result BIGINT(11),
	id_score BIGINT(11),
	
	FOREIGN KEY (id_score_semester_result) REFERENCES score_semester_result(id),
	FOREIGN KEY (id_score) REFERENCES score(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE classroom_school_x_student (
	id_classroom_school BIGINT(11),
	id_student BIGINT(11),
	
	FOREIGN KEY (id_classroom_school) REFERENCES classroom_school(id),
	FOREIGN KEY (id_student) REFERENCES student(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE score_x_score_additional (
	id_score BIGINT(11),
	id_score_additional BIGINT(11),
	
	FOREIGN KEY (id_score) REFERENCES score(id),
	FOREIGN KEY (id_score_additional) REFERENCES score_additional(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE score_x_type_score (
	id_score BIGINT(11),
	id_type_score BIGINT(11),
	
	FOREIGN KEY (id_score) REFERENCES score(id),
	FOREIGN KEY (id_type_score) REFERENCES type_score(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
