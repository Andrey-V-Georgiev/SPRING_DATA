package spring_data.car_dealer.models.dtos;


import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierSeedDto {

    @XmlAttribute(name = "name")
    private String name;
    @XmlAttribute(name = "is-importer")
    private boolean isImporter;

    public SupplierSeedDto() {
    }

    public SupplierSeedDto(String name, boolean isImporter) {
        this.name = name;
        this.isImporter = isImporter;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public boolean isImporter() {
        return isImporter;
    }

    public void setImporter(boolean importer) {
        isImporter = importer;
    }
}
