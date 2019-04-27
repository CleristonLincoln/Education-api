package com.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.model.people.School;
import com.education.repository.query.SchoolRepositoryQuery;


public interface SchoolRepository extends JpaRepository<School, Long>, SchoolRepositoryQuery{

	School findByIe(String ie);
	School findByCnpj(String cnpj);
	School findByIm(String im);
	School findByNameSchool(String nameSchool);
	
		
}
