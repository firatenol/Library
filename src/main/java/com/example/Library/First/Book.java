package com.example.Library.First;

import java.time.LocalDateTime;
import java.util.Set;

public class Book {
    private final Long id;
    private final String label;
    private final String author;
    private final String isbn;
    private final Integer publicationYear;
    private final String publisher;
    private final String genre;
    private final Integer pageCount;
    private final String language;
    private final String description;
    private final Integer totalCopies;
    private final Integer availableCopies;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final Set<String> tags;

    public Book(String label, String author, String isbn, Integer publicationYear,
                String publisher, String genre, Integer pageCount, String language,
                String description, Integer totalCopies, Set<String> tags) {
        this(null, label, author, isbn, publicationYear, publisher, genre, 
             pageCount, language, description, totalCopies, totalCopies, 
             null, null, tags);
    }

    public Book(Long id, String label, String author, String isbn, 
                Integer publicationYear, String publisher, String genre,
                Integer pageCount, String language, String description,
                Integer totalCopies, Integer availableCopies,
                LocalDateTime createdAt, LocalDateTime updatedAt, Set<String> tags) {
        this.id = id;
        this.label = label;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.publisher = publisher;
        this.genre = genre;
        this.pageCount = pageCount;
        this.language = language;
        this.description = description;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.tags = tags;
    }

    public Long getId() { return id; }
    public String getLabel() { return label; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public Integer getPublicationYear() { return publicationYear; }
    public String getPublisher() { return publisher; }
    public String getGenre() { return genre; }
    public Integer getPageCount() { return pageCount; }
    public String getLanguage() { return language; }
    public String getDescription() { return description; }
    public Integer getTotalCopies() { return totalCopies; }
    public Integer getAvailableCopies() { return availableCopies; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public Set<String> getTags() { return tags; }
}