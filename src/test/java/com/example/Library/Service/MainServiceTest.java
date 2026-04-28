package com.example.Library.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Library.Dto.BookResponse;
import com.example.Library.Dto.CreateBookRequestDto;
import com.example.Library.Entity.BookEntity;
import com.example.Library.Repository.BookRepository;
import com.example.Library.map.EntityMapper;

@ExtendWith(MockitoExtension.class)
class MainServiceTest {

    @Mock
    private BookRepository repository;

    @Mock
    private EntityMapper mapper;

    private MainService service;  // Не используем @InjectMocks

    @BeforeEach
    void setUp() {
        // Ручное внедрение зависимостей
        service = new MainService(repository, mapper);
    }

    @Test
    public void getBooksTest(){
        List<BookEntity> books = List.of(new BookEntity(), new BookEntity());

        when(repository.findAll()).thenReturn(books);

        List<BookEntity> result = service.getBooks();

        assertEquals(2, result.size());
        verify(repository).findAll();
    }

    @Test
    public void getBookByIdTest(){
        BookEntity book = new BookEntity();
        book.setId(1L);
        book.setLabel("label");
        book.setAuthor("author");
        book.setGenre("genre");
        book.setDescription("desc");

        BookResponse response = BookResponse.builder()
                .id(1L)
                .label("label")
                .author("author")
                .genre("genre")
                .description("desc")
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(book));
        when(mapper.toBookResponseDto(book)).thenReturn(response);

        BookResponse result = service.getBookById(1L);

        assertEquals(response, result);

        verify(repository).findById(1L);
        verify(mapper).toBookResponseDto(book);
    }

    @Test
    public void saveTest(){
        CreateBookRequestDto dto = CreateBookRequestDto.builder()
                .label("label")
                .author("author")
                .description("desc")
                .genre("genre")
                .build();

        BookEntity entity = new BookEntity();
        BookEntity saved = new BookEntity();
        saved.setId(1L);
        saved.setLabel("label");
        saved.setAuthor("author");
        saved.setGenre("genre");
        saved.setDescription("desc");

        BookResponse response = BookResponse.builder()
                .id(1L)
                .label("label")
                .author("author")
                .genre("genre")
                .description("desc")
                .build();

        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(saved);
        when(mapper.toBookResponseDto(saved)).thenReturn(response);

        BookResponse result = service.save(dto);

        assertEquals(response, result);

        verify(mapper).toEntity(dto);
        verify(repository).save(entity);
        verify(mapper).toBookResponseDto(saved);
    }
}