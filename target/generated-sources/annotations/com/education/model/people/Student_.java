package com.education.model.people;

import com.education.model.adress.City;
import com.education.model.adress.Street;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Student.class)
public abstract class Student_ {

	public static volatile SingularAttribute<Student, LocalDate> birthday;
	public static volatile SingularAttribute<Student, Boolean> emailNotification;
	public static volatile SingularAttribute<Student, String> note;
	public static volatile SingularAttribute<Student, String> numberRegister;
	public static volatile SingularAttribute<Student, String> phone2;
	public static volatile SingularAttribute<Student, Boolean> active;
	public static volatile SingularAttribute<Student, String> shortname;
	public static volatile SingularAttribute<Student, String> phone1;
	public static volatile SingularAttribute<Student, String> numberHome;
	public static volatile SingularAttribute<Student, String> complementHome;
	public static volatile SingularAttribute<Student, String> rg;
	public static volatile SingularAttribute<Student, School> school;
	public static volatile SingularAttribute<Student, Street> street;
	public static volatile SingularAttribute<Student, String> name;
	public static volatile SingularAttribute<Student, String> cpf;
	public static volatile SingularAttribute<Student, City> cityRegister;
	public static volatile SingularAttribute<Student, Boolean> isNote;
	public static volatile SingularAttribute<Student, Long> id;
	public static volatile SingularAttribute<Student, String> email;

}

