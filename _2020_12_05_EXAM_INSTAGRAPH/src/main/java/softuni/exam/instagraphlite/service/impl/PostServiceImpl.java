package softuni.exam.instagraphlite.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.constants.GlobalConstants;
import softuni.exam.instagraphlite.models.dtos.post.PostSeedDto;
import softuni.exam.instagraphlite.models.dtos.post.PostSeedRootDto;
import softuni.exam.instagraphlite.models.dtos.post.PostViewDto;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.models.entities.Post;
import softuni.exam.instagraphlite.models.entities.User;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.PostService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.utils.FileUtil;
import softuni.exam.instagraphlite.utils.ValidationUtil;
import softuni.exam.instagraphlite.utils.XmlParser;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final PostRepository postRepository;
    private final UserService userService;
    private final PictureService pictureService;

    @Autowired
    public PostServiceImpl(XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, PostRepository postRepository, UserService userService, PictureService pictureService) {
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.postRepository = postRepository;
        this.userService = userService;
        this.pictureService = pictureService;
    }

    @Override
    public Boolean аreImported() {
        return this.postRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        String inputString = this.fileUtil.readFileAddedNewLines(GlobalConstants.POSTS_INPUT_PATH);
        return inputString;
    }

    @Override
    public String importPosts() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        /* Parse the XMLs to Dtos */
        PostSeedRootDto rootDto = this.xmlParser
                .unmarshalFromFile(GlobalConstants.POSTS_INPUT_PATH, PostSeedRootDto.class);

        List<PostSeedDto> dtos = rootDto.getPosts();
        for (PostSeedDto dto : dtos) {

            if (this.validationUtil.isValid(dto)) {
                /* Prevent duplicates */
                Optional<Post> postOptional = this.postRepository.findPostByCaption(dto.getCaption());

                /* Validate the dto */
                if (postOptional.isEmpty()) {
                    Post post = this.modelMapper.map(dto, Post.class);

                    /* Set additional mappings */
                    Optional<User> userByUsername = this.userService.findUserByUsername(dto.getUser().getUsername());
                    Optional<Picture> pictureByPath = this.pictureService.findPictureByPath(dto.getPicture().getPath());

                    /* Validate user and picture are available in DB */
                    if(userByUsername.isEmpty() || pictureByPath.isEmpty()) {
                        sb.append("Invalid Post\n");
                        continue;
                    }
                    /* Save entity to DB */
                    post.setUser(userByUsername.get());
                    post.setPicture(pictureByPath.get());
                    this.postRepository.saveAndFlush(post);

                    sb.append(String.format("Successfully imported Post, made by %s\n", dto.getUser().getUsername()));
                }
            } else {
                sb.append("Invalid Post\n");
            }
        }
        return sb.toString();
    }
}
