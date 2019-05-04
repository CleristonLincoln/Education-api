package com.education.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.education.event.ResourceCreateEvent;
import com.education.model.score.TypeScore;
import com.education.repository.TypeScoreRepository;
import com.education.service.TypeScoreService;

@RestController
@RequestMapping("/typescore")
public class TypeScoreResource {

	@Autowired private TypeScoreRepository repository;
	@Autowired private ApplicationEventPublisher publisher;
	@Autowired private TypeScoreService service;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_GET_TYPE_SCORE) and #oauth2.hasScoe('read')")
	public List<TypeScore> getFilterTypeScore() {
		return repository.findAll();
	}

	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_POST_TYPE_SCORE') and #oauth2.hasScoe('write')")
	public ResponseEntity<TypeScore> postTypeScore(@RequestBody @Valid TypeScore typeScore, HttpServletResponse response) {
		TypeScore saved = service.saving(typeScore);
		
		publisher.publishEvent(new ResourceCreateEvent(this, response, saved.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	@PreAuthorize("hasAuthority('ROLE_DELETE_TYPE_SCORE') and #oauth2.hasScoe('write')")
	public void deleteTypeScore(@PathVariable Long id) {
		repository.deleteById(id);
	}

	
	
}
