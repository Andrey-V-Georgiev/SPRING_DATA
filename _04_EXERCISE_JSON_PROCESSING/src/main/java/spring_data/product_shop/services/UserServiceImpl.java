package spring_data.product_shop.services;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_data.product_shop.models.dtos.UserSeedDto;
import spring_data.product_shop.models.entities.User;
import spring_data.product_shop.repositories.UserRepository;
import spring_data.product_shop.utils.RandomUtil;
import spring_data.product_shop.utils.ValidationUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final UserRepository userRepository;
    private final RandomUtil randomUtil;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, UserRepository userRepository, RandomUtil randomUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.userRepository = userRepository;
        this.randomUtil = randomUtil;
    }

    @Override
    public void seedUsers(String filePath) throws FileNotFoundException {

        /* Read the json */
        UserSeedDto[] dtos = this.gson.fromJson(new FileReader(filePath), UserSeedDto[].class);
        for (UserSeedDto dto : dtos) {
            User userFromDb = this.userRepository
                    .getUserByFirstNameAndLastNameAndAge(dto.getFirstName(), dto.getLastName(), dto.getAge());
            if (userFromDb != null) {
                continue;
            }
            /* Validate the dtos */
            if (this.validationUtil.isValid(dto)) {
                User user = this.modelMapper.map(dto, User.class);
                this.userRepository.saveAndFlush(user);
            } else {
                this.validationUtil.printConstraintViolations(dto);
            }
        }
    }

    @Override
    public User getRandomUser() {
        /* Get random user id */
        int randomUserId = this.randomUtil.getRandomIntFromOneTo((int) this.userRepository.count());
        User randomUser = this.userRepository.getUserById(randomUserId);
        return randomUser;
    }

    @Override
    public User getRandomUserOrNull() {
        /* Get random user id */
        int randomUserId = this.randomUtil.getRandomIntFromZeroTo((int) this.userRepository.count());
        User randomUserOrNull = this.userRepository.getUserById(randomUserId);
        return randomUserOrNull;
    }

}
