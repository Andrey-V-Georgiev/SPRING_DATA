package spring_data.ex_spring_data_intro.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_data.ex_spring_data_intro.entities.Author;
import spring_data.ex_spring_data_intro.entities.Book;
import spring_data.ex_spring_data_intro.entities.Category;
import spring_data.ex_spring_data_intro.enums.AgeRestriction;
import spring_data.ex_spring_data_intro.enums.EditionType;
import spring_data.ex_spring_data_intro.repositories.BookRepository;
import spring_data.ex_spring_data_intro.services.BookService;
import spring_data.ex_spring_data_intro.utils.LocalDateUtil;
import spring_data.ex_spring_data_intro.utils.RandomAuthorUtil;
import spring_data.ex_spring_data_intro.utils.RandomCategoriesUtil;
import spring_data.ex_spring_data_intro.utils.ReadFileUtil;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static spring_data.ex_spring_data_intro.constants.PathConstants.*;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final ReadFileUtil readFileUtil;
    private final RandomAuthorUtil randomAuthorUtil;
    private final LocalDateUtil localDateUtil;
    private final RandomCategoriesUtil randomCategoriesUtil;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, ReadFileUtil readFileUtil, RandomAuthorUtil randomAuthorUtil, LocalDateUtil localDateUtil, RandomCategoriesUtil randomCategoriesUtil) {
        this.bookRepository = bookRepository;
        this.readFileUtil = readFileUtil;
        this.randomAuthorUtil = randomAuthorUtil;
        this.localDateUtil = localDateUtil;
        this.randomCategoriesUtil = randomCategoriesUtil;
    }

    @Override
    public void seedBooks() throws IOException {
        /* Check and seed only initially */
        if (this.bookRepository.count() > 0) {
            return;
        }

        String[] lines = this.readFileUtil.read(BOOKS_FILE_RELATIVE_PATH);
        for (int i = 0; i < lines.length; i++) {
            /* Get args */
            String[] args = lines[i].split("\\s+");

            /* Get edition type */
            EditionType editionType = EditionType.values()[Integer.parseInt(args[0])];

            /* Get release date */
            LocalDate releaseDate = localDateUtil.parseByPattern("d/M/yyyy", args[1]);

            /* Get copies */
            long copies = Long.parseLong(args[2]);

            /* Get price */
            BigDecimal price = new BigDecimal(args[3]);

            /* Get age restriction */
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(args[4])];

            /* Get title */
            String title = Arrays.stream(args).skip(5).collect(Collectors.joining(" "));

            /* Get author */
            Author randomAuthor = this.randomAuthorUtil.getRandom();

            /* Get categories */
            Set<Category> randomCategories = this.randomCategoriesUtil.getRandom();

            /* Create book */
            Book book = new Book(ageRestriction, copies, editionType, price, releaseDate, title, randomAuthor);
            book.setCategories(randomCategories);

            /* Save the book */
            this.bookRepository.saveAndFlush(book);
        }
    }

    @Override
    public long repoSize() {
        return this.bookRepository.count();
    }

    @Override
    public List<Book> findAllByAgeRestriction(String ageRestriction) {
        List<Book> books = this.bookRepository.findAllByAgeRestriction(AgeRestriction.valueOf(ageRestriction.toUpperCase()));
        return books;
    }

    @Override
    public List<Book> findBooksByEditionTypeAndCopiesLessThan(String editionTypeStr, long copies) {
        EditionType editionType = EditionType.valueOf(editionTypeStr);
        List<Book> books = this.bookRepository.findBooksByEditionTypeAndCopiesLessThan(editionType, copies);
        return books;
    }

    @Override
    public List<Book> findBooksByPriceLessThanOrPriceGreaterThan(long lessThanLong, long greatThanLong) {
        BigDecimal lessThan = new BigDecimal(lessThanLong);
        BigDecimal greatThan = new BigDecimal(greatThanLong);
        List<Book> books = this.bookRepository.findBooksByPriceLessThanOrPriceGreaterThan(lessThan, greatThan);
        return books;
    }

    @Override
    public List<Book> findBooksNotReleasedInYear(int year) {
        LocalDate beforeDate = LocalDate.of(year, 1, 1);
        LocalDate afterDate = LocalDate.of(year, 12, 31);
        List<Book> books = this.bookRepository.findBooksByReleaseDateBeforeOrReleaseDateAfter(beforeDate, afterDate);
        return books;
    }

    @Override
    public List<Book> findBooksByReleaseDateBefore(String localDateStr) {
        LocalDate localDate = LocalDate.parse(localDateStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        List<Book> books = this.bookRepository.findBooksByReleaseDateBefore(localDate);
        return books;
    }
}
