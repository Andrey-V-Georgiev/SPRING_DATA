package softuni.car_dealer_exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.car_dealer_exam.constants.GlobalConstants;
import softuni.car_dealer_exam.models.dtos.offerDtos.OfferSeedDto;
import softuni.car_dealer_exam.models.dtos.offerDtos.OfferSeedRootDto;
import softuni.car_dealer_exam.models.entities.Car;
import softuni.car_dealer_exam.models.entities.Offer;
import softuni.car_dealer_exam.models.entities.Seller;
import softuni.car_dealer_exam.repository.OfferRepository;
import softuni.car_dealer_exam.service.CarService;
import softuni.car_dealer_exam.service.OfferService;
import softuni.car_dealer_exam.utils.FileUtil;
import softuni.car_dealer_exam.utils.ValidationUtil;
import softuni.car_dealer_exam.utils.XmlParser;
import softuni.car_dealer_exam.service.SellerService;

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
        String offersJson = this.fileUtil.readFileAddedNewLines(GlobalConstants.OFFERS_INPUT_PATH);
        return offersJson;
    }

    @Override
    public String importOffers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        /* Parse the XMLs to Dtos */
        OfferSeedRootDto offerSeedRootDto = this.xmlParser
                .unmarshalFromFile(GlobalConstants.OFFERS_INPUT_PATH, OfferSeedRootDto.class);
        List<OfferSeedDto> offerSeedDtos = offerSeedRootDto.getOffers();
        for (OfferSeedDto dto : offerSeedDtos) {
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
                    sb.append(String.format("Successfully added offer %s%n", dto.getPrice()));
                }
            } else {
                sb.append("Invalid offer%n");
            }
        }
        return sb.toString();
    }
}
