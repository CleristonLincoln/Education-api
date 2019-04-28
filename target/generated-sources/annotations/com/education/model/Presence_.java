package com.education.model;

import com.education.model.people.Student;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Presence.class)
public abstract class Presence_ {

	public static volatile SingularAttribute<Presence, LocalDate> dateEvent;
	public static volatile SingularAttribute<Presence, Student> student;
	public static volatile SingularAttribute<Presence, Long> id;
	public static volatile SingularAttribute<Presence, SituationPresence> situation;

}

