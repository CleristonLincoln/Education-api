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

import com.education.model.adress.City;
import com.education.model.adress.City_;
import com.education.model.adress.Country_;
import com.education.model.adress.State_;
import com.education.repository.filter.CityFilter;
import com.education.repository.projection.CityProjection;
import com.education.repository.query.CityRepositoryQuery;

public class CityRepositoryImpl implements CityRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<City> filter(CityFilter cityFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<City> criteria = builder.createQuery(City.class);
		Root<City> root = criteria.from(City.class);

		Predicate[] predicates = createFilter(cityFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<City> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);
		return new PageImpl<>(query.getResultList(), pageable, total(cityFilter));
	}

	@Override
	public Page<CityProjection> shortFilter(CityFilter cityFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<CityProjection> criteria = builder.createQuery(CityProjection.class);
		Root<City> root = criteria.from(City.class);

		criteria.select(builder.construct(
				CityProjection.class, 
				root.get(City_.id), 
				root.get(City_.name),
				root.get(City_.state).get(State_.id), 
				root.get(City_.state).get(State_.name), 
				root.get(City_.state).get(State_.country).get(Country_.id),
				root.get(City_.state).get(State_.country).get(Country_.name)
				));

		Predicate[] predicates = createFilter(cityFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<CityProjection> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(cityFilter));
	}

	private Predicate[] createFilter(CityFilter cityFilter, CriteriaBuilder builder, Root<City> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(cityFilter.getName())) {
			predicates.add(
					builder.like(
							builder.lower(
									root.get(City_.name)), 
							"%" + cityFilter.getName().toLowerCase() + "%"
							));
		}
		
		if (!StringUtils.isEmpty(cityFilter.getStateUf())) {
			predicates.add(
					builder.like(
							builder.lower(root.get(City_.state).get(State_.shortname)),
							"%" + cityFilter.getStateUf() + "%"	
							));
		}
		
		if (!StringUtils.isEmpty(cityFilter.getStateId())) {
			predicates.add(
					builder.equal(
							root.get(City_.state).get(State_.id), 
							cityFilter.getStateId()
							));
		}
		

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private Long total(CityFilter cityFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<City> root = criteria.from(City.class);

		Predicate[] predicates = createFilter(cityFilter, builder, root);
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
