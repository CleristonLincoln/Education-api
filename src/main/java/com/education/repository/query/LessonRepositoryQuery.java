package com.education.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.education.model.Lesson;
import com.education.repository.filter.LessonFilter;

public interface LessonRepositoryQuery {
	Page<Lesson> filter(LessonFilter filter, Pageable pageable);
}
