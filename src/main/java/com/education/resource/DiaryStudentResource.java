package com.education.resource;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.education.event.ResourceCreateEvent;
import com.education.model.diary.DiaryStudent;
import com.education.repository.DiaryStudentRepository;
import com.education.repository.filter.DiaryStudentFilter;
import com.education.repository.projection.DiaryStudentProjection;

@RestController
@RequestMapping("/diarystudent")
public class DiaryStudentResource {

	@Autowired private DiaryStudentRepository repository;
	@Autowired private ApplicationEventPublisher publisher;
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_GET_DIARY_STUDENT') and #oauth2.hasScope('read')")
	public Page<DiaryStudent> getFilterDiaryStudent(DiaryStudentFilter diaryStudentFilter, Pageable pageable) {
		return repository.filterDiaryStudent(diaryStudentFilter, pageable);
	}

	@GetMapping(params = "resume")
	@PreAuthorize("hasAuthority('ROLE_GET_DIARY_STUDENT') and #oauth2.hasScope('read')")
	public Page<DiaryStudentProjection> getShortFilterDiaryStudent(DiaryStudentFilter diaryStudentFilter, Pageable pageable) {
		return repository.shortFilterDiaryStudent(diaryStudentFilter, pageable);
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_POST_DIARY_STUDENT') and #oauth2.hasScope('post')")
	public ResponseEntity<DiaryStudent> postDiaryStudent (@Valid DiaryStudent diaryStudent, HttpServletResponse response) {
		
		DiaryStudent save = repository.save(diaryStudent);
		publisher.publishEvent(new ResourceCreateEvent(this, response, save.getId()));
	
		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}
	
	
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_DELETE_DIARY_STUDENT') and #oauth2.hasScope('write')")
	public void deleteDiaryStudent(@PathVariable Long id) {
		repository.deleteById(id);
		
	}

	
	@PutMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('ROLE_PUT_DIARY_STUDENT') and #oauth2.hasScope('write')")
	public ResponseEntity<DiaryStudent> putDiaryStudent(@PathVariable Long id, @RequestBody DiaryStudent diaryStudent) {
		
		DiaryStudent getDiaryStudent = repository.findById(id).get();
		BeanUtils.copyProperties(diaryStudent, getDiaryStudent, "id");
		DiaryStudent saving = repository.save(diaryStudent);
		
		return ResponseEntity.ok(saving);
		
	}

	
}
