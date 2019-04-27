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

import com.education.model.Presence;
import com.education.model.Presence_;
import com.education.repository.filter.PresenceFilter;
import com.education.repository.projection.PresenceProjection;
import com.education.repository.query.PresenceRepositoryQuery;

public class PresenceRepositoryImpl implements PresenceRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Presence> filter(PresenceFilter presenceFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Presence> criteria = builder.createQuery(Presence.class);
		Root<Presence> root = criteria.from(Presence.class);

		Predicate[] predicates = createFilter(presenceFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Presence> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(presenceFilter));
	}

	@Override
	public Page<PresenceProjection> shortFilter(PresenceFilter presenceFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<PresenceProjection> criteria = builder.createQuery(PresenceProjection.class);
		Root<Presence> root = criteria.from(Presence.class);

		criteria.select(builder.construct(PresenceProjection.class, root.get(Presence_.id)));

		Predicate[] predicates = createFilter(presenceFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<PresenceProjection> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(presenceFilter));
	}

	private Predicate[] createFilter(PresenceFilter presenceFilter, CriteriaBuilder builder, Root<Presence> root) {

		List<Predicate> predicates = new ArrayList<>();
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void addPageRestrict(TypedQuery<?> query, Pageable pageable) {
		int primeiraAtual = pageable.getPageNumber();
		int totalRegistrosPagina = pageable.getPageSize();
		
		int primeiroRegistro = primeiraAtual * totalRegistrosPagina;
	
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalRegistrosPagina);
	
	
	}

	private Long total(PresenceFilter presenceFilter) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Presence> root = criteria.from(Presence.class);
		
		Predicate[] predicates = createFilter(presenceFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}

}
