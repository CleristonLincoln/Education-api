package com.education.repository.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.education.model.ClassroomSchool;
import com.education.repository.filter.ClassroomSchoolFilter;
import com.education.repository.projection.ClassroomSchoolProjection;
import com.education.repository.projection.ClassroomSchoolStudentProjection;

public interface ClassroomSchoolRepositoryQuery {

	Page<ClassroomSchool> filter(ClassroomSchoolFilter classroomSchoolFilter, Pageable pageable);
	
	Page<ClassroomSchoolProjection> shortFilter(ClassroomSchoolFilter classroomSchoolFilter, Pageable pageable);

	Page<ClassroomSchoolStudentProjection> filterClassroomSchoolStudent(ClassroomSchoolFilter classroomSchoolFilter,
			Pageable pageable);
}
