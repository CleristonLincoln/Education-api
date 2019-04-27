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
import com.education.model.diary.DiaryClassroomSchool;
import com.education.repository.DiaryClassroomSchoolRepository;
import com.education.repository.filter.DiaryClassroomSchoolFilter;
import com.education.repository.projection.DiaryClassroomSchoolProjection;
import com.education.service.DiaryClassroomSchoolService;

@RestController
@RequestMapping("/diaryclassroomschool")
public class DiaryClassroomSchoolResource {

	@Autowired private DiaryClassroomSchoolRepository repository;
	@Autowired private ApplicationEventPublisher publisher;
	@Autowired private DiaryClassroomSchoolService service;
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_GET_DIARY_CLASSROOM_SCHOOL') and #oauth2.hasScope('read')")
	public Page<DiaryClassroomSchool> getFilterDiaryClassrommSchool(DiaryClassroomSchoolFilter diaryClassroomFilter, Pageable pageable) {
		return repository.filter(diaryClassroomFilter, pageable);
	}
	
	@GetMapping(params="resume")
	@PreAuthorize("hasAuthority('ROLE_GET_DIARY_CLASSROOM_SCHOOL') and #oauth2.hasScope('read')")
	public Page<DiaryClassroomSchoolProjection> getShortDiaryClassroomSchool(DiaryClassroomSchoolFilter diaryClassroomFilter, Pageable pageable) {
		return repository.shortFilter(diaryClassroomFilter, pageable);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_POST_DIARY_CLASSROOM_SCHOOL') and #oauth2.hasScope('write')")
	public ResponseEntity<DiaryClassroomSchool> postDiaryClassroomSchool(@Valid @RequestBody DiaryClassroomSchool diaryClassroomSchool, 
			HttpServletResponse response){
		DiaryClassroomSchool save = service.saving(diaryClassroomSchool);
		publisher.publishEvent(new ResourceCreateEvent(this, response, save.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(save);
	}
	
	
	@PutMapping(value = "/{id}")
	@PreAuthorize("hasAuthority('ROLE_PUT_DIARY_CLASSROOM_SCHOOL') and #oauth2.hasScope('write')")
	public ResponseEntity<DiaryClassroomSchool> putDiaryClassroomSchool(@PathVariable Long id, @Valid @ RequestBody DiaryClassroomSchool diaryClassroomSchool) {
		
		DiaryClassroomSchool getDiaryCLassroomSchool = repository.findById(id).get();
		BeanUtils.copyProperties(diaryClassroomSchool, getDiaryCLassroomSchool, "id");
		DiaryClassroomSchool saveDiaryClassroomSchool = repository.save(diaryClassroomSchool);
		
		return ResponseEntity.ok(saveDiaryClassroomSchool);
	}


	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_DELETE_DIARY_CLASSROOM_SCHOOL') and #oauth2.hasScope('write')")
	public void deleteDiaryClassroomSchool(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
}
