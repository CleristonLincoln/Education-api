package com.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.model.Lesson;
import com.education.repository.query.LessonRepositoryQuery;

public interface LessonRepository extends JpaRepository<Lesson, Long>, LessonRepositoryQuery{

	

}
