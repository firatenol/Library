package com.example.Library.First;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    
    List<BookEntity> findByIsActiveTrue();
    
    List<BookEntity> findByAuthorContainingIgnoreCase(String author);
    
    List<BookEntity> findByGenre(String genre);
    
    List<BookEntity> findByAvailableCopiesGreaterThan(Integer minCopies);
    
    @Query("SELECT b FROM BookEntity b WHERE " +
           "LOWER(b.label) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(b.author) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
           "LOWER(b.isbn) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<BookEntity> searchBooks(@Param("query") String query);
    
    boolean existsByIsbn(String isbn);
}