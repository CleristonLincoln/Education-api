package com.education.repository.projection;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class DiaryStudentProjection {
	

	private String topic;
	private LocalDate dataDiary;
	private Long idStudent;
	private String nameStudent;
	private String cpfStudent;
	
	
	public DiaryStudentProjection(String topic, LocalDate dataDiary, Long idStudent, String nameStudent,
			String cpfStudent) {
		this.topic = topic;
		this.dataDiary = dataDiary;
		this.idStudent = idStudent;
		this.nameStudent = nameStudent;
		this.cpfStudent = cpfStudent;
	}
	
	
}
