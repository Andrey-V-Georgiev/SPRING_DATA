package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.constants.GlobalConstants;
import softuni.exam.models.dtos.carDtos.CarSeedDto;
import softuni.exam.models.dtos.carDtos.CarViewDto;
import softuni.exam.models.entities.Car;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;
import softuni.exam.utils.FileUtil;
import softuni.exam.utils.ValidationUtil;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarServiceImpl implements CarService {

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, CarRepository carRepository) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.carRepository = carRepository;
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count() > 0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        String carsJson = this.fileUtil.readFileAddedNewLines(GlobalConstants.CARS_INPUT_PATH);
        return carsJson;
    }

    @Override
    public String importCars() throws IOException {
        /* Get the JSON */
        String carsJson = this.readCarsFileContent();
        /* Turn it to dtos */
        CarSeedDto[] carSeedDtos = this.gson.fromJson(carsJson, CarSeedDto[].class);
        StringBuilder sb = new StringBuilder();
        for (CarSeedDto dto : carSeedDtos) {
            Optional<Car> carOptional = this.carRepository
                    .findCarByMakeAndModelAndKilometers(dto.getMake(), dto.getModel(), dto.getKilometers());
            /* If no present in DB */
            if(carOptional.isEmpty()) {
                if (this.validationUtil.isValid(dto)) {
                    Car car = this.modelMapper.map(dto, Car.class);
                    this.carRepository.saveAndFlush(car);
                    sb.append(String.format("Successfully imported car - %s %s%n", dto.getMake(), dto.getModel()));
                } else {
                    sb.append("Invalid car");
                }
            }
        }
        return sb.toString();
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        List<CarViewDto> carViewDtos = this.carRepository.getCarsOrderByPicturesCountThenByMake();
        StringBuilder sb = new StringBuilder();
        for (CarViewDto dto : carViewDtos) {
            sb.append(String.format("Car make - %s, model - %s%n", dto.getMake(), dto.getModel()));
            sb.append(String.format("\tKilometers - %d%n", dto.getKilometers()));
            sb.append(String.format("\tRegistered on - %s%n", dto.getRegisteredOn()));
            sb.append(String.format("\tNumber of pictures - %d%n", dto.getNumberOfPictures()));
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public Car findCarById(Long id) {
        Car carById = this.carRepository.findCarById(id);
        return carById;
    }
}
