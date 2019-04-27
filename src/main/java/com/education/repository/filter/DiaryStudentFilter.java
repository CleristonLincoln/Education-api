package com.education.repository.filter;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DiaryStudentFilter {

	private Long id;
	private String topic;
	private LocalDate dateDiaryFrom;
	private LocalDate dateDiaryTo;
	private Long idStudent;
	private String nameStudent;
	private String cpfStudent;
	
}
