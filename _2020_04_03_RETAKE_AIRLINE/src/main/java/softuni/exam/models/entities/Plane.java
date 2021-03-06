package softuni.exam.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;

@Entity
@Table(name = "planes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"register_number"})
})
public class Plane extends BaseEntity {

    private String registerNumber;
    private Integer capacity;
    private String airline;

    public Plane() {
    }

    public Plane(String registerNumber, Integer capacity, String airline) {
        this.registerNumber = registerNumber;
        this.capacity = capacity;
        this.airline = airline;
    }

    @Column(name = "register_number")
    @Length(min = 5)
    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    @Column(name = "capacity")
    @Min(0)
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Column(name = "airline")
    @Length(min = 2)
    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }
}
