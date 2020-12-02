package softuni.exam.models.dtos.xml_dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "tickets")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketSeedRootDto {

    @XmlElement(name = "ticket")
    private List<TicketSeedDto> tickets = new ArrayList<>();

    public TicketSeedRootDto() {
    }

    public TicketSeedRootDto(List<TicketSeedDto> tickets) {
        this.tickets = tickets;
    }

    public List<TicketSeedDto> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketSeedDto> tickets) {
        this.tickets = tickets;
    }
}
