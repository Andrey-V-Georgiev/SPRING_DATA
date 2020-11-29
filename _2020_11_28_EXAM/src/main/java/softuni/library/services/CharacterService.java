package softuni.library.services;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface CharacterService {
    boolean areImported();
    String readCharactersFileContent();
    String importCharacters() throws JAXBException, FileNotFoundException;
    String findCharactersInBookOrderedByLastNameDescendingThenByAge();
}
