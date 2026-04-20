package com.electoral.election_query_service.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.electoral.election_query_service.cache.RedisCacheAdapter;
import com.electoral.election_query_service.dto.ElectionResponse;
import com.electoral.election_query_service.entity.Election;
import com.electoral.election_query_service.exception.ResourceNotFoundException;
import com.electoral.election_query_service.mapper.ElectionMapper;
import com.electoral.election_query_service.repository.ElectionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ElectionService {

    private final ElectionRepository repository;
    private final RedisCacheAdapter cache;
    private final ElectionMapper mapper;

    private static final Logger log = LoggerFactory.getLogger(ElectionService.class);

    @SuppressWarnings("unchecked")
    public List<ElectionResponse> getAll() {

        String key = "elections:all";

        List<ElectionResponse> cached = (List<ElectionResponse>) cache.get(key);

        if (cached != null) {
            log.info("CACHE HIT - elections");
            return cached;
        }

        log.info("CACHE MISS - querying DB - elections");

        List<ElectionResponse> result = repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();

        cache.set(key, result);
        log.info("CACHE STORE - elections");

        return result;
    }

    public ElectionResponse getById(Long id) {

        String key = "election:" + id;

        ElectionResponse cached = (ElectionResponse) cache.get(key);

        if (cached != null) {
            log.info("CACHE HIT - election id={}", id);
            return cached;
        }

        log.info("CACHE MISS - querying DB - election id={}", id);

        Election election = repository.findById(id)
                .orElseThrow(() -> {
                    log.error("Election not found - id={}", id);
                    return new ResourceNotFoundException("Election not found");
                });

        ElectionResponse response = mapper.toResponse(election);

        cache.set(key, response);
        log.info("CACHE STORE - election id={}", id);

        return response;
    }
}