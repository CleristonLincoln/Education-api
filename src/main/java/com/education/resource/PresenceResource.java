package com.education.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.education.event.ResourceCreateEvent;
import com.education.model.Presence;
import com.education.repository.PresenceRepository;
import com.education.repository.filter.PresenceFilter;
import com.education.repository.projection.PresenceProjection;

@RestController
@RequestMapping("/presence")
public class PresenceResource {

	@Autowired private PresenceRepository repository;
	@Autowired private ApplicationEventPublisher publisher;
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_GET_SCHOOL') and #oauth2.hasScoe('read')")
	public Page<Presence> gelAll(PresenceFilter presenceFilter, Pageable pageable) {
		return repository.filter(presenceFilter, pageable);
	}

	
	@GetMapping(params="resume")
	@PreAuthorize("hasAuthority('ROLE_GET_PRESENCE') and #oauth2.hasScoe('read')")
	public Page<PresenceProjection> gelShortAll(PresenceFilter presenceFilter, Pageable pageable) {
		return repository.shortFilter(presenceFilter, pageable);
	}
	
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_POST_PRESENCE') and #oauth2.hasScoe('write')")
	public ResponseEntity<Presence> postEntity(@Valid @RequestBody Presence presence, HttpServletResponse response) {
		
		Presence save = repository.save(presence);
		publisher.publishEvent(new ResourceCreateEvent(this, response, save.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}
	
}
