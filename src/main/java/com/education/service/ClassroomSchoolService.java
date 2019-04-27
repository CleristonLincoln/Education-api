package com.education.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.model.ClassroomSchool;
import com.education.model.people.Student;
import com.education.repository.ClassroomSchoolRepository;

@Service
public class ClassroomSchoolService {

	@Autowired private ClassroomSchoolRepository repository;

	
	public List<Student> listAllClass(Long id) {
		ClassroomSchool idClassroomSchool = repository.findById(id).get();
		
		List<Student> listStudent = idClassroomSchool.getStudent();
		
		return listStudent;
	}

	public ClassroomSchool saving(@Valid ClassroomSchool classroomSchool) {

		return repository.save(classroomSchool);
	}

	
}
