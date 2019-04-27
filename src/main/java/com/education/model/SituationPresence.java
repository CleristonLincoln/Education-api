package com.education.model;

public enum SituationPresence {
	FALTA_JUSTIFICADA("Falta_justificada"), 
	FALTA_INJUSTIFICADA("Falta_injustificada");
	
	private final String situation;
	
	
	SituationPresence(String situation) {
		this.situation = situation;
	}
	
	public String getSituation() {
		return situation;
	}
	
	
}
