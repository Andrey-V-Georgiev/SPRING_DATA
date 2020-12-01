package softuni.exam.domain.dtos.playerdtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;
import softuni.exam.enums.Position;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PlayerSeedDto {

    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private Integer number;
    @Expose
    private BigDecimal salary;
    @Expose
    private Position position;
    @Expose
    private PlayerSeedPictureDto picture;
    @Expose
    private PlayerSeedTeamDto team;

    public PlayerSeedDto() {
    }

    public PlayerSeedDto(String firstName, String lastName, Integer number, BigDecimal salary, Position position, PlayerSeedPictureDto picture, PlayerSeedTeamDto team) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
        this.salary = salary;
        this.position = position;
        this.picture = picture;
        this.team = team;
    }

    @NotNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Length(min = 3, max = 15)
    @NotNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @Min(1)
    @Max(99)
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @NotNull
    @DecimalMin(value = "0")
    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @NotNull
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @NotNull
    public PlayerSeedPictureDto getPicture() {
        return picture;
    }

    public void setPicture(PlayerSeedPictureDto picture) {
        this.picture = picture;
    }

    @NotNull
    public PlayerSeedTeamDto getTeam() {
        return team;
    }

    public void setTeam(PlayerSeedTeamDto team) {
        this.team = team;
    }
}
