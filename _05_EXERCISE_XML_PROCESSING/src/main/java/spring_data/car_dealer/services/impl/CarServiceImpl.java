package spring_data.car_dealer.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_data.car_dealer.models.dtos.exportdtos.*;
import spring_data.car_dealer.models.dtos.importdtos.CarSeedDto;
import spring_data.car_dealer.models.dtos.importdtos.CarSeedRootDto;
import spring_data.car_dealer.models.entities.Car;
import spring_data.car_dealer.models.entities.Part;
import spring_data.car_dealer.repositories.CarRepository;
import spring_data.car_dealer.services.CarService;
import spring_data.car_dealer.services.PartService;
import spring_data.car_dealer.services.RandomService;
import spring_data.car_dealer.utils.ValidationUtil;
import spring_data.car_dealer.utils.XmlParser;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final XmlParser xmlParser;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final CarRepository carRepository;
    private final PartService partService;
    private final RandomService randomService;

    @Autowired
    public CarServiceImpl(XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper, CarRepository carRepository, PartService partService, RandomService randomService) {
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.carRepository = carRepository;
        this.partService = partService;
        this.randomService = randomService;
    }

    @Override
    public void seedCars(String filePath) throws JAXBException, FileNotFoundException {
        /* Parse the XMLs to Dtos */
        CarSeedRootDto carSeedRootDto = this.xmlParser.unmarshalFromFile(filePath, CarSeedRootDto.class);
        /* Validate the Dtos */
        if (this.validationUtil.isValid(carSeedRootDto)) {
            /* Get the parts from root Dto */
            List<CarSeedDto> carSeedDtos = carSeedRootDto.getCars();
            for (CarSeedDto c : carSeedDtos) {
                if (this.validationUtil.isValid(c)) {
                    /* Check in DB for already existing entity */
                    Optional<Car> carOptional = this.carRepository.findCar(c.getMake(), c.getModel(), c.getTravelledDistance());
                    if (carOptional.isEmpty()) {
                        /* If there is no occurrence save */
                        Car car = this.modelMapper.map(c, Car.class);
                        /* Set random parts */
                        List<Part> randomPartList = this.partService.getRandomPartList(10, 20);
                        car.setParts(randomPartList);
                        this.carRepository.saveAndFlush(car);
                    } else {
                        System.out.println("Car already exists in DB");
                    }
                } else {
                    this.validationUtil.printConstraintViolations(c);
                }
            }
        } else {
            this.validationUtil.printConstraintViolations(carSeedRootDto);
        }
    }

    @Override
    public Car getRandomCar() {
        int repoCount = (int) this.carRepository.count();
        Car randomCar = this.randomService.getRandomInstance(
                repoCount,  Car.class, this.carRepository
        );
        return randomCar;
    }

    @Override
    public void exportCarsFromMake(String filePath, String make) throws JAXBException {
        List<Car> carsFromDb = this.carRepository.findCarByMakeAndTravelDistance(make);
        List<CarExportDto> carExportDtos = carsFromDb
                .stream()
                .map(c -> this.modelMapper.map(c, CarExportDto.class))
                .collect(Collectors.toList());
        CarExportRootDto carExportRootDto = new CarExportRootDto();
        carExportRootDto.setCars(carExportDtos);
        this.xmlParser.marshalToFile(filePath, carExportRootDto);
    }

    @Override
    public void exportCarsWithTheirListOfParts(String filePath) throws JAXBException {
        List<CarExportDto2> carExportDtos2 = this.carRepository.findAllCarExportDto2();
        for (CarExportDto2 carExportDto : carExportDtos2) {
            List<PartExportDto> partExportDtos = this.partService.findPartsAllPerCarId(carExportDto.getId());
            PartExportRootDto partExportRootDto = new PartExportRootDto();
            partExportRootDto.setParts(partExportDtos);
            carExportDto.setParts(partExportRootDto);
        }
        CarExportRootDto2 carExportRootDto2 = new CarExportRootDto2();
        carExportRootDto2.setCars(carExportDtos2);
        this.xmlParser.marshalToFile(filePath, carExportRootDto2);
    }
}
