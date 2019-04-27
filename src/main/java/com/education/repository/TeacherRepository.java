package com.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.model.people.Teacher;
import com.education.repository.query.TeacherRepositoryQuery;

public interface TeacherRepository extends JpaRepository<Teacher, Long>, TeacherRepositoryQuery{

	Teacher findByCpf(String cpfValid);



}
