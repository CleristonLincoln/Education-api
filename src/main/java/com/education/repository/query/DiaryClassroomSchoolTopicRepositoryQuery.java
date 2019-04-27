package com.education.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.education.model.diary.DiaryClassroomSchoolTopic;
import com.education.repository.filter.DiaryClassroomSchoolTopicFilter;
import com.education.repository.projection.DiaryClassroomSchoolTopicProjection;

public interface DiaryClassroomSchoolTopicRepositoryQuery {

	Page<DiaryClassroomSchoolTopic> filter(DiaryClassroomSchoolTopicFilter diaryClassroomSchoolTopicFilter, Pageable pageable);
	
	Page<DiaryClassroomSchoolTopicProjection> shortFilter(DiaryClassroomSchoolTopicFilter diaryClassroomSchoolTopicFilter, Pageable pageable);

}
