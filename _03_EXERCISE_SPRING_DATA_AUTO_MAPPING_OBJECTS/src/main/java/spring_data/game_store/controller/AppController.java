package spring_data.game_store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import spring_data.game_store.domain.dto.*;
import spring_data.game_store.service.DtoService;
import spring_data.game_store.service.GameService;
import spring_data.game_store.service.UserService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.BufferedReader;
import java.util.Set;

@Controller
public class AppController implements CommandLineRunner {

    private final BufferedReader bufferedReader;
    private final DtoService dtoService;
    private final Validator validator;
    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public AppController(BufferedReader bufferedReader, DtoService dtoService, Validator validator, UserService userService, GameService gameService) {
        this.bufferedReader = bufferedReader;
        this.dtoService = dtoService;
        this.validator = validator;
        this.userService = userService;
        this.gameService = gameService;
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
                    /* Check if email and confirmed email match */
                    if (userRegisterDto == null) {
                        System.out.println("Confirmed password doesn't match");
                        break;
                    }
                    /* Validate registration input data */
                    if (this.validator.validate(userRegisterDto).isEmpty()) {
                        try {
                            this.userService.registerUser(userRegisterDto);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                            break;
                        }
                    } else {
                        Set<ConstraintViolation<UserRegisterDto>> violations = this.validator.validate(userRegisterDto);
                        for (ConstraintViolation<UserRegisterDto> violation : violations) {
                            System.out.println(violation.getMessage());
                        }
                    }
                    break;

                case "LoginUser":
                    UserLoginDto userLoginDto = this.dtoService.generateUserLoginDto(input);
                    /* Validate login input data */
                    if (this.validator.validate(userLoginDto).isEmpty()) {
                        try {
                            this.userService.loginUser(userLoginDto);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        this.validator.validate(userLoginDto).forEach(System.out::println);
                    }
                    break;

                case "Logout":
                    this.userService.logoutUser();
                    break;

                case "AddGame":
                    /* Check for logged user */
                    if (!this.userService.isLogged()) {
                        System.out.println("No user was logged in\n");
                        break;
                    }
                    GameAddDto gameAddDto = this.dtoService.generateGameAddDto(input);
                    /* Validate DTO */
                    if (this.validator.validate(gameAddDto).isEmpty()) {
                        try {
                            this.gameService.addGame(gameAddDto);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        Set<ConstraintViolation<GameAddDto>> violations = this.validator.validate(gameAddDto);
                        for (ConstraintViolation<GameAddDto> violation : violations) {
                            System.out.println(violation.getMessage());
                        }
                    }
                    break;

                /* A game should be edited in case of valid id */
                case "EditGame":
                    /* Check for logged user */
                    if (!this.userService.isLogged()) {
                        System.out.println("No user was logged in\n");
                        break;
                    }
                    GameEditDto gameEditDto = this.dtoService.generateGameEditDto(input);
                    if (this.validator.validate(gameEditDto).isEmpty()) {
                        try {
                            this.gameService.editGame(gameEditDto);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        Set<ConstraintViolation<GameEditDto>> violations = this.validator.validate(gameEditDto);
                        for (ConstraintViolation<GameEditDto> violation : violations) {
                            System.out.println(violation.getMessage());
                        }
                    }
                    break;

                /* A game should be deleted in case of valid id */
                case "DeleteGame":
                    /* Check for logged user */
                    if (!this.userService.isLogged()) {
                        System.out.println("No user was logged in\n");
                        break;
                    }
                    GameDeleteDto gameDeleteDto = this.dtoService.generateGameDeleteDto(input);
                    if (this.validator.validate(gameDeleteDto).isEmpty()) {
                        try {
                            this.gameService.deleteGame(gameDeleteDto);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        Set<ConstraintViolation<GameDeleteDto>> violations = this.validator.validate(gameDeleteDto);
                        for (ConstraintViolation<GameDeleteDto> violation : violations) {
                            System.out.println(violation.getMessage());
                        }
                    }
                    break;

            }
        }
    }
}
