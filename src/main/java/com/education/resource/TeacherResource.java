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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.education.event.ResourceCreateEvent;
import com.education.exceptionHandle.SchoolExceptionHandle.Erro;
import com.education.model.people.Teacher;
import com.education.repository.TeacherRepository;
import com.education.repository.filter.TeacherFilter;
import com.education.repository.projection.TeacherProjection;
import com.education.service.TeacherService;
import com.education.service.exception.TeacherIsExist;

@RestController
@RequestMapping("/teacher")
public class TeacherResource {
	
	@Autowired private TeacherRepository repository;
	@Autowired private TeacherService service;
	@Autowired private ApplicationEventPublisher publisher;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_GET_TEACHER') and #oauth2.hasScope('read')")
	public Page<Teacher> getFilterTeacher(TeacherFilter teacherFilter, Pageable pageable){
		return repository.filter(teacherFilter, pageable);
	}
	
	@GetMapping(params="resume")
	@PreAuthorize("hasAuthority('ROLE_GET_TEACHER') and #oauth2.hasScope('read')")
	public Page<TeacherProjection> getShortFilterEntity (TeacherFilter teacherFilter, Pageable pageable){
		return repository.shortFilter(teacherFilter, pageable);
	}
	
	@GetMapping( "/{id}")
	@PreAuthorize("hasAuthority('ROLE_GET_TEACHER') and #oauth2.hasScope('read')")
	public ResponseEntity<Teacher> getTeacherPerId(@RequestParam Long id) {
			
		Teacher teacher = repository.findById(id).get();
		
		return teacher != null ? ResponseEntity.ok(teacher) : ResponseEntity.notFound().build();
	}


	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_POST_TEACHER') and #oauth2.hasScope('wride')")
	public ResponseEntity<Teacher> postTeacher(@Valid @RequestBody Teacher teacher, HttpServletResponse response) {
		
		Teacher saving = service.saved(teacher);
		publisher.publishEvent(new ResourceCreateEvent(this, response, saving.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(saving);
	}
	
	
	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PUT_TEACHER') and #oauth2.hasScope('put')")
	public ResponseEntity<Teacher> putTeacher(@PathVariable Long id, @Valid @RequestBody Teacher teacher) {
		Teacher putEntity = service.putTeacher(id, teacher);
		return ResponseEntity.ok(putEntity);
	}
	
	@PutMapping("/{id}/image")
	public void putSendImage(@PathVariable Long id, String image) {
	
	//	service.setImageAnexo(id, image);
		
		
	}

	
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_DELETE_TEACHER') and #oauth2.hasScope('delete')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteTeacher(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@ExceptionHandler({TeacherIsExist.class})
	public ResponseEntity<List<Erro>> teacherException(TeacherIsExist ex){
		String messageUser = "Professor já cadastrado";
		String messageDeveloper = ex.toString();
	
		List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDeveloper));
	return ResponseEntity.badRequest().body(erros);
	
	}
}
