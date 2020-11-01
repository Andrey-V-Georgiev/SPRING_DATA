package spring_data.ex_spring_data_intro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import spring_data.ex_spring_data_intro.entities.Book;
import spring_data.ex_spring_data_intro.enums.AgeRestriction;
import spring_data.ex_spring_data_intro.enums.EditionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate localDate);

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findBooksByEditionTypeAndCopiesLessThan(EditionType editionType, long copies);

    List<Book> findBooksByPriceLessThanOrPriceGreaterThan(BigDecimal lessThan, BigDecimal greatThan);

    List<Book> findBooksByReleaseDateBeforeOrReleaseDateAfter(LocalDate beforeDate, LocalDate afterDate);

    List<Book> findBooksByReleaseDateBefore(LocalDate localDate);

    List<Book> findBooksByTitleContains(String containsString);

    @Query(value = "SELECT b FROM Book AS b INNER JOIN Author AS a ON a.id = b.author.id WHERE a.lastName LIKE ?1%")
    List<Book> findBooksWrittenByAuthorWhoseLastNameStartsWith(String startsWithStr);

    @Query(value = "SELECT b FROM Book AS b WHERE length(b.title) > ?1")
    List<Book> findBooksByTitleLongerThan(int length);

    @Query(value = "SELECT sum(b.copies) FROM Book AS b WHERE concat(b.author.firstName, ' ', b.author.lastName) = ?1")
    int findCountOfCopiesByAuthorName(String name);

    @Query(value = "SELECT b, b.price FROM Book AS b WHERE b.title = ?1")
    Book findBooksByTitle(String title);

    @Modifying
    @Query(value = "UPDATE Book AS b SET b.copies = b.copies + ?2 WHERE b.releaseDate > ?1")
    int updateBooksByReleaseDateAfter(LocalDate localDate, long increaseValue);

    @Modifying
    @Query(value = "DELETE FROM Book AS b WHERE b.copies < ?1")
    int deleteBooksByCopiesLowerThan(long countOfCopies);
}
