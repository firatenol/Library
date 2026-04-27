package com.example.Library.Service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Library.Dto.BookResponse;
import com.example.Library.Dto.CreateBookRequestDto;
import com.example.Library.Entity.BookEntity;
import com.example.Library.Repository.BookRepository;
import com.example.Library.map.EntityMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MainService {
    private final BookRepository repository;
    private final EntityMapper mapper;

    public Optional<BookEntity> getBookById(Long id) {  
        return repository.findById(id);
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