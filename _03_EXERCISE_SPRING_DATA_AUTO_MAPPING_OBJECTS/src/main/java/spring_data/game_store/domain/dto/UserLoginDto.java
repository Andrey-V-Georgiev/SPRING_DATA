package spring_data.game_store.domain.dto;

import spring_data.game_store.constants.ErrorConstants;
import spring_data.game_store.constants.RegexConstants;

import javax.persistence.Column;
import javax.validation.constraints.Pattern;

public class UserLoginDto {

    private String email;
    private String password;

    public UserLoginDto() {
    }

    public UserLoginDto(String email, String password ) {
        this.email = email;
        this.password = password;
    }

    @Pattern(regexp = RegexConstants.EMAIL_REGEX , message = ErrorConstants.USER_EMAIL_ERROR)
    @Column(name = "email", nullable = false,unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Pattern(regexp = RegexConstants.PASSWORD_REGEX , message = ErrorConstants.USER_PASSWORD_ERROR)
    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
