package spring_data.car_dealer.services;

import spring_data.car_dealer.models.entities.Customer;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface CustomerService {

    void seedCustomers(String filePath) throws JAXBException, FileNotFoundException;

    Customer getRandomCustomer();

    void exportOrderedCustomers(String filePath) throws JAXBException;

    void exportTotalSalesByCustomer(String filePath) throws JAXBException;
}
