package com.education.service;

import org.springframework.stereotype.Service;

import com.education.model.score.ScoreAdditional;

@Service
public class ScoreAdditionalService {

	public ScoreAdditional saving(ScoreAdditional scoreAdditional) {

		Long idStudent = scoreAdditional.getStudent().getId();
		Long idLesson = scoreAdditional.getLesson().getId();
		Long idTeacher = scoreAdditional.getTeacher().getId();
		Long idTypeScore = scoreAdditional.getTypeScore().getId();
		Long idSchool = scoreAdditional.getSchool().getId();
		
		validIfStudentExist(idStudent);
		validIfLessonExist(idLesson);
		validIfTeacherExist(idTeacher);
		valisIfTeacherLesson(idLesson, idTeacher);
		validIfTypeScoreSxist(idTypeScore);
		validIfSchoolExist(idSchool);
		
		return null;
	}

	private void validIfSchoolExist(Long idSchool) {
		// TODO Auto-generated method stub
		
	}

	private void validIfTypeScoreSxist(Long idTypeScore) {
		// TODO Auto-generated method stub
		
	}

	private void valisIfTeacherLesson(Long idLesson, Long idTeacher) {
		// TODO Auto-generated method stub
		
	}

	private void validIfTeacherExist(Long idTeacher) {
		// TODO Auto-generated method stub
		
	}

	private void validIfLessonExist(Long idLesson) {
		// TODO Auto-generated method stub
		
	}

	private void validIfStudentExist(Long idStudent) {
		// TODO Auto-generated method stub
		
	}

	
	
}
