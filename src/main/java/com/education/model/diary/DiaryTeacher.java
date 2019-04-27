package com.education.model.diary;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.education.model.ClassroomSchool;
import com.education.model.Lesson;
import com.education.model.people.Teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Planejamento que o professor faz 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="diary_teacher")
public class DiaryTeacher {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	// data em que o tópico vai se iniciar
	@Column(name="date_start")
	private LocalDate dateStart;
	
	// data em que o topico vai ser finalizado
	@Column(name="date_finish")
	private LocalDate dateFinish;

	// quando true indica que o diário foi finalizado. e trava para não ser mais alterado.
	private Boolean active;
	//______________RELATIONSHIP______________\\

	
	@ManyToOne
	@JoinColumn(name = "id_classroom_school")
	private ClassroomSchool classroomSchool;
	
	@ManyToOne
	@JoinColumn(name="id_lesson")
	private Lesson lesson;

	@ManyToOne
	@JoinColumn(name = "id_teacher")
	private Teacher teacher;

	
	//______________AUDITING______________\\
	

}
