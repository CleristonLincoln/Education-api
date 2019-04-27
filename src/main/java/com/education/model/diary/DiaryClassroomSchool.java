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

import org.springframework.format.annotation.DateTimeFormat;

import com.education.model.ClassroomSchool;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * observações gerais de uma sala de aula como: 
 * atividades desenvolvidas, revisão de matéria.
 * Sendo posivel até agendar que em um dia especifico 
 * deverá ser entregue um trabalho.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="diary_classroom_school")
public class DiaryClassroomSchool {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	// dia em que os topicos se referem.
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="date_diary")
	private LocalDate dateDiary;

	
	//______________RELATIONSHIP______________\\
	

	@ManyToOne
	@JoinColumn(name = "id_classroom_school")
	private ClassroomSchool classroomSchool;
}
