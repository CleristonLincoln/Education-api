package com.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.model.ClassroomSchool;
import com.education.model.people.Student;
import com.education.repository.query.ClassroomSchoolRepositoryQuery;

public interface ClassroomSchoolRepository extends JpaRepository<ClassroomSchool, Long>, ClassroomSchoolRepositoryQuery{

	List<Student> findByStudent(Long id);


	

}
