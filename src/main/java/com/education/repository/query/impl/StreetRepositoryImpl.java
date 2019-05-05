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
import com.education.model.adress.Street;
import com.education.model.adress.Street_;
import com.education.repository.filter.StreetFilter;
import com.education.repository.projection.StreetProjection;
import com.education.repository.query.StreetRepositoryQuery;

public class StreetRepositoryImpl implements StreetRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public Page<Street> filter(StreetFilter streetFilter, Pageable pageable) {
			CriteriaBuilder builder = manager.getCriteriaBuilder();
			CriteriaQuery<Street> criteria = builder.createQuery(Street.class);
			Root<Street> root = criteria.from(Street.class);
			
			Predicate[] predicates = createFilter(streetFilter, builder, root);
			criteria.where(predicates);
			
			TypedQuery<Street> query = manager.createQuery(criteria);
			addPageRestrict(query, pageable);
			
		return new PageImpl<>(query.getResultList(), pageable, total(streetFilter));
	}
	

	@Override
	public Page<StreetProjection> shortFilter(StreetFilter streetFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<StreetProjection> criteria = builder.createQuery(StreetProjection.class);
		Root<Street> root = criteria.from(Street.class);
		
		criteria.select(builder.construct(
				StreetProjection.class,
				root.get(Street_.id),
				root.get(Street_.name)
				));
		
		
		Predicate[] predicates = createFilter(streetFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<StreetProjection> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);
				
		return new PageImpl<>(query.getResultList(), pageable, total(streetFilter));
	}

	private Predicate[] createFilter(StreetFilter streetFilter, CriteriaBuilder builder, Root<Street> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(streetFilter.getName())) {
			predicates.add(
					builder.like(
							builder.lower(root.get(Street_.name))
							, "%"+ streetFilter.getName().toLowerCase() + "%"));
		}

		
		if (!StringUtils.isEmpty(streetFilter.getNeightborhoodName())) {
			predicates.add(
					builder.like(
							builder.lower(root.get(Street_.neighborhood).get(Neighborhood_.name)),
							"%" + streetFilter.getNeightborhoodName().toLowerCase() + "%"
							));
		}
		
		
		if (!StringUtils.isEmpty(streetFilter.getCityName())) {
			predicates.add(
					builder.like(
							builder.lower(root.get(Street_.neighborhood).get(Neighborhood_.city).get(City_.name)), 
							"%" + streetFilter.getCityName().toLowerCase() + "%"
							));
		}
		
		
		if (!StringUtils.isEmpty(streetFilter.getStateShortName())) {
			predicates.add(
					builder.like(
							builder.lower(root.get(Street_.neighborhood).get(Neighborhood_.city).get(City_.state).get(State_.shortname)), 
							"%" + streetFilter.getStateShortName() + "%"
							));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
	private Long total(StreetFilter streetFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Street> root = criteria.from(Street.class);

		Predicate[] predicates = createFilter(streetFilter, builder, root);
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



}
