package com.education.repository.query.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.education.model.adress.Street_;
import com.education.model.people.School;
import com.education.model.people.School_;
import com.education.repository.filter.SchoolFilter;
import com.education.repository.projection.SchoolProjection;
import com.education.repository.query.SchoolRepositoryQuery;

public class SchoolRepositoryImpl implements SchoolRepositoryQuery {

	@Autowired
	private EntityManager manager;

	
	//private CommumMethods commumMethods;

	@Override
	public Page<School> filterEntity(SchoolFilter SchoolFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<School> criteria = builder.createQuery(School.class);

		Root<School> root = criteria.from(School.class);

		Predicate[] predicates = createFilter(SchoolFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<School> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(SchoolFilter));
	}


	@Override
	public Page<SchoolProjection> shortEntity(SchoolFilter SchoolFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<SchoolProjection> criteria = builder.createQuery(SchoolProjection.class);

		Root<School> root = criteria.from(School.class);
		
	
		criteria.select(builder.construct(SchoolProjection.class
				,root.get(School_.id)				
				,root.get(School_.nameSchool)
				,root.get(School_.nameSocial)
				,root.get(School_.cnpj)
				,root.get(School_.ie)
				,root.get(School_.im)
				,root.get(School_.complementHome)
				,root.get(School_.numberHome)
				,root.get(School_.street).get(Street_.id)
				,root.get(School_.street).get(Street_.name)
				));

		Predicate[] predicates = createFilter(SchoolFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<SchoolProjection> query = manager.createQuery(criteria);

		return new PageImpl<>(query.getResultList(), pageable, total(SchoolFilter));
	}

	
	private Predicate[] createFilter(SchoolFilter SchoolFilter, CriteriaBuilder builder, Root<School> root) {

		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(SchoolFilter.getNameCompany())) {
			predicates.add(builder.like(builder.lower(root.get(School_.nameSchool)),
					"%" + SchoolFilter.getNameCompany().toLowerCase() + "%"));
		}
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private Long total(SchoolFilter SchoolFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<School> root= criteria.from(School.class);
		
		Predicate[] predicates = createFilter(SchoolFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}
	
	

	private void addPageRestrict(TypedQuery<School> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalRegistrosPorPagina);
				
	}

}
