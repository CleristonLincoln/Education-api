package com.education.model.score;

import com.education.model.people.School;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SchoolYear.class)
public abstract class SchoolYear_ {

	public static volatile SingularAttribute<SchoolYear, LocalDate> dateStart;
	public static volatile SingularAttribute<SchoolYear, School> school;
	public static volatile SingularAttribute<SchoolYear, Boolean> generateScore;
	public static volatile SingularAttribute<SchoolYear, Boolean> active;
	public static volatile SingularAttribute<SchoolYear, Integer> scoreAverange;
	public static volatile SingularAttribute<SchoolYear, Long> id;
	public static volatile SingularAttribute<SchoolYear, LocalDate> dateFinish;
	public static volatile SingularAttribute<SchoolYear, LocalDate> currentYear;

}

