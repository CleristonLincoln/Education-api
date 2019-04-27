package com.education.repository.projection;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class NeighborhoodProjection {

	private Long id;
	private String name;
	
	
	public NeighborhoodProjection(Long id, String name) {
			this.id = id;
		this.name = name;
	}
	
	

	
}
