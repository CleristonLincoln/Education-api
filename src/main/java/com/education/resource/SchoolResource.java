package com.education.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.education.event.ResourceCreateEvent;
import com.education.exceptionHandle.SchoolExceptionHandle.Erro;
import com.education.model.people.School;
import com.education.repository.SchoolRepository;
import com.education.repository.filter.SchoolFilter;
import com.education.repository.projection.SchoolProjection;
import com.education.service.SchoolService;
import com.education.service.exception.SchoolIsExist;

@RestController
@RequestMapping("/school")
public class SchoolResource {

	@Autowired private SchoolRepository repository;
	@Autowired private SchoolService service;
	@Autowired private ApplicationEventPublisher publisher;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_GET_SCHOOL') and #oauth2.hasScoe('read')")
	public Page<School> getAllEntity(SchoolFilter SchoolFilter, Pageable pageable) {
		return repository.filterEntity(SchoolFilter, pageable);
	}

	@GetMapping(params = "resume")
	@PreAuthorize("hasAuthority('ROLE_GET_SCHOOL') and #oauth2.hasScoe('read')")
	public Page<SchoolProjection> getshortEntity(SchoolFilter SchoolFilter, Pageable pageable) {
		return repository.shortEntity(SchoolFilter, pageable);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_GET_SCHOOL') and #oauth2.hasScoe('read')")
	public ResponseEntity<? extends Object> postEntiyt(@PathVariable Long id) {
		Optional<School> School = repository.findById(id);
		return School.isPresent() ? ResponseEntity.ok(School.get()) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_POST_SCHOOL') and #oauth2.hasScoe('write')")
	public ResponseEntity<School> saveEntity(@Valid @RequestBody School school, HttpServletResponse response) {
		School saveSchool = service.save(school);
		publisher.publishEvent(new ResourceCreateEvent(this, response, saveSchool.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(saveSchool);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PUT_SCHOOL') and #oauth2.hasScoe('write')")
	public ResponseEntity<School> putEntity(@PathVariable Long id, @Valid @RequestBody School School) {
		School getSchool = repository.findById(id).get();

		BeanUtils.copyProperties(School, getSchool, "id");
		School saveSchool = repository.save(getSchool);

		return ResponseEntity.ok(saveSchool);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_DELETE_SCHOOL') and #oauth2.hasScoe('delete')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEntity(@PathVariable Long id) {
		service.deleteSchool(id);
	}
	
	
	@ExceptionHandler({ SchoolIsExist.class })
	public ResponseEntity<List<Erro>> SchoolIsExist(SchoolIsExist ex) {

		String messageUser = "Empresa já cadastrada";
		String messageDeveloper = ex.toString();

		List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDeveloper));
		return ResponseEntity.badRequest().body(erros);
	}

}
