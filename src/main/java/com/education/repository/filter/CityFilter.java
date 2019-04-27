package com.education.repository.filter;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CityFilter {

	private Long id;
	private String name;
	private Long stateUf;
	private Long stateId;
}
