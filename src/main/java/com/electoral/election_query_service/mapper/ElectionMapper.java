package com.electoral.election_query_service.mapper;

import org.springframework.stereotype.Component;

import com.electoral.election_query_service.dto.ElectionResponse;
import com.electoral.election_query_service.entity.Election;

@Component
public class ElectionMapper {

    public ElectionResponse toResponse(Election e) {
        ElectionResponse res = new ElectionResponse();
        res.setId(e.getId());
        res.setName(e.getName());
        res.setStatus(e.getStatus());
        return res;
    }
}