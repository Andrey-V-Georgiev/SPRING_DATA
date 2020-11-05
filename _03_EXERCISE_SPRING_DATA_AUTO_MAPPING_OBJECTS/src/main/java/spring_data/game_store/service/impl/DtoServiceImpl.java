package spring_data.game_store.service.impl;

import org.springframework.stereotype.Service;
import spring_data.game_store.domain.dto.GameAddDto;
import spring_data.game_store.domain.dto.GameEditDto;
import spring_data.game_store.domain.dto.UserLoginDto;
import spring_data.game_store.domain.dto.UserRegisterDto;
import spring_data.game_store.service.DtoService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

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

    @Override
    public GameAddDto generateGameAddDto(String[] input) {
        /* AddGame|<title>|<price>|<size>|<trailer>|<thubnailURL>|<description>|<releaseDate> */
        String title = input[1];
        BigDecimal price = new BigDecimal(input[2]);
        Double size = Double.parseDouble(input[3]);
        String trailer = input[4];
        String image = input[5];
        String description = input[6];
        LocalDate releaseDate = LocalDate.from(DateTimeFormatter.ofPattern("dd-MM-yyyy").parse(input[7]));
        GameAddDto dto = new GameAddDto(title, price, size, trailer, image, description, releaseDate);
        return dto;
    }

    @Override
    public GameEditDto generateGameEditDto(String[] input) {

        GameEditDto dto = new GameEditDto();
        dto.setId(Long.parseLong(input[1]));
        String[] argArr = Arrays.copyOfRange(input, 2, input.length);

        for (String arg : argArr) {
            String[] pair = Arrays.stream(arg.split("=")).map(String::trim).toArray(String[]::new);
            String key = pair[0];
            String value = pair[1];
            switch(key) {
                case "title":
                    String title = value;
                    dto.setTitle(title);
                    break;
                case "price":
                    BigDecimal price = new BigDecimal(value);
                    dto.setPrice(price);
                    break;
                case "size":
                    Double size = Double.parseDouble(value);
                    dto.setSize(size);
                    break;
                case "trailer":
                    String trailer = value;
                    dto.setTrailer(trailer);
                    break;
                case "image":
                    String image = value;
                    dto.setImage(image);
                    break;
                case "description":
                    String description = value;
                    dto.setDescription(description);
                    break;
                case "releaseDate":
                    LocalDate releaseDate = LocalDate.from(DateTimeFormatter.ofPattern("dd-MM-yyyy").parse(value));
                    dto.setReleaseDate(releaseDate);
                    break;
            }
        }
        return dto;
    }
}
