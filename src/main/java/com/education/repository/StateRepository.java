package com.education.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.model.adress.Country;
import com.education.model.adress.State;

public interface StateRepository extends JpaRepository<State, Long>{

	List<State> findByCountry(Country id);

}
