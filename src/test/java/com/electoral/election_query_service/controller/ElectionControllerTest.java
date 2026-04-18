package com.electoral.election_query_service.controller;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.electoral.election_query_service.dto.ElectionResponse;
import com.electoral.election_query_service.exception.ResourceNotFoundException;
import com.electoral.election_query_service.service.ElectionService;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ElectionController.class)
class ElectionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ElectionService service;

    @Test
    void shouldReturnAllElections() throws Exception {
        ElectionResponse res = new ElectionResponse();
        res.setId(1L);

        when(service.getAll()).thenReturn(List.of(res));

        mockMvc.perform(get("/api/v1/elections"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void shouldReturnElectionById() throws Exception {
        ElectionResponse res = new ElectionResponse();
        res.setId(1L);

        when(service.getById(1L)).thenReturn(res);

        mockMvc.perform(get("/api/v1/elections/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void shouldReturn404WhenNotFound() throws Exception {
        when(service.getById(1L))
                .thenThrow(new ResourceNotFoundException("Not found"));

        mockMvc.perform(get("/api/v1/elections/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturn400WhenIdIsInvalid() throws Exception {
        mockMvc.perform(get("/api/v1/elections/abc"))
                .andExpect(status().isBadRequest());
    }
}