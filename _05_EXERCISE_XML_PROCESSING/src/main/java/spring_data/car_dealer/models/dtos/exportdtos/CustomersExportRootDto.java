package spring_data.car_dealer.models.dtos.exportdtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersExportRootDto {

    @XmlElement(name = "customer")
    List<CustomerExportDto> customers = new ArrayList<>();

    public CustomersExportRootDto() {
    }

    public CustomersExportRootDto(List<CustomerExportDto> customers) {
        this.customers = customers;
    }

    public List<CustomerExportDto> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerExportDto> customers) {
        this.customers = customers;
    }
}
