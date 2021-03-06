package com.education.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.education.model.people.School;
import com.education.model.people.Student;
import com.education.repository.SchoolRepository;
import com.education.repository.StudentRepository;
import com.education.service.exception.SchoolInexstOrInative;
import com.education.service.exception.StudentCpfException;
import com.education.service.exception.StudentRgException;

@Service
public class StudentService {

	@Autowired private StudentRepository repository;
	@Autowired private SchoolRepository schoolRepository;


	public Student saveStudent(@Valid Student student) {
		
		Boolean verifyNote = verifyIfNote(student);
		student.setIsNote(verifyNote);
		student.setActive(true);
		
		validCpfIsExist(student); // valida se cpf duplicado
		validRgIsExist(student); // valida se rg duplicado
		validIsSchoolExist(student);
		
		return repository.save(student);
	}
	

	private void validIsSchoolExist(Student student) {
		Long idSchool = student.getSchool().getId();
		Optional<School> getSchool = schoolRepository.findById(idSchool);
		
		if (getSchool.isEmpty()) {
			throw new SchoolInexstOrInative();
		}
		
	}


	// valida se o aluno tem observação.
	private Boolean verifyIfNote(Student student) {

		String note = student.getNote();
		
		return note == null || note.isEmpty() ? false : true;
	}

	
	public void validCpfIsExist(Student student) {

		Student searchStudentCpf = repository.findByCpf(student.getCpf());
		
		if (searchStudentCpf != null) {
			throw new StudentCpfException();
		}
	}
	
	public void validRgIsExist(Student student) {

		Student searchStudentRg = repository.findByRg(student.getRg());
		
		if (searchStudentRg != null) {
			throw new StudentRgException();
		}
	}
	

	public void putStudentActive(Long id, @Valid Boolean active) {
		Student student = searchStudent(id);
		student.setActive(active);
		repository.save(student);
	}

	public Student putStudent(Long id, @Valid Student student) {
		
		Student getStudent = searchStudent(id);
	
		BeanUtils.copyProperties(student, getStudent, "id");
		
		return repository.save(getStudent);
	}
		

	public void deleteStudent(Long id) {
		
		searchStudent(id);
		
		repository.deleteById(id);
	}
	

	private Student searchStudent(Long id) {
		
		Student studentSave = repository.findById(id).get();
		
		if (studentSave == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return studentSave;
	}

}
