package softuni.library.services.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.library.constants.GlobalConstants;
import softuni.library.models.dtos.AuthorDto;
import softuni.library.models.entities.Author;
import softuni.library.repositories.AuthorRepository;
import softuni.library.services.AuthorService;
import softuni.library.utils.FileUtil;
import softuni.library.utils.RandomUtil;
import softuni.library.utils.ValidationUtil;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final RandomUtil randomUtil;
    private final FileUtil fileUtil;
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, RandomUtil randomUtil, FileUtil fileUtil, AuthorRepository authorRepository) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.randomUtil = randomUtil;
        this.fileUtil = fileUtil;
        this.authorRepository = authorRepository;
    }

    @Override
    public boolean areImported() {

        return this.authorRepository.count() > 0 ? true : false;
    }

    @Override
    public String readAuthorsFileContent() throws IOException {
        String inputString = this.fileUtil.readFile(GlobalConstants.AUTHORS_INPUT_PATH);
        return inputString;
    }

    @Override
    public String importAuthors() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        /* Parse the JSON to dtos */
        AuthorDto[] dtos = this.gson.fromJson(
                new FileReader(GlobalConstants.AUTHORS_INPUT_PATH), AuthorDto[].class);

        /* Validate dtos */
        for (AuthorDto dto : dtos) {

            /* Prevent duplicates */
            Optional<Author> authorOptional = this.authorRepository
                    .findAuthorByFirstNameAndLastName(dto.getFirstName(), dto.getLastName());

            if (authorOptional.isEmpty()) {
                if (this.validationUtil.isValid(dto)) {
                    Author author = this.modelMapper.map(dto, Author.class);

                    this.authorRepository.saveAndFlush(author);
                    sb.append(String.format("Successfully imported Author: %s %s - %s\n",
                            dto.getFirstName(), dto.getLastName(), dto.getBirthTown()));
                } else {
                    sb.append("Invalid Author\n");
                }
            }
        }
        return sb.toString();
    }
}
