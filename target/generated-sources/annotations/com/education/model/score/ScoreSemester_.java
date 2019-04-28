package com.education.model.score;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ScoreSemester.class)
public abstract class ScoreSemester_ {

	public static volatile SingularAttribute<ScoreSemester, String> name;
	public static volatile SingularAttribute<ScoreSemester, Boolean> active;
	public static volatile SingularAttribute<ScoreSemester, SchoolYear> schoolYear;
	public static volatile SingularAttribute<ScoreSemester, Long> id;
	public static volatile SingularAttribute<ScoreSemester, LocalDate> dateGenerateScore;

}

