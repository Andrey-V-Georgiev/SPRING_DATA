package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.constants.GlobalConstants;
import softuni.exam.models.dtos.offerDtos.OfferSeedDto;
import softuni.exam.models.dtos.offerDtos.OfferSeedRootDto;
import softuni.exam.models.entities.Car;
import softuni.exam.models.entities.Offer;
import softuni.exam.models.entities.Seller;
import softuni.exam.repository.OfferRepository;
import softuni.exam.service.CarService;
import softuni.exam.service.OfferService;
import softuni.exam.utils.FileUtil;
import softuni.exam.utils.ValidationUtil;
import softuni.exam.utils.XmlParser;
import softuni.exam.service.SellerService;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OfferServiceImpl implements OfferService {

    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final OfferRepository offerRepository;
    private final CarService carService;
    private final SellerService sellerService;

    @Autowired
    public OfferServiceImpl(XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, OfferRepository offerRepository, CarService carService, SellerService sellerService) {
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.offerRepository = offerRepository;
        this.carService = carService;
        this.sellerService = sellerService;
    }

    @Override
    public boolean areImported() {
        return this.offerRepository.count() > 0;
    }

    @Override
    public String readOffersFileContent() throws IOException {
        String inputString = this.fileUtil.readFileAddedNewLines(GlobalConstants.OFFERS_INPUT_PATH);
        return inputString;
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        /* Parse the XMLs to Dtos */
        OfferSeedRootDto rootDto = this.xmlParser
                .unmarshalFromFile(GlobalConstants.OFFERS_INPUT_PATH, OfferSeedRootDto.class);

        List<OfferSeedDto> dtos = rootDto.getOffers();
        for (OfferSeedDto dto : dtos) {
            if (this.validationUtil.isValid(dto)) {
                /* Prevent duplicates */
                Optional<Offer> offerOptional = this.offerRepository
                        .findOfferByPriceAndCarIdAndSellerId(dto.getPrice(), dto.getCar().getId(), dto.getSeller().getId());
                /* Validate the dto */
                if (offerOptional.isEmpty()) {
                    Offer offer = this.modelMapper.map(dto, Offer.class);
                    /* Set additional mappings */
                    Car carById = this.carService.findCarById(dto.getCar().getId());
                    Seller sellerById = this.sellerService.findSellerById(dto.getSeller().getId());
                    offer.setCar(carById);
                    offer.setSeller(sellerById);
                    /* Save entity to DB */
                    this.offerRepository.saveAndFlush(offer);
                    sb.append(String.format("Successfully added offer %s\n", dto.getPrice()));
                }
            } else {
                sb.append("Invalid offer\n");
            }
        }
        return sb.toString();
    }
}
