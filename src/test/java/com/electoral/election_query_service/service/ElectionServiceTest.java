package com.electoral.election_query_service.service;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import com.electoral.election_query_service.cache.RedisCacheAdapter;
import com.electoral.election_query_service.dto.ElectionResponse;
import com.electoral.election_query_service.entity.Election;
import com.electoral.election_query_service.exception.ResourceNotFoundException;
import com.electoral.election_query_service.mapper.ElectionMapper;
import com.electoral.election_query_service.repository.ElectionRepository;

@ExtendWith(MockitoExtension.class)
class ElectionServiceTest {

    @Mock
    private ElectionRepository repository;

    @Mock
    private RedisCacheAdapter cache;

    @Mock
    private ElectionMapper mapper;

    @InjectMocks
    private ElectionService service;

    private Election election;
    private ElectionResponse response;

    @BeforeEach
    void setup() {
        election = new Election();
        election.setId(1L);
        election.setName("Elección Test");

        response = new ElectionResponse();
        response.setId(1L);
        response.setName("Elección Test");
    }

    // GET ALL - CACHE HIT
    @Test
    void shouldReturnFromCacheWhenGetAll() {
        when(cache.get("elections:all")).thenReturn(List.of(response));

        List<ElectionResponse> result = service.getAll();

        assertEquals(1, result.size());
        verify(repository, never()).findAll();
    }

    // GET ALL - CACHE MISS
    @Test
    void shouldQueryDbAndStoreCacheWhenGetAll() {
        when(cache.get("elections:all")).thenReturn(null);
        when(repository.findAll()).thenReturn(List.of(election));
        when(mapper.toResponse(election)).thenReturn(response);

        List<ElectionResponse> result = service.getAll();

        assertEquals(1, result.size());

        verify(repository).findAll();
        verify(cache).set(eq("elections:all"), any());
    }

    // GET BY ID - CACHE HIT
    @Test
    void shouldReturnFromCacheWhenGetById() {
        when(cache.get("election:1")).thenReturn(response);

        ElectionResponse result = service.getById(1L);

        assertNotNull(result);
        verify(repository, never()).findById(any());
    }

    // GET BY ID - CACHE MISS
    @Test
    void shouldQueryDbAndStoreCacheWhenGetById() {
        when(cache.get("election:1")).thenReturn(null);
        when(repository.findById(1L)).thenReturn(Optional.of(election));
        when(mapper.toResponse(election)).thenReturn(response);

        ElectionResponse result = service.getById(1L);

        assertNotNull(result);

        verify(repository).findById(1L);
        verify(cache).set("election:1", response);
    }

    // NOT FOUND
    @Test
    void shouldThrowExceptionWhenElectionNotFound() {
        when(cache.get("election:1")).thenReturn(null);
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            service.getById(1L);
        });
    }
}