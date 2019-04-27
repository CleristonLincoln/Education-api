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
import com.education.model.diary.DiaryClassroomSchoolTopic;
import com.education.model.diary.DiaryClassroomSchoolTopic_;
import com.education.model.diary.DiaryClassroomSchool_;
import com.education.repository.filter.DiaryClassroomSchoolTopicFilter;
import com.education.repository.projection.DiaryClassroomSchoolTopicProjection;
import com.education.repository.query.DiaryClassroomSchoolTopicRepositoryQuery;

public class DiaryClassroomSchoolTopicRepositoryImpl implements DiaryClassroomSchoolTopicRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<DiaryClassroomSchoolTopic> filter(DiaryClassroomSchoolTopicFilter diaryClassroomSchoolTopicFilter,
			Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<DiaryClassroomSchoolTopic> criteria = builder.createQuery(DiaryClassroomSchoolTopic.class);
		Root<DiaryClassroomSchoolTopic> root = criteria.from(DiaryClassroomSchoolTopic.class);

		Predicate[] predicates = createFilter(diaryClassroomSchoolTopicFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<DiaryClassroomSchoolTopic> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(diaryClassroomSchoolTopicFilter));
	}

	@Override
	public Page<DiaryClassroomSchoolTopicProjection> shortFilter(
			DiaryClassroomSchoolTopicFilter diaryClassroomSchoolTopicFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<DiaryClassroomSchoolTopicProjection> criteria = builder.createQuery(DiaryClassroomSchoolTopicProjection.class);
		Root<DiaryClassroomSchoolTopic> root = criteria.from(DiaryClassroomSchoolTopic.class);
		
		criteria.select(builder.construct(
				DiaryClassroomSchoolTopicProjection.class, 
					root.get(DiaryClassroomSchoolTopic_.id),
					root.get(DiaryClassroomSchoolTopic_.topic),
					root.get(DiaryClassroomSchoolTopic_.diaryClassroomSchool).get(DiaryClassroomSchool_.id),
					root.get(DiaryClassroomSchoolTopic_.diaryClassroomSchool).get(DiaryClassroomSchool_.classroomSchool).get(ClassroomSchool_.name)
				));

		Predicate[] predicates = createFilter(diaryClassroomSchoolTopicFilter, builder, root);
		criteria.where(predicates);
				
		TypedQuery<DiaryClassroomSchoolTopicProjection> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(diaryClassroomSchoolTopicFilter));
	}

	private Predicate[] createFilter(DiaryClassroomSchoolTopicFilter diaryClassroomSchoolTopicFilter,
			CriteriaBuilder builder, Root<DiaryClassroomSchoolTopic> root) {
		List<Pageable> predicates = new ArrayList<>();

		// TODO: restrictiorn here!

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private Long total(DiaryClassroomSchoolTopicFilter diaryClassroomSchoolTopicFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<DiaryClassroomSchoolTopic> root = criteria.from(DiaryClassroomSchoolTopic.class);
		
		Predicate[] predicates = createFilter(diaryClassroomSchoolTopicFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}

	private void addPageRestrict(TypedQuery<?> query, Pageable pageable) {

		int numeroPagina = pageable.getPageNumber();
		int totalPorPagina = pageable.getPageSize();
		int primeiroRegistro = numeroPagina * totalPorPagina;
		
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalPorPagina);
	
	
	}

}
