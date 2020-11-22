package spring_data.car_dealer.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    private String name;
    private LocalDate dateOfBirth;
    private boolean isYoungDriver;

    public Customer() {
    }

    public Customer(String name, LocalDate dateOfBirth, boolean isYoungDriver) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.isYoungDriver = isYoungDriver;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "birth_date")
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Column(name = "is_young_driver")
    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
