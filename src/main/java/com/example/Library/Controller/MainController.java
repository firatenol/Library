package com.example.Library.Controller;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import com.example.Library.Dto.BookResponse;
import com.example.Library.Dto.CreateBookRequestDto;
import com.example.Library.Entity.BookEntity;
import com.example.Library.Service.MainService;
import com.example.Library.map.EntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class MainController {
    private final MainService mainService;
    private final EntityMapper mapper;


    @GetMapping
    public List<BookResponse> getBooks(){
        log.info("Get all books");
        List<BookResponse> listBookResponses = new ArrayList<>();
        List<BookEntity> listEntityBooks = mainService.getBooks();
        for(BookEntity book : listEntityBooks){
            listBookResponses.add(mapper.toBookResponseDto(book));
        }
        return listBookResponses;
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable Long id) {
        log.info("Get book by id={}",id);
        BookEntity bookEntity = mainService.getBookById(id).orElseThrow(() -> new RuntimeException("Book id=" + id + " not found"));
        return mapper.toBookResponseDto(bookEntity);
    }

    @PostMapping
    public BookResponse saveBook(@RequestBody CreateBookRequestDto requestDto){
        log.info("Create new book");
        BookResponse bookResponse = mainService.save(requestDto);
        return bookResponse;
    }
    
}