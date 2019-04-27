package com.education.model.people;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import com.education.model.adress.Street;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "school")
public class School {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 4, max = 100)
	@Column(name = "name_school")
	private String nameSchool;

	@NotNull
	@Size(min = 5, max = 45)
	@Column(name = "name_social")
	private String nameSocial;
	
	@CNPJ
	@NotNull
	private String cnpj;

	@NotNull
	@Size(min = 8, max = 14)
	private String ie;

	@NotNull
	@Size(min = 5, max = 14)
	private String im;

	@Size(min = 5, max = 45)
	@Column(name="complement_home")
	private String complementHome;

	@NotNull
	@Size(min = 5, max = 10)
	@Column(name = "number_home")
	private String numberHome;
	

	//___________RELATIONSHIP___________\\
	
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "id_street")
	private Street street;
	
	@ManyToMany
	@JoinTable(name = "school_x_teacher", 
		joinColumns = @JoinColumn(name = "id_school"), 
		inverseJoinColumns = @JoinColumn(name = "id_teacher"))
	private List<Teacher> teacher;


	
	
}
