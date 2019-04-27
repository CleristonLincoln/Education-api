package com.education.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.education.model.Classroom;
import com.education.repository.ClassroomRepository;

@RestController
@RequestMapping("/classroom")
public class ClassroomResource {
	
	@Autowired private ClassroomRepository repository;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_GET_CLASSROOM') and #oauth2.hasScope('read')")
	public List<Classroom> getClassrooms(){
		return repository.findAll();
	}
	
}
