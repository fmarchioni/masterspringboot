package com.masterspringboot.repository;

import com.masterspringboot.model.Author;
import com.masterspringboot.model.Book;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    private final AuthorRepository authorRepository;
    private List<Book> books = new ArrayList<>();

    public BookRepository(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Book> findAll() {
        return books;
    }

    public Book findOne(Integer id) {
        return books.stream().filter(book -> book.id() == id).findFirst().orElseThrow(() -> new RuntimeException("Book not found"));
    }
    public boolean add(String title, String authorName) {
        int id = books.size() + 1;
        Author author = authorRepository.findByName(authorName);

        Book book = new Book(id, title, author);
        return books.add(book);
    }
    @PostConstruct
    private void init() {
        books.add(new Book(1,"Shining", authorRepository.findByName("Stephen King")));
        books.add(new Book(2,"Da Vinci Code", authorRepository.findByName("Dan Brown")));
        books.add(new Book(3,"Harry Potter", authorRepository.findByName("JK Rowling")));
    }

}
