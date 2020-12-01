package softuni.car_dealer_exam.service;

import softuni.car_dealer_exam.models.entities.Car;

import java.io.IOException;

public interface CarService {

    boolean areImported();

    String readCarsFileContent() throws IOException;
	
	String importCars() throws IOException;

    String getCarsOrderByPicturesCountThenByMake();

    Car findCarById(Long id);
}
