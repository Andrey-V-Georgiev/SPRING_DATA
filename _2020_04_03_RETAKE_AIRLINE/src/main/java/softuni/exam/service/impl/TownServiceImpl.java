package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.constants.GlobalConstants;
import softuni.exam.models.dtos.json_dtos.TownSeedDto;
import softuni.exam.models.entities.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;
import softuni.exam.utils.FileUtil;
import softuni.exam.utils.ValidationUtil;

import java.io.IOException;
import java.util.Optional;

@Service
public class TownServiceImpl implements TownService {

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final TownRepository townRepository;

    @Autowired
    public TownServiceImpl(Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, TownRepository townRepository) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.townRepository = townRepository;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() > 0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        String inputString = this.fileUtil.readFileAddedNewLines(GlobalConstants.TOWNS_INPUT_PATH);
        return inputString;
    }

    @Override
    public String importTowns() throws IOException {
        StringBuilder sb = new StringBuilder();
        /* Get the JSON */
        String inputString = this.fileUtil.readFileAddedNewLines(GlobalConstants.TOWNS_INPUT_PATH);
        /* Turn it to dtos */
        TownSeedDto[] dtos = this.gson.fromJson(inputString, TownSeedDto[].class);
        for (TownSeedDto dto : dtos) {
            Optional<Town> townOptional = this.townRepository
                    .findTownByNameAndPopulation(dto.getName(), dto.getPopulation());
            /* If no present in DB */
            if(townOptional.isEmpty()) {
                if (this.validationUtil.isValid(dto)) {
                    Town town = this.modelMapper.map(dto, Town.class);
                    this.townRepository.saveAndFlush(town);
                    sb.append(String.format("Successfully imported town - %s %d\n", dto.getName(), dto.getPopulation()));
                } else {
                    sb.append("Invalid town\n");
                }
            }
        }
        return sb.toString();
    }

    @Override
    public Town findTownByName(String name) {
        return this.townRepository.findTownByName(name);
    }
}
