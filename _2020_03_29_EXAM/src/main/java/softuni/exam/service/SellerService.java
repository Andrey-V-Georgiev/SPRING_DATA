package softuni.car_dealer_exam.service;

import softuni.car_dealer_exam.models.entities.Seller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface SellerService {
    
    boolean areImported();

    String readSellersFromFile() throws IOException;

    String importSellers() throws IOException, JAXBException;

    Seller findSellerById(Long id);
}
