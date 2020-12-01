package softuni.car_dealer_exam.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@Table(name = "cars", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"make", "model", "kilometers"})
})
public class Car extends BaseEntity {

    private String make;
    private String model;
    private Integer kilometers;
    private LocalDate registeredOn;

    public Car() {
    }

    public Car(String make, String model, Integer kilometers, LocalDate registeredOn) {
        this.make = make;
        this.model = model;
        this.kilometers = kilometers;
        this.registeredOn = registeredOn;
    }

    @Column(name = "make")
    @Length(min = 2, max = 20)
    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Column(name = "model")
    @Length(min = 2, max = 20)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "kilometers")
    @Min(0)
    public Integer getKilometers() {
        return kilometers;
    }

    public void setKilometers(Integer kilometers) {
        this.kilometers = kilometers;
    }

    @Column(name = "registered_on")
    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }
}
