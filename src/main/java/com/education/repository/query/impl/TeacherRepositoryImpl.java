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
import com.education.model.people.Teacher;
import com.education.model.people.Teacher_;
import com.education.repository.filter.TeacherFilter;
import com.education.repository.projection.TeacherProjection;
import com.education.repository.query.TeacherRepositoryQuery;


public class TeacherRepositoryImpl implements TeacherRepositoryQuery{

	@PersistenceContext
	private  EntityManager manager;
	
	@Override
	public Page<Teacher> filter(TeacherFilter teacherFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Teacher> criteria = builder.createQuery(Teacher.class);
		Root<Teacher> root = criteria.from(Teacher.class);
		
		Predicate[] predicates = createFilter(teacherFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Teacher> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(teacherFilter));
	}

	@Override
	public Page<TeacherProjection> shortFilter(TeacherFilter teacherFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<TeacherProjection> criteria = builder.createQuery(TeacherProjection.class);
		Root<Teacher> root = criteria.from(Teacher.class);
		
		criteria.select(
				builder.construct(
				TeacherProjection.class,
				root.get(Teacher_.id),
				root.get(Teacher_.name),
				root.get(Teacher_.cpf),
				root.get(Teacher_.rg),
				root.get(Teacher_.numberHome),
				root.get(Teacher_.complementHome),
				root.get(Teacher_.street).get(Street_.id),
				root.get(Teacher_.street).get(Street_.name),
				root.get(Teacher_.street).get(Street_.neighborhood).get(Neighborhood_.id),
				root.get(Teacher_.street).get(Street_.neighborhood).get(Neighborhood_.name),
				root.get(Teacher_.street).get(Street_.neighborhood).get(Neighborhood_.city).get(City_.id),
				root.get(Teacher_.street).get(Street_.neighborhood).get(Neighborhood_.city).get(City_.name),
				root.get(Teacher_.street).get(Street_.neighborhood).get(Neighborhood_.city).get(City_.state).get(State_.id),
				root.get(Teacher_.street).get(Street_.neighborhood).get(Neighborhood_.city).get(City_.state).get(State_.name)
				));
		
		Predicate[] predicates = createFilter(teacherFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<TeacherProjection> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(teacherFilter));
	}
	
	private Predicate[] createFilter(TeacherFilter teacherFilter, CriteriaBuilder builder, Root<Teacher> root) {
	
		List<Predicate> predicates = new ArrayList<>();
		
				
		if (!StringUtils.isEmpty(teacherFilter.getName())) {
		predicates.add(
				builder.like( 
						builder.lower(root.get(Teacher_.name)), 
						"%" + teacherFilter.getName().toLowerCase() +"%"));
		
		}
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private Long total(TeacherFilter teacherFilter) {
	
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Teacher> root = criteria.from(Teacher.class);
		
		Predicate[] predicates = createFilter(teacherFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}

	private void addPageRestrict(TypedQuery<?> query, Pageable pageable) {
		
		int pageNumber = pageable.getPageNumber();
		int itensPagina = pageable.getPageSize();
		int firstResult = pageNumber * itensPagina;
		
		query.setFirstResult(firstResult);
		query.setMaxResults(itensPagina);
	}

	
}
