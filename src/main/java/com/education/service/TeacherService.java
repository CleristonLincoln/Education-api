package com.education.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.education.model.people.Teacher;
import com.education.repository.TeacherRepository;
import com.education.service.exception.TeacherIsExist;
import com.education.storage.S3;

@Service
public class TeacherService {

	// https://s3.amazonaws.com/cl-education-files/20160215_132559-COLLAGE.png
	
	
	@Autowired private TeacherRepository repository;
	@Autowired private S3 s3;
	
	public Teacher saved(@Valid Teacher teacher) {
		validIfExist(teacher);
		return repository.save(teacher);
	}


	private void validIfExist(@Valid Teacher teacher) {

		Teacher teacherSearch = repository.findByCpf(teacher.getCpf());
		
		if (teacherSearch != null) {
			throw new TeacherIsExist();
		}
	}

	
	public Teacher putTeacher(Long id, Teacher teacher) {
		Teacher teacherId = getTeacherId(id).get();
		BeanUtils.copyProperties(teacher, teacherId, "id");
		Teacher teacherSave = repository.save(teacherId);
		return teacherSave;
	}
	
	
	public Optional<Teacher> getTeacherId( Long id) {
		Optional<Teacher> teacherSave = repository.findById(id);
		
		if (teacherSave.isEmpty()) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return teacherSave;
	}


	public void setImageAnexo(String image) {
		
		s3.salvar(image);
		
		
	}
	

}
