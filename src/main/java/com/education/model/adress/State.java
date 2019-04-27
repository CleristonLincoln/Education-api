package com.education.model.adress;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "state")
public class State {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=3, max=45)
	private String name;
	
	@NotNull
	@Size(min=2, max=2)
	private String shortname;

	
	//______________RELATIONSHIP______________\\
	
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "id_country")
	private Country country;

}
