package com.education.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.education.model.score.Score;
import com.education.repository.filter.ScoreFilter;
import com.education.repository.projection.ScoreProjection;

public interface ScoreRepositoryQuery {
	
   Page<ScoreProjection> filterShort(ScoreFilter scoreFilter, Pageable pageable);
   Page<Score> filtrar(ScoreFilter scoreFilter, Pageable pageable);
   
}
