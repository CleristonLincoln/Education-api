package com.education.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.education.model.adress.Neighborhood;
import com.education.model.adress.Street;
import com.education.repository.NeighborhoodRepository;
import com.education.repository.StreetRepository;
import com.education.service.exception.StreetException;
import com.education.service.exception.StreetIdNeighborException;

@Service
public class StreetService {

	@Autowired
	private StreetRepository repository;
	@Autowired
	private NeighborhoodRepository neighborhoodRepository;

	public Street saving(@Valid Street entity) {

		validIfCepExist(entity);
		validIfidNeighborExist(entity);

		return repository.save(entity);
	}

	private void validIfidNeighborExist(Street entity) {
		Long idNeighborhood = entity.getNeighborhood().getId();
		
		Optional<Neighborhood> idNeighbor = neighborhoodRepository.findById(idNeighborhood);

		if (!idNeighbor.isPresent()) {
			throw new StreetIdNeighborException();
		}
	}

	private void validIfCepExist(Street entity) {
		String getCep = repository.findByCep(entity.getCep());

		if (getCep.isEmpty()) {
			throw new StreetException();
		}
	}

}
