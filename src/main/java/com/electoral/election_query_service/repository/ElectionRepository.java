package com.electoral.election_query_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electoral.election_query_service.entity.Election;

public interface ElectionRepository extends JpaRepository<Election, Long> {
}