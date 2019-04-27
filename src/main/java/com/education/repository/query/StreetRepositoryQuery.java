package com.education.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.education.model.adress.Street;
import com.education.repository.filter.StreetFilter;
import com.education.repository.projection.StreetProjection;

public interface StreetRepositoryQuery {
	
	Page<Street> filter(StreetFilter streetFilter, Pageable pageable);
	
	Page<StreetProjection> shortFilter(StreetFilter streetFilter, Pageable pageable);

}
