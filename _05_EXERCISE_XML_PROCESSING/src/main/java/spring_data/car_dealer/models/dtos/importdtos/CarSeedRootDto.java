package spring_data.car_dealer.models.dtos.importdtos;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarSeedRootDto {

    @XmlElement(name = "car")
    List<CarSeedDto> cars = new ArrayList<>();

    public CarSeedRootDto() {
    }

    public CarSeedRootDto(List<CarSeedDto> cars) {
        this.cars = cars;
    }

    public List<CarSeedDto> getCars() {
        return cars;
    }

    public void setCars(List<CarSeedDto> cars) {
        this.cars = cars;
    }
}
