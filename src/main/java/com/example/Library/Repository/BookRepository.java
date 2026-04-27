package com.example.Library.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Library.Entity.BookEntity;


public interface BookRepository extends JpaRepository<BookEntity, Long> {

}