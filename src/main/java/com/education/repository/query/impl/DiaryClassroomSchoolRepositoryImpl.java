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

import com.education.model.ClassroomSchool_;
import com.education.model.diary.DiaryClassroomSchool;
import com.education.model.diary.DiaryClassroomSchool_;
import com.education.repository.filter.DiaryClassroomSchoolFilter;
import com.education.repository.projection.DiaryClassroomSchoolProjection;
import com.education.repository.query.DiaryClassroomSchoolRepositoryQuery;

public class DiaryClassroomSchoolRepositoryImpl implements DiaryClassroomSchoolRepositoryQuery {

	@PersistenceContext	private EntityManager manager;
	
	@Override
	public Page<DiaryClassroomSchool> filter(DiaryClassroomSchoolFilter diaryClassroomFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<DiaryClassroomSchool> criteria = builder.createQuery(DiaryClassroomSchool.class);
		Root<DiaryClassroomSchool> root = criteria.from(DiaryClassroomSchool.class);
		
		Predicate[] predicates = createFilter(diaryClassroomFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<DiaryClassroomSchool> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);
		
		
		return new PageImpl<>(query.getResultList(), pageable, total(diaryClassroomFilter));
	}
	
	
	@Override
	public Page<DiaryClassroomSchoolProjection> shortFilter(DiaryClassroomSchoolFilter diaryClassroomFilter,
			Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<DiaryClassroomSchoolProjection> criteria = builder.createQuery(DiaryClassroomSchoolProjection.class);
		Root<DiaryClassroomSchool> root = criteria.from(DiaryClassroomSchool.class);
		
		criteria.select(builder.construct(
				DiaryClassroomSchoolProjection.class, 
				root.get(DiaryClassroomSchool_.id),
				root.get(DiaryClassroomSchool_.dateDiary),
				root.get(DiaryClassroomSchool_.classroomSchool).get(ClassroomSchool_.id),
				root.get(DiaryClassroomSchool_.classroomSchool).get(ClassroomSchool_.name)
				
				));
		
		Predicate[] predicates = createFilter(diaryClassroomFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<DiaryClassroomSchoolProjection> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(diaryClassroomFilter));
	}


	private Predicate[] createFilter(DiaryClassroomSchoolFilter diaryClassroomFilter, CriteriaBuilder builder,
			Root<DiaryClassroomSchool> root) {

		List<Predicate> predicates = new ArrayList<>();
		
	
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	

	private Long total(DiaryClassroomSchoolFilter diaryClassroomFilter) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<DiaryClassroomSchool> root = criteria.from(DiaryClassroomSchool.class);
		
		Predicate[] predicates = createFilter(diaryClassroomFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}

		
	private void addPageRestrict(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);		
	}



}
