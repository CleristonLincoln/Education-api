package com.education.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.education.model.diary.DiaryTeacher;
import com.education.repository.filter.DiaryTeacherFilter;
import com.education.repository.projection.DiaryTeacherProjection;

public interface DiaryTeacherRepositoryQuery {
	
	Page<DiaryTeacher> filter (DiaryTeacherFilter diaryTeacherFilter, Pageable pageable); 
	
	Page<DiaryTeacherProjection> shortFilter(DiaryTeacherFilter diaryTeacherFilter, Pageable pageable); 

}
