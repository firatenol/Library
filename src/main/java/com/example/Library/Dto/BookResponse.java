package com.example.Library.Dto;

import lombok.Builder;

@Builder
public record BookResponse(
    Long id,
    String label,
    String author,
    String genre,
    String description    
)
    {
}
