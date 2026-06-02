package com.resace.backend.dto;

public record TopicDto(
    Long id,
    String name,
    String slug,
    String paper,
    String description,
    long questionCount
) {}
