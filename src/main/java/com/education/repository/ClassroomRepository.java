package com.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.model.Classroom;

public interface ClassroomRepository extends JpaRepository<Classroom, Long>{

}
