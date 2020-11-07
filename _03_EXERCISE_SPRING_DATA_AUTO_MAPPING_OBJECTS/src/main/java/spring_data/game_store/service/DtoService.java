package spring_data.game_store.service;

import spring_data.game_store.domain.dto.*;

public interface DtoService {

    UserRegisterDto generateUserRegistrationDto(String[] input);

    UserLoginDto generateUserLoginDto(String[] input);

    GameAddDto generateGameAddDto(String[] input);

    GameEditDto generateGameEditDto(String[] input);

    GameDeleteDto generateGameDeleteDto(String[] input);
}
