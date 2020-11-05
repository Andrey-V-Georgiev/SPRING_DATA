package spring_data.game_store.service;

import spring_data.game_store.domain.dto.UserLoginDto;
import spring_data.game_store.domain.dto.UserRegisterDto;

public interface UserService {

    long registerUser(UserRegisterDto dto);

    void loginUser(UserLoginDto dto);

    void logoutUser();
}
