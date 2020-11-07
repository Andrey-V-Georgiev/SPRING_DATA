package spring_data.game_store.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_data.game_store.domain.dto.UserLoginDto;
import spring_data.game_store.domain.dto.UserRegisterDto;
import spring_data.game_store.domain.dto.UserSessionDto;
import spring_data.game_store.domain.entity.User;
import spring_data.game_store.domain.enumeration.Role;
import spring_data.game_store.repository.UserRepository;
import spring_data.game_store.service.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private UserSessionDto userSessionDto = null;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public long registerUser(UserRegisterDto dto) {
        User user = this.modelMapper.map(dto, User.class);
        Role role = this.userRepository.count() < 1 ? Role.ADMINISTRATOR : Role.BASIC_USER;
        user.setRole(role);
        User savedUser = this.userRepository.saveAndFlush(user);
        System.out.printf("User %s was registered successfully\n", savedUser.getFullName());
        return savedUser.getId();
    }

    @Override
    public void loginUser(UserLoginDto dto) {
        if (this.userSessionDto != null) {
            System.out.print("You are already logged in\n");
            return;
        }
        Optional<User> userDB = this.userRepository.findUserByEmail(dto.getEmail());
        if (userDB.isEmpty()) {
            System.out.println("Wrong email");
            return;
        }
        if(!dto.getPassword().equals(userDB.get().getPassword())) {
            System.out.print("Unauthorized - wrong password\n");
            return;
        }
        this.userSessionDto = this.modelMapper.map(userDB.get(), UserSessionDto.class);
        System.out.printf("Hello %s\n", this.userSessionDto.getFullName());
    }

    @Override
    public void logoutUser() {
        if (this.userSessionDto == null) {
            System.out.print("Cannot log out. No user was logged in\n");
        } else {
            String userName = this.userSessionDto.getFullName();
            this.userSessionDto = null;
            System.out.printf("User %s successfully logged out\n", userName);
        }
    }

    @Override
    public boolean isAdmin() {
        return this.userSessionDto.getRole() == Role.ADMINISTRATOR;
    }

    @Override
    public boolean isLogged() {
        boolean loggedStatus = this.userSessionDto != null;
        return loggedStatus;
    }
}
