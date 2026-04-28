package com.example.Library.Controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.Library.Service.MainService;
import com.example.Library.map.EntityMapper;
import com.example.Library.Entity.BookEntity;
import com.example.Library.Dto.BookResponse;
import com.example.Library.Dto.CreateBookRequestDto;

@WebMvcTest(MainController.class)
class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MainService mainService;

    @MockBean
    private EntityMapper mapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getBooks_shouldReturnList() throws Exception {

        BookEntity entity1 = new BookEntity();
        BookEntity entity2 = new BookEntity();

        when(mainService.getBooks()).thenReturn(List.of(entity1, entity2));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));

        verify(mainService).getBooks();
    }

    @Test
    void getBookById_shouldReturnBook() throws Exception {

        BookResponse response = BookResponse.builder()
                .id(1L)
                .label("A")
                .author("Auth")
                .genre("G")
                .description("D")
                .build();

        when(mainService.getBookById(1L)).thenReturn(response);

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(mainService).getBookById(1L);
    }

    @Test
    void getBookById_shouldReturn404_whenNotFound() throws Exception {

        when(mainService.getBookById(1L))
                .thenThrow(new RuntimeException("Book not found"));

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveBook_shouldReturnCreatedBook() throws Exception {

        CreateBookRequestDto request = CreateBookRequestDto.builder()
                .label("A")
                .author("Auth")
                .description("D")
                .genre("G")
                .build();

        BookResponse response = BookResponse.builder()
                .id(1L)
                .label("A")
                .author("Auth")
                .genre("G")
                .description("D")
                .build();

        when(mainService.save(any(CreateBookRequestDto.class)))
                .thenReturn(response);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));

        verify(mainService).save(any(CreateBookRequestDto.class));
    }
}