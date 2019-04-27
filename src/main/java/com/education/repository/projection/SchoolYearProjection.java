package com.education.repository.projection;

import lombok.Getter;

@Getter
public class SchoolYearProjection {

	private Long id;
	private String year;
	
	
	public SchoolYearProjection(Long id, String year) {
		super();
		this.id = id;
		this.year = year;
	}


	
	
	
}
