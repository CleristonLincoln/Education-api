package com.education.model.score;

import com.education.model.ClassroomSchool;
import com.education.model.Lesson;
import com.education.model.people.School;
import com.education.model.people.Student;
import com.education.model.people.Teacher;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Score.class)
public abstract class Score_ {

	public static volatile SingularAttribute<Score, Integer> finalScore;
	public static volatile SingularAttribute<Score, ScoreSemester> scoreSemester;
	public static volatile SingularAttribute<Score, Student> student;
	public static volatile ListAttribute<Score, ScoreAdditional> scoreAditional;
	public static volatile SingularAttribute<Score, Integer> initialScore;
	public static volatile SingularAttribute<Score, Lesson> lesson;
	public static volatile SingularAttribute<Score, Boolean> active;
	public static volatile SingularAttribute<Score, ClassroomSchool> classroomSchool;
	public static volatile SingularAttribute<Score, Integer> points;
	public static volatile SingularAttribute<Score, Teacher> teacher;
	public static volatile SingularAttribute<Score, School> school;
	public static volatile SingularAttribute<Score, Long> id;
	public static volatile SingularAttribute<Score, TypeScore> typeScore;
	public static volatile SingularAttribute<Score, LocalDate> dateScore;
	public static volatile SingularAttribute<Score, Boolean> situation;

}

