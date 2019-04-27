package com.education.repository.query.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.education.model.ClassroomSchool;
import com.education.model.ClassroomSchool_;
import com.education.model.Classroom_;
import com.education.model.people.School_;
import com.education.model.score.SchoolYear_;
import com.education.repository.filter.ClassroomSchoolFilter;
import com.education.repository.projection.ClassroomSchoolProjection;
import com.education.repository.projection.ClassroomSchoolStudentProjection;
import com.education.repository.query.ClassroomSchoolRepositoryQuery;

public class ClassroomSchoolRepositoryImpl implements ClassroomSchoolRepositoryQuery {

	@PersistenceContext
	EntityManager manager;

	@Override
	public Page<ClassroomSchool> filter(ClassroomSchoolFilter classroomSchoolFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ClassroomSchool> criteria = builder.createQuery(ClassroomSchool.class);
		Root<ClassroomSchool> root = criteria.from(ClassroomSchool.class);

		Predicate[] predicates = createFilter(builder, root, classroomSchoolFilter);
		criteria.where(predicates);

		TypedQuery<ClassroomSchool> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(classroomSchoolFilter));

	}

	@Override
	public Page<ClassroomSchoolProjection> shortFilter(ClassroomSchoolFilter classroomSchoolFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ClassroomSchoolProjection> criteria = builder.createQuery(ClassroomSchoolProjection.class);
		Root<ClassroomSchool> root = criteria.from(ClassroomSchool.class);

		criteria.select(builder.construct(ClassroomSchoolProjection.class,
				root.get(ClassroomSchool_.id),
				root.get(ClassroomSchool_.name),
				root.get(ClassroomSchool_.classroom).get(Classroom_.name),
				root.get(ClassroomSchool_.active),
				root.get(ClassroomSchool_.schoolYear).get(SchoolYear_.currentYear),
				root.get(ClassroomSchool_.school).get(School_.id),
				root.get(ClassroomSchool_.school).get(School_.nameSocial),
				root.get(ClassroomSchool_.school).get(School_.cnpj)
		));

		Predicate[] predicates = createFilter(builder, root, classroomSchoolFilter);
		criteria.where(predicates);

		TypedQuery<ClassroomSchoolProjection> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(classroomSchoolFilter));
	}

	private Predicate[] createFilter(CriteriaBuilder builder, Root<ClassroomSchool> root, ClassroomSchoolFilter classroomSchoolFilter) {
		
		List<Predicate> predicates = new ArrayList<>();

//		if (!StringUtils.isEmpty(classroomSchoolFilter.getName())) {
//			predicates.add(
//					builder.like(
//							builder.lower(
//									root.get(ClassroomSchool_.classRoom.getName())),
//										"%" + classroomSchoolFilter.getName().toLowerCase() + "%"
//								));
//		}
//		
		if (!StringUtils.isEmpty(classroomSchoolFilter.getClassroomId())) {
			predicates.add(
					builder.equal(
							root.get(ClassroomSchool_.classroom).get(Classroom_.id), 
							classroomSchoolFilter.getClassroomId()
							));
		}
		
		if (!StringUtils.isEmpty(classroomSchoolFilter.getSchoolId())) {
			predicates.add(
					builder.equal(
							root.get(ClassroomSchool_.school).get(School_.id), 
							classroomSchoolFilter.getSchoolId()
							));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private Long total(ClassroomSchoolFilter classroomSchoolFilter) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<ClassroomSchool> root = criteria.from(ClassroomSchool.class);

		Predicate[] predicates = createFilter(builder, root, classroomSchoolFilter);
		criteria.where(predicates);

		criteria.select(builder.count(root));

		return manager.createQuery(criteria).getSingleResult();
	}

	private void addPageRestrict(TypedQuery<?> query, Pageable pageable) {

		int paginaAtual = pageable.getPageNumber();
		int totalRegistros = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistros;

		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalRegistros);
	}

	@Override
	public Page<ClassroomSchoolStudentProjection> filterClassroomSchoolStudent(
			ClassroomSchoolFilter classroomSchoolFilter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ClassroomSchoolStudentProjection> criteria = builder.createQuery(ClassroomSchoolStudentProjection.class);
		Root<ClassroomSchool> root = criteria.from(ClassroomSchool.class);
		
		criteria.select(builder.construct(
				ClassroomSchoolStudentProjection.class,
				root.get(ClassroomSchool_.name),
				root.join(ClassroomSchool_.student)
				));
		
		Predicate[] predicates = createFilter(builder, root, classroomSchoolFilter);
		criteria.where(predicates);
		
		TypedQuery<ClassroomSchoolStudentProjection> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(classroomSchoolFilter));
	}

}
