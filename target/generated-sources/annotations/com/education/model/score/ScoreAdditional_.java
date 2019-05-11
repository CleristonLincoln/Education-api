package com.education.model.score;

import com.education.model.Lesson;
import com.education.model.people.School;
import com.education.model.people.Student;
import com.education.model.people.Teacher;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScoreAdditional.class)
public abstract class ScoreAdditional_ {

	public static volatile SingularAttribute<ScoreAdditional, Integer> score;
	public static volatile SingularAttribute<ScoreAdditional, Teacher> teacher;
	public static volatile SingularAttribute<ScoreAdditional, Student> student;
	public static volatile SingularAttribute<ScoreAdditional, School> school;
	public static volatile SingularAttribute<ScoreAdditional, Lesson> lesson;
	public static volatile SingularAttribute<ScoreAdditional, Long> id;
	public static volatile SingularAttribute<ScoreAdditional, TypeScore> typeScore;
	public static volatile SingularAttribute<ScoreAdditional, LocalDate> dateScore;

}

