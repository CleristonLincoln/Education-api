package com.education.model.adress;

public enum TypeAdress {

	RUA("Rua"),
	AVENIDA("Avenida"),
	PRACA("Praça");

	public final String type;
	
	TypeAdress(String type) {
		this.type = type;
	}

	
	public String getType() {
		return type;
	}

}
