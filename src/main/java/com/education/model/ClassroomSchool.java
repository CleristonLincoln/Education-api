package com.education.model;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.education.model.people.School;
import com.education.model.people.Student;
import com.education.model.score.SchoolYear;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="classroom_school")
public class ClassroomSchool {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Size(min=3, max=20)
	private String name;
	
	private Boolean active;

	
	//______________RELATIONSHIP______________\\
	

	// ano que esta sala de aula será representada, 2ª série a de 2017 
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_school_year")
	private SchoolYear schoolYear;

	@NotNull
	@ManyToOne
	@JoinColumn(name="id_school")
	private School school;
	 
	@NotNull
	@ManyToOne
	@JoinColumn(name="id_classroom")
	private Classroom classroom;
	
	@OneToMany
	@JoinTable(name="classroom_school_x_student",
		joinColumns = @JoinColumn(name="id_classroom_school"),
		inverseJoinColumns = @JoinColumn(name="id_student")	)
	private List<Student> student;

}
