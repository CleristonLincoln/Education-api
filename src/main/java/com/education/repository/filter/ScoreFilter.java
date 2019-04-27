package com.education.repository.filter;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ScoreFilter {

	private Integer Score;
	private Integer scoreFrom;
	private Integer scoreTo;
	private Boolean active;
	private LocalDate dateScore;
	private Long studentId;
	private Long teacherId;
	private Long lessonId;
	private Long schoolId;
	private Long semestreId;
	
}
