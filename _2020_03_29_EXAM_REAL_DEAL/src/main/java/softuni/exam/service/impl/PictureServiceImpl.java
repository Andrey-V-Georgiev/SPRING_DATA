package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.constants.GlobalConstants;
import softuni.exam.models.dtos.pictureDtos.PictureSeedDto;
import softuni.exam.models.entities.Car;
import softuni.exam.models.entities.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.PictureService;
import softuni.exam.utils.FileUtil;
import softuni.exam.utils.ValidationUtil;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Optional;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final CarService carService;
    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpl(Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, CarService carService, PictureRepository pictureRepository) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.carService = carService;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        String inputString = this.fileUtil.readFileAddedNewLines(GlobalConstants.PICTURES_INPUT_PATH);
        return inputString;
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder sb = new StringBuilder();
        /* Get the JSON */
        String inputString = this.readPicturesFromFile();
        /* Turn it to dtos */
        PictureSeedDto[] dtos = this.gson.fromJson(inputString, PictureSeedDto[].class);
        for (PictureSeedDto dto : dtos) {
            /* Prevent duplicates */
            Optional<Picture> pictureOptional = this.pictureRepository
                    .findPictureByName(dto.getName());
            if(pictureOptional.isEmpty()) {
                if (this.validationUtil.isValid(dto)) {
                    Picture picture = this.modelMapper.map(dto, Picture.class);
                    Car carById = this.carService.findCarById(dto.getCar());
                    picture.setCar(carById);
                    this.pictureRepository.saveAndFlush(picture);
                    sb.append(String.format("Successfully imported picture - %s\n", dto.getName()));
                } else {
                    sb.append("Invalid picture\n");
                }
            }
        }
        return sb.toString();
    }
}
