package spring_data.car_dealer.services;

import spring_data.car_dealer.models.entities.Car;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface CarService {

    void seedCars(String filePath) throws JAXBException, FileNotFoundException;

    Car getRandomCar();
}
