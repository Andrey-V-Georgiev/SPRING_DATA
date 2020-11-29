package softuni.library.services;

import java.io.FileNotFoundException;

public interface BookService {
    boolean areImported();
    String readBooksFileContent();
    String importBooks() throws FileNotFoundException;
}
