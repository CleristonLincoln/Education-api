package com.education.model.diary;

import com.education.model.people.School;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(SchoolCalendar.class)
public abstract class SchoolCalendar_ {

	public static volatile SingularAttribute<SchoolCalendar, LocalDate> dateStart;
	public static volatile SingularAttribute<SchoolCalendar, School> school;
	public static volatile SingularAttribute<SchoolCalendar, String> topic;
	public static volatile SingularAttribute<SchoolCalendar, Long> id;
	public static volatile SingularAttribute<SchoolCalendar, LocalDate> dateFinish;

}

