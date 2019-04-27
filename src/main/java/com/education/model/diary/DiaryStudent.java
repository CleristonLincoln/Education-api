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

import com.education.model.people.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
 * Diário com observações individuais do aluno.
 * 
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "diary_student")
public class DiaryStudent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "topic")
	private String topic;

	// data em que o que motivou a observação ocorreu.
	@Column(name = "date_diary")
	private LocalDate dateDiary;
	
	
	//______________RELATIONSHIP______________\\

	
	@ManyToOne
	@JoinColumn(name = "id_student")
	private Student student;

	
}
