package softuni.library.services;

import java.io.FileNotFoundException;

public interface AuthorService {
    boolean areImported();
    String readAuthorsFileContent();
    String importAuthors() throws FileNotFoundException;
}
