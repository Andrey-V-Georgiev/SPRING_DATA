package softuni.exam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return null;
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        return null;
    }
}
