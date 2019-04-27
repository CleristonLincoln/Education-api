package com.education.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.education.model.diary.DiaryClassroomSchool;
import com.education.repository.filter.DiaryClassroomSchoolFilter;
import com.education.repository.projection.DiaryClassroomSchoolProjection;

public interface DiaryClassroomSchoolRepositoryQuery {
 
	Page<DiaryClassroomSchool> filter(DiaryClassroomSchoolFilter diaryClassroomFilter, Pageable pageable);
	
	Page<DiaryClassroomSchoolProjection> shortFilter(DiaryClassroomSchoolFilter diaryClassroomFilter, Pageable pageable);
}
