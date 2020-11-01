package spring_data.ex_spring_data_intro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import spring_data.ex_spring_data_intro.entities.Author;
import spring_data.ex_spring_data_intro.entities.Book;
import spring_data.ex_spring_data_intro.enums.EditionType;
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

        /* 2.Golden Books */
//        this.bookService
//                .findBooksByEditionTypeAndCopiesLessThan(EditionType.GOLD.name(), 5000)
//                .forEach(b -> System.out.println(b.getTitle()));

        /* 3.Books by Price */
//        List<Book> books = this.bookService.findBooksByPriceLessThanOrPriceGreaterThan(5, 40);
//        books.forEach(b-> System.out.printf("%s - $%.2f\n", b.getTitle(), b.getPrice()));


        /* 4.Not Released Books */
//        System.out.println("Enter year:");
//        int year = Integer.parseInt(this.bufferedReader.readLine().trim());
//        List<Book> books = this.bookService.findBooksNotReleasedInYear(year);
//        books.forEach(b -> System.out.println(b.getTitle()));

        /* 5.Books Released Before Date */
//        System.out.println("Enter date:");
//        String localDateStr = this.bufferedReader.readLine().trim();
//        List<Book> books = this.bookService.findBooksByReleaseDateBefore(localDateStr);
//        books.forEach(b -> System.out.println(b.getTitle()));

        /* 6.Authors Search */
        System.out.println("Enter ending string:");
        String endingString = this.bufferedReader.readLine().trim();
        List<Author> authors = this.authorService.findAuthorsByFirstNameEndsWith(endingString);
        authors.forEach(a -> {
            String output = String.format("%s %s", a.getFirstName(), a.getLastName());
            System.out.println(output);
        });

        /* 7.Books Search */


        /* 8.Book Titles Search */


    }
}
