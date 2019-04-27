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

import com.education.model.adress.Neighborhood;
import com.education.model.adress.Neighborhood_;
import com.education.repository.filter.NeighborhoodFilter;
import com.education.repository.projection.NeighborhoodProjection;
import com.education.repository.query.NeighborhoodRepositoryQuery;

public class NeighborhoodRepositoryImpl implements NeighborhoodRepositoryQuery {

	
	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public Page<Neighborhood> filtrar(NeighborhoodFilter neighborhoodFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Neighborhood> criteria = builder.createQuery(Neighborhood.class);
		Root<Neighborhood> root = criteria.from(Neighborhood.class);
		
		Predicate[] predicates = createFilter(neighborhoodFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Neighborhood> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(neighborhoodFilter));
	}

	
	@Override
	public Page<NeighborhoodProjection> shortEntity(NeighborhoodFilter neighborhoodFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<NeighborhoodProjection> criteria = builder.createQuery(NeighborhoodProjection.class);
		Root<Neighborhood> root = criteria.from(Neighborhood.class);
		
		criteria.select(builder.construct(NeighborhoodProjection.class
				,root.get(Neighborhood_.id)
				,root.get(Neighborhood_.name)
				));
		
		Predicate[] predicates = createFilter(neighborhoodFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<NeighborhoodProjection> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(neighborhoodFilter));

	}

	
	private Predicate[] createFilter(NeighborhoodFilter neighborhoodFilter, CriteriaBuilder builder,
			Root<Neighborhood> root) {

		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(neighborhoodFilter.getName())) {
			predicates.add(
					builder.like(
							builder.lower(root.get(Neighborhood_.name))
							, "%" + neighborhoodFilter.getName().toLowerCase() + "%")
					);
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

		
	private Long total(NeighborhoodFilter neighborhoodFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Neighborhood> root = criteria.from(Neighborhood.class);
		
		Predicate[] predicates = createFilter(neighborhoodFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}

	
	private void addPageRestrict(TypedQuery<?> query, Pageable pageable) {

		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistro = paginaAtual * totalRegistrosPorPagina;
		
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalRegistrosPorPagina);
	}
	
	
}
