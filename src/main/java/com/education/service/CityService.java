package com.education.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.model.adress.City;
import com.education.model.adress.State;
import com.education.repository.CityRepository;
import com.education.repository.StateRepository;
import com.education.service.exception.CityException;

@Service
public class CityService {
	
	
	@Autowired private CityRepository repository;
	@Autowired private StateRepository stateRepository;

	
	public City saving(@Valid City city) {

		Long idCity = city.getState().getId();
		Optional<State> findIdState = stateRepository.findById(idCity);

		if (!findIdState.isPresent()) {
			throw new CityException();
		}
		
		return repository.save(city);
	}

}
