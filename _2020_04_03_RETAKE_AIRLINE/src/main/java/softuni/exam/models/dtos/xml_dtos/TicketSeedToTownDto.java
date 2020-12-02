package softuni.exam.models.dtos.xml_dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "to-town")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketSeedToTownDto {

    @XmlElement(name = "name")
    private String name;

    public TicketSeedToTownDto() {
    }

    public TicketSeedToTownDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
