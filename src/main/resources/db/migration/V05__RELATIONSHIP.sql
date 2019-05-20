CREATE TABLE school_x_teacher (
	id_school MEDIUMINT,
	id_teacher MEDIUMINT,
	
	FOREIGN KEY (id_school) REFERENCES school(id),
	FOREIGN KEY (id_teacher) REFERENCES teacher(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
             
CREATE TABLE teacher_x_lesson (
	id_teacher MEDIUMINT,
	id_lesson TINYINT,
	
	FOREIGN KEY (id_teacher) REFERENCES teacher(id),
	FOREIGN KEY (id_lesson) REFERENCES lesson(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE users_x_user_permition (
	id_users MEDIUMINT,
	id_user_permition TINYINT,
	
	FOREIGN KEY (id_users) REFERENCES users(id),
	FOREIGN KEY (id_user_permition) REFERENCES user_permition(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE score_x_score_semester_result(
	id_score_semester_result MEDIUMINT,
	id_score MEDIUMINT,
	
	FOREIGN KEY (id_score_semester_result) REFERENCES score_semester_result(id),
	FOREIGN KEY (id_score) REFERENCES score(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE score_x_score_additional (
	id_score MEDIUMINT,
	id_score_additional MEDIUMINT,
	
	FOREIGN KEY (id_score) REFERENCES score(id),
	FOREIGN KEY (id_score_additional) REFERENCES score_additional(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE score_x_type_score (
	id_score MEDIUMINT,
	id_type_score MEDIUMINT,
	
	FOREIGN KEY (id_score) REFERENCES score(id),
	FOREIGN KEY (id_type_score) REFERENCES type_score(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
