package com.education.repository.projection;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentProjection {

	private Long id;
	private String name;
	private String shortname;
	private String cpf;
	private LocalDate birthday;

	private String complementHome;
	private String numberHome;

	private Long streetId;
	private String streetName;

	private Long neighborhoodId;
	private String neighborhoodName;

	private Long cityId;
	private String cityName;

	private Long stateId;
	private String stateName;

	private Boolean active;

	public StudentProjection(Long id, String name, String shortName, String cpf, LocalDate birthday,
			String complementHome, String numberHome, Long streetId, String streetName, Long neighborhoodId,
			String neighborhoodName, Long cityId, String cityName, Long stateId, String stateName, Boolean active) {
		this.id = id;
		this.name = name;
		this.shortname = shortName;
		this.cpf = cpf;
		this.birthday = birthday;
		this.complementHome = complementHome;
		this.numberHome = numberHome;
		this.streetId = streetId;
		this.streetName = streetName;
		this.neighborhoodId = neighborhoodId;
		this.neighborhoodName = neighborhoodName;
		this.cityId = cityId;
		this.cityName = cityName;
		this.stateId = stateId;
		this.stateName = stateName;
		this.active = active;
	}

	
}
