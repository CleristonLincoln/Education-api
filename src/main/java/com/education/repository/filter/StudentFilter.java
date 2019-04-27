package com.education.repository.filter;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StudentFilter {
	
	 private String name;
	 private LocalDate birthday;
	 private LocalDate birthdayFrom;
	 private LocalDate birthdayTo;
	 private Long stateId;
	 private Long cityId;
	 private Long neighborhoodId;
	 private Long schoolId;
	 
	 
}
