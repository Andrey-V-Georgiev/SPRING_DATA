package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.constants.GlobalConstants;
import softuni.exam.models.dtos.jsondtos.PassengerSeedDto;
import softuni.exam.models.entities.Passenger;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.service.PassengerService;
import softuni.exam.service.TownService;
import softuni.exam.utils.FileUtil;
import softuni.exam.utils.ValidationUtil;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
public class PassengerServiceImpl implements PassengerService {

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final PassengerRepository passengerRepository;
    private final TownService townService;

    @Autowired
    public PassengerServiceImpl(Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, PassengerRepository passengerRepository, TownService townService) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.passengerRepository = passengerRepository;
        this.townService = townService;
    }

    @Override
    public boolean areImported() {
        return this.passengerRepository.count() > 0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        String inputString = this.fileUtil.readFileAddedNewLines(GlobalConstants.PASSENGERS_INPUT_PATH);
        return inputString;
    }

    @Override
    public String importPassengers() throws IOException {
        StringBuilder sb = new StringBuilder();
        /* Get the JSON */
        String inputString = this.fileUtil.readFileAddedNewLines(GlobalConstants.PASSENGERS_INPUT_PATH);
        /* Turn it to dtos */
        PassengerSeedDto[] dtos = this.gson.fromJson(inputString, PassengerSeedDto[].class);
        for (PassengerSeedDto dto : dtos) {
            Optional<Passenger> passengerOptional = this.passengerRepository.findPassengerByEmail(dto.getEmail());
            /* If no present in DB */
            if(passengerOptional.isEmpty()) {
                if (this.validationUtil.isValid(dto)) {
                    Passenger passenger = this.modelMapper.map(dto, Passenger.class);
                    Town townByName = this.townService.findTownByName(dto.getTown());
                    passenger.setTown(townByName);

                    this.passengerRepository.saveAndFlush(passenger);
                    sb.append(String.format("Successfully imported passenger - %s %s\n", dto.getFirstName(), dto.getLastName()));
                } else {
                    sb.append("Invalid passenger\n");
                }
            }
        }
        return sb.toString();
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {
        return null;
    }
}
