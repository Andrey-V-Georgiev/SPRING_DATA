package softuni.library.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.library.constants.GlobalConstants;
import softuni.library.models.dtos.CharacterDto;
import softuni.library.models.dtos.CharacterExportDto;
import softuni.library.models.dtos.CharacterRootDto;
import softuni.library.models.entities.Book;
import softuni.library.models.entities.Character;
import softuni.library.repositories.BookRepository;
import softuni.library.repositories.CharacterRepository;
import softuni.library.services.CharacterService;
import softuni.library.utils.FileUtil;
import softuni.library.utils.ValidationUtil;
import softuni.library.utils.XmlParser;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CharacterServiceImpl implements CharacterService {

    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final CharacterRepository characterRepository;
    private final BookRepository bookRepository;
    private final FileUtil fileUtil;

    public CharacterServiceImpl(XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper, CharacterRepository characterRepository, BookRepository bookRepository, FileUtil fileUtil) {
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.characterRepository = characterRepository;
        this.bookRepository = bookRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public boolean areImported() {
        return this.characterRepository.count() > 0 ? true : false;
    }

    @Override
    public String readCharactersFileContent() throws IOException {
        String inputString = this.fileUtil.readFile(GlobalConstants.CHARACTERS_INPUT_PATH);
        return inputString;
    }

    @Override
    public String importCharacters() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        /* Parse the XMLs to Dtos */
        CharacterRootDto rootDto = this.xmlParser
                .unmarshalFromFile(GlobalConstants.CHARACTERS_INPUT_PATH, CharacterRootDto.class);

        /* Validate the Dtos */
        List<CharacterDto> dtos = rootDto.getCharacters();
        for (CharacterDto dto : dtos) {
            if (this.validationUtil.isValid(dto)) {

                /* Prevent duplicates */
                Optional<Character> characterOptional = this.characterRepository
                        .findCharacterByFirstNameAndLastName(dto.getFirstName(), dto.getLastName());

                if (characterOptional.isEmpty()) {
                    Character character = this.modelMapper.map(dto, Character.class);
                    Book bookById = this.bookRepository.findBookById(dto.getBook().getId());
                    character.setBook(bookById);

                    this.characterRepository.saveAndFlush(character);
                    sb.append("Successfully added \n");
                }
            } else {
                sb.append("Invalid Character\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String findCharactersInBookOrderedByLastNameDescendingThenByAge() {
        List<CharacterExportDto> charsByCriteria = this.characterRepository.findCharsByCriteria();
        StringBuilder sb = new StringBuilder();
        charsByCriteria.forEach(c -> sb.append(
                String.format("Character name %s, age %d, in book %s%n", c.getName(), c.getAge(), c.getBookName())));
        String s = sb.toString();
        return s;
    }
}
