package spring_data.car_dealer.models.dtos;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerSeedRootDto {

    @XmlElement(name = "customer")
    List<CustomerSeedDto> customers = new ArrayList<>();

    public CustomerSeedRootDto() {
    }

    public CustomerSeedRootDto(List<CustomerSeedDto> customers) {
        this.customers = customers;
    }

    public List<CustomerSeedDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerSeedDto> customers) {
        this.customers = customers;
    }
}
