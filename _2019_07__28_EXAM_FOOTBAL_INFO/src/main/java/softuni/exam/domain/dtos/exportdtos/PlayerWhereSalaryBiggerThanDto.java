package softuni.exam.domain.dtos.exportdtos;


import java.math.BigDecimal;

public class PlayerWhereSalaryBiggerThanDto {

    private String name;
    private Integer number;
    private BigDecimal salary;
    private String teamName;

    public PlayerWhereSalaryBiggerThanDto() {
    }

    public PlayerWhereSalaryBiggerThanDto(String name, Integer number, BigDecimal salary, String teamName) {
        this.name = name;
        this.number = number;
        this.salary = salary;
        this.teamName = teamName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
}
