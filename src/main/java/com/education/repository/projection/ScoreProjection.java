package com.education.repository.projection;

import lombok.Getter;

@Getter
public class ScoreProjection {
	
	private Long id;
	private Integer score;
	private Boolean active;
	private Long studentId;
	private String studentName;
	private String lessonName;  // nome da matéria
	private String teacherName; // nome do professor
	private String classroomScoolName;
	private Long classroomSchoolSchoolId;// id da escola a que a sala de aula pertence
	private String classroomSchoolSchoolName; // nome da escola a que a sala de aula pertence
	
	
	public ScoreProjection(Long id, Integer score, Boolean active, Long studentId, String studentName,
			String lessonName, String teacherName, String classroomScoolName, Long classroomSchoolSchoolId,
			String classroomSchoolSchoolName) {
		super();
		this.id = id;
		this.score = score;
		this.active = active;
		this.studentId = studentId;
		this.studentName = studentName;
		this.lessonName = lessonName;
		this.teacherName = teacherName;
		this.classroomScoolName = classroomScoolName;
		this.classroomSchoolSchoolId = classroomSchoolSchoolId;
		this.classroomSchoolSchoolName = classroomSchoolSchoolName;
	}

	
	

}



/*
   _____________________________FULL JSON RETURN_____________________________

{
        "id": 1,
        "score": 70,
        "active": true,
        "dateScore": "2018-09-27",
        "student": {
            "id": 1,
            "name": "Clériston Lincoln",
            "shortname": "clecle",
            "cpf": "00435534351",
            "rg": "2001098125779",
            "birthday": "1986-02-14",
            "numberRegister": "466352186954",
            "complementHome": null,
            "numberHome": "19",
            "street": {
                "id": 1,
                "name": "Travessa Prefeito Ozimo de Alencar Lima",
                "typeAdress": "Travessa",
                "cep": "60840460",
                "complement": null,
                "neighborhood": {
                    "id": 1,
                    "name": "Conscelheiro Estelita",
                    "city": {
                        "id": 1,
                        "name": "Baturité",
                        "idIBGE": "541726",
                        "state": {
                            "id": 1,
                            "name": "Ceará",
                            "shortname": "CE",
                            "country": {
                                "id": 1,
                                "name": "Brasil"
                            }
                        }
                    }
                }
            },
            "cityRegister": {
                "id": 1,
                "name": "Baturité",
                "idIBGE": "541726",
                "state": {
                    "id": 1,
                    "name": "Ceará",
                    "shortname": "CE",
                    "country": {
                        "id": 1,
                        "name": "Brasil"
                    }
                }
            }
        },
        "classroomSchool": {
            "id": 1,
            "name": "1ª Série A",
            "active": true,
            "yearScore": {
                "id": 1,
                "year": "2018-01-01",
                "dateStart": "2018-01-01",
                "dateFinish": "2018-12-21"
            },
            "school": {
                "id": 1,
                "nameCompany": "Instituto Educacional Joaquim Nogueira",
                "nameSocial": "INSA",
                "image": null,
                "cnpj": "13783097000106",
                "ie": "16549",
                "im": "695498",
                "complement": null,
                "numberHome": "1",
                "street": {
                    "id": 1,
                    "name": "Travessa Prefeito Ozimo de Alencar Lima",
                    "typeAdress": "Travessa",
                    "cep": "60840460",
                    "complement": null,
                    "neighborhood": {
                        "id": 1,
                        "name": "Conscelheiro Estelita",
                        "city": {
                            "id": 1,
                            "name": "Baturité",
                            "idIBGE": "541726",
                            "state": {
                                "id": 1,
                                "name": "Ceará",
                                "shortname": "CE",
                                "country": {
                                    "id": 1,
                                    "name": "Brasil"
                                }
                            }
                        }
                    }
                },
                "people": []
            },
            "classRoom": {
                "id": 1,
                "name": "1º Ano"
            }
        },
        "lesson": {
            "id": 2,
            "name": "Português"
        },
        "teacher": {
            "id": 1,
            "name": "Cristiane Andrade de Melo",
            "cpf": "72463805315",
            "rg": "2546875554",
            "complementHome": null,
            "numberHome": "399",
            "street": {
                "id": 1,
                "name": "Travessa Prefeito Ozimo de Alencar Lima",
                "typeAdress": "Travessa",
                "cep": "60840460",
                "complement": null,
                "neighborhood": {
                    "id": 1,
                    "name": "Conscelheiro Estelita",
                    "city": {
                        "id": 1,
                        "name": "Baturité",
                        "idIBGE": "541726",
                        "state": {
                            "id": 1,
                            "name": "Ceará",
                            "shortname": "CE",
                            "country": {
                                "id": 1,
                                "name": "Brasil"
                            }
                        }
                    }
                }
            },
            "school": [
                {
                    "id": 1,
                    "nameCompany": "Instituto Educacional Joaquim Nogueira",
                    "nameSocial": "INSA",
                    "image": null,
                    "cnpj": "13783097000106",
                    "ie": "16549",
                    "im": "695498",
                    "complement": null,
                    "numberHome": "1",
                    "street": {
                        "id": 1,
                        "name": "Travessa Prefeito Ozimo de Alencar Lima",
                        "typeAdress": "Travessa",
                        "cep": "60840460",
                        "complement": null,
                        "neighborhood": {
                            "id": 1,
                            "name": "Conscelheiro Estelita",
                            "city": {
                                "id": 1,
                                "name": "Baturité",
                                "idIBGE": "541726",
                                "state": {
                                    "id": 1,
                                    "name": "Ceará",
                                    "shortname": "CE",
                                    "country": {
                                        "id": 1,
                                        "name": "Brasil"
                                    }
                                }
                            }
                        }
                    },
                    "people": []
                }
            ]
        },
        "scoreSemester": {
            "id": 1,
            "name": "Semestre de test",
            "yearScore": {
                "id": 1,
                "year": "2018-01-01",
                "dateStart": "2018-01-01",
                "dateFinish": "2018-12-21"
            }
        },
        "school": {
            "id": 1,
            "nameCompany": "Instituto Educacional Joaquim Nogueira",
            "nameSocial": "INSA",
            "image": null,
            "cnpj": "13783097000106",
            "ie": "16549",
            "im": "695498",
            "complement": null,
            "numberHome": "1",
            "street": {
                "id": 1,
                "name": "Travessa Prefeito Ozimo de Alencar Lima",
                "typeAdress": "Travessa",
                "cep": "60840460",
                "complement": null,
                "neighborhood": {
                    "id": 1,
                    "name": "Conscelheiro Estelita",
                    "city": {
                        "id": 1,
                        "name": "Baturité",
                        "idIBGE": "541726",
                        "state": {
                            "id": 1,
                            "name": "Ceará",
                            "shortname": "CE",
                            "country": {
                                "id": 1,
                                "name": "Brasil"
                            }
                        }
                    }
                }
            },
            "people": []
        }
    }



*/