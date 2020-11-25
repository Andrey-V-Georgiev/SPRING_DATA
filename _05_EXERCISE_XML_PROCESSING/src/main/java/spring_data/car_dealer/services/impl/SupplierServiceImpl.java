package spring_data.car_dealer.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_data.car_dealer.models.dtos.exportdtos.SupplierExportDto;
import spring_data.car_dealer.models.dtos.exportdtos.SupplierExportRootDto;
import spring_data.car_dealer.models.dtos.importdtos.SupplierSeedDto;
import spring_data.car_dealer.models.dtos.importdtos.SupplierSeedRootDto;
import spring_data.car_dealer.models.entities.Supplier;
import spring_data.car_dealer.repositories.SupplierRepository;
import spring_data.car_dealer.services.RandomService;
import spring_data.car_dealer.services.SupplierService;
import spring_data.car_dealer.utils.ValidationUtil;
import spring_data.car_dealer.utils.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {

    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final SupplierRepository supplierRepository;
    private final RandomService randomService;

    @Autowired
    public SupplierServiceImpl(XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper, SupplierRepository supplierRepository, RandomService randomService) {
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.supplierRepository = supplierRepository;
        this.randomService = randomService;
    }

    @Override
    public void seedSuppliers(String filePath) throws JAXBException, FileNotFoundException {
        SupplierSeedRootDto supplierRootDtos = this.xmlParser.unmarshalFromFile(filePath, SupplierSeedRootDto.class);
        if (this.validationUtil.isValid(supplierRootDtos)) {
            List<SupplierSeedDto> suppliersDtos = supplierRootDtos.getSuppliers();
            for (SupplierSeedDto s : suppliersDtos) {
                if (this.validationUtil.isValid(s)) {
                    Optional<Supplier> supplierOptional = this.supplierRepository.findSupplierByName(s.getName());
                    if (supplierOptional.isEmpty()) {
                        Supplier supplier = this.modelMapper.map(s, Supplier.class);
                        this.supplierRepository.saveAndFlush(supplier);
                    } else {
                        System.out.printf("Supplier with name %s already exists%n", s.getName());
                    }
                } else {
                    this.validationUtil.printConstraintViolations(s);
                }
            }
        } else {
            this.validationUtil.printConstraintViolations(supplierRootDtos);
        }
    }

    @Override
    public Supplier getRandomSupplier() {
        int repoCount = (int) this.supplierRepository.count();
        Supplier randomSupplier = this.randomService.getRandomInstance(
                repoCount, Supplier.class, this.supplierRepository
        );
        return randomSupplier;
    }

    @Override
    public void exportLocalSuppliers(String filePath) throws JAXBException {
        List<SupplierExportDto> supplierExportDtos = this.supplierRepository.findSuppliersNotImporters();
        SupplierExportRootDto supplierExportRootDto = new SupplierExportRootDto();
        supplierExportRootDto.setSuppliers(supplierExportDtos);
        this.xmlParser.marshalToFile(filePath, supplierExportRootDto);
    }
}
