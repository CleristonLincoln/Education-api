package com.education.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.model.diary.DiaryTeacher;
import com.education.repository.DiaryTeacherRepository;

@Service
public class DiaryTeacherService {
	
	@Autowired private DiaryTeacherRepository repository;

	public void putPropertyActive(Long id, Boolean active) {
		
		DiaryTeacher diaryTeacher = repository.findById(id).get(); 
		diaryTeacher.setActive(active);
		
	}

	public void putDiaryTeacher(Long id) {
	
		validGetId(id);
		
		repository.deleteById(id);
		
	}

	private void validGetId(Long id) {
		Optional<DiaryTeacher> getId = repository.findById(id);
		
		if (!getId.isPresent()) {
			throw new IllegalArgumentException();
		}
	}
	
	
	

}
   