package com.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.model.people.Student;
import com.education.repository.query.StudentRepositoryQuery;

public interface StudentRepository extends JpaRepository<Student, Long>, StudentRepositoryQuery{

	List<Student> findByName(String name);

	Student findByCpf(String cpf);
	
	Student findByRg(String rg);

//	List<Student> findBySchoolId(Long idSchool);
	
}
