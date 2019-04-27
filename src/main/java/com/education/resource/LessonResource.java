package com.education.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.education.model.Lesson;
import com.education.repository.LessonRepository;
import com.education.repository.filter.LessonFilter;

@RestController
@RequestMapping("/lesson")
public class LessonResource {
	
	@Autowired private LessonRepository repository;
	
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_GET_LESSON') and #oauth2.hasScope('read')")
	public Page<Lesson> getFilterLesson(LessonFilter filter, Pageable pageable) {
		return repository.filter(filter,pageable);
	}


	
}
