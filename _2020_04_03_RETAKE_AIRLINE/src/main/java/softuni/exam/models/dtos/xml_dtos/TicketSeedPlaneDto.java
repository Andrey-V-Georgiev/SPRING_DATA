package softuni.exam.models.dtos.xml_dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "plane")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketSeedPlaneDto {

    @XmlElement(name = "register-number")
    private String registerNumber;

    public TicketSeedPlaneDto() {
    }

    public TicketSeedPlaneDto(String registerNumber) {
        this.registerNumber = registerNumber;
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public void setRegisterNumber(String registerNumber) {
        this.registerNumber = registerNumber;
    }
}