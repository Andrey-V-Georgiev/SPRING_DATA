package spring_data.car_dealer.models.dtos.exportdtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "customers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomersExportRootDto2 {

    @XmlElement(name = "customer")
    List<CustomerExportDto2> customers = new ArrayList<>();

    public CustomersExportRootDto2() {
    }

    public CustomersExportRootDto2(List<CustomerExportDto2> customers) {
        this.customers = customers;
    }

    public List<CustomerExportDto2> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerExportDto2> customers) {
        this.customers = customers;
    }
}
