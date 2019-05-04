package com.education.repository.projection;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClassroomSchoolStudentProjection {
	

	private Long studentId;
	
	private String studentName;

	
	public ClassroomSchoolStudentProjection(Long studentId, String studentName) {
	
		this.studentId = studentId;
		this.studentName = studentName;
	}

	
}
