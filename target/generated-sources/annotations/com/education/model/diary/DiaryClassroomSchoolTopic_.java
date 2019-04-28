package com.education.model.diary;

import com.education.model.Lesson;
import com.education.model.people.Teacher;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(DiaryClassroomSchoolTopic.class)
public abstract class DiaryClassroomSchoolTopic_ {

	public static volatile SingularAttribute<DiaryClassroomSchoolTopic, DiaryClassroomSchool> diaryClassroomSchool;
	public static volatile SingularAttribute<DiaryClassroomSchoolTopic, Teacher> teacher;
	public static volatile SingularAttribute<DiaryClassroomSchoolTopic, Lesson> lesson;
	public static volatile SingularAttribute<DiaryClassroomSchoolTopic, String> topic;
	public static volatile SingularAttribute<DiaryClassroomSchoolTopic, Long> id;

}

