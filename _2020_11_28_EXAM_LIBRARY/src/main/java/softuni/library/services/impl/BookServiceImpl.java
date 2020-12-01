package softuni.library.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.library.constants.GlobalConstants;
import softuni.library.models.dtos.BookDto;
import softuni.library.models.entities.Author;
import softuni.library.models.entities.Book;
import softuni.library.repositories.AuthorRepository;
import softuni.library.repositories.BookRepository;
import softuni.library.services.BookService;
import softuni.library.utils.FileUtil;
import softuni.library.utils.RandomUtil;
import softuni.library.utils.ValidationUtil;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final RandomUtil randomUtil;
    private final FileUtil fileUtil;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, RandomUtil randomUtil, FileUtil fileUtil, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.randomUtil = randomUtil;
        this.fileUtil = fileUtil;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public boolean areImported() {
        return this.bookRepository.count() > 0 ? true : false;
    }

    @Override
    public String readBooksFileContent() throws IOException {
        String booksFileContent = this.fileUtil.readFile(GlobalConstants.BOOKS_INPUT_PATH);
        return booksFileContent;
    }

    @Override
    public String importBooks() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        BookDto[] dtos = this.gson.fromJson(new FileReader(GlobalConstants.BOOKS_INPUT_PATH), BookDto[].class);

        for (BookDto dto : dtos) {
            Book authorFromDb = this.bookRepository
                    .findBookByNameAndEdition(dto.getName(), dto.getEdition());
            if (authorFromDb != null) {
                continue;
            }
            /* Validate the dtos */
            if (this.validationUtil.isValid(dto)) {
                Book book = this.modelMapper.map(dto, Book.class);
                Author authorById = this.authorRepository.findAuthorById(dto.getAuthor());
                book.setAuthor(authorById);

                this.bookRepository.saveAndFlush(book);
                sb.append(String.format(
                        "Successfully imported Book: %s written in %s%n",  dto.getName(), dto.getWritten()));
            } else {
                Set<ConstraintViolation<BookDto>> violations = this.validationUtil.violations(dto);
                sb.append("Invalid Book%n");
            }
        }
        return sb.toString();
    }
}
