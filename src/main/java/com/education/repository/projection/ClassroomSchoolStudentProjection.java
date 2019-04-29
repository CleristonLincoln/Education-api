package com.education.repository.projection;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClassroomSchoolStudentProjection {
	

	private Long studentId;
	
	private String studentName;

	
	public ClassroomSchoolStudentProjection(Long studentId, String studentName) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
	}


	public ClassroomSchoolStudentProjection(Long studentId) {
		super();
		this.studentId = studentId;
	}

	
	
}
