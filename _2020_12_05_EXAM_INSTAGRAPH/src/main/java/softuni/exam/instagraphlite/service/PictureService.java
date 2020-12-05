package softuni.exam.instagraphlite.service;

import softuni.exam.instagraphlite.models.entities.Picture;

import java.io.IOException;
import java.util.Optional;

public interface PictureService {
    Boolean areImported();
    String readFromFileContent() throws IOException;
    String importPictures() throws IOException;
    String exportPictures();
    Optional<Picture> findPictureByPath(String path);
}
