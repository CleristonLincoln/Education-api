package com.education.model.adress;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="city")
public class City {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min=3, max=80)
	private String name;
	
	@Size(max=10)
	@Column(name="idibge")
	private String idIBGE;
	
	
	//______________RELATIONSHIP______________\\
	
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "id_state")
	private State state;


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


	public String getIdIBGE() {
		return idIBGE;
	}


	public void setIdIBGE(String idIBGE) {
		this.idIBGE = idIBGE;
	}


	public State getState() {
		return state;
	}


	public void setState(State state) {
		this.state = state;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idIBGE == null) ? 0 : idIBGE.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
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
		City other = (City) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idIBGE == null) {
			if (other.idIBGE != null)
				return false;
		} else if (!idIBGE.equals(other.idIBGE))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "City [id=" + id + ", name=" + name + ", idIBGE=" + idIBGE + ", state=" + state + "]";
	}


	public City(Long id, String name,  String idIBGE, State state) {
		super();
		this.id = id;
		this.name = name;
		this.idIBGE = idIBGE;
		this.state = state;
	}


	public City() {
		super();
	}

	
	
	
	
}
