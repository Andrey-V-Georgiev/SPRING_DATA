package spring_data.car_dealer.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_data.car_dealer.models.dtos.SupplierSeedDto;
import spring_data.car_dealer.models.dtos.SuppliersSeedRootDto;
import spring_data.car_dealer.models.entities.Supplier;
import spring_data.car_dealer.repositories.SupplierRepository;
import spring_data.car_dealer.services.SupplierService;
import spring_data.car_dealer.utils.RandomUtil;
import spring_data.car_dealer.utils.ValidationUtil;
import spring_data.car_dealer.utils.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final SupplierRepository supplierRepository;
    private final RandomUtil randomUtil;

    @Autowired
    public SupplierServiceImpl(XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper, SupplierRepository supplierRepository, RandomUtil randomUtil) {
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.supplierRepository = supplierRepository;
        this.randomUtil = randomUtil;
    }

    @Override
    public void seedSuppliers(String filePath) throws JAXBException, FileNotFoundException {
        SuppliersSeedRootDto suppliersRootDtos = this.xmlParser.unmarshalFromFile(filePath, SuppliersSeedRootDto.class);
        if (this.validationUtil.isValid(suppliersRootDtos)) {
            List<SupplierSeedDto> suppliersDtos = suppliersRootDtos.getSuppliers();
            for (SupplierSeedDto s : suppliersDtos) {
                Supplier supplierByName = this.supplierRepository.findSupplierByName(s.getName());
                if (supplierByName == null) {
                    Supplier supplier = this.modelMapper.map(s, Supplier.class);
                    this.supplierRepository.saveAndFlush(supplier);
                } else {
                    System.out.printf("Supplier with name %s already exists%n", s.getName());
                }
            }
        } else {
            this.validationUtil.printConstraintViolations(suppliersRootDtos);
        }
    }

    @Override
    public Supplier getRandomSupplier() {
        int repoCount = (int) this.supplierRepository.count();
        Supplier randomSupplier = (Supplier) this.randomUtil.getRandomInstance(
                repoCount,  Supplier.class, this.supplierRepository
        );
        return randomSupplier;
    }
}
