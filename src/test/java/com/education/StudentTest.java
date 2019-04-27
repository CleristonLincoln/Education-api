package com.education;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.education.model.people.Student;
import com.education.repository.StudentRepository;

public class StudentTest extends EducationApiApplicationTests {

	@Autowired TestEntityManager entityManager;
	@Autowired private StudentRepository repository;

	@Test
	public void postStudent() {
		student1();
		
	}

	public void student1() {
		Student st = new Student();
		
		st.setId(1L);
		st.setName("Nome de Teste");
		repository.save(st);

	}
}
