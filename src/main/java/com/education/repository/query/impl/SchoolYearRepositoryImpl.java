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
import com.education.model.adress.Street_;
import com.education.model.people.School_;
import com.education.model.score.SchoolYear;
import com.education.model.score.SchoolYear_;
import com.education.repository.filter.SchoolYearFilter;
import com.education.repository.projection.SchoolYearProjection;
import com.education.repository.query.SchoolYearRepositoryQuery;

public class SchoolYearRepositoryImpl implements SchoolYearRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<SchoolYear> filterEntity(Pageable pageable, SchoolYearFilter schoolYearFilter) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<SchoolYear> criteria = builder.createQuery(SchoolYear.class);
		Root<SchoolYear> root = criteria.from(SchoolYear.class);

		Predicate[] predicates = createFilter(schoolYearFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<SchoolYear> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(schoolYearFilter));

	}

	@Override
	public Page<SchoolYearProjection> filterShortEntity(SchoolYearFilter schoolYearFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<SchoolYearProjection> criteria = builder.createQuery(SchoolYearProjection.class);
		Root<SchoolYear> root = criteria.from(SchoolYear.class);
		
		
		criteria.select(builder.construct(SchoolYearProjection.class, 
				root.get(SchoolYear_.id),
				root.get(SchoolYear_.currentYear),
				root.get(SchoolYear_.dateStart),
				root.get(SchoolYear_.dateFinish),
				root.get(SchoolYear_.scoreAverange),
				root.get(SchoolYear_.active)
				));
		
		
		Predicate[] predicates = createFilter(schoolYearFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<SchoolYearProjection> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(schoolYearFilter));
	}

	private Predicate[] createFilter(SchoolYearFilter schoolYearFilter, CriteriaBuilder builder,
			Root<SchoolYear> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(schoolYearFilter.getCurrentYear())) {
			predicates.add(
					builder.equal(
							root.get(SchoolYear_.currentYear), 
							schoolYearFilter.getCurrentYear()
							));
		}
		
		if (!StringUtils.isEmpty(schoolYearFilter.getDateStartFrom())) {
			predicates.add(
					builder.greaterThanOrEqualTo(
							root.get(SchoolYear_.dateStart), 
							schoolYearFilter.getDateStartFrom()
							));
		}
		
		if (!StringUtils.isEmpty(schoolYearFilter.getDateStartTo())) {
			predicates.add(
					builder.lessThanOrEqualTo(
							root.get(SchoolYear_.dateStart),
							schoolYearFilter.getDateStartTo()
							));
		}
	
		if (!StringUtils.isEmpty(schoolYearFilter.getDateFinishFrom())) {
			predicates.add(
					builder.greaterThanOrEqualTo(
							root.get(SchoolYear_.dateFinish), 
							schoolYearFilter.getDateFinishFrom()
							));
		}
		
		if (!StringUtils.isEmpty(schoolYearFilter.getDateFinishTo())) {
			predicates.add(
					builder.lessThanOrEqualTo(
							root.get(SchoolYear_.dateFinish), 
							schoolYearFilter.getDateFinishTo()
							));
		}
		
		if (!StringUtils.isEmpty(schoolYearFilter.getSchoolId())) {
			predicates.add(
					builder.equal(
							root.get(SchoolYear_.school).get(School_.id), 
							schoolYearFilter.getSchoolId()
							));
		}
		
		if (!StringUtils.isEmpty(schoolYearFilter.getSchoolCityId())) {
			predicates.add(
					builder.equal(
						root.get(SchoolYear_.school).get(School_.street)
							.get(Street_.neighborhood).get(Neighborhood_.city)
							.get(City_.id), 
						schoolYearFilter.getSchoolCityId()
					));
		}
	
		

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private Long total(SchoolYearFilter schoolYearFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<SchoolYear> root = criteria.from(SchoolYear.class);

		Predicate[] predicates = createFilter(schoolYearFilter, builder, root);
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
