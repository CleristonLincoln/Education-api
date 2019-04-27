package com.education.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.model.score.ScoreSemester;
import com.education.repository.query.ScoreSemesterRepositoryQuery;

public interface ScoreSemesterRepository extends JpaRepository<ScoreSemester, Long>, ScoreSemesterRepositoryQuery{

	
	Optional<ScoreSemester> findByIdAndActiveIsTrue(Long id);

	


}
