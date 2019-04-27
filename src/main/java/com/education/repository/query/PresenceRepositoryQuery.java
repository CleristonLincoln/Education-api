package com.education.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.education.model.Presence;
import com.education.repository.filter.PresenceFilter;
import com.education.repository.projection.PresenceProjection;

public interface PresenceRepositoryQuery {

	Page<Presence> filter(PresenceFilter presenceFilter, Pageable pageable);
	
	Page<PresenceProjection> shortFilter(PresenceFilter presenceFilter, Pageable pageable);
}
