package softuni.exam.models.entities;

import org.hibernate.validator.constraints.Length;
import softuni.exam.enumerations.Rating;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {

    private String name;
    private LocalDateTime dateAndTime;
    private Car car;
    private List<Offer> offers = new ArrayList<>();

    public Picture() {
    }

    public Picture(String name, LocalDateTime dateAndTime) {
        this.name = name;
        this.dateAndTime = dateAndTime;
    }

    public Picture(String name, LocalDateTime dateAndTime, Car car) {
        this.name = name;
        this.dateAndTime = dateAndTime;
        this.car = car;
    }

    @Column(name = "name")
    @Length(min = 2, max = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "date_and_time")
    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToMany(mappedBy = "pictures")
    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
