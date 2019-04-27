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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * @author: CleristonLincoln
 * 
 * Gera um nome para um agrupamento de notas, o mais semelhante a atualidade é
 * criar um periodo como por ex. 1º semestre, 2 semestre, Recuperação períodos 1 e 2
 * e posterior gerar uma média. O resultado das médias está na class ScoreSemesterResult.
 * 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "score_semester")
public class ScoreSemester {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min=5, max=20)
	@NotNull
	private String name;

	// quando false fecha o semestre, impedindo que seja lançadas mais notas.
	@Column(name = "active")
	private Boolean active;
	
	
	// data em que foi gerada as médias
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name="date_generate_score")
	private LocalDate dateGenerateScore;
	

	//______________RELATIONSHIP______________\\
	

	@ManyToOne
	@JoinColumn(name = "id_school_year")
	private SchoolYear schoolYear;

}
