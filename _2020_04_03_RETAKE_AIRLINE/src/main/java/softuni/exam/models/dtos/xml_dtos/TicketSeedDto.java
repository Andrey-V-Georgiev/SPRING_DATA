package softuni.exam.models.dtos.xml_dtos;

import com.sun.istack.Nullable;
import org.hibernate.validator.constraints.Length;
import softuni.exam.adapters.LocalDateTimeAdapterXML;

import javax.validation.constraints.DecimalMin;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketSeedDto {

    @XmlElement(name = "serial-number")
    private String serialNumber;

    @XmlElement(name = "price")
    private String price;

    @XmlElement(name = "take-off")
    @XmlJavaTypeAdapter(LocalDateTimeAdapterXML.class)
    private LocalDateTime takeOff;


    @XmlElement(name = "from-town")
    private TicketSeedFromTownDto fromTown;

    @XmlElement(name = "to-town")
    private TicketSeedToTownDto toTown;

    @XmlElement(name = "passenger")
    private TicketSeedPassengerDto passenger;

    @XmlElement(name = "plane")
    private TicketSeedPlaneDto plane;

    public TicketSeedDto() {
    }

    public TicketSeedDto(String serialNumber, String price, LocalDateTime takeOff, TicketSeedFromTownDto fromTown, TicketSeedToTownDto toTown, TicketSeedPassengerDto passenger, TicketSeedPlaneDto plane) {
        this.serialNumber = serialNumber;
        this.price = price;
        this.takeOff = takeOff;
        this.fromTown = fromTown;
        this.toTown = toTown;
        this.passenger = passenger;
        this.plane = plane;
    }

    @Length(min = 2)
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    @DecimalMin("0")
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Nullable
    public LocalDateTime getTakeOff() {
        return takeOff;
    }

    public void setTakeOff(LocalDateTime takeOff) {
        this.takeOff = takeOff;
    }

    @Nullable
    public TicketSeedFromTownDto getFromTown() {
        return fromTown;
    }

    public void setFromTown(TicketSeedFromTownDto fromTown) {
        this.fromTown = fromTown;
    }

    @Nullable
    public TicketSeedToTownDto getToTown() {
        return toTown;
    }

    public void setToTown(TicketSeedToTownDto toTown) {
        this.toTown = toTown;
    }

    @Nullable
    public TicketSeedPassengerDto getPassenger() {
        return passenger;
    }

    public void setPassenger(TicketSeedPassengerDto passenger) {
        this.passenger = passenger;
    }

    @Nullable
    public TicketSeedPlaneDto getPlane() {
        return plane;
    }

    public void setPlane(TicketSeedPlaneDto plane) {
        this.plane = plane;
    }
}
