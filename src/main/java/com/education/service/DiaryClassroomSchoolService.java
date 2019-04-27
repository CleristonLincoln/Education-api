package com.education.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.model.diary.DiaryClassroomSchool;
import com.education.repository.DiaryClassroomSchoolRepository;

@Service
public class DiaryClassroomSchoolService {
	
	
	@Autowired
	DiaryClassroomSchoolRepository repository;

	public DiaryClassroomSchool saving(@Valid DiaryClassroomSchool diaryClassroomSchool) {
		
		DiaryClassroomSchool saving = repository.save(diaryClassroomSchool);
		
		return saving;
	}


}
