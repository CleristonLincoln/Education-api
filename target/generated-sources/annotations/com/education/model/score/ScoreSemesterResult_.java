package com.education.model.score;

import com.education.model.Lesson;
import com.education.model.people.Student;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScoreSemesterResult.class)
public abstract class ScoreSemesterResult_ {

	public static volatile ListAttribute<ScoreSemesterResult, Score> score;
	public static volatile SingularAttribute<ScoreSemesterResult, ScoreSemester> scoreSemester;
	public static volatile SingularAttribute<ScoreSemesterResult, Student> student;
	public static volatile SingularAttribute<ScoreSemesterResult, Lesson> lesson;
	public static volatile SingularAttribute<ScoreSemesterResult, Boolean> active;
	public static volatile SingularAttribute<ScoreSemesterResult, Long> id;
	public static volatile SingularAttribute<ScoreSemesterResult, Integer> scoreAvarenge;
	public static volatile SingularAttribute<ScoreSemesterResult, Boolean> situation;

}

