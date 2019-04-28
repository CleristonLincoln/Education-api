package com.education.model.diary;

import com.education.model.people.Student;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DiaryStudent.class)
public abstract class DiaryStudent_ {

	public static volatile SingularAttribute<DiaryStudent, Student> student;
	public static volatile SingularAttribute<DiaryStudent, String> topic;
	public static volatile SingularAttribute<DiaryStudent, Long> id;
	public static volatile SingularAttribute<DiaryStudent, LocalDate> dateDiary;

}

