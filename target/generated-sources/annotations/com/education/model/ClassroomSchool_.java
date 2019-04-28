package com.education.model;

import com.education.model.people.School;
import com.education.model.people.Student;
import com.education.model.score.SchoolYear;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ClassroomSchool.class)
public abstract class ClassroomSchool_ {

	public static volatile SingularAttribute<ClassroomSchool, School> school;
	public static volatile ListAttribute<ClassroomSchool, Student> student;
	public static volatile SingularAttribute<ClassroomSchool, String> name;
	public static volatile SingularAttribute<ClassroomSchool, Boolean> active;
	public static volatile SingularAttribute<ClassroomSchool, SchoolYear> schoolYear;
	public static volatile SingularAttribute<ClassroomSchool, Classroom> classroom;
	public static volatile SingularAttribute<ClassroomSchool, Long> id;

}

