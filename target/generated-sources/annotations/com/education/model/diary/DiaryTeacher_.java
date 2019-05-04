package com.education.model.diary;

import com.education.model.ClassroomSchool;
import com.education.model.Lesson;
import com.education.model.people.Teacher;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DiaryTeacher.class)
public abstract class DiaryTeacher_ {

	public static volatile SingularAttribute<DiaryTeacher, Teacher> teacher;
	public static volatile SingularAttribute<DiaryTeacher, LocalDate> dateStart;
	public static volatile SingularAttribute<DiaryTeacher, Lesson> lesson;
	public static volatile SingularAttribute<DiaryTeacher, Boolean> active;
	public static volatile SingularAttribute<DiaryTeacher, Long> id;
	public static volatile SingularAttribute<DiaryTeacher, LocalDate> dateFinish;
	public static volatile SingularAttribute<DiaryTeacher, ClassroomSchool> classroomSchool;

}

