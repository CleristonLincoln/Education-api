package com.education.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.education.model.adress.Country;
import com.education.repository.CountryRepository;

@RestController
@RequestMapping("/countrys")
public class CountryResource {

	@Autowired private CountryRepository repository;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_GET_COUTRY') and #oauth2.hasScope('read')")
	public List<Country> getCountries(){
		return repository.findAll();
	}
	
}
