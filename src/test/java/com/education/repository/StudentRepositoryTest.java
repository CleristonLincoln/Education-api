package com.education.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.education.model.people.Student;
import com.education.resource.StudentResource;
import com.education.service.StudentService;

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
//@Sql(value = "/load_database_test.sql")
//@AutoConfigureTestDatabase
public class StudentRepositoryTest {

	@Autowired private StudentRepository repository;
	@Autowired private StudentResource resource;
	@Autowired private StudentService service;
	
	@Rule public ExpectedException exception = ExpectedException.none();
	
	
	
	@Before
	public void loadData() {
		Student student = new Student("testde o nome de um aluno", "nome-curto", "77703015059", 
				"984161891", "aluno1@email.com", 
				false, "88888888888", "99999999999", null, false, "54", 
				null, true, "9841665194194149411");
		
		service.saveStudent(student);
		this.repository.save(student);
	}
	
	@Test
	public void verifyIfCreateIdIfPost() {
				
		Student getId = repository.findById(1L).get();
		
		assertThat(getId.getId()).isEqualTo(1);
	}
	
	
	@Test
	public void deleteStudent() {
		assertThat(repository.findById(1L)).isNotEmpty();
		repository.deleteById(1L);
		
		assertThat(repository.findById(1L)).isEmpty();
		
		
		
	}
	
}
