package spring_data.car_dealer.services;

import spring_data.car_dealer.models.entities.Part;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

public interface PartService {

    void seedParts(String filePath) throws JAXBException, FileNotFoundException;

    List<Part> getRandomPartList(int lowerBorder, int upperBorder);
}
