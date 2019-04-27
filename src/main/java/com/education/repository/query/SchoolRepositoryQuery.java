package com.education.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.education.model.people.School;
import com.education.repository.filter.SchoolFilter;
import com.education.repository.projection.SchoolProjection;

public interface SchoolRepositoryQuery {
	
     public Page<School> filterEntity (SchoolFilter SchoolFilter, Pageable pageable);
     
     public Page<SchoolProjection> shortEntity (SchoolFilter SchoolFilter, Pageable pageable);
	
}
