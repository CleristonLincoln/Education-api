package com.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.model.diary.DiaryClassroomSchool;
import com.education.repository.query.DiaryClassroomSchoolRepositoryQuery;

public interface DiaryClassroomSchoolRepository extends JpaRepository<DiaryClassroomSchool, Long>, DiaryClassroomSchoolRepositoryQuery{

}
