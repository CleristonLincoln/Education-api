package com.education.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
import com.education.model.adress.City;
import com.education.repository.CityRepository;
import com.education.repository.filter.CityFilter;
import com.education.repository.projection.CityProjection;
import com.education.service.CityService;
import com.education.service.exception.CityException;

@RestController
@RequestMapping("/citys")
public class CityResource {

	@Autowired private CityRepository repository;
	@Autowired private CityService service;
	@Autowired private ApplicationEventPublisher publisher;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_GET_CITY') and #oauth2.hasScope('read')")
	public Page<City> getFilterCities( CityFilter cityFilter, Pageable pageable) {
		return repository.filter(cityFilter, pageable);
	}
	
	@GetMapping(params="resume")
	@PreAuthorize("hasAuthority('ROLE_GET_CITY') and #oauth2.hasScope('read')")
	public Page<CityProjection> shortFilterCity( CityFilter cityFilter, Pageable pageable) {
		return repository.shortFilter(cityFilter, pageable);
	}
	
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_POST_CITY') and #oauth2.hasScope('write')")
	public ResponseEntity<City> postCity(@Valid @RequestBody City city, HttpServletResponse response) {
		City save = service.saving(city);
		publisher.publishEvent(new ResourceCreateEvent(this, response, save.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}
	
	@ExceptionHandler({CityException.class})
	public ResponseEntity<List<Erro>> cityException(CityException ex) {
		String messageUser = "Estado inexistente";
		String messageDeveloper = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDeveloper));
		return ResponseEntity.badRequest().body(erros);
	}

}
