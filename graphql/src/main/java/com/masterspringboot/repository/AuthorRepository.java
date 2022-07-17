package com.masterspringboot.repository;

import com.masterspringboot.model.Author;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepository {

    private List<Author> authors = new ArrayList<>();

    public List<Author> findAll() {
        return authors;
    }



    public Author findByName(String name) {
        return authors.stream()
                .filter(author -> author.name().equals(name))
                .findFirst().orElse(add(name));

               // .orElseThrow(() -> new RuntimeException("Author not found"));
    }
    public Author add(String authorName) {
        int id = authors.size() + 1;
        Author author = new Author(id,authorName);
        return author;
    }
    @PostConstruct
    private void init() {
        authors.add(new Author(1,"Stephen King"));
        authors.add(new Author(2,"Dan Brown"));
        authors.add(new Author(3,"JK Rowling"));


    }

 //   public boolean addAuthor(Author a) {
     //   return authors.add(a);
   // }


}
