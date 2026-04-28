package com.example.Library.Service;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Library.Dto.BookResponse;
import com.example.Library.Dto.CreateBookRequestDto;
import com.example.Library.Entity.BookEntity;
import com.example.Library.Repository.BookRepository;
import com.example.Library.map.EntityMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MainService {
    private final BookRepository repository;
    private final EntityMapper mapper;

    @Cacheable("library:books")
    public BookResponse getBookById(Long id) {  
        var entity = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return mapper.toBookResponseDto(entity);
    }

    public List<BookEntity> getBooks() {
        return repository.findAll();
    }

    public BookResponse save(CreateBookRequestDto dto) {
    BookEntity entity = mapper.toEntity(dto);
    BookEntity saved = repository.save(entity);
    return mapper.toBookResponseDto(saved);
    }
}