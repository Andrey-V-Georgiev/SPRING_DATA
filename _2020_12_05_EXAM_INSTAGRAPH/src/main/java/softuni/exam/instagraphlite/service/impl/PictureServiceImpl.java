package softuni.exam.instagraphlite.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.instagraphlite.constants.GlobalConstants;
import softuni.exam.instagraphlite.models.dtos.picture.PictureSeedDto;
import softuni.exam.instagraphlite.models.entities.Picture;
import softuni.exam.instagraphlite.repository.PictureRepository;
import softuni.exam.instagraphlite.service.PictureService;
import softuni.exam.instagraphlite.utils.FileUtil;
import softuni.exam.instagraphlite.utils.ValidationUtil;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpl(Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, PictureRepository pictureRepository) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readFromFileContent() throws IOException {
        String inputString = this.fileUtil.readFileAddedNewLines(GlobalConstants.PICTURES_INPUT_PATH);
        return inputString;
    }

    @Override
    public String importPictures() throws IOException {
        StringBuilder sb = new StringBuilder();
        /* Get the JSON */
        String inputString = this.fileUtil.readFileAddedNewLines(GlobalConstants.PICTURES_INPUT_PATH);

        /* Turn it to dtos */
        PictureSeedDto[] dtos = this.gson.fromJson(inputString, PictureSeedDto[].class);
        for (PictureSeedDto dto : dtos) {
            /* Prevent duplicates */
            Optional<Picture> pictureOptional = this.pictureRepository.findPictureByPath(dto.getPath());

            /* Validate dtos */
            if(pictureOptional.isEmpty()) {
                if (this.validationUtil.isValid(dto)) {
                    Picture picture = this.modelMapper.map(dto, Picture.class);
                    this.pictureRepository.saveAndFlush(picture);

                    /* Save to DB */
                    sb.append(String.format("Successfully imported Picture, with size %.2f\n", dto.getSize()));
                } else {
                    sb.append("Invalid Picture\n");
                }
            }
        }
        return sb.toString();
    }

    @Override
    public String exportPictures() {
        StringBuilder sb = new StringBuilder();

        /* Get the pictures */
        List<Picture> pictures = this.pictureRepository.findPicturesBySizeGreaterThan(30000.0);
        for (Picture p : pictures) {
            sb.append(String.format("%.2f - %s\n", p.getSize(), p.getPath()));
        }
        return sb.toString();
    }

    @Override
    public Optional<Picture> findPictureByPath(String path) {
        return this.pictureRepository.findPictureByPath(path);
    }
}
