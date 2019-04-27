package com.education.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.education.model.adress.City;
import com.education.repository.filter.CityFilter;
import com.education.repository.projection.CityProjection;

public interface CityRepositoryQuery {

	public Page<City> filter(CityFilter cityFilter, Pageable pageable);

	public Page<CityProjection> shortFilter(CityFilter cityFilter, Pageable pageable);
	
	
	
}
