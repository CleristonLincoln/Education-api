package com.education.repository.projection;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SchoolProjection {
	
	
	private Long id;
	private String nameSchool;
	private String nameSocial;
	private String cnpj;
	private String ie;
	private String im;
	private String complementHome;
	private String numberHome;
	private Long streetId;
	private String streetName;
	
	
	
	public SchoolProjection(Long id, String nameSchool, String nameSocial, String cnpj, String ie, String im,
			String complementHome, String numberHome, Long streetId, String streetName) {
		this.id = id;
		this.nameSchool = nameSchool;
		this.nameSocial = nameSocial;
		this.cnpj = cnpj;
		this.ie = ie;
		this.im = im;
		this.complementHome = complementHome;
		this.numberHome = numberHome;
		this.streetId = streetId;
		this.streetName = streetName;
	}	
	
	
	
	


	
	
	

	
	

}
