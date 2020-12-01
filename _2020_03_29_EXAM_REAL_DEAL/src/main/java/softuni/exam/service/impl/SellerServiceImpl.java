package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.constants.GlobalConstants;
import softuni.exam.models.dtos.sellerDtos.SellerSeedDto;
import softuni.exam.models.dtos.sellerDtos.SellerSeedRootDto;
import softuni.exam.models.entities.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.utils.FileUtil;
import softuni.exam.utils.ValidationUtil;
import softuni.exam.utils.XmlParser;
import softuni.exam.service.SellerService;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final SellerRepository sellerRepository;

    @Autowired
    public SellerServiceImpl(XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, SellerRepository sellerRepository) {
        this.xmlParser = xmlParser;

        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        String sellersXML = this.fileUtil.readFileAddedNewLines(GlobalConstants.SELLERS_INPUT_PATH);
        return sellersXML;
    }

    @Override
    public String importSellers() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();
        /* Parse the XMLs to Dtos */
        SellerSeedRootDto sellerSeedRootDto = this.xmlParser
                .unmarshalFromFile(GlobalConstants.SELLERS_INPUT_PATH, SellerSeedRootDto.class);
        /* Validate the Dtos */
        List<SellerSeedDto> dtos = sellerSeedRootDto.getSellers();
        for (SellerSeedDto dto : dtos) {
            if (this.validationUtil.isValid(dto)) {
                /* Prevent duplicates */
                Optional<Seller> sellerOptional = this.sellerRepository
                        .findSellerByFirstNameAndLastNameAndEmail(dto.getFirstName(), dto.getLastName(), dto.getEmail());
                if (sellerOptional.isEmpty()) {
                    Seller seller = this.modelMapper.map(dto, Seller.class);
                    this.sellerRepository.saveAndFlush(seller);
                    sb.append(String.format("Successfully added seller %s, %s\n", dto.getLastName(), dto.getEmail()));
                }
            } else {
                sb.append("Invalid Character\n");
            }
        }
        return sb.toString();
    }

    @Override
    public Seller findSellerById(Long id) {

        Seller sellerById = this.sellerRepository.findSellerById(id);
        return sellerById;
    }
}
