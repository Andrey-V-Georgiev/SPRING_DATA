package spring_data.ex_spring_data_intro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_data.ex_spring_data_intro.entities.Author;
import spring_data.ex_spring_data_intro.entities.Category;
import spring_data.ex_spring_data_intro.repositories.AuthorRepository;
import spring_data.ex_spring_data_intro.utils.ReadFileUtil;

import java.io.IOException;

import static spring_data.ex_spring_data_intro.constants.PathConstants.AUTHORS_FILE_RELATIVE_PATH;
import static spring_data.ex_spring_data_intro.constants.PathConstants.CATEGORIES_FILE_RELATIVE_PATH;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;
    private ReadFileUtil readFileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ReadFileUtil readFileUtil) {
        this.authorRepository = authorRepository;
        this.readFileUtil = readFileUtil;
    }

    @Override
    public void seedAuthors() throws IOException {
        /* Check and seed only initially */
        if (this.authorRepository.count() != 0) {
            return;
        }
        String[] lines = readFileUtil.read(AUTHORS_FILE_RELATIVE_PATH);
        for (int i = 0; i < lines.length; i++) {
            String[] args = lines[i].split("\\s+");
            String firstName = args[0];
            String lastName = args[1];
            Author author = new Author(firstName, lastName);
            this.authorRepository.saveAndFlush(author);
        }
    }

    @Override
    public Author getAuthorById(long id) {

        Author one = this.authorRepository.getOne(id);
        return one;
    }

    @Override
    public long getAuthorsCount() {
        return this.authorRepository.count();
    }
}
