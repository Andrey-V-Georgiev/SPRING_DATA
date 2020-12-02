package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.constants.GlobalConstants;
import softuni.exam.models.dtos.xml_dtos.PlaneSeedDto;
import softuni.exam.models.dtos.xml_dtos.PlaneSeedRootDto;
import softuni.exam.models.entities.Plane;
import softuni.exam.repository.PlaneRepository;
import softuni.exam.service.PlaneService;
import softuni.exam.utils.FileUtil;
import softuni.exam.utils.ValidationUtil;
import softuni.exam.utils.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PlaneServiceImpl implements PlaneService {

    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final PlaneRepository planeRepository;

    @Autowired
    public PlaneServiceImpl(XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, PlaneRepository planeRepository) {
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.planeRepository = planeRepository;
    }

    @Override
    public boolean areImported() {
        return this.planeRepository.count() > 0;
    }

    @Override
    public String readPlanesFileContent() throws IOException {
        String inputString = this.fileUtil.readFileAddedNewLines(GlobalConstants.PLANES_INPUT_PATH);
        return inputString;
    }

    @Override
    public String importPlanes() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        /* Parse the XMLs to Dtos */
        PlaneSeedRootDto rootDto = this.xmlParser
                .unmarshalFromFile(GlobalConstants.PLANES_INPUT_PATH, PlaneSeedRootDto.class);

        /* Validate the Dtos */
        List<PlaneSeedDto> dtos = rootDto.getPlanes();
        for (PlaneSeedDto dto : dtos) {
            if (this.validationUtil.isValid(dto)) {

                /* Prevent duplicates */
                Optional<Plane> pictureOptional = this.planeRepository
                        .findPlaneByRegisterNumber(dto.getRegisterNumber());

                if (pictureOptional.isEmpty()) {
                    Plane plane = this.modelMapper.map(dto, Plane.class);
                    this.planeRepository.saveAndFlush(plane);
                    sb.append(String.format(
                            "Successfully imported plane - %s %s\n", dto.getAirline(), dto.getRegisterNumber()));
                }
            } else {
                sb.append("Invalid plane\n");
            }
        }
        return sb.toString();
    }

    @Override
    public Plane findPlaneByRegisterNumber(String registerNumber) {
        return this.planeRepository.findPlaneByRegisterNumber(registerNumber).get();
    }
}
