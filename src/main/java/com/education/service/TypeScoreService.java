package com.education.service;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.education.model.score.TypeScore;
import com.education.service.exception.ScoreException;

@Service
public class TypeScoreService {

	public TypeScore saving(@Valid TypeScore typeScore) {

		validIfScoreSemesterExist(typeScore);
		
		if (typeScore.getSumOrFinally() == false) {
			typeScore.setActive(false);
		} else {
			typeScore.setActive(true);
		}
		
		return typeScore;
	}

	private void validIfScoreSemesterExist(TypeScore typeScore) {
		Long idScoreSesmester = typeScore.getScoreSemester().getId();
		if (idScoreSesmester == null) {
			throw new ScoreException();
		}
	}

	
	
}
