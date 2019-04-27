package com.education.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.education.model.score.ScoreSemesterResult;
import com.education.repository.filter.ScoreSemesterResultFilter;
import com.education.repository.projection.ScoreSemesterResultProjection;

public interface ScoreSemesterResultRepositoryQuery {

	Page<ScoreSemesterResult> filter(Pageable pageable, ScoreSemesterResultFilter scoreSemesterResultFilter);
	
	Page<ScoreSemesterResultProjection> shortFilter(Pageable pageable, ScoreSemesterResultFilter scoreSemesterResultFilter);
	
}
