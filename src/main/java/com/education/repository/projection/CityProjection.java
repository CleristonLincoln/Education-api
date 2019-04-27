package com.education.repository.projection;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CityProjection {

	private Long id;
	private String name;
	
	private Long stateId;
	private String stateName;
	
	private Long countryId;
	private String coutryName;
	
	
	public CityProjection(Long id, String name, Long stateId, String stateName, Long countryId, String coutryName) {
		this.id = id;
		this.name = name;
		this.stateId = stateId;
		this.stateName = stateName;
		this.countryId = countryId;
		this.coutryName = coutryName;
	}
	
	

	
	
}
