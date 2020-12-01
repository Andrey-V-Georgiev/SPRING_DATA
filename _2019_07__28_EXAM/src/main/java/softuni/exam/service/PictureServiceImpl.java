package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.constants.GlobalConstants;
import softuni.exam.domain.dtos.picturedtos.PictureSeedDto;
import softuni.exam.domain.dtos.picturedtos.PictureSeedRootDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.repository.PictureRepository;
import softuni.exam.utils.FileUtil;
import softuni.exam.utils.ValidationUtil;
import softuni.exam.utils.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PictureServiceImpl implements PictureService {

    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpl(XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, PictureRepository pictureRepository) {
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.pictureRepository = pictureRepository;
    }

    @Override
    public String importPictures() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        /* Parse the XMLs to Dtos */
        PictureSeedRootDto pictureSeedRootDto = this.xmlParser
                .unmarshalFromFile(GlobalConstants.PICTURES_INPUT_PATH, PictureSeedRootDto.class);

        /* Validate the Dtos */
        List<PictureSeedDto> pictureSeedDtos = pictureSeedRootDto.getPictures();
        for (PictureSeedDto dto : pictureSeedDtos) {
            if (this.validationUtil.isValid(dto)) {

                Optional<Picture> pictureOptional = this.pictureRepository.findPictureByUrl(dto.getUrl());
                if (pictureOptional.isEmpty()) {
                    Picture picture = this.modelMapper.map(dto, Picture.class);
                    this.pictureRepository.saveAndFlush(picture);
                    sb.append(String.format("Successfully imported picture - %s\n", dto.getUrl()));
                }
            } else {
                sb.append("Invalid picture\n");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count() > 0;
    }

    @Override
    public String readPicturesXmlFile() throws IOException {
        String sellersXML = this.fileUtil.readFileAddedNewLines(GlobalConstants.PICTURES_INPUT_PATH);
        return sellersXML;
    }
}
