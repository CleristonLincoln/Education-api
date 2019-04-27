package com.education.repository.projection;

import com.education.model.people.Student;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClassroomSchoolStudentProjection {
	
	private String nome;
	
	private Student student;
	
	
	public ClassroomSchoolStudentProjection(String nome, Student student) {
		this.nome = nome;
		this.student = student;
	
	}
	
	
	
	
	
}
