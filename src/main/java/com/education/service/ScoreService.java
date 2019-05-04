package com.education.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.model.score.Score;
import com.education.repository.ScoreRepository;

@Service
public class ScoreService {

	@Autowired private ScoreRepository repository;

	
	public Score saveScore(Score score) {

		return repository.save(score);
	}

	

}