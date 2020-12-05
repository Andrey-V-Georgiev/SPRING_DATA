package softuni.exam.instagraphlite.service;

import softuni.exam.instagraphlite.models.dtos.post.PostViewDto;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface PostService {
    Boolean аreImported();
    String readFromFileContent() throws IOException;
    String importPosts() throws IOException, JAXBException;
}
