package softuni.car_dealer_exam.models.dtos.offerDtos;

import org.hibernate.validator.constraints.Length;
import softuni.car_dealer_exam.adapters.LocalDateTimeAdapterXML;

import javax.validation.constraints.DecimalMin;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferSeedDto {

    @XmlElement(name = "description")
    private String description;

    @XmlElement(name = "price")
    private BigDecimal price;

    @XmlElement(name = "added-on")
    @XmlJavaTypeAdapter(LocalDateTimeAdapterXML.class)
    private LocalDateTime addedOn;

    @XmlElement(name = "has-gold-status")
    private Boolean hasGoldStatus;

    @XmlElement(name = "car")
    private OfferSeedCarDto car;

    @XmlElement(name = "seller")
    private OfferSeedSellerDto seller;


    public OfferSeedDto() {
    }

    public OfferSeedDto(String description, BigDecimal price, LocalDateTime addedOn, Boolean hasGoldStatus, OfferSeedCarDto car, OfferSeedSellerDto seller) {
        this.description = description;
        this.price = price;
        this.addedOn = addedOn;
        this.hasGoldStatus = hasGoldStatus;
        this.car = car;
        this.seller = seller;
    }

    @Length(min = 5)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @DecimalMin(value = "0")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getAddedOn() {
        return addedOn;
    }

    public void setAddedOn(LocalDateTime addedOn) {
        this.addedOn = addedOn;
    }

    public Boolean getHasGoldStatus() {
        return hasGoldStatus;
    }

    public void setHasGoldStatus(Boolean hasGoldStatus) {
        this.hasGoldStatus = hasGoldStatus;
    }

    public OfferSeedCarDto getCar() {
        return car;
    }

    public void setCar(OfferSeedCarDto car) {
        this.car = car;
    }

    public OfferSeedSellerDto getSeller() {
        return seller;
    }

    public void setSeller(OfferSeedSellerDto seller) {
        this.seller = seller;
    }
}
