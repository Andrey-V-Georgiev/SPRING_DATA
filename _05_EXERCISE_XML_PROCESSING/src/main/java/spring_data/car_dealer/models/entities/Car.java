package spring_data.car_dealer.models.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car extends BaseEntity {

    private String make;
    private String model;
    private int travelledDistance;
    private List<Part> parts = new ArrayList<>();

    public Car() {
    }

    public Car(String make, String model, int travelledDistance) {
        this.make = make;
        this.model = model;
        this.travelledDistance = travelledDistance;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "travelled_distance")
    public int getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(int travelledDistance) {
        this.travelledDistance = travelledDistance;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    public List<Part> getParts() {
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }
}
