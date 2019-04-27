package com.education.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.education.model.Presence;
import com.education.repository.query.PresenceRepositoryQuery;

public interface PresenceRepository extends JpaRepository<Presence, Long>, PresenceRepositoryQuery {

}
