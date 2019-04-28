package com.education.repository.filter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SchoolYearFilter {

	private Long id;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate currentYear;
	
	private LocalDate dateStartFrom;
	
	private LocalDate dateStartTo;
	
	private LocalDate dateFinishFrom;
	
	private LocalDate dateFinishTo;
	
	private Long SchoolId;
	
	private Long schoolCityId;

}

