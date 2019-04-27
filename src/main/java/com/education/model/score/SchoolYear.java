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

import com.education.model.people.School;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "school_year")
public class SchoolYear {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// ano letivo
	@Column(name = "current_year")
	private LocalDate currentYear;

	// data oficial do inicio ano letivo
	@Column(name = "date_start")
	private LocalDate dateStart;

	// fim oficial do ano letivo
	@Column(name = "date_finish")
	private LocalDate dateFinish;

	// média da escola para que o aluno seja aprovado
	@Column(name = "score_averange")
	private Integer scoreAverange;
	
	// finalisa o período letivo e bloqueia qualquer lançamento adicional.
	@Column(name = "generate_score")
	private Boolean generateScore;

	// quando false, impede novos pançamentos e criações do periodo
	private Boolean active;
	
	
	//______________RELATIONSHIP______________\\
	
	
	@ManyToOne
	@JoinColumn(name="id_school")
	private School school;
}

