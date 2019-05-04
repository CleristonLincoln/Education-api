package com.education.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.education.event.ResourceCreateEvent;
import com.education.model.score.ScoreAdditional;
import com.education.repository.ScoreAdditionalRepository;


@RestController
@RequestMapping("scoreadditional")
public class SdoreAdditionalResource {

	
	@Autowired private ScoreAdditionalRepository repository;
	@Autowired private ApplicationEventPublisher publisher;
	
	
	@GetMapping
	public java.util.List<ScoreAdditional> getFilterScoreAdditional() {
		return repository.findAll();
	}

	@PostMapping
	public ResponseEntity<ScoreAdditional> postScoreAdditional(@RequestBody @Valid ScoreAdditional scoreAdditional,
			HttpServletResponse response) {
		ScoreAdditional saved = repository.save(scoreAdditional);
		
		publisher.publishEvent(new ResourceCreateEvent(this, response, saved.getId()) );
		
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteScoreAdditional(@PathVariable Long id) {
		repository.deleteById(id);
	}


	
}
