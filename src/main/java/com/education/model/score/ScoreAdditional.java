package com.education.model.score;

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

import com.education.model.Lesson;
import com.education.model.people.School;
import com.education.model.people.Student;
import com.education.model.people.Teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Registra os pontos a serem usados.
 * 
 * ex: um trabalho de classe que vale 20 pontos a serem somados futuramente a 
 * um score.
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "score_additional")
public class ScoreAdditional {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// data de realização 
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "date_score")
	private LocalDate dateScore;
	
	private Integer score;
	
	
	//__________RELATIONSHIP__________\\
	
	
	@ManyToOne
	@JoinColumn(name = "id_student")
	private Student student;
	
	@ManyToOne
	@JoinColumn(name = "id_lesson")
	private Lesson lesson;
	
	@ManyToOne
	@JoinColumn(name = "id_teacher")
	private Teacher teacher;
	
	@ManyToOne
	@JoinColumn(name = "id_school")
	private School school;

	@ManyToOne
	@JoinColumn(name="id_type_score")
	private TypeScore typeScore;
	
}
