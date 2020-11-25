package spring_data.car_dealer.services;

import spring_data.car_dealer.models.entities.Supplier;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface SupplierService {

    void seedSuppliers(String filePath) throws JAXBException, FileNotFoundException;

    Supplier getRandomSupplier();

    void exportLocalSuppliers(String filePath) throws JAXBException;
}
