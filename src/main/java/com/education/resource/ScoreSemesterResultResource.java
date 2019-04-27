package com.education.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.education.model.score.ScoreSemesterResult;
import com.education.repository.ScoreSemesterResultRepository;
import com.education.repository.filter.ScoreSemesterResultFilter;
import com.education.repository.projection.ScoreSemesterResultProjection;

@RestController
@RequestMapping("/scoresemesterresult")
public class ScoreSemesterResultResource {

	@Autowired private ScoreSemesterResultRepository repository;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_GET_SCORE_SEMESTER_RESULT') and #oauth2.hasScope('read')")
	public Page<ScoreSemesterResult> getFilterScoreSemesterResult(Pageable pageable, ScoreSemesterResultFilter scoreSemesterResultFilter) {
		return repository.filter(pageable, scoreSemesterResultFilter);
	}

	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_GET_SCORE_SEMESTER_RESULT') and #oauth2.hasScope('read')")
	public Page<ScoreSemesterResultProjection> getShortFilterScoreSemesterResult(Pageable pageable,
			ScoreSemesterResultFilter scoreSemesterResultFilter) {
		return repository.shortFilter(pageable, scoreSemesterResultFilter);
	}
	
	// alterar o resultado para que seja possivel fazer um novo calculo de recuperação
	
}





