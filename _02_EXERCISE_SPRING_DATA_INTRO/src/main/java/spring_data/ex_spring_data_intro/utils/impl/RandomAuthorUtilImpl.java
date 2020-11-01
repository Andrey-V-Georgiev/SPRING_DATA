package spring_data.ex_spring_data_intro.utils.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring_data.ex_spring_data_intro.entities.Author;
import spring_data.ex_spring_data_intro.services.AuthorService;
import spring_data.ex_spring_data_intro.utils.RandomAuthorUtil;

import java.util.Random;

@Component
public class RandomAuthorUtilImpl implements RandomAuthorUtil {

    private final AuthorService authorService;

    @Autowired
    public RandomAuthorUtilImpl(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    public Author getRandom() {
        Random random = new Random();
        int randomAuthorId = random.nextInt((int) this.authorService.getAuthorsCount()) + 1;
        Author randomAuthor = this.authorService.getAuthorById(randomAuthorId);
        return randomAuthor;
    }
}
