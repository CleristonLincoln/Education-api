package com.education.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.model.people.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{

	public Optional<Users> findByUsername(String username);
	
}
