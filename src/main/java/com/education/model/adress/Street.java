package com.education.model.adress;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="street")
public class Street {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min=1, max=100)
	private String name;
	
	private String complement;// não delete
	
	@Enumerated(EnumType.STRING)
	@Column(name="type_adress")
	private TypeAdress typeAdress; 
	
	@NotNull
	@Size(min=8, max=8)
	private String cep;
		
	
	//______________RELATIONSHIP______________\\
	
	
	@ManyToOne
	@NotNull
	@JoinColumn(name="id_neighborhood")
	private Neighborhood neighborhood;

}
