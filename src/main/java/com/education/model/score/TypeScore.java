package com.education.model.score;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Identifica os tipos, se nota final ou pontos a serem somados
 * dando nome a nota como: avaliação final, trabalho de casa sobre tiradentes, etc...
 * 
 * Não será vinculado a uma lição especicica, possibilitando que seja usado pontos obtidos
 * em uma lição em outra matéria.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="type_score")
public class TypeScore {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	// 0 - pontos para somar a futuras notas, 1 - nota final.
	@NotNull
	@Column(name = "sum_or_finally")
	private Boolean sumOrFinally;
	
	/* *
	 * Controla se os pontos lançados foram usados para complementar alguma nota
	 * 0 - pode ser usada 
	 * 1 - bloqueada
	 */
	//@JsonProperty(access = Access.READ_ONLY)
	@Column(name = "sum_active")
	private Boolean active; 
	
	
	//______________RELATIONSHIP______________\\
	
	
	@ManyToOne
	@JoinColumn(name = "id_score_semester")
	private ScoreSemester scoreSemester;
	
}
