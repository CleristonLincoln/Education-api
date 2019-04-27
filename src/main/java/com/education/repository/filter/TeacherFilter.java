package com.education.repository.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherFilter {
	
	private String name;
	private String cpf;
	private Long streetId;
	private Long cityId;
	private Long stateId;
	
	
}
