package com.education.model.diary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.education.model.Lesson;
import com.education.model.people.Teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "diary_classroom_school_topic")
public class DiaryClassroomSchoolTopic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// terá o limite de 255 caracteres.
	@Column(name = "topic")
	private String topic;

	
	// ______________RELATIONSHIP______________\\

	
	@ManyToOne
	@JoinColumn(name = "id_diary_classroom_school")
	private DiaryClassroomSchool diaryClassroomSchool;

	@ManyToOne
	@JoinColumn(name = "id_teacher")
	private Teacher teacher;
	
	@ManyToOne
	@JoinColumn(name = "id_lesson")
	private Lesson lesson;
}
