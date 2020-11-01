package spring_data.ex_spring_data_intro.services;

import spring_data.ex_spring_data_intro.entities.Book;
import spring_data.ex_spring_data_intro.enums.AgeRestriction;
import spring_data.ex_spring_data_intro.enums.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {

    void seedBooks() throws IOException;

    long repoSize();

    List<Book> findAllByAgeRestriction(String ageRestriction);

    List<Book> findBooksByEditionTypeAndCopiesLessThan(String editionTypeStr, long copies);

    List<Book> findBooksByPriceLessThanOrPriceGreaterThan(long lessThanLong, long greatThanLong);

    List<Book> findBooksNotReleasedInYear(int year);

    List<Book> findBooksByReleaseDateBefore(String localDateStr);

    List<Book> findBooksByTitleContains(String containsString);

    List<Book> findBooksWrittenByAuthorWhoseLastNameStartsWith(String startsWithStr);

    List<Book> findBooksByTitleLongerThan(int length);

    int findCountOfCopiesByAuthorName(String name);

    Book findBooksByTitle(String title);

    int updateBooksByReleaseDateAfter(String localDateStr, long increaseValue);

    int deleteBooksByCopiesLowerThan(long countOfCopies);
}