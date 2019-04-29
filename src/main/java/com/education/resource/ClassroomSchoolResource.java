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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.education.event.ResourceCreateEvent;
import com.education.model.ClassroomSchool;
import com.education.repository.ClassroomSchoolRepository;
import com.education.repository.filter.ClassroomSchoolFilter;
import com.education.repository.projection.ClassroomSchoolProjection;
import com.education.repository.projection.ClassroomSchoolStudentProjection;
import com.education.service.ClassroomSchoolService;

@RestController
@RequestMapping("/classroomschool")
public class ClassroomSchoolResource {

	@Autowired private ClassroomSchoolRepository repository;
	@Autowired private ApplicationEventPublisher publisher;
	@Autowired private ClassroomSchoolService service;

	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_GET_CLASSROOM_SCOOL') and #oauth2.hasScope('read')")
	public Page<ClassroomSchool> getFilterClassroomSchool(ClassroomSchoolFilter classroomSchoolFilter,
			Pageable pageable) {
		return repository.filter(classroomSchoolFilter, pageable);
	}

	
	@GetMapping(params = "resume")
	@PreAuthorize("hasAuthority('ROLE_GET_CLASSROOM_SCOOL') and #oauth2.hasScope('read')")
	public Page<ClassroomSchoolProjection> getShortFilter(ClassroomSchoolFilter classroomSchoolFilter,
			Pageable pageable) {
		return repository.shortFilter(classroomSchoolFilter, pageable);
	}
	
	
	@GetMapping(params = "student")
	public Page<ClassroomSchoolStudentProjection> getMethodName(ClassroomSchoolFilter classroomSchoolFilter, Pageable pageable) {
		return repository.filterClassroomSchoolStudent(classroomSchoolFilter, pageable);
	}


	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_POST_CLASSROOM_SCOOL') and #oauth2.hasScope('write')")
	public ResponseEntity<ClassroomSchool> postEntity(@Valid @RequestBody ClassroomSchool classroomSchool,
			HttpServletResponse response) {
		ClassroomSchool save = service.saving(classroomSchool);
		publisher.publishEvent(new ResourceCreateEvent(this, response, save.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}

	
	@DeleteMapping
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void deleteClassroomSchool(@PathVariable Long id) {
		repository.deleteById(id);
	}

	
	
}
