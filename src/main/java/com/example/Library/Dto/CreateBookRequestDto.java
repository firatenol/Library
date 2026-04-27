package com.example.Library.Dto;

import lombok.Builder;

@Builder
public record CreateBookRequestDto(
    String label,
    String author,
    String description,
    String genre
) {
} 
