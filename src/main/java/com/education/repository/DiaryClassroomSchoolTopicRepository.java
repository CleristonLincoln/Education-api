package com.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.model.diary.DiaryClassroomSchoolTopic;
import com.education.repository.query.DiaryClassroomSchoolTopicRepositoryQuery;

public interface DiaryClassroomSchoolTopicRepository extends JpaRepository<DiaryClassroomSchoolTopic, Long>, DiaryClassroomSchoolTopicRepositoryQuery{

}
