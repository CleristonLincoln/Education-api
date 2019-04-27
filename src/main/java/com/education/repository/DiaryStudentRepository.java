package com.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.model.diary.DiaryStudent;
import com.education.repository.query.DiaryStudentRepositoryQuery;

public interface DiaryStudentRepository extends JpaRepository<DiaryStudent, Long>, DiaryStudentRepositoryQuery {



}
