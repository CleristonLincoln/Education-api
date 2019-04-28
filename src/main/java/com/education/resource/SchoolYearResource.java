package com.education.resource;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.education.exceptionHandle.SchoolExceptionHandle.Erro;
import com.education.model.score.SchoolYear;
import com.education.repository.SchoolYearRepository;
import com.education.repository.filter.SchoolYearFilter;
import com.education.service.SchoolYearService;
import com.education.service.exception.SchoolInexstOrInative;
import com.education.service.exception.SchoolYearException;


@RestController
@RequestMapping("/yearSchool")
public class SchoolYearResource {
	

	private static final Logger log = LoggerFactory.getLogger(SchoolYearResource.class);


	@Autowired private SchoolYearRepository repository;
	@Autowired private SchoolYearService service;
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_GET_SCHOOL_YEAR') and #oauth2.hasScoe('read')")
	public Page<SchoolYear> getFilterSchoolYear(Pageable pageable, SchoolYearFilter schoolYearFilter) {
		return repository.filterEntity(pageable, schoolYearFilter);
	}
	
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_GET_SCHOOL_YEAR') and #oauth2.hasScoe('write')")
	public ResponseEntity<SchoolYear> postSchollYear(@RequestBody SchoolYear schoolYear) {
				
		SchoolYear saving = service.saving(schoolYear);
		
		log.info("Salvando");
				
		return ResponseEntity.status(HttpStatus.CREATED).body(saving);
	}
	
	
	@ExceptionHandler({SchoolInexstOrInative.class})
	public ResponseEntity<List<Erro>> schoolInexist( SchoolInexstOrInative ex) {
		String messageUser = "Escola não existe";
		String messageDeveloper = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDeveloper));
		
		return ResponseEntity.badRequest().body(erros);
	}

	@ExceptionHandler({SchoolYearException.class})
	public ResponseEntity<List<Erro>> launchDupliced(SchoolYearException ex) {
		String messageUser = "Periodo letivo já existe";
		String messageDeveloper = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDeveloper));
		
		return ResponseEntity.badRequest().body(erros);
	}
}
