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

import com.education.model.diary.DiaryStudent;
import com.education.model.diary.DiaryStudent_;
import com.education.model.people.Student_;
import com.education.repository.filter.DiaryStudentFilter;
import com.education.repository.projection.DiaryStudentProjection;
import com.education.repository.query.DiaryStudentRepositoryQuery;

public class DiaryStudentRepositoryImpl implements DiaryStudentRepositoryQuery{

	@PersistenceContext private EntityManager manager;
	
	@Override
	public Page<DiaryStudent> filterDiaryStudent(DiaryStudentFilter diaryStudentFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<DiaryStudent> criteria = builder.createQuery(DiaryStudent.class);
		Root<DiaryStudent> root = criteria.from(DiaryStudent.class); 
		
		Predicate[] predicates = createFilter(builder, diaryStudentFilter, root);
		criteria.where(predicates);
		
		TypedQuery<DiaryStudent> query = manager.createQuery(criteria);
		
		addPageRestrict(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(diaryStudentFilter));
	}


	@Override
	public Page<DiaryStudentProjection> shortFilterDiaryStudent(DiaryStudentFilter diaryStudentFilter,
			Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<DiaryStudentProjection> criteria = builder.createQuery(DiaryStudentProjection.class);
		Root<DiaryStudent> root = criteria.from(DiaryStudent.class); 
		
		criteria.select(builder.construct(DiaryStudentProjection.class, 
				root.get(DiaryStudent_.id),
				root.get(DiaryStudent_.topic),
				root.get(DiaryStudent_.dateDiary),
				root.get(DiaryStudent_.student).get(Student_.id),
				root.get(DiaryStudent_.student).get(Student_.name),
				root.get(DiaryStudent_.student).get(Student_.cpf)
				));
		
		
		Predicate[] predicates = createFilter(builder, diaryStudentFilter, root);
		criteria.where(predicates);
		
		TypedQuery<DiaryStudentProjection> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(diaryStudentFilter));
	}

	
	private Predicate[] createFilter(CriteriaBuilder builder, DiaryStudentFilter diaryStudentFilter, Root<DiaryStudent> root) {
		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(diaryStudentFilter.getTopic())) {
			predicates.add(
					builder.like(
							builder.lower(root.get(DiaryStudent_.topic)), 
							"%" + diaryStudentFilter.getTopic().toLowerCase() + "%"
							));
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	
	
	private Long total(DiaryStudentFilter diaryStudentFilter) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<DiaryStudent> root = criteria.from(DiaryStudent.class); 
		
		Predicate[] predicates = createFilter(builder, diaryStudentFilter, root);
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
