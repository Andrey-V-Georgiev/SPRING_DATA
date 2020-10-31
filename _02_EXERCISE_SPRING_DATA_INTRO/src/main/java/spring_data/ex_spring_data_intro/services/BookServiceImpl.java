package spring_data.ex_spring_data_intro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_data.ex_spring_data_intro.entities.Author;
import spring_data.ex_spring_data_intro.entities.Book;
import spring_data.ex_spring_data_intro.entities.Category;
import spring_data.ex_spring_data_intro.enums.AgeRestriction;
import spring_data.ex_spring_data_intro.enums.EditionType;
import spring_data.ex_spring_data_intro.repositories.BookRepository;
import spring_data.ex_spring_data_intro.utils.LocalDateUtil;
import spring_data.ex_spring_data_intro.utils.RandomAuthorUtil;
import spring_data.ex_spring_data_intro.utils.RandomCategoriesUtil;
import spring_data.ex_spring_data_intro.utils.ReadFileUtil;

import javax.transaction.Transactional;
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
        String[] lines = this.readFileUtil.read(BOOKS_FILE_RELATIVE_PATH);

        for (int i = 0; i < lines.length; i++) {

            String[] args = lines[i].split("\\s+");

            EditionType editionType = EditionType.values()[Integer.parseInt(args[0])];
            //System.out.printf("editionType: %s\n", editionType);

            LocalDate releaseDate = localDateUtil.parseByPattern("d/M/yyyy", args[1]);
            //System.out.printf("releaseDate: %s\n", releaseDate);

            long copies = Long.parseLong(args[2]);
            //System.out.printf("copies: %d\n", copies);

            BigDecimal price = new BigDecimal(args[3]);
            //System.out.printf("price: %s\n", price);

            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(args[4])];
            //System.out.printf("ageRestriction: %s\n", ageRestriction);

            String title = Arrays.stream(args).skip(5).collect(Collectors.joining(" "));
            //System.out.printf("title: %s\n", title);

            Author randomAuthor = this.randomAuthorUtil.getRandom();
            //System.out.printf("title: %s\n", randomAuthor.getFirstName());

            Set<Category> randomCategories = this.randomCategoriesUtil.getRandom();

            Book book = new Book(ageRestriction, copies, editionType, price, releaseDate, title, randomAuthor);
            book.setCategories(randomCategories);
            this.bookRepository.saveAndFlush(book);
      }
    }

    @Override
    public List<Book> getAllBooksAfter2000() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
        LocalDate localDate = LocalDate.parse("2000-12-31", formatter);
        List<Book> booksAfterYear = this.bookRepository.findAllByReleaseDateAfter(localDate);
        return booksAfterYear;
    }
}
