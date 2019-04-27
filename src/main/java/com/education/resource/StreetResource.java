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
import com.education.model.adress.Street;
import com.education.repository.StreetRepository;
import com.education.repository.filter.StreetFilter;
import com.education.repository.projection.StreetProjection;
import com.education.service.StreetService;
import com.education.service.exception.StreetException;
import com.education.service.exception.StreetIdNeighborException;

@RestController
@RequestMapping("/streets")
public class StreetResource {

	@Autowired private StreetRepository repository;
	@Autowired private StreetService service;
	@Autowired private ApplicationEventPublisher publisher;
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_GET_STREET') and #oauth2.hasScope('read')")
	public Page<Street> getFilterStreet(StreetFilter streetFilter, Pageable pageable) {
		return repository.filter(streetFilter, pageable);
	}
	
	@GetMapping(params="resume")
	@PreAuthorize("hasAuthority('ROLE_GET_STREET') and #oauth2.hasScope('read')")
	public Page<StreetProjection> getShortFilterStreet(StreetFilter streetFilter, Pageable pageable) {
		return repository.shortFilter(streetFilter, pageable);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_POST_STREET') and #oauth2.hasScope('write')")
	public ResponseEntity<Street> postStreet(@RequestBody @Valid Street street, HttpServletResponse response) {
		Street saved = service.saving(street);
		publisher.publishEvent(new ResourceCreateEvent(this, response, saved.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	
	@ExceptionHandler({StreetException.class})
	public ResponseEntity<List<Erro>> streetIsExist(StreetException ex){
	
		String messageUser = "O CEP informado já esta cadastrado";
		String messageDeveloper = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDeveloper));
		return ResponseEntity.badRequest().body(erros);
	}
	
	@ExceptionHandler({StreetIdNeighborException.class})
	public ResponseEntity<List<Erro>> streetIsNExist(StreetException ex){
	
		String messageUser = "Bairro inexistente";
		String messageDeveloper = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDeveloper));
		return ResponseEntity.badRequest().body(erros);
	}
	
}
