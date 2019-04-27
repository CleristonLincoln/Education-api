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

import com.education.model.ClassroomSchool_;
import com.education.model.Lesson_;
import com.education.model.people.School_;
import com.education.model.people.Student_;
import com.education.model.people.Teacher_;
import com.education.model.score.Score;
import com.education.model.score.ScoreSemester_;
import com.education.model.score.Score_;
import com.education.repository.filter.ScoreFilter;
import com.education.repository.projection.ScoreProjection;
import com.education.repository.query.ScoreRepositoryQuery;

public class ScoreRepositoryImpl implements ScoreRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public Page<Score> filtrar(ScoreFilter scoreFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Score> criteria = builder.createQuery(Score.class);
		Root<Score> root = criteria.from(Score.class);
		
		Predicate[] predicates = createFilter(scoreFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Score> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(scoreFilter));
	}

	
	@Override
	public Page<ScoreProjection> filterShort(ScoreFilter scoreFilter, Pageable pageable) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ScoreProjection> criteria = builder.createQuery(ScoreProjection.class);
		Root<Score> root = criteria.from(Score.class);

		criteria.select(builder.construct(
				ScoreProjection.class,
				root.get(Score_.id),
				root.get(Score_.initialScore),
				root.get(Score_.active),
				root.get(Score_.student).get(Student_.id),
				root.get(Score_.student).get(Student_.name),
				root.get(Score_.lesson).get(Lesson_.name),
				root.get(Score_.teacher).get(Teacher_.name),
				root.get(Score_.classroomSchool).get(ClassroomSchool_.name),
				root.get(Score_.classroomSchool).get(ClassroomSchool_.school).get(School_.id),
				root.get(Score_.classroomSchool).get(ClassroomSchool_.school).get(School_.nameSchool)
				));
		
		Predicate[] predicates = createFilter(scoreFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ScoreProjection> query = manager.createQuery(criteria);
		addPageRestrict(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(scoreFilter));
	}

	
	private Predicate[] createFilter(ScoreFilter scoreFilter, CriteriaBuilder builder, Root<Score> root) {

		List<Predicate> predicates = new ArrayList<>();
		
		if (!StringUtils.isEmpty(scoreFilter.getScore())) {
			predicates.add(builder.equal(root.get(Score_.finalScore),scoreFilter.getScore()));
		}
		
		if (!StringUtils.isEmpty(scoreFilter.getScoreFrom())) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(Score_.finalScore),scoreFilter.getScoreFrom()));
		}
		
		if (!StringUtils.isEmpty(scoreFilter.getScoreTo())) {
			predicates.add(builder.lessThanOrEqualTo(root.get(Score_.finalScore),scoreFilter.getScoreTo()));
		}
		
		if (!StringUtils.isEmpty(scoreFilter.getDateScore())) {
			predicates.add(builder.equal(root.get(Score_.dateScore), scoreFilter.getDateScore()));
		}

		if (!StringUtils.isEmpty(scoreFilter.getStudentId())) {
			predicates.add(builder.equal(root.get(Score_.student).get(Student_.id), scoreFilter.getStudentId()));
		}
		
		if (!StringUtils.isEmpty(scoreFilter.getTeacherId())) {
			predicates.add(builder.equal(root.get(Score_.teacher).get(Teacher_.id), scoreFilter.getTeacherId()));
		}
		
		if (!StringUtils.isEmpty(scoreFilter.getLessonId())) {
			predicates.add(builder.equal(root.get(Score_.lesson).get(Lesson_.id), scoreFilter.getLessonId()));
		}
		
		if (!StringUtils.isEmpty(scoreFilter.getSemestreId())) {
			predicates.add(builder.equal(root.get(Score_.scoreSemester).get(ScoreSemester_.id), scoreFilter.getSemestreId()));
		}
	

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	
	private void addPageRestrict(TypedQuery<?> query, Pageable pageable) {
	
		int paginaAtual = pageable.getPageNumber();
		int totalRegistros = pageable.getPageSize();
		
		int primeiroRegistro = paginaAtual * totalRegistros;
		
		query.setFirstResult(primeiroRegistro);
		query.setMaxResults(totalRegistros);
	}
	
	
	private Long total(ScoreFilter scoreFilter) {

		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Score> root = criteria.from(Score.class);
		
		Predicate[] predicates = createFilter(scoreFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}
	
	
}
