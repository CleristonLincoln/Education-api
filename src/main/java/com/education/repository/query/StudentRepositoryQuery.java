package com.education.repository.query;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.education.DTO.StudentPerSchool;
import com.education.model.people.Student;
import com.education.repository.filter.StudentFilter;
import com.education.repository.projection.StudentProjection;
import com.education.repository.projection.StudentProjectionShort;

public interface StudentRepositoryQuery {

	
	Page<Student> filter(StudentFilter studentFilter, Pageable pageable);
	
	Page<StudentProjection> shortFilter(StudentFilter studentFilter, Pageable pageable);

	List<StudentProjectionShort> filterStudentsPerSchool(Long id);

	public List<StudentPerSchool> reportStudentSchool(Long id);

}
