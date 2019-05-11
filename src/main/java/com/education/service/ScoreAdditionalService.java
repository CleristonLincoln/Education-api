package com.education.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.model.score.ScoreAdditional;
import com.education.repository.ScoreAdditionalRepository;
import com.education.service.exception.LessonException;
import com.education.service.exception.SchoolInexstOrInative;
import com.education.service.exception.StudentInexixtentException;
import com.education.service.exception.TeacherIsExist;
import com.education.service.exception.TypeScoreException;

@Service
public class ScoreAdditionalService {
	
	@Autowired private ScoreAdditionalRepository repository;

	public ScoreAdditional saving(ScoreAdditional scoreAdditional) {

		if (scoreAdditional.getStudent().getId() == null)
			throw new StudentInexixtentException();
		
		if (scoreAdditional.getLesson().getId() == null)
			throw new LessonException();
		
		if (scoreAdditional.getTeacher().getId() == null)
			throw new TeacherIsExist();
			
		if (scoreAdditional.getTypeScore().getId() == null)
			throw new TypeScoreException();
			
		if (scoreAdditional.getSchool().getId() == null)
			throw new SchoolInexstOrInative();

		
		return repository.save(scoreAdditional);
	}

	
	
	
	
}
