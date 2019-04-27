package com.education.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.education.event.ResourceCreateEvent;
import com.education.exceptionHandle.SchoolExceptionHandle.Erro;
import com.education.model.score.Score;
import com.education.repository.ScoreRepository;
import com.education.repository.filter.ScoreFilter;
import com.education.repository.projection.ScoreProjection;
import com.education.service.ScoreService;
import com.education.service.exception.ScoreException;

@RestController
@RequestMapping("/score")
public class ScoreResource {

	@Autowired private ScoreRepository repository;
	@Autowired private ScoreService service;
	@Autowired private ApplicationEventPublisher publisher;
	@Autowired private MessageSource messageSource;
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_GET_SCORE') and #oauth2.hasScoe('read')")
	public Page<Score> getFilterScore(ScoreFilter scoreFilter, Pageable pageable) {
		return repository.filtrar(scoreFilter, pageable);
	}

	
	@GetMapping(params = "resume")
	@PreAuthorize("hasAuthority('ROLE_GET_SCORE') and #oauth2.hasScoe('read')")
	public Page<ScoreProjection> getShortFilterScore(ScoreFilter scoreFilter, Pageable pageable) {
		return repository.filterShort(scoreFilter, pageable);
	}

	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_POTS_SCORE') and #oauth2.hasScoe('post')")
	public ResponseEntity<Score> postScore(@Valid @RequestBody Score score, HttpServletResponse response) {

		Score save = service.saveScore(score);
		publisher.publishEvent(new ResourceCreateEvent(this, response, save.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}
	
	
	@ExceptionHandler({ScoreException.class})
	public ResponseEntity<List<Erro>> handleScoreException(ScoreException ex){
	
		String messageUser = messageSource.getMessage("scoresemester.false", null, LocaleContextHolder.getLocale());
		String messageDeveloper = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDeveloper));
		return ResponseEntity.badRequest().body(erros);
	}
	

}
