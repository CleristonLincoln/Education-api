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
import com.education.model.diary.DiaryClassroomSchoolTopic;
import com.education.repository.DiaryClassroomSchoolTopicRepository;
import com.education.repository.filter.DiaryClassroomSchoolTopicFilter;
import com.education.repository.projection.DiaryClassroomSchoolTopicProjection;

@RestController
@RequestMapping("/diaryclassroomschooltopic")
public class DiaryClassroomSchoolTopicResource {

	@Autowired private DiaryClassroomSchoolTopicRepository repository;
	@Autowired private ApplicationEventPublisher publisher;
	//@Autowired private DiaryClassroomSchoolTopicService service;
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_GET_DIARY_CLASSROOM_SCHOOL_TOPIC') and #oauth2.hasScope('read')")
	public Page<DiaryClassroomSchoolTopic> getAll(DiaryClassroomSchoolTopicFilter diaryClassroomSchoolTopicFilter, Pageable pageable) {
		return repository.filter(diaryClassroomSchoolTopicFilter, pageable);
	}
	
	@GetMapping(params="resume")
	@PreAuthorize("hasAuthority('DIARY_CLASSROOM_SCHOOL_TOPIC') and #oauth2.hasScope('read')")
	public Page<DiaryClassroomSchoolTopicProjection> getShortAll(DiaryClassroomSchoolTopicFilter diaryClassroomSchoolTopicFilter, Pageable pageable) {
		return repository.shortFilter(diaryClassroomSchoolTopicFilter, pageable);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_POST_DIARY_CLASSROOM_SCHOOL_TOPIC') and #oauth2.hasScope('write')")
	public ResponseEntity<DiaryClassroomSchoolTopic> postEntity(@Valid @RequestBody DiaryClassroomSchoolTopic diaryClassroomSchoolTopic, HttpServletResponse response){
		DiaryClassroomSchoolTopic saving = repository.save(diaryClassroomSchoolTopic);
		publisher.publishEvent(new ResourceCreateEvent(this, response, saving.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(saving);
	}
	
	@PutMapping("{id}")
	@PreAuthorize("hasAuthority('ROLE_PUT_DIARY_CLASSROOM_SCHOOL_TOPIC') and #oauth2.hasScope('put')")
	public ResponseEntity<DiaryClassroomSchoolTopic> putEntity(@PathVariable Long id, @Valid DiaryClassroomSchoolTopic entity){
		DiaryClassroomSchoolTopic diary = repository.findById(id).get();
		BeanUtils.copyProperties(entity, diary, "id");
		DiaryClassroomSchoolTopic save = repository.save(entity);
		return ResponseEntity.ok(save);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_DELETE_DIARY_CLASSROOM_SCHOOL_TOPIC') and #oauth2.hasScope('delete')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteEntity(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
}
