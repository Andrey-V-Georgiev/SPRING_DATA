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
//        System.out.println("Enter ending string:");
//        String endingString = this.bufferedReader.readLine().trim();
//        List<Author> authors = this.authorService.findAuthorsByFirstNameEndsWith(endingString);
//        authors.forEach(a -> {
//            String output = String.format("%s %s", a.getFirstName(), a.getLastName());
//            System.out.println(output);
//        });

        /* 7.Books Search */
//        System.out.println("Enter contains string:");
//        String containsString = this.bufferedReader.readLine().trim();
//        List<Book> books = this.bookService.findBooksByTitleContains(containsString);
//        books.forEach(b -> System.out.println(b.getTitle()));

        /* 8.Book Titles Search */
//        System.out.println("Enter startsWithStr:");
//        String startsWithStr = this.bufferedReader.readLine().trim();
//        List<Book> books = this.bookService.findBooksWrittenByAuthorWhoseLastNameStartsWith(startsWithStr);
//        books.forEach(b -> System.out.println(b.getTitle()));

        /* 9.Count Books */
//        System.out.println("Enter length:");
//        int length = Integer.parseInt(this.bufferedReader.readLine().trim());
//        List<Book> books = this.bookService.findBooksByTitleLongerThan(length);
//        System.out.println(books.size());

        /* 10.Total Book Copies */
//        System.out.println("Enter author name:");
//        String name = this.bufferedReader.readLine().trim();
//        int countOfCopies = this.bookService.findCountOfCopiesByAuthorName(name);
//        System.out.println(countOfCopies);

        /* 11.Reduced Book */
//        System.out.println("Enter book title:");
//        String title = this.bufferedReader.readLine().trim();
//        Book book = this.bookService.findBooksByTitle(title);
//        System.out.printf("%s %s %s %.2f\n",
//                book.getTitle(), book.getEditionType(), book.getAgeRestriction(), book.getPrice());

        /* 12.Increase Book Copies */
//        System.out.println("Enter date and increaseValue:");
//        String date = this.bufferedReader.readLine().trim();
//        long increaseValue = Long.parseLong(this.bufferedReader.readLine().trim());
//        int countOfUpdatedRows = this.bookService.updateBooksByReleaseDateAfter(date, increaseValue);
//        System.out.println(countOfUpdatedRows * increaseValue);

        /* 13.Remove Books */
//        System.out.println("Enter count of copies:");
//        long countOfCopies = Long.parseLong(this.bufferedReader.readLine().trim());
//        int countOfDeletedBooks = this.bookService.deleteBooksByCopiesLowerThan(countOfCopies);
//        System.out.println(countOfDeletedBooks);

    }
}
