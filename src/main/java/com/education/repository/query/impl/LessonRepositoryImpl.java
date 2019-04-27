package com.education.repository.query.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.education.model.Lesson;
import com.education.repository.filter.LessonFilter;
import com.education.repository.query.LessonRepositoryQuery;

public class LessonRepositoryImpl implements LessonRepositoryQuery{

	public Page<Lesson> filter(LessonFilter filter, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

}
