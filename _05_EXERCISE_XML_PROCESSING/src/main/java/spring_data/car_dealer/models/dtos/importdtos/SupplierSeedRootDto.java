package spring_data.car_dealer.models.dtos.importdtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;


@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierSeedRootDto {

    @XmlElement(name = "supplier")
    private List<SupplierSeedDto> suppliers;

    public SupplierSeedRootDto() {
    }

    public SupplierSeedRootDto(List<SupplierSeedDto> suppliers) {
        this.suppliers = suppliers;
    }

    public List<SupplierSeedDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierSeedDto> suppliers) {
        this.suppliers = suppliers;
    }
}
