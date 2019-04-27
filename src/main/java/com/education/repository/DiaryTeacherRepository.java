package com.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.model.diary.DiaryTeacher;
import com.education.repository.query.DiaryTeacherRepositoryQuery;

public interface DiaryTeacherRepository extends JpaRepository<DiaryTeacher, Long>, DiaryTeacherRepositoryQuery {

}
