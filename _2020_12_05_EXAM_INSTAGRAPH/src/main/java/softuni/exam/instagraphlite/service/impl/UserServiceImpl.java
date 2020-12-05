package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.constants.GlobalConstants;
import softuni.exam.instagraphlite.models.dtos.post.PostViewDto;
import softuni.exam.instagraphlite.models.dtos.user.UserSeedDto;
import softuni.exam.instagraphlite.models.dtos.user.UserViewDto;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.models.entities.User;
import softuni.exam.instagraphlite.repository.PostRepository;
import softuni.exam.instagraphlite.repository.UserRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.service.UserService;
import softuni.exam.instagraphlite.utils.FileUtil;
import softuni.exam.instagraphlite.utils.ValidationUtil;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final PictureService pictureService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public UserServiceImpl(Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, UserRepository userRepository, PictureService pictureService, PostRepository postRepository) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.userRepository = userRepository;
        this.pictureService = pictureService;
        this.postRepository = postRepository;
    }

    @Override
    public Boolean аreImported() {
        return this.userRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        String inputString = this.fileUtil.readFileAddedNewLines(GlobalConstants.USERS_INPUT_PATH);
        return inputString;
    }

    @Override
    public String importUsers() throws IOException {
        StringBuilder sb = new StringBuilder();
        /* Get the JSON */
        String inputString = this.fileUtil.readFileAddedNewLines(GlobalConstants.USERS_INPUT_PATH);

        /* Turn it to dtos */
        UserSeedDto[] dtos = this.gson.fromJson(inputString, UserSeedDto[].class);
        for (UserSeedDto dto : dtos) {
            /* Prevent duplicates */
            Optional<User> userOptional = this.userRepository
                    .findUserByUsernameAndPassword(dto.getUsername(), dto.getPassword());

            /* Validate dtos */
            if (userOptional.isEmpty()) {
                if (this.validationUtil.isValid(dto)) {

                    User user = this.modelMapper.map(dto, User.class);

                    /* Validate picture is available in DB */
                    Optional<Picture> pictureByPath = this.pictureService.findPictureByPath(dto.getProfilePicture());
                    if (pictureByPath.isEmpty()) {
                        sb.append("Invalid User\n");
                        continue;
                    }
                    user.setProfilePicture(pictureByPath.get());
                    this.userRepository.saveAndFlush(user);

                    /* Save to DB */
                    sb.append(String.format("Successfully imported User, with size %s\n", dto.getUsername()));
                } else {
                    sb.append("Invalid User\n");
                }
            }
        }
        return sb.toString();
    }

    @Override
    public String exportUsersWithTheirPosts() {
        StringBuilder sb = new StringBuilder();

        /* Get user info */
        List<UserViewDto> userViewDtos = this.userRepository.findUsersWithTheirPostsCount();
        for (UserViewDto userViewDto : userViewDtos) {

            sb.append(String.format("User: %s\n", userViewDto.getUsername()));
            sb.append(String.format("Post count: %d\n", userViewDto.getPostsCount()));

            /* Get posts info */
            List<PostViewDto> postViewDtos = this.postRepository.findPostDetailsByUserId(userViewDto.getId());
            for (PostViewDto postViewDto : postViewDtos) {

                sb.append("==Post Details:\n");
                sb.append(String.format("----Caption: %s\n", postViewDto.getCaption()));
                sb.append(String.format("----Picture Size: %.2f\n", postViewDto.getPictureSize()));
            }
        }
        return sb.toString();
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return this.userRepository.findUserByUsername(username);
    }
}
