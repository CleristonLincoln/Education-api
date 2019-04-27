package com.education.repository.projection;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DiaryClassroomSchoolProjection {

	private Long id;
	private LocalDate dateDiary;
	private Boolean active;
	private Long idClassroomSchool;
	private String nameClassroomSchool;

	
	public DiaryClassroomSchoolProjection(Long id, LocalDate dateDiary, Boolean active, Long idClassroomSchool,	String nameClassroomSchool) {
		this.id = id;
		this.dateDiary = dateDiary;
		this.active = active;
		this.idClassroomSchool = idClassroomSchool;
		this.nameClassroomSchool = nameClassroomSchool;
	
	}
	
	
}
