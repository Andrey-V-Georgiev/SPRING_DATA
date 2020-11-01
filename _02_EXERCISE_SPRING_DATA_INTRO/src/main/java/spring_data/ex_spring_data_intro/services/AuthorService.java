package spring_data.ex_spring_data_intro.services;


import spring_data.ex_spring_data_intro.entities.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {

    void seedAuthors() throws IOException;

    Author getAuthorById(long id);

    long getAuthorsCount();

    List<Author> findAuthorsByFirstNameEndsWith(String endingStr);
}
