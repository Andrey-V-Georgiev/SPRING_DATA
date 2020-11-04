package spring_data.game_store.domain.dto;

import spring_data.game_store.constants.ErrorConstants;
import spring_data.game_store.constants.RegexConstants;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserRegisterDto {
    /* RegisterUser|<email>|<password>|<confirmPassword>|<fullName>  */
    private String email;
    private String password;
    private String fullName;

    public UserRegisterDto() {
    }

    public UserRegisterDto(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    @Pattern(regexp = RegexConstants.EMAIL_REGEX , message = ErrorConstants.USER_EMAIL_ERROR)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Pattern(regexp = RegexConstants.PASSWORD_REGEX , message = ErrorConstants.USER_PASSWORD_ERROR)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotNull
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
