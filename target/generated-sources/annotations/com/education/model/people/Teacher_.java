package com.education.model.people;

import com.education.model.Lesson;
import com.education.model.adress.Street;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Teacher.class)
public abstract class Teacher_ {

	public static volatile SingularAttribute<Teacher, String> image;
	public static volatile SingularAttribute<Teacher, String> note;
	public static volatile SingularAttribute<Teacher, String> phone2;
	public static volatile ListAttribute<Teacher, Lesson> lesson;
	public static volatile SingularAttribute<Teacher, String> shortname;
	public static volatile SingularAttribute<Teacher, String> phone1;
	public static volatile SingularAttribute<Teacher, String> numberHome;
	public static volatile SingularAttribute<Teacher, String> complementHome;
	public static volatile SingularAttribute<Teacher, String> rg;
	public static volatile SingularAttribute<Teacher, Street> street;
	public static volatile SingularAttribute<Teacher, String> name;
	public static volatile SingularAttribute<Teacher, String> cpf;
	public static volatile SingularAttribute<Teacher, Long> id;
	public static volatile SingularAttribute<Teacher, String> email;

}

