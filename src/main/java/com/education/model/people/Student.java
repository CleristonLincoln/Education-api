package com.education.model.people;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.education.model.adress.City;
import com.education.model.adress.Street;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor()
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(min = 10, max = 75)
	private String name;

	@Column(name = "shortname")
	private String shortname;

	@CPF
	@NotNull
	private String cpf;

	@Size(min = 4, max = 15)
	private String rg;
	
	// yyyy-MM-dd
	private LocalDate birthday;

	@Email
	@Size(max=45)
	private String email;

	// Recebe notificações por email?
	@Column(name="email_notification")
	private Boolean emailNotification;

	@Size(max=11)
	@Column(name = "phone_1")
	private String phone1;

	@Column(name = "phone_2")
	@Size(max=11)
	private String phone2;

	// informativo se ele tem diabetes, é hipertenso, possui alguma necessidade especial, etc...
	// Sempre que consultado o aluno, deverá ser destacado que ha observações a serem vistas
	@Size(max=255)
	private String note;
	
	// caso o aluno tenha alguma observação importante este será destacado.
	@Column(name="is_note")
	private Boolean isNote;

	@Size(max=10)
	@Column(name = "number_home")
	private String numberHome;

	@Size(max=80)
	@Column(name = "complement_home")
	private String complementHome;

	private Boolean active;

	// registro de nascimento
	@Size(max = 45)
	@Column(name = "number_register")
	private String numberRegister;
	

	//______________RELATIONSHIP______________\\
	
	
	// cidade de nascimento "naturatidade"
	@ManyToOne
	@JoinColumn(name = "id_city")
	private City cityRegister;
	
	@ManyToOne
	@JoinColumn(name = "id_street")
	private Street street;

	@ManyToOne
	@JoinColumn(name = "id_school")
	private School school;
	
	
	
	// construtor para test
	public Student(String name, String shortname, String cpf,
			String rg, String email,
			Boolean emailNotification, String phone1, String phone2,
			String note, Boolean isNote, String numberHome,
			String complementHome, Boolean active,String numberRegister) {
		this.name = name;
		this.shortname = shortname;
		this.cpf = cpf;
		this.rg = rg;
		this.email = email;
		this.emailNotification = emailNotification;
		this.phone1 = phone1;
		this.phone2 = phone2;
		this.note = note;
		this.isNote = isNote;
		this.numberHome = numberHome;
		this.complementHome = complementHome;
		this.active = active;
		this.numberRegister = numberRegister;
	}

	
	
	
	
}
