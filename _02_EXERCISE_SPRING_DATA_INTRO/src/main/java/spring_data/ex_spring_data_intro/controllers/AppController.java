package spring_data.ex_spring_data_intro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import spring_data.ex_spring_data_intro.entities.Book;
import spring_data.ex_spring_data_intro.services.AuthorService;
import spring_data.ex_spring_data_intro.services.BookService;
import spring_data.ex_spring_data_intro.services.CategoryService;

import java.io.BufferedReader;
import java.util.*;

@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    @Autowired
    public AppController(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
        /* Seed initial records */
        this.categoryService.seedCategories();
        this.authorService.seedAuthors();
        this.bookService.seedBooks();

        /* 1.Books Titles by Age Restriction */
//        System.out.println("Enter age restriction:");
//        String ageRestriction = this.bufferedReader.readLine().trim();
//        List<Book> books = this.bookService.findAllByAgeRestriction(ageRestriction);
//        books.forEach(b -> System.out.println(b.getTitle()));


    }
}
