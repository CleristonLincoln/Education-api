package com.education.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.model.people.School;
import com.education.model.score.SchoolYear;
import com.education.repository.SchoolRepository;
import com.education.repository.SchoolYearRepository;
import com.education.service.exception.SchoolIsExist;
import com.education.service.exception.SchoolYearException;

@Service
public class SchoolYearService {

	
	@Autowired private SchoolYearRepository repository;
	@Autowired private SchoolRepository schoolRepository;
	
	
	public SchoolYear saving(SchoolYear schoolYear) {
		
		Long idSchool = schoolYear.getSchool().getId();
		
		Optional<School> school = schoolRepository.findById(idSchool);
		
		schoolYear.setGenerateScore(false);
		schoolYear.setActive(true);
		
		if (!school.isPresent()) {
			throw new SchoolIsExist();
		}
		
		ferifyIfYearDupliced(schoolYear);
		
		return repository.save(schoolYear);
	}


	// verifica se já possui um ano letivo cadastrado e lança uma exeção.
	private void ferifyIfYearDupliced(SchoolYear schoolYear) {

		LocalDate data = schoolYear.getCurrentYear();
		Long idSchool = schoolYear.getSchool().getId();
		
		Optional<SchoolYear> search = repository.findByCurrentYearAndSchoolId(data, idSchool);
				System.out.println(search.toString());
		if (search.isPresent()) {
			throw new SchoolYearException();
		}
	}



	

	
	
	
}
