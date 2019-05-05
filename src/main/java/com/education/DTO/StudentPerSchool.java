package com.education.DTO;

import com.education.model.people.School;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
@AllArgsConstructor
public class StudentPerSchool {

	private Long id;
	private String name;
	private String shortname;

	
}
