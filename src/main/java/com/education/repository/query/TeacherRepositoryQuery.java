package com.education.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.education.model.people.Teacher;
import com.education.repository.filter.TeacherFilter;
import com.education.repository.projection.TeacherProjection;

public interface TeacherRepositoryQuery {
	
	Page<Teacher> filter(TeacherFilter teacherFilter, Pageable pageable);
	
	Page<TeacherProjection> shortFilter(TeacherFilter teacherFilter, Pageable pageable);
}
