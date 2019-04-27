package com.education.model.diary;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.education.model.people.School;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="school_calendar")
public class SchoolCalendar {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	// data em que o topico vai se iniciar
	@Column(name="date_start")
	private LocalDate dateStart;
	
	// data em que o topico vai ser finalizado
	@Column(name="date_finish")
	private LocalDate dateFinish;
	
	private String topic;
	
	@ManyToOne
	@JoinColumn(name = "id_school")
	private School school;


}
