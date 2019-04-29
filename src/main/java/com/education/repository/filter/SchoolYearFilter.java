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
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateStartFrom;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateStartTo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateFinishFrom;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateFinishTo;
	
	private Long SchoolId;
	
	private Long schoolCityId;

}

