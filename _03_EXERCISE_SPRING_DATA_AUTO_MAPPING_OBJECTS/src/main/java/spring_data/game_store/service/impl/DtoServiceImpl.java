package spring_data.game_store.service.impl;

import org.springframework.stereotype.Service;
import spring_data.game_store.domain.dto.UserLoginDto;
import spring_data.game_store.domain.dto.UserRegisterDto;
import spring_data.game_store.service.DtoService;

@Service
public class DtoServiceImpl implements DtoService {
    @Override
    public UserRegisterDto generateUserRegistrationDto(String[] input) {
        /* RegisterUser|<email>|<password>|<confirmPassword>|<fullName> */
        String email = input[1];
        String password = input[2];
        String confirmPassword = input[3];
        String fullName = input[4];
        if (!password.equals(confirmPassword)) {
            return null;
        }
        UserRegisterDto dto = new UserRegisterDto(email, password, fullName);
        return dto;
    }

    @Override
    public UserLoginDto generateUserLoginDto(String[] input) {
        /* LoginUser|ivan@ivan.com|Ivan12 */
        String email = input[1];
        String password = input[2];
        UserLoginDto dto = new UserLoginDto(email, password);
        return dto;
    }
}
