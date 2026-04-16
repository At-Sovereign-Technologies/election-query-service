package com.electoral.election_query_service.dto;

import lombok.Data;

@Data
public class ElectionResponse {
    private Long id;
    private String name;
    private String status;
}