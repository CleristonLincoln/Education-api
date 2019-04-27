package com.education.repository.projection;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StreetProjection {

	private Long id;
	private String name;
	
	
	public StreetProjection(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	
	
	
}
