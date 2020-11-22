package spring_data.car_dealer.models.dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;


@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SuppliersSeedRootDto {

    @XmlElement(name = "supplier")
    private List<SupplierSeedDto> suppliers;

    public SuppliersSeedRootDto() {
    }

    public SuppliersSeedRootDto(List<SupplierSeedDto> suppliers) {
        this.suppliers = suppliers;
    }

    public List<SupplierSeedDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierSeedDto> suppliers) {
        this.suppliers = suppliers;
    }
}
