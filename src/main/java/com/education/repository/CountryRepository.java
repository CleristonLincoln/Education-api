package com.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.model.adress.Country;

public interface CountryRepository extends JpaRepository<Country, Long>{

}
