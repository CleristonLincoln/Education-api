package com.education.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.model.people.School;
import com.education.repository.SchoolRepository;
import com.education.service.exception.SchoolInexstOrInative;

@Service
public class SchoolService {

	@Autowired private SchoolRepository repository;

	public School save(@Valid School school) {
		School SchoolSave = ifExistSchool(school);
		return repository.save(SchoolSave);
	}

	public School ifExistSchool(School school) {
	
		School ie = repository.findByIe(school.getIe());
		School cnpj = repository.findByCnpj(school.getCnpj());

		if (ie != null || cnpj != null) {
			throw new SchoolInexstOrInative();
		}

		return school;
	}

	
	public void deleteSchool(Long id) {
		repository.deleteById(id);
	}

}
