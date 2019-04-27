package com.education.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.model.score.Score;
import com.education.repository.ScoreRepository;

@Service
public class ScoreService {

	@Autowired private ScoreRepository repository;

	
	public Score saveScore(Score score) {

		Integer average = score.getScoreSemester().getSchoolYear().getScoreAverange();
	
		
		
		
		return repository.save(score);
	}


	
	// facilitar a digitação de notas pelos usuários.
	private Score scoreLaunch(Score score) {


		
		return  null;
		
		
	}
		
	
	
	private void scoreSituation(Score score, Integer scoreAverange) {

		

	}
	
	

}