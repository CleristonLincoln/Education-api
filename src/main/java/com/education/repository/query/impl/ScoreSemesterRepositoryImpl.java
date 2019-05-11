package com.education.repository.query.impl;

import java.time.LocalDate;
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

import com.education.model.people.School_;
import com.education.model.score.SchoolYear_;
import com.education.model.score.ScoreSemester;
import com.education.model.score.ScoreSemester_;
import com.education.repository.filter.ScoreSemesterFilter;
import com.education.repository.projection.ScoreSemesterProjection;
import com.education.repository.query.ScoreSemesterRepositoryQuery;

public class ScoreSemesterRepositoryImpl implements ScoreSemesterRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<ScoreSemester> filterScoreSemester(ScoreSemesterFilter scoreSemesterFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ScoreSemester> criteria = builder.createQuery(ScoreSemester.class);

		Root<ScoreSemester> root = criteria.from(ScoreSemester.class);
		
		
		

		Predicate[] predicates = createFilter(scoreSemesterFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ScoreSemester> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(scoreSemesterFilter));

	}



	@Override
	public Page<ScoreSemesterProjection> shortFilterScoreSemester(ScoreSemesterFilter scoreSemesterFilter,
			Pageable pageable) {
	
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ScoreSemesterProjection> criteria = builder.createQuery(ScoreSemesterProjection.class);

		Root<ScoreSemester> root = criteria.from(ScoreSemester.class);
		
		criteria.select(builder.construct(
				ScoreSemesterProjection.class
				, root.get(ScoreSemester_.id)
				, root.get(ScoreSemester_.name)
				, root.get(ScoreSemester_.active)
				, root.get(ScoreSemester_.dateGenerateScore)
				, root.get(ScoreSemester_.schoolYear).get(SchoolYear_.id)
				, root.get(ScoreSemester_.schoolYear).get(SchoolYear_.currentYear)
				,root.get(ScoreSemester_.schoolYear).get(SchoolYear_.school).get(School_.id)
				,root.get(ScoreSemester_.schoolYear).get(SchoolYear_.school).get(School_.nameSchool)
				));
		
		Predicate[] predicates = createFilter(scoreSemesterFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ScoreSemesterProjection> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(scoreSemesterFilter));
	}
	
	
	private Predicate[] createFilter(ScoreSemesterFilter scoreSemesterFilter, CriteriaBuilder builder,
			Root<ScoreSemester> root) {

		List<Predicate> predicates = new ArrayList<>();

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	
	private void addPageRestrict(TypedQuery<?> query, Pageable pageable) {

		int pageNumber = pageable.getPageNumber();
		int itensPage = pageable.getPageSize();
		int firstResult = pageNumber * itensPage;
		
		query.setFirstResult(firstResult);
		query.setMaxResults(itensPage);
	}
	
	
	private Long total(ScoreSemesterFilter scoreSemesterFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);

		Root<ScoreSemester> root = criteria.from(ScoreSemester.class);
		
		Predicate[] predicates = createFilter(scoreSemesterFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}

	

}
