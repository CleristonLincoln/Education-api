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

import com.education.model.score.ScoreSemesterResult;
import com.education.model.score.ScoreSemesterResult_;
import com.education.repository.filter.ScoreSemesterResultFilter;
import com.education.repository.projection.ScoreSemesterResultProjection;
import com.education.repository.query.ScoreSemesterResultRepositoryQuery;

public class ScoreSemesterResultRepositoryImpl implements ScoreSemesterResultRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public Page<ScoreSemesterResult> filter(Pageable pageable, ScoreSemesterResultFilter scoreSemesterResultFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ScoreSemesterResult> criteria = builder.createQuery(ScoreSemesterResult.class);
		Root<ScoreSemesterResult> root = criteria.from(ScoreSemesterResult.class);
		
		Predicate[] predicates = createFilter(scoreSemesterResultFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ScoreSemesterResult> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(scoreSemesterResultFilter));
	}
	
	
	@Override
	public Page<ScoreSemesterResultProjection> shortFilter(Pageable pageable,
			ScoreSemesterResultFilter scoreSemesterResultFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ScoreSemesterResultProjection> criteria = builder.createQuery(ScoreSemesterResultProjection.class);
		Root<ScoreSemesterResult> root = criteria.from(ScoreSemesterResult.class);
		
			criteria.select(builder.construct(
					ScoreSemesterResultProjection.class, 
					root.get(ScoreSemesterResult_.id)
					));
		
		Predicate[] predicates = createFilter(scoreSemesterResultFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ScoreSemesterResultProjection> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(scoreSemesterResultFilter));
	}
	

	private Predicate[] createFilter(ScoreSemesterResultFilter scoreSemesterResultFilter, CriteriaBuilder builder,
			Root<ScoreSemesterResult> root) {
		List<Predicate> predicates = new ArrayList<>();

		
		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void addPageRestrict(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Long total(ScoreSemesterResultFilter scoreSemesterResultFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);

		Root<ScoreSemesterResult> root = criteria.from(ScoreSemesterResult.class);

		Predicate[] predicates = createFilter(scoreSemesterResultFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

	

}
