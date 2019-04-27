package com.education.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;

/*
 * Cadastro de materias a serem lecionadas, cadastro feito somente pelo adm do sistema.
 * Exemplo: Matematica, Física, POrtuguês...
 */

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="lesson")
public class Lesson {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min=3, max=20)
	//@JsonProperty(value="nome")
	private String name;

}
