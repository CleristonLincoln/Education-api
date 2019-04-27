package com.education.repository.projection;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TeacherProjection {

		private Long id;
		private String name;
		private String cpf;
		private String rg;
		
		private String numberHome;
		private String complementHome;
		private Long streetId;
		private String streetName;
		private Long neighborhoodId;
		private String neighborhoodName;
		private Long cityId;
		private String cityName;
		private Long stateId;
		private String stateName;
		
		
		public TeacherProjection(Long id, String name, String cpf, String rg, String numberHome, String complementHome,
				Long streetId, String streetName, Long neighborhoodId, String neighborhoodName, Long cityId,
				String cityName, Long stateId, String stateName) {
			this.id = id;
			this.name = name;
			this.cpf = cpf;
			this.rg = rg;
			this.numberHome = numberHome;
			this.complementHome = complementHome;
			this.streetId = streetId;
			this.streetName = streetName;
			this.neighborhoodId = neighborhoodId;
			this.neighborhoodName = neighborhoodName;
			this.cityId = cityId;
			this.cityName = cityName;
			this.stateId = stateId;
			this.stateName = stateName;
		}

}
