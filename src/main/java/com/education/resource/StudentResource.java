package com.education.resource;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.education.event.ResourceCreateEvent;
import com.education.exceptionHandle.SchoolExceptionHandle.Erro;
import com.education.model.people.Student;
import com.education.repository.StudentRepository;
import com.education.repository.filter.StudentFilter;
import com.education.repository.projection.StudentProjection;
import com.education.service.StudentService;
import com.education.service.exception.SchoolInexstOrInative;
import com.education.service.exception.StudentCpfException;
import com.education.service.exception.StudentRgException;

@RestController
@RequestMapping("/student")
public class StudentResource {

	@Autowired private StudentRepository repository;
	@Autowired private StudentService service;
	@Autowired private ApplicationEventPublisher publisher;

	
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_GET_STUDENT') and #oauth2.hasScoe('read')")
	public Page<Student> getFilterStudent(StudentFilter studentFilter, Pageable pageable) {
		return repository.filter(studentFilter, pageable);
	}

	@GetMapping(params = "resume")
	@PreAuthorize("hasAuthority('ROLE_GET_STUDENT') and #oauth2.hasScoe('read')")
	public Page<StudentProjection> getShortFilterStudent(StudentFilter studentFilter, Pageable pageable) {
		return repository.shortFilter(studentFilter, pageable);
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_GET_STUDENTID') and #oauth2.hasScoe('read')")
	public ResponseEntity<Optional<Student>> getStudentPerId(@PathVariable Long id) {
		Optional<Student> student = repository.findById(id);
		
		return student.isPresent() ? ResponseEntity.ok(student) : ResponseEntity.notFound().build();
	}
	
	
	@GetMapping(params = "shortresume")
	public Page<StudentProjection> getStudentsPerSchool(StudentFilter studentFilter, Pageable pageable) {
		return repository.filterStudentsPerSchool(studentFilter, pageable);
	}

	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_POST_STUDENT') and #oauth2.hasScoe('write')")
	public ResponseEntity<Student> postStudent(@Valid @RequestBody Student student, HttpServletResponse response) {
	
		Student saving = service.saveStudent(student);
		publisher.publishEvent(new ResourceCreateEvent(this, response, saving.getId()));

		return ResponseEntity.status(HttpStatus.CREATED).body(saving);
	}

	@PutMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_PUT_STUDENT') and #oauth2.hasScoe('write')")
	public ResponseEntity<Student> putStudent(@PathVariable Long id, @Valid @RequestBody Student student) {
	
		Student studentSave = service.putStudent(id, student);
		return ResponseEntity.ok(studentSave);
	}
	
	
	@PutMapping("active/{id}")
	@PreAuthorize("hasAuthority('ROLE_PUT_STUDENT') and #oauth2.hasScoe('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void putStudentActive(@PathVariable Long id, @Valid @RequestBody Boolean active) {
		service.putStudentActive(id, active);

	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_DELETE_STUDENT') and #oauth2.hasScoe('write')")
	public void deleteStudent(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	
	@ExceptionHandler({StudentCpfException.class})
	public ResponseEntity<List<Erro>> studentCpfIsExist( StudentCpfException ex){
		
		String messageUser = "Aluno já cadastrado com este CPF";
		String messageDeveloper = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDeveloper));
		return ResponseEntity.badRequest().body(erros);
	}

	@ExceptionHandler({StudentRgException.class})
	public ResponseEntity<List<Erro>> studentRgIsExist( StudentRgException ex) {
		
		String messageUser = "Aluno já cadastrado com este RG";
		String messageDeveloper = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDeveloper));
		return ResponseEntity.badRequest().body(erros);
	}
	
	@ExceptionHandler({SchoolInexstOrInative.class})
	public ResponseEntity<List<Erro>> schoolInexist( SchoolInexstOrInative ex) {
		String messageUser = "Escola não existe ou esta inativa";
		String messageDeveloper = ex.toString();
		
		List<Erro> erros = Arrays.asList(new Erro(messageUser, messageDeveloper));
		
		return ResponseEntity.badRequest().body(erros);
	}

}
