package softuni.library.services;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface LibraryService {
    boolean areImported();
    String readLibrariesFileContent();
    String importLibraries() throws JAXBException, FileNotFoundException;
}
