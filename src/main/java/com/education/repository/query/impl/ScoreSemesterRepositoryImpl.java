package com.education.repository.query.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.education.model.score.ScoreSemester;
import com.education.repository.filter.ScoreSemesterFilter;
import com.education.repository.projection.ScoreSemesterProjection;
import com.education.repository.query.ScoreSemesterRepositoryQuery;

public class ScoreSemesterRepositoryImpl implements ScoreSemesterRepositoryQuery{

	@Override
	public Page<ScoreSemester> filterScoreSemester(ScoreSemesterFilter scoreSemesterFilter, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<ScoreSemesterProjection> shortFilterScoreSemester(ScoreSemesterFilter scoreSemesterFilter,
			Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
