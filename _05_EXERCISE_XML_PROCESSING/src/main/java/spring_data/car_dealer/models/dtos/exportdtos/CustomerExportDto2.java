package spring_data.car_dealer.models.dtos.exportdtos;

import javax.xml.bind.annotation.*;
import java.math.BigDecimal;

@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerExportDto2 {

    @XmlElement(name = "id")
    private Long id;

    @XmlElement(name = "full-name")
    private String fullName;

    @XmlElement(name = "bought-cars")
    private Long boughtCars;

    @XmlElement(name = "spent-money")
    private BigDecimal spentMoney;

    public CustomerExportDto2() {
    }

    public CustomerExportDto2(Long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
    }

    public Long getBoughtCars() {
        return boughtCars;
    }

    public void setBoughtCars(Long boughtCars) {
        this.boughtCars = boughtCars;
    }


}
