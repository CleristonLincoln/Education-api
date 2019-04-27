package com.education.repository.filter;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StreetFilter {

	private String name;
	
	private Long neightborhoodId;
	private String neightborhoodName;
	
	private Long cityId;
	private String cityName;
	
	private Long stateId;
	private String stateName;
	private String stateShortName;
	
}
