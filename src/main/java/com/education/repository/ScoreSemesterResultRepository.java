package com.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.model.score.ScoreSemesterResult;
import com.education.repository.query.ScoreSemesterResultRepositoryQuery;

public interface ScoreSemesterResultRepository extends JpaRepository<ScoreSemesterResult, Long>, ScoreSemesterResultRepositoryQuery {

}
