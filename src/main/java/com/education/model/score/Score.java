package com.education.model.score;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.education.model.ClassroomSchool;
import com.education.model.Lesson;
import com.education.model.people.School;
import com.education.model.people.Student;
import com.education.model.people.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="score")
public class Score {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@DecimalMax("100")
	@DecimalMin("0")
	@Column(name="initial_score")
	private Integer initialScore;
	
	// resultado da soma dos pontos da lista scoreAddictional
	@JsonIgnore
	private Integer points;
	
	// resultado da nota obtida na avaliação + os pontos.
	@JsonIgnore
	@Column(name = "final_score")
	private Integer finalScore;
	
	
	// levar em consideração o score + ponts.
	@JsonIgnore
	@Column(name="situation")
	private Boolean situation;	
	
	@Column(name="date_score")
	private LocalDate dateScore;
	
	
	/*
	 * se true a nota é computada para as médias.
	 * Quando false é para ter uma outra substitindo (recuperação)
	 */
	@Column(name="active")
	private Boolean active;
	
	 
	//______________RELATIONSHIP______________\\
	
	
	@ManyToOne
	@JoinColumn(name="id_student")
	private Student student;
	
	@ManyToOne
	@JoinColumn(name="id_classroom_school")
	private ClassroomSchool classroomSchool;

	@ManyToOne
	@JoinColumn(name="id_lesson")
	private Lesson lesson;

	@ManyToOne
	@JoinColumn(name="id_teacher")
	private Teacher teacher;

	@ManyToOne
	@JoinColumn(name="id_score_semester")
	private ScoreSemester scoreSemester;

	// colocado para melhorar a performace das consutas sql
	@ManyToOne
	@JoinColumn(name="id_school")
	private School school;
	
	@OneToMany
	@JoinTable(name = "score_x_score_additional",
			joinColumns = @JoinColumn(name = "id_score"),
			inverseJoinColumns = @JoinColumn(name = "id_score_additional"))
	private List<ScoreAdditional> scoreAditional;

	@ManyToOne
	@JoinColumn(name="id_type_score")
	private TypeScore typeScore;

}
