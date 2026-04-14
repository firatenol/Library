package com.example.Library.First;
import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;

@Service
@Transactional
public class MainService {
    private final BookRepository repository;

    public MainService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAllBooks() {
        return repository.findByIsActiveTrue().stream()
                .map(this::toDomain)
                .toList();
    }

    public List<Book> searchBooks(String query) {
        return repository.searchBooks(query).stream()
                .map(this::toDomain)
                .toList();
    }

    public Book getBookById(Long id) {
        BookEntity bookEntity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id=" + id));
        return toDomain(bookEntity);
    }

    public Book createBook(Book book) {
        if (book.getId() != null) {
            throw new IllegalArgumentException("ID must be null for new books");
        }
        
        validateBook(book);
        
        BookEntity bookEntity = toEntity(book);
        BookEntity saved = repository.save(bookEntity);
        return toDomain(saved);
    }

    public Book updateBook(Long id, Book bookToUpdate) {
        BookEntity existingBook = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id=" + id));
        validateBook(bookToUpdate);
        existingBook.setLabel(bookToUpdate.getLabel());
        existingBook.setAuthor(bookToUpdate.getAuthor());
        existingBook.setIsbn(bookToUpdate.getIsbn());
        existingBook.setPublicationYear(bookToUpdate.getPublicationYear());
        existingBook.setPublisher(bookToUpdate.getPublisher());
        existingBook.setGenre(bookToUpdate.getGenre());
        existingBook.setPageCount(bookToUpdate.getPageCount());
        existingBook.setLanguage(bookToUpdate.getLanguage());
        existingBook.setDescription(bookToUpdate.getDescription());
        existingBook.setTotalCopies(bookToUpdate.getTotalCopies());
        existingBook.setTags(bookToUpdate.getTags());
        int borrowedCopies = existingBook.getTotalCopies() - existingBook.getAvailableCopies();
        existingBook.setAvailableCopies(bookToUpdate.getTotalCopies() - borrowedCopies);
        
        BookEntity updatedEntity = repository.save(existingBook);
        return toDomain(updatedEntity);
    }

    public void deleteBook(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Book not found with id=" + id);
        }
        BookEntity book = repository.findById(id).get();
        book.setIsActive(false);
        repository.save(book);
    }

    public void borrowBook(Long id) {
        BookEntity book = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        
        if (book.getAvailableCopies() <= 0) {
            throw new IllegalStateException("No available copies of this book");
        }
        
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        repository.save(book);
    }

    public void returnBook(Long id) {
        BookEntity book = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));
        
        if (book.getAvailableCopies() >= book.getTotalCopies()) {
            throw new IllegalStateException("All copies are already returned");
        }
        
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        repository.save(book);
    }

    private void validateBook(Book book) {
        if (book.getLabel() == null || book.getLabel().trim().isEmpty()) {
            throw new IllegalArgumentException("Book label cannot be empty");
        }
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }
        if (book.getTotalCopies() != null && book.getTotalCopies() < 1) {
            throw new IllegalArgumentException("Total copies must be at least 1");
        }
    }

    private Book toDomain(BookEntity entity) {
        return new Book(
            entity.getId(),
            entity.getLabel(),
            entity.getAuthor(),
            entity.getIsbn(),
            entity.getPublicationYear(),
            entity.getPublisher(),
            entity.getGenre(),
            entity.getPageCount(),
            entity.getLanguage(),
            entity.getDescription(),
            entity.getTotalCopies(),
            entity.getAvailableCopies(),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            entity.getTags()
        );
    }

    private BookEntity toEntity(Book book) {
        BookEntity entity = new BookEntity();
        entity.setLabel(book.getLabel());
        entity.setAuthor(book.getAuthor());
        entity.setIsbn(book.getIsbn());
        entity.setPublicationYear(book.getPublicationYear());
        entity.setPublisher(book.getPublisher());
        entity.setGenre(book.getGenre());
        entity.setPageCount(book.getPageCount());
        entity.setLanguage(book.getLanguage());
        entity.setDescription(book.getDescription());
        entity.setTotalCopies(book.getTotalCopies());
        entity.setAvailableCopies(book.getTotalCopies());
        entity.setTags(book.getTags());
        return entity;
    }
}