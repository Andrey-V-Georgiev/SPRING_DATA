package spring_data.car_dealer.models.dtos.exportdtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierExportRootDto {

    @XmlElement(name = "supplier")
    List<SupplierExportDto> suppliers = new ArrayList<>();

    public SupplierExportRootDto() {
    }

    public SupplierExportRootDto(List<SupplierExportDto> suppliers) {
        this.suppliers = suppliers;
    }

    public List<SupplierExportDto> getSuppliers() {
        return suppliers;
    }

    public void setSuppliers(List<SupplierExportDto> suppliers) {
        this.suppliers = suppliers;
    }
}
