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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.education.event.ResourceCreateEvent;
import com.education.model.score.ScoreSemester;
import com.education.repository.ScoreSemesterRepository;
import com.education.repository.filter.ScoreSemesterFilter;
import com.education.repository.projection.ScoreSemesterProjection;
import com.education.service.ScoreSemesterService;

@RestController
@RequestMapping("/scoresemester")
public class ScoreSemesterResource {

	@Autowired private ScoreSemesterRepository repository;
	@Autowired private ApplicationEventPublisher publisher;
	@Autowired private ScoreSemesterService service;

	
	@GetMapping
	public Page<ScoreSemester> getFilterScoreSemester(ScoreSemesterFilter scoreSemesterFilter, Pageable pageable) {
		return repository.filterScoreSemester(scoreSemesterFilter, pageable);
	}
	
	
	@GetMapping(params = "resume")
	public Page<ScoreSemesterProjection> getShortFilterScoreSemester(ScoreSemesterFilter scoreSemesterFilter, Pageable pageable) {
		return repository.shortFilterScoreSemester(scoreSemesterFilter, pageable);
	}
	

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_POST_SCORE_SEMESTER') and #oauth2.hasScope('write')")
	public ResponseEntity<ScoreSemester> postEntity(@Valid @RequestBody ScoreSemester scoreSemester,
			HttpServletResponse response) {

		ScoreSemester saving = repository.save(scoreSemester);
		publisher.publishEvent(new ResourceCreateEvent(this, response, saving.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(saving);
	}

	
	//recebe o Id e o parametro true, gerando as notas do semestre na classe SemesterResult.
	@PutMapping("/{id}/generatescore")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_PUT_SCORE_SEMESTER_GENERATE_SCORE') and #oauth2.hasScope('write')")
	public void generateResult(@PathVariable Long id, @RequestBody Boolean generateScore) {
		service.putPropertieResult(id, generateScore);
	}
}
