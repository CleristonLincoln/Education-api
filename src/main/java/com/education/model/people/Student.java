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
import lombok.NoArgsConstructor;


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

	@Size(max=11)
	@Column(name = "phone_2")
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getEmailNotification() {
		return emailNotification;
	}

	public void setEmailNotification(Boolean emailNotification) {
		this.emailNotification = emailNotification;
	}

	public String getPhone1() {
		return phone1;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Boolean getIsNote() {
		return isNote;
	}

	public void setIsNote(Boolean isNote) {
		this.isNote = isNote;
	}

	public String getNumberHome() {
		return numberHome;
	}

	public void setNumberHome(String numberHome) {
		this.numberHome = numberHome;
	}

	public String getComplementHome() {
		return complementHome;
	}

	public void setComplementHome(String complementHome) {
		this.complementHome = complementHome;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getNumberRegister() {
		return numberRegister;
	}

	public void setNumberRegister(String numberRegister) {
		this.numberRegister = numberRegister;
	}

	public City getCityRegister() {
		return cityRegister;
	}

	public void setCityRegister(City cityRegister) {
		this.cityRegister = cityRegister;
	}

	public Street getStreet() {
		return street;
	}

	public void setStreet(Street street) {
		this.street = street;
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((active == null) ? 0 : active.hashCode());
		result = prime * result + ((birthday == null) ? 0 : birthday.hashCode());
		result = prime * result + ((cityRegister == null) ? 0 : cityRegister.hashCode());
		result = prime * result + ((complementHome == null) ? 0 : complementHome.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((emailNotification == null) ? 0 : emailNotification.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isNote == null) ? 0 : isNote.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((note == null) ? 0 : note.hashCode());
		result = prime * result + ((numberHome == null) ? 0 : numberHome.hashCode());
		result = prime * result + ((numberRegister == null) ? 0 : numberRegister.hashCode());
		result = prime * result + ((phone1 == null) ? 0 : phone1.hashCode());
		result = prime * result + ((phone2 == null) ? 0 : phone2.hashCode());
		result = prime * result + ((rg == null) ? 0 : rg.hashCode());
		result = prime * result + ((school == null) ? 0 : school.hashCode());
		result = prime * result + ((shortname == null) ? 0 : shortname.hashCode());
		result = prime * result + ((street == null) ? 0 : street.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (active == null) {
			if (other.active != null)
				return false;
		} else if (!active.equals(other.active))
			return false;
		if (birthday == null) {
			if (other.birthday != null)
				return false;
		} else if (!birthday.equals(other.birthday))
			return false;
		if (cityRegister == null) {
			if (other.cityRegister != null)
				return false;
		} else if (!cityRegister.equals(other.cityRegister))
			return false;
		if (complementHome == null) {
			if (other.complementHome != null)
				return false;
		} else if (!complementHome.equals(other.complementHome))
			return false;
		if (cpf == null) {
			if (other.cpf != null)
				return false;
		} else if (!cpf.equals(other.cpf))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (emailNotification == null) {
			if (other.emailNotification != null)
				return false;
		} else if (!emailNotification.equals(other.emailNotification))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isNote == null) {
			if (other.isNote != null)
				return false;
		} else if (!isNote.equals(other.isNote))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (note == null) {
			if (other.note != null)
				return false;
		} else if (!note.equals(other.note))
			return false;
		if (numberHome == null) {
			if (other.numberHome != null)
				return false;
		} else if (!numberHome.equals(other.numberHome))
			return false;
		if (numberRegister == null) {
			if (other.numberRegister != null)
				return false;
		} else if (!numberRegister.equals(other.numberRegister))
			return false;
		if (phone1 == null) {
			if (other.phone1 != null)
				return false;
		} else if (!phone1.equals(other.phone1))
			return false;
		if (phone2 == null) {
			if (other.phone2 != null)
				return false;
		} else if (!phone2.equals(other.phone2))
			return false;
		if (rg == null) {
			if (other.rg != null)
				return false;
		} else if (!rg.equals(other.rg))
			return false;
		if (school == null) {
			if (other.school != null)
				return false;
		} else if (!school.equals(other.school))
			return false;
		if (shortname == null) {
			if (other.shortname != null)
				return false;
		} else if (!shortname.equals(other.shortname))
			return false;
		if (street == null) {
			if (other.street != null)
				return false;
		} else if (!street.equals(other.street))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", shortname=" + shortname + ", cpf=" + cpf + ", rg=" + rg
				+ ", birthday=" + birthday + ", email=" + email + ", emailNotification=" + emailNotification
				+ ", phone1=" + phone1 + ", phone2=" + phone2 + ", note=" + note + ", isNote=" + isNote
				+ ", numberHome=" + numberHome + ", complementHome=" + complementHome + ", active=" + active
				+ ", numberRegister=" + numberRegister + ", cityRegister=" + cityRegister + ", street=" + street
				+ ", school=" + school + "]";
	}
	
	
	
	
}
