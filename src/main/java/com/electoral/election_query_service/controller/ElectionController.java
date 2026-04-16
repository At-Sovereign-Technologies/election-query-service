package com.electoral.election_query_service.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electoral.election_query_service.dto.ElectionResponse;
import com.electoral.election_query_service.service.ElectionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/elections")
@RequiredArgsConstructor
@Validated
public class ElectionController {

    private final ElectionService service;

    @GetMapping
    @Operation(summary = "Get all elections")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response")
    })
    public List<ElectionResponse> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get election by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful response"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Election not found")
    })
    public ElectionResponse getById(
            @PathVariable
            @Pattern(regexp = "^[0-9]+$", message = "id must be numeric")
            String id
    ) {
        return service.getById(Long.parseLong(id));
    }
}