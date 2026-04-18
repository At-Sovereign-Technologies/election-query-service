package com.electoral.election_query_service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.electoral.election_query_service.dto.ElectionResponse;
import com.electoral.election_query_service.entity.Election;

class ElectionMapperTest {

    private final ElectionMapper mapper = new ElectionMapper();

    @Test
    void shouldMapEntityToResponse() {
        Election election = new Election();
        election.setId(1L);
        election.setName("Elección 2026");
        election.setStatus("ACTIVE");

        ElectionResponse response = mapper.toResponse(election);

        assertEquals(1L, response.getId());
        assertEquals("Elección 2026", response.getName());
        assertEquals("ACTIVE", response.getStatus());
    }
}