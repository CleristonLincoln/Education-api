package com.education.model.score;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.education.model.Lesson;
import com.education.model.people.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="score_semester_result")
public class ScoreSemesterResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Nota alcançada
	@Column(name = "score_avarenge")
	private Integer scoreAvarenge; 

	/*
	 *  0 - Reprovado
	 *  1 - Aprovado
	 */
	@JsonIgnore // evitar que alguem envie este atributo por json
	private Boolean situation;

	/*
	 * padrão será true. Quando false a nota não entrará para cálculos de média.
	 * Exemplo:
	 * 	Aluno x está aprovado e como ativo, seguindo o padrão.
	 *  Aluno y está reprovado e atribuido false, em contra partida a um 
	 *  scoreSemester de nome recuperação em substituição a esta.
	 *
	 */
	private Boolean active;

	
	//______________RELATIONSHIP______________\\	
	
	@ManyToOne
	@JoinColumn(name = "id_lesson")
	private Lesson lesson;

	@ManyToOne
	@JoinColumn(name = "id_student")
	private Student student;

	@ManyToOne
	@JoinColumn(name = "id_score_semester")
	private ScoreSemester scoreSemester;

	@ManyToMany
	@JoinTable(name = "score_x_score_semester_result", 
		joinColumns = @JoinColumn(name = "id_score_semester_result"),
		inverseJoinColumns = @JoinColumn(name = "id_score"))
	private List<Score> score;

}
