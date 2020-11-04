package spring_data.game_store.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_data.game_store.domain.dto.UserRegisterDto;
import spring_data.game_store.domain.entity.User;
import spring_data.game_store.domain.enumeration.Role;
import spring_data.game_store.repository.UserRepository;
import spring_data.game_store.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

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
}
