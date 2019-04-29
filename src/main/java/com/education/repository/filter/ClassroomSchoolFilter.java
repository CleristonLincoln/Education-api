package com.education.repository.filter;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClassroomSchoolFilter {

	private String id;
	private String name;
	private Long classroomId;
	private Long schoolYearId;
	private Long schoolId;

}
