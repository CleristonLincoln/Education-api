package com.education.repository.projection;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class SchoolYearProjection {

	private Long id;
	private LocalDate year;
	private LocalDate dateStart;
	private LocalDate dateFinish;
	private Integer averange;
	private Boolean active;
	

		
	
}

