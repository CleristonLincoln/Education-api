package com.education.resource;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

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
import com.education.model.adress.Neighborhood;
import com.education.repository.NeighborhoodRepository;
import com.education.repository.filter.NeighborhoodFilter;
import com.education.repository.projection.NeighborhoodProjection;
import com.education.service.NeighborhoodService;
import com.education.service.exception.CityException;

@RestController
@RequestMapping("/neighborhoods")
public class NeighborhoodResource {

	@Autowired private NeighborhoodRepository repository;
	@Autowired private NeighborhoodService service;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_GET_NEIGHBORHOOD') and #oauth2.hasScope('read')")
	public Page<Neighborhood> getAllEntity( NeighborhoodFilter neighborhoodFilter, Pageable pageable) {
		return repository.filtrar(neighborhoodFilter, pageable);
	}
	
	
	@GetMapping(params="resume")
	@PreAuthorize("hasAuthority('ROLE_GET_NEIGHBORHOOD') and #oauth2.hasScope('read')")
	public Page<NeighborhoodProjection> shortEntity(NeighborhoodFilter neighborhoodFilter, Pageable pageable){
		return repository.shortEntity(neighborhoodFilter, pageable);
	}
	
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_POST_NEIGHBORHOOD') and #oauth2.hasScope('wride')")
	public ResponseEntity<Neighborhood> postEntity(@RequestBody @Valid Neighborhood entity) {
		Neighborhood saved = service.saving(entity);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	
	@ExceptionHandler({CityException.class})
	public ResponseEntity<List<Erro>> exception(CityException ex){
		String messageUser = "Cidade não localizada ou inexistente";
		String messageDeveloper = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDeveloper));
		return ResponseEntity.badRequest().body(erros);
	}
}
