package spring_data.game_store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import spring_data.game_store.domain.dto.UserRegisterDto;
import spring_data.game_store.service.DtoService;
import spring_data.game_store.service.UserService;

import javax.validation.Validator;
import java.io.BufferedReader;

@Controller
public class AppController implements CommandLineRunner {

    private final BufferedReader bufferedReader;
    private final DtoService dtoService;
    private final Validator validator;
    private final UserService userService;

    @Autowired
    public AppController(BufferedReader bufferedReader, DtoService dtoService, Validator validator, UserService userService) {
        this.bufferedReader = bufferedReader;
        this.dtoService = dtoService;
        this.validator = validator;
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {

        while (true) {
            System.out.println("Enter input:");
            String[] input = bufferedReader.readLine().split("\\|");
            String command = input[0];
            switch (command) {
                case "RegisterUser":
                    UserRegisterDto userRegisterDto = this.dtoService.generateUserRegistrationDto(input);
                    if (userRegisterDto == null) {
                        System.out.println("Confirmed password doesn't match");
                        break;
                    }
                    if (this.validator.validate(userRegisterDto).isEmpty()) {
                        try {
                            this.userService.registerUser(userRegisterDto);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    } else {
                        this.validator.validate(userRegisterDto).forEach(System.out::println);
                    }
                    break;
                case "LoginUser":

                    break;
                case "Logout":

                    break;

            }
        }
    }
}
