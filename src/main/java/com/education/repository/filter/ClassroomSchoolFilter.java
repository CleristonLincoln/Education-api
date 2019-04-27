package com.education.repository.filter;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClassroomSchoolFilter {

	private String name;
	private Long classroomId;
	private LocalDate year;
	private Long schoolId;

}
