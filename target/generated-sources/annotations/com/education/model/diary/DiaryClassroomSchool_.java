package com.education.model.diary;

import com.education.model.ClassroomSchool;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DiaryClassroomSchool.class)
public abstract class DiaryClassroomSchool_ {

	public static volatile SingularAttribute<DiaryClassroomSchool, Long> id;
	public static volatile SingularAttribute<DiaryClassroomSchool, LocalDate> dateDiary;
	public static volatile SingularAttribute<DiaryClassroomSchool, ClassroomSchool> classroomSchool;

}

