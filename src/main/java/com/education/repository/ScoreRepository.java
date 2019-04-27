package com.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.model.score.Score;
import com.education.repository.query.ScoreRepositoryQuery;

public interface ScoreRepository extends JpaRepository<Score, Long>, ScoreRepositoryQuery{

	List<Score> findByScoreSemesterId(Long idScoreSemester);

	Boolean findByStudentId(Long idStudent);

	
}
