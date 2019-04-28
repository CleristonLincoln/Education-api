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

import com.education.model.adress.City_;
import com.education.model.adress.Neighborhood_;
import com.education.model.adress.State_;
import com.education.model.adress.Street_;
import com.education.model.people.Student;
import com.education.model.people.Student_;
import com.education.repository.filter.StudentFilter;
import com.education.repository.projection.StudentProjection;
import com.education.repository.query.StudentRepositoryQuery;

public class StudentRepositoryImpl implements StudentRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Student> filter(StudentFilter studentFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Student> criteria = builder.createQuery(Student.class);
		Root<Student> root = criteria.from(Student.class);

		Predicate[] predicates = createFilter(studentFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Student> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(studentFilter));
	}

	@Override
	public Page<StudentProjection> shortFilter(StudentFilter studentFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<StudentProjection> criteria = builder.createQuery(StudentProjection.class);
		Root<Student> root = criteria.from(Student.class);

		criteria.select(builder.construct(StudentProjection.class, root.get(Student_.id), root.get(Student_.name),
				root.get(Student_.shortname), root.get(Student_.cpf), root.get(Student_.birthday),

				root.get(Student_.complementHome), root.get(Student_.numberHome),

				root.get(Student_.street).get(Street_.id), root.get(Student_.street).get(Street_.name),

				root.get(Student_.street).get(Street_.neighborhood).get(Neighborhood_.id),
				root.get(Student_.street).get(Street_.neighborhood).get(Neighborhood_.name),

				root.get(Student_.street).get(Street_.neighborhood).get(Neighborhood_.city).get(City_.id),
				root.get(Student_.street).get(Street_.neighborhood).get(Neighborhood_.city).get(City_.name),

				root.get(Student_.street).get(Street_.neighborhood).get(Neighborhood_.city).get(City_.state)
						.get(State_.id),
				root.get(Student_.street).get(Street_.neighborhood).get(Neighborhood_.city).get(City_.state)
						.get(State_.name),
				root.get(Student_.active)));

		Predicate[] predicates = createFilter(studentFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<StudentProjection> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(studentFilter));
	}

	@Override
	public Page<StudentProjection> filterStudentsPerSchool(StudentFilter studentFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<StudentProjection> criteria = builder.createQuery(StudentProjection.class);
		Root<Student> root = criteria.from(Student.class);

		criteria.select(builder.construct(StudentProjection.class, root.get(Student_.id), root.get(Student_.name),
				root.get(Student_.shortname)));

		Predicate[] predicates = createFilter(studentFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<StudentProjection> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(studentFilter));
	}

	private Predicate[] createFilter(StudentFilter studentFilter, CriteriaBuilder builder, Root<Student> root) {
		List<Predicate> predicates = new ArrayList<>();

		// name
		if (!StringUtils.isEmpty(studentFilter.getName())) {
			predicates.add(builder.like(builder.lower(root.get(Student_.name)),
					"%" + studentFilter.getName().toLowerCase() + "%"));
		}

		// birthday
		if (!StringUtils.isEmpty(studentFilter.getBirthday())) {
			predicates.add(
					builder.equal(
							root.get(Student_.birthday), 
							studentFilter.getBirthday()));
		}
		
		// birthdayFrom
		if (!StringUtils.isEmpty(studentFilter)) {
			predicates.add(
					builder.greaterThanOrEqualTo(
							root.get(Student_.birthday), 
							studentFilter.getBirthdayFrom()));
		}
		
		// birthdayTo
		if (!StringUtils.isEmpty(studentFilter)) {
			predicates.add(
					builder.lessThanOrEqualTo(
							root.get(Student_.birthday), 
							studentFilter.getBirthdayTo()));
		}
		
		// stateId
		if (!StringUtils.isEmpty(studentFilter)) {
			predicates.add(
					builder.equal(
							root.get(Student_.street)
								.get(Street_.neighborhood)
								.get(Neighborhood_.city)
								.get(City_.state), 
							studentFilter.getStateId()));
			
		}
		
		// cityId
		if (!StringUtils.isEmpty(studentFilter)) {
			predicates.add(
					builder.equal(
							root.get(Student_.street)
								.get(Street_.neighborhood)
								.get(Neighborhood_.city),
							studentFilter.getCityId()));
		}
		
		// NeighborhoodId
		if (!StringUtils.isEmpty(studentFilter)) {
			predicates.add(
					builder.equal(
							root.get(Student_.street)
								.get(Street_.neighborhood), 
							studentFilter.getNeighborhoodId()));
		}
		
		// SchoolId
		if (!StringUtils.isEmpty(studentFilter)) {
			predicates.add(
						builder.equal(
								root.get(Student_.school), 
								studentFilter.getSchoolId()));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void addPageRestrict(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Long total(StudentFilter studentFilter) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);

		Root<Student> root = criteria.from(Student.class);

		Predicate[] predicates = createFilter(studentFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
