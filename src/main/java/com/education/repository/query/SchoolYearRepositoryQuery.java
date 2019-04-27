package com.education.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.education.model.score.SchoolYear;
import com.education.repository.filter.SchoolYearFilter;
import com.education.repository.projection.SchoolYearProjection;

public interface SchoolYearRepositoryQuery {

	Page<SchoolYear> filterEntity(Pageable pageable, SchoolYearFilter schoolYearFilter);
	
	Page<SchoolYearProjection> filterShortEntity(SchoolYearFilter schoolYearFilter, Pageable pageable);

	
}