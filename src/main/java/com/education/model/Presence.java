package com.education.model;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.education.model.people.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="presence")
public class Presence {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="date_event")
	private LocalDate dateEvent;

	@Enumerated(EnumType.ORDINAL)
	@Column(name="situation")
	private SituationPresence situation;
	
	
	//______________RELATIONSHIP______________\\
	
	
	@ManyToOne
	@JoinColumn(name="id_student")
	private Student student;
	
}
