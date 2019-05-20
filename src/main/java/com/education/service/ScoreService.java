package com.education.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.model.score.Score;
import com.education.model.score.ScoreAdditional;
import com.education.model.score.TypeScore;
import com.education.repository.ScoreAdditionalRepository;
import com.education.repository.ScoreRepository;

@Service
public class ScoreService {

	@Autowired
	private ScoreRepository repository;
	@Autowired
	private ScoreAdditionalRepository additionalRepository;

	public Score saveScore(Score score) {

		// inclui os pontos a serem somados a nota
		includePoints(score);

		Long idStudent = score.getStudent().getId();

		return repository.save(score);
	}

	private void includePoints(Score score) {

		Integer ponts = 0;

		List<TypeScore> idsScoreAdditional = score.getListTypeScoreAdditional();

        //		se a lista for vazia insere 0 aos pontos
		if (idsScoreAdditional == null) {
		
			score.setPoints(0);
	
		} else {

			for (TypeScore typeScore : idsScoreAdditional) {

				ScoreAdditional ad = additionalRepository.findByTypeScoreId(typeScore.getId());
				Integer scoreAdditional = ad.getScore();
				ponts = scoreAdditional++;
			}

		}
	}

}