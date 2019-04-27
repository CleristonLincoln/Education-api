package com.education.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.education.model.score.ScoreSemester;
import com.education.repository.filter.ScoreSemesterFilter;
import com.education.repository.projection.ScoreSemesterProjection;

public interface ScoreSemesterRepositoryQuery {

	
	public Page<ScoreSemester> filterScoreSemester(ScoreSemesterFilter scoreSemesterFilter, Pageable pageable);
	
	public Page<ScoreSemesterProjection> shortFilterScoreSemester(ScoreSemesterFilter scoreSemesterFilter, Pageable pageable);
	
}
