package softuni.exam.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "offers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"description", "added_on"})
})
public class Offer extends BaseEntity {

    private BigDecimal price;
    private String description;
    private Boolean hasGoldStatus;
    private LocalDateTime addedOn;
    private Car car;
    private Seller seller;
    private List<Picture> pictures = new ArrayList<>();

    public Offer() {
    }

    public Offer(BigDecimal price, String description, Boolean hasGoldStatus, LocalDateTime addedOn) {
        this.price = price;
        this.description = description;
        this.hasGoldStatus = hasGoldStatus;
        this.addedOn = addedOn;
    }

    public Offer(BigDecimal price, String description, Boolean hasGoldStatus, LocalDateTime addedOn, Car car, Seller seller) {
        this.price = price;
        this.description = description;
        this.hasGoldStatus = hasGoldStatus;
        this.addedOn = addedOn;
        this.car = car;
        this.seller = seller;
    }

    @DecimalMin(value = "0")
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(name = "description", columnDefinition="TEXT")
    @Length(min = 5)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "has_gold_status")
    public Boolean getHasGoldStatus() {
        return hasGoldStatus;
    }

    public void setHasGoldStatus(Boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
    }

    @Column(name = "added_on")
    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "car_id")
    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "saller_id")
    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    @ManyToMany(cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
}
