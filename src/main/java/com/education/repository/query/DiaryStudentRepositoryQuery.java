package com.education.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.education.model.diary.DiaryStudent;
import com.education.repository.filter.DiaryStudentFilter;
import com.education.repository.projection.DiaryStudentProjection;

public interface DiaryStudentRepositoryQuery {
	
	public Page<DiaryStudent> filterDiaryStudent(DiaryStudentFilter diaryStudentFilter, Pageable pageable);
	
	public Page<DiaryStudentProjection> shortFilterDiaryStudent(DiaryStudentFilter diaryStudentFilter, Pageable pageable);
}
