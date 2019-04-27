package com.education.repository.projection;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DiaryClassroomSchoolTopicProjection {

	private Long id;
	private String topic;
	private Long diaryClassroomSchoolTopicId;
	private String diaryClassroomSchoolTopicName;
	
	
	public DiaryClassroomSchoolTopicProjection(Long id, String topic, Long diaryClassroomSchoolTopicId, String diaryClassroomSchoolTopicName) {
		this.id = id;
		this.topic = topic;
		this.diaryClassroomSchoolTopicId = diaryClassroomSchoolTopicId;
		this.diaryClassroomSchoolTopicName = diaryClassroomSchoolTopicName;
	}

	
}
