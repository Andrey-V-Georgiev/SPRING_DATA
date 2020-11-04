package spring_data.game_store.service;

import spring_data.game_store.domain.dto.UserRegisterDto;

public interface DtoService {
    UserRegisterDto generateUserRegistrationDto(String[] input);

}
