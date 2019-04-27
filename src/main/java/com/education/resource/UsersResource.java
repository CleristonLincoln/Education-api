package com.education.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.education.model.people.Users;
import com.education.repository.UsersRepository;


@RestController
@RequestMapping("/users")
public class UsersResource {

	@Autowired private UsersRepository repository;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_GET_USERS') and #oauth2.hasScope('read')")
	public List<Users> getMethodName() {
		return repository.findAll();
	}

	
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_POST_USERS') and #oauth2.hasScope('write')")
	public ResponseEntity<Users> postMethodName(@RequestBody Users entity) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String pass = encoder.encode(entity.getPwd());
		
		entity.setPwd(pass);
		
		Users saved = repository.save(entity);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
}
