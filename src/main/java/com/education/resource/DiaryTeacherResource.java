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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.education.event.ResourceCreateEvent;
import com.education.model.diary.DiaryTeacher;
import com.education.repository.DiaryTeacherRepository;
import com.education.repository.filter.DiaryTeacherFilter;
import com.education.repository.projection.DiaryTeacherProjection;
import com.education.service.DiaryTeacherService;

@RestController
@RequestMapping("/diaryteacher")
public class DiaryTeacherResource {

	@Autowired private DiaryTeacherRepository repository;
	@Autowired private ApplicationEventPublisher publisher;
	@Autowired private DiaryTeacherService service;
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_GET_DIARY_TEACHER') and #oauth2.hasScope('read')")
	public Page<DiaryTeacher> getAll(DiaryTeacherFilter diaryTeacherFilter, Pageable pageable) {
		return repository.filter(diaryTeacherFilter, pageable);
	}
	
	
	@GetMapping(params="resume")
	@PreAuthorize("hasAuthority('ROLE_GET_DIARY_TEACHER') and #oauth2.hasScope('read')")
	public Page<DiaryTeacherProjection> getFilterDiaryTeacher(DiaryTeacherFilter diaryTeacherFilter, Pageable pageable) {
		return repository.shortFilter(diaryTeacherFilter, pageable);
	}
	

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_POST_DIARY_TEACHER') and #oauth2.hasScope('write')")
	public ResponseEntity<DiaryTeacher> postDiaryTeacher(@Valid @RequestBody DiaryTeacher diaryTeacher, HttpServletResponse response) {
		
		DiaryTeacher saved = repository.save(diaryTeacher);
		publisher.publishEvent(new ResourceCreateEvent(this, response, saved.getId()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	
	@DeleteMapping(value = "path/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_DELETE_DIARY_TEACHER') and #oauth2.hasScope('write')")
	public void deleteDiaryTeacher(@PathVariable Long id) {
		
		service.putDiaryTeacher(id);
		
		
		
	}


	@PutMapping("/{id}/active")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_PUT_ACTIVE_DIARY_TEACHER') and #oauth2.hasScope('write')")
	public void putDiaryTeacherActive(@PathVariable Long id, @RequestBody Boolean active) {

	service.putPropertyActive(id, active);
	
	
	}

	
	
}
