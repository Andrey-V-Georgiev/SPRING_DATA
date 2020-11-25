package spring_data.car_dealer.models.dtos.exportdtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarExportRootDto2 {

    @XmlElement(name = "car")
    List<CarExportDto2> cars = new ArrayList<>();

    public CarExportRootDto2() {
    }

    public CarExportRootDto2(List<CarExportDto2> cars) {
        this.cars = cars;
    }

    public List<CarExportDto2> getCars() {
        return cars;
    }

    public void setCars(List<CarExportDto2> cars) {
        this.cars = cars;
    }
}
