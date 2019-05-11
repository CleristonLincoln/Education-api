package com.education.repository.projection;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter  @Setter 
public class ScoreSemesterProjection {

	private Long id;
	private String name;
	private Boolean active;
	private LocalDate dateScoreGenerate;
	
	private Long schoolYearId;
	private LocalDate schoolYearDate;
	
	private Long schoolId;
	private String schoolName;
	
	public ScoreSemesterProjection(Long id, String name, Boolean active, LocalDate dateScoreGenerate, Long schoolYearId,
			LocalDate schoolYearDate, Long schoolId, String schoolName) {
		this.id = id;
		this.name = name;
		this.active = active;
		this.dateScoreGenerate = dateScoreGenerate;
		this.schoolYearId = schoolYearId;
		this.schoolYearDate = schoolYearDate;
		this.schoolId = schoolId;
		this.schoolName = schoolName;
	}
	
	
}
