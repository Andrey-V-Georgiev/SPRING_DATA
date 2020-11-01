package spring_data.ex_spring_data_intro.services;

import spring_data.ex_spring_data_intro.entities.Book;
import spring_data.ex_spring_data_intro.enums.AgeRestriction;
import spring_data.ex_spring_data_intro.enums.EditionType;

import java.io.IOException;
import java.util.List;

public interface BookService {

    void seedBooks() throws IOException;

    long repoSize();

    List<Book> findAllByAgeRestriction(String ageRestriction);

    List<Book> findBooksByEditionTypeAndCopiesLessThan(String editionTypeStr, long copies);
}
