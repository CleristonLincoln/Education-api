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
import com.education.model.diary.DiaryTeacher;
import com.education.model.diary.DiaryTeacher_;
import com.education.model.people.Teacher_;
import com.education.repository.filter.DiaryTeacherFilter;
import com.education.repository.projection.DiaryTeacherProjection;
import com.education.repository.query.DiaryTeacherRepositoryQuery;

public class DiaryTeacherRepositoryImpl implements DiaryTeacherRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<DiaryTeacher> filter(DiaryTeacherFilter diaryTeacherFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<DiaryTeacher> criteria = builder.createQuery(DiaryTeacher.class);
		Root<DiaryTeacher> root = criteria.from(DiaryTeacher.class);

		Predicate[] predicates = createFilter(diaryTeacherFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<DiaryTeacher> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(diaryTeacherFilter));
	}

	@Override
	public Page<DiaryTeacherProjection> shortFilter(DiaryTeacherFilter diaryTeacherFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<DiaryTeacherProjection> criteria = builder.createQuery(DiaryTeacherProjection.class);
		Root<DiaryTeacher> root = criteria.from(DiaryTeacher.class);
		
		
		criteria.select(builder.construct(
				DiaryTeacherProjection.class, 
				root.get(DiaryTeacher_.id),
				root.get(DiaryTeacher_.teacher).get(Teacher_.id),
				root.get(DiaryTeacher_.teacher).get(Teacher_.name),
				root.get(DiaryTeacher_.dateFinish),
				root.get(DiaryTeacher_.classroomSchool).get(ClassroomSchool_.id),
				root.get(DiaryTeacher_.classroomSchool).get(ClassroomSchool_.name),
				root.get(DiaryTeacher_.active)
				));
		

		Predicate[] predicates = createFilter(diaryTeacherFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<DiaryTeacherProjection> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(diaryTeacherFilter));
	}
	
	private Predicate[] createFilter(DiaryTeacherFilter diaryTeacherFilter, CriteriaBuilder builder,
			Root<DiaryTeacher> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}


	private Long total(DiaryTeacherFilter diaryTeacherFilter) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<DiaryTeacher> root = criteria.from(DiaryTeacher.class);

		Predicate[] predicates = createFilter(diaryTeacherFilter, builder, root);
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
