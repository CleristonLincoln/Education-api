package com.education.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.education.model.score.SchoolYear;
import com.education.repository.query.SchoolYearRepositoryQuery;

public interface SchoolYearRepository extends JpaRepository<SchoolYear, Long>, SchoolYearRepositoryQuery {

	List<SchoolYear> findByCurrentYear(LocalDate data);

	@Query("select u.id from SchoolYear u where u.currentYear=?1 and u.school.id = ?2")
	Optional<SchoolYear> findByCurrentYearAndSchoolId(LocalDate data, Long idSchool);
	
	
	

}
