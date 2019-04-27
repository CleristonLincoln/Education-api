package com.education.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.education.model.adress.Neighborhood;
import com.education.repository.filter.NeighborhoodFilter;
import com.education.repository.projection.NeighborhoodProjection;

public interface NeighborhoodRepositoryQuery {

	Page<Neighborhood> filtrar(NeighborhoodFilter neighborhoodFilter, Pageable pageable);
	
	Page<NeighborhoodProjection> shortEntity(NeighborhoodFilter neighborhoodFilter, Pageable pageable);
	
	
}
