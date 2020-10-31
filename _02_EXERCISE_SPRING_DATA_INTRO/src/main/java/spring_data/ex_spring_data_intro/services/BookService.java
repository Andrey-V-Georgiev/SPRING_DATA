package spring_data.ex_spring_data_intro.services;

import spring_data.ex_spring_data_intro.entities.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;
    List<Book> getAllBooksAfter2000();
}
