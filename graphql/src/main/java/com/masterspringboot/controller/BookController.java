package com.masterspringboot.controller;

import com.masterspringboot.model.Book;
import com.masterspringboot.repository.BookRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    private final BookRepository bookRepository;


    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /*
    query {
  allBooks {
    title

    author {
       name
    }
  }
}
     */
    @SchemaMapping(typeName = "Query",value = "allBooks")
    public List<Book> findAll() {
        return bookRepository.findAll();
    }
    /*
    query {
      findOne(id: 1) {
        title

        author {
          name
        }
      }
    }
     */
    @QueryMapping
    public Book findOne(@Argument Integer id) {
        return bookRepository.findOne(id);
    }

    @MutationMapping
    public boolean createBook(@Argument("title") String title, @Argument("author") String author) {
        return bookRepository.add(title, author);
    }
    /*
    mutation CreateBook($title:String, $author:String) {
  createBook(title: $title, author: $author)
}
{
  "title":"This Dick",
  "author": "Dan Brown"

}
     */
}
