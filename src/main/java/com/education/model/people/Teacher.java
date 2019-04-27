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
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.education.model.Lesson;
import com.education.model.adress.Street;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teacher")
public class Teacher  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "image")
	private String image;

	@Size(min = 10, max = 45)
	@NotNull
	private String name;
	
	@Size(max=15)
	@Column(name="shortname")
	private String shortname;
	
	@CPF
	@NotNull
	private String cpf;
	
	@Size(min=4, max=15)
	private String rg;

	@Email
	@Size(max=45)
	private String email;

	@Column(name = "phone_1")
	@Size(max=11)
	private String phone1;
	
	@Column(name = "phone_2")
	private String phone2;

	private String note;

	@Column(name = "number_home")
	private String numberHome;

	@Size(max=80)
	@Column(name = "complement_home")
	private String complementHome;

	
	//______________RELATIONSHIP______________\\
	
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "id_street")
	private Street street;
	
	
	@ManyToMany
	@JoinTable(name = "teacher_x_lesson", 
	joinColumns = @JoinColumn(name = "id_teacher"), 
	inverseJoinColumns = @JoinColumn(name = "id_lesson"))
	private List<Lesson> lesson;
		

}
