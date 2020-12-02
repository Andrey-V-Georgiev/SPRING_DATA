package softuni.exam.models.dtos.xml_dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "passenger")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketSeedPassengerDto {

    @XmlElement(name = "email")
    private String email;

    public TicketSeedPassengerDto() {
    }

    public TicketSeedPassengerDto(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
