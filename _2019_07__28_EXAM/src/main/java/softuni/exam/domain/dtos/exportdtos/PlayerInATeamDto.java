package softuni.exam.domain.dtos.exportdtos;

import softuni.exam.enums.Position;

public class PlayerInATeamDto {

    private String firstName;
    private String lastName;
    private Position position;
    private Integer number;

    public PlayerInATeamDto() {
    }

    public PlayerInATeamDto(String firstName, String lastName, Position position, Integer number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
