package com.education.repository.projection;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class DiaryTeacherProjection {
	
	private Long id;
	private Long teacherId;
	private String teacherName;
	private LocalDate dateCreate;
	private LocalDate dateFInish;
	private Long classroomSchoolId;
	private String classroomSchoolName;
	private Boolean active;
	
	
	public DiaryTeacherProjection(Long id, Long teacherId, String teacherName, LocalDate dateCreate,
			LocalDate dateFInish, Long classroomSchoolId, String classroomSchoolName, Boolean active) {
		this.id = id;
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.dateCreate = dateCreate;
		this.dateFInish = dateFInish;
		this.classroomSchoolId = classroomSchoolId;
		this.classroomSchoolName = classroomSchoolName;
		this.active = active;
	}
	
}
