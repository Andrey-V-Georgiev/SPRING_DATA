package spring_data.product_shop.services;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_data.product_shop.models.dtos.ProductSeedDto;
import spring_data.product_shop.models.entities.Product;
import spring_data.product_shop.models.entities.User;
import spring_data.product_shop.repositories.ProductRepository;
import spring_data.product_shop.utils.ValidationUtil;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileReader;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final ProductRepository productRepository;
    private final UserService userService;

    @Autowired
    public ProductServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, ProductRepository productRepository, UserService userService) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    public void seedProducts(String filePath) throws FileNotFoundException {
        /* Read the json */
        ProductSeedDto[] dtos = this.gson.fromJson(new FileReader(filePath), ProductSeedDto[].class);
        for (ProductSeedDto dto : dtos) {
            Product productFromDb = this.productRepository.getProductByNameAndPrice(dto.getName(), dto.getPrice());
            if (productFromDb != null) {
                continue;
            }
            /* Validate the dtos */
            if (this.validationUtil.isValid(dto)) {
                Product product = this.modelMapper.map(dto, Product.class);
                /* Set random seller */
                User randomSeller = this.userService.getRandomUser();
                product.setSeller(randomSeller);
                /* Set random buyers */
                User randomBuyerOrNull = this.userService.getRandomUserOrNull();
                product.setBuyer(randomBuyerOrNull);
                this.productRepository.saveAndFlush(product);
            } else {
                this.validationUtil.printConstraintViolations(dto);
            }
        }
    }
}
