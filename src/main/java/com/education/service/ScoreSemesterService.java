package com.education.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.model.Lesson;
import com.education.model.people.Student;
import com.education.model.score.SchoolYear;
import com.education.model.score.Score;
import com.education.model.score.ScoreSemester;
import com.education.model.score.ScoreSemesterResult;
import com.education.repository.ClassroomSchoolRepository;
import com.education.repository.LessonRepository;
import com.education.repository.SchoolYearRepository;
import com.education.repository.ScoreRepository;
import com.education.repository.ScoreSemesterRepository;
import com.education.repository.ScoreSemesterResultRepository;

@Service
public class ScoreSemesterService {

	@Autowired private ScoreSemesterRepository repository;
	@Autowired private ScoreSemesterResultRepository scoreSemesterResultRepository;
	@Autowired private ScoreRepository scoreRepository;
	@Autowired private LessonRepository lessonRepository;
	@Autowired private SchoolYearRepository schoolYearRespository;
	@Autowired private ClassroomSchoolRepository classroomSchoolRepository;

	public ScoreSemester save(@Valid ScoreSemester scoreSemester) {

		return null;
	}

	public void putPropertieResult(Long id, Boolean generateScore) {

		
		ScoreSemester scoreSemester = repository.findById(id).get();

		Long idScoreSemester = scoreSemester.getId();

		List<Student> listStudent = classroomSchoolRepository.findByStudent(id);
		List<Lesson> listLesson = lessonRepository.findAll();
		List<Score> listScore = scoreRepository.findByScoreSemesterId(idScoreSemester);

		if (generateScore == true) {
			generateAvagenge(listStudent, listLesson, listScore, scoreSemester);
		}
	}

	
	private void generateAvagenge(List<Student> listStudent, List<Lesson> listLesson, List<Score> listScore,
			ScoreSemester scoreSemester) {

		for (Student student : listStudent) {
			for (Lesson lesson : listLesson) {
				int nota = 0;
				int contaNota = 0;

				for (Score score : listScore) {
					if (score.getLesson().getId() == lesson.getId() && student.getId() == score.getStudent().getId()) {
					//	nota = nota + score.getScore();
						contaNota++;
					}
				} // for score

				// sertifica que não salve nota com valor zerado
				if(nota != 0) {
			
					Long y = scoreSemester.getSchoolYear().getId();
					SchoolYear schoolYear = schoolYearRespository.findById(y).get();

					int mediaCadastrada = schoolYear.getScoreAverange();

					int media = nota / contaNota;

					Boolean approvedOrNo = verifyIFApproved(mediaCadastrada, media);

					ScoreSemesterResult entity = setProperties(lesson, media, mediaCadastrada, approvedOrNo, scoreSemester,
							student);

					scoreSemesterResultRepository.save(entity);
					
				}
		

			} // for lesson
		} // for student
	}

	private ScoreSemesterResult setProperties(Lesson lesson, int media, int mediaCadastrada, Boolean approvedOrNo,
			ScoreSemester scoreSemester, Student student) {
		ScoreSemesterResult sc = new ScoreSemesterResult();
		sc.setScoreAvarenge(media);
		//sc.setScoreMin(mediaCadastrada);
		sc.setSituation(approvedOrNo);
		sc.setActive(true);
		sc.setLesson(lesson);
	//	sc.setSchool(scoreSemester.getSchool());
		sc.setStudent(student);
		sc.setScoreSemester(scoreSemester);

		return sc;
	}

	private Boolean verifyIFApproved(int mediaCadastrada, int media) {
		return media >= mediaCadastrada ? true : false;
	}

}
