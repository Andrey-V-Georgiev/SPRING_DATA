package spring_data.car_dealer.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import spring_data.car_dealer.models.dtos.exportdtos.PartExportDto;
import spring_data.car_dealer.models.dtos.importdtos.PartSeedDto;
import spring_data.car_dealer.models.dtos.importdtos.PartSeedRootDto;
import spring_data.car_dealer.models.entities.Part;
import spring_data.car_dealer.models.entities.Supplier;
import spring_data.car_dealer.repositories.PartRepository;
import spring_data.car_dealer.services.PartService;
import spring_data.car_dealer.services.RandomService;
import spring_data.car_dealer.services.SupplierService;
import spring_data.car_dealer.utils.ValidationUtil;
import spring_data.car_dealer.utils.XmlParser;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PartServiceImpl implements PartService {

    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final PartRepository partRepository;
    private final SupplierService supplierService;
    private final RandomService randomService;

    public PartServiceImpl(XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper, PartRepository partRepository, SupplierService supplierService, RandomService randomService) {
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.partRepository = partRepository;
        this.supplierService = supplierService;
        this.randomService = randomService;
    }

    @Override
    public void seedParts(String filePath) throws JAXBException, FileNotFoundException {
        /* Parse the XMLs to Dtos */
        PartSeedRootDto partSeedRootDto = this.xmlParser.unmarshalFromFile(filePath, PartSeedRootDto.class);
        /* Validate the Dtos */
        if (this.validationUtil.isValid(partSeedRootDto)) {
            /* Get the parts from root Dto */
            List<PartSeedDto> partSeedDtos = partSeedRootDto.getParts();
            for (PartSeedDto p : partSeedDtos) {
                if (this.validationUtil.isValid(p)) {
                    /* Check in DB for already existing entity */
                    Optional<Part> partOptional = this.partRepository.findPartByName(p.getName());
                    if (partOptional.isEmpty()) {
                        /* If there is no occurrence save */
                        Part part = this.modelMapper.map(p, Part.class);
                        /* Set random supplier */
                        Supplier randomSupplier = this.supplierService.getRandomSupplier();
                        part.setSupplier(randomSupplier);
                        this.partRepository.saveAndFlush(part);
                    } else {
                        System.out.printf("Part with name %s already exists%n", p.getName());
                    }
                } else {
                    this.validationUtil.printConstraintViolations(p);
                }
            }
        } else {
            this.validationUtil.printConstraintViolations(partSeedRootDto);
        }
    }

    @Override
    public List<Part> getRandomPartList(int lowerBorder, int upperBorder) {
        /* Get random parts list by randomService */
        List<Part> randomPartsList = this.randomService.getListOfRandomInstances(
                lowerBorder, upperBorder, Part.class, this.partRepository
        );
        return randomPartsList;
    }

    @Override
    public List<PartExportDto> findPartsAllPerCarId(Long carId) {
        List<PartExportDto> partDtos = this.partRepository.findPartsAllPerCarId(carId);
        return partDtos;
    }
}
