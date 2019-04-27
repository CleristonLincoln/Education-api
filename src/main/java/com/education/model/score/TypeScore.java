package com.education.model.score;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Identifica os tipos, se nota final ou pontos a serem somados
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
	
	// 0 - pontos para somar a futuras notas, 1 - nota cheia final.
	@Column(name = "sum_or_finally")
	private Boolean sumOrFinally;
	
	@Column(name = "sum_active")
	private Boolean sumActive; 
	
	
	//______________RELATIONSHIP______________\\
	
	
	@ManyToOne
	@JoinColumn(name = "id_score_semester")
	private ScoreSemester scoreSemester;
	
}
