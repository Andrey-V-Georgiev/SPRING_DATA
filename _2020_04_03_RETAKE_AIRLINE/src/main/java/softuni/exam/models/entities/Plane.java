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

    private String registrationNumber;
    private Integer capacity;
    private String airline;

    public Plane() {
    }

    public Plane(String registrationNumber, Integer capacity, String airline) {
        this.registrationNumber = registrationNumber;
        this.capacity = capacity;
        this.airline = airline;
    }

    @Column(name = "register_number")
    @Length(min = 5)
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
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
