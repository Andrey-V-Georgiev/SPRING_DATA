package softuni.library.services;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface CharacterService {
    boolean areImported();
    String readCharactersFileContent() throws IOException;
    String importCharacters() throws JAXBException, FileNotFoundException;
    String findCharactersInBookOrderedByLastNameDescendingThenByAge();
}