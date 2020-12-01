package softuni.library.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.library.constants.GlobalConstants;
import softuni.library.models.dtos.LibraryDto;
import softuni.library.models.dtos.LibraryRootDto;
import softuni.library.models.entities.Book;
import softuni.library.models.entities.Library;
import softuni.library.repositories.BookRepository;
import softuni.library.repositories.LibraryRepository;
import softuni.library.services.LibraryService;
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
public class LibraryServiceImpl implements LibraryService {

    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final LibraryRepository libraryRepository;
    private final BookRepository bookRepository;
    private final FileUtil fileUtil;

    public LibraryServiceImpl(XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper, LibraryRepository libraryRepository, BookRepository bookRepository, FileUtil fileUtil) {
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.libraryRepository = libraryRepository;
        this.bookRepository = bookRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public boolean areImported() {
        return this.libraryRepository.count() > 0 ? true : false;
    }

    @Override
    public String readLibrariesFileContent() throws IOException {
        String librariesFileContent = this.fileUtil.readFile(GlobalConstants.LIBRARIES_INPUT_PATH);
        return librariesFileContent;
    }

    @Override
    public String importLibraries() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        /* Parse the XMLs to Dtos */
        LibraryRootDto libraryRootDto = this.xmlParser
                .unmarshalFromFile(GlobalConstants.LIBRARIES_INPUT_PATH, LibraryRootDto.class);

        /* Validate the Dtos */
        List<LibraryDto> libraryDtos = libraryRootDto.getLibraries();
        for (LibraryDto dto : libraryDtos) {
            if (this.validationUtil.isValid(dto)) {

                Optional<Library> libraryOptional = this.libraryRepository
                        .findLibraryByNameAndLocation(dto.getName(), dto.getLocation());
                if (libraryOptional.isEmpty()) {
                    Library library = this.modelMapper.map(dto, Library.class);
                    Book bookById = this.bookRepository.findBookById(dto.getBook().getId());
                    List<Book> libraryBooks = library.getBooks();
                    libraryBooks.add(bookById);
                    library.setBooks(libraryBooks);
                    this.libraryRepository.saveAndFlush(library);
                    sb.append(String.format(
                            "Successfully added Library: %s - %d %s %n",
                            library.getName(), library.getRating(), library.getLocation()));
                } else {
                    System.out.println("Character");
                }
            } else {
                sb.append("Invalid Library%n");
            }
        }
        return sb.toString();
    }
}
