package com.education.repository.projection;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClassroomSchoolProjection {

	private Long id;
	private String name;
	private String classroom;
	private Boolean active;
	private LocalDate year;
	private Long schoolId;
	private String schoolNameSocial;
	private String schoolCnpj;
	
	public ClassroomSchoolProjection(Long id, String name, String classroom, Boolean active, LocalDate year,
			Long schoolId, String schoolNameSocial, String schoolCnpj) {
		this.id = id;
		this.name = name;
		this.classroom = classroom;
		this.active = active;
		this.year = year;
		this.schoolId = schoolId;
		this.schoolNameSocial = schoolNameSocial;
		this.schoolCnpj = schoolCnpj;
	}

}
