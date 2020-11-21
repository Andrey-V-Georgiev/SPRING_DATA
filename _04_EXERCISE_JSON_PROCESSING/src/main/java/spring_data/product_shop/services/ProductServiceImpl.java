package spring_data.product_shop.services;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_data.product_shop.models.dtos.ProductExportDto;
import spring_data.product_shop.models.dtos.ProductSeedDto;
import spring_data.product_shop.models.entities.Category;
import spring_data.product_shop.models.entities.Product;
import spring_data.product_shop.models.entities.User;
import spring_data.product_shop.repositories.ProductRepository;
import spring_data.product_shop.utils.FileUtil;
import spring_data.product_shop.utils.ValidationUtil;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

import static spring_data.product_shop.constants.GlobalConstants.QUERY_1_FILE_PATH;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final FileUtil fileUtil;
    private final CategoryService categoryService;

    @Autowired
    public ProductServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, ProductRepository productRepository, UserService userService, FileUtil fileUtil, CategoryService categoryService) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.productRepository = productRepository;
        this.userService = userService;
        this.fileUtil = fileUtil;
        this.categoryService = categoryService;
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
                /* Set random categories */
                Set<Category> randomCategories = this.categoryService.getRandomCategories();
                product.setCategories(randomCategories);
                this.productRepository.saveAndFlush(product);
            } else {
                this.validationUtil.printConstraintViolations(dto);
            }
        }
    }

    @Override
    public void productsInRange(BigDecimal priceFrom, BigDecimal priceTo) throws IOException {
        List<Product> productsDB = this.productRepository.getAllByPriceBetweenAndBuyerIsNull(priceFrom, priceTo);
        List<ProductExportDto> productDtos =  new ArrayList<>();
        for (Product p : productsDB) {
            ProductExportDto exportDto = this.modelMapper.map(p, ProductExportDto.class);
            exportDto.setSeller(String.format("%s %s", p.getSeller().getFirstName(), p.getSeller().getLastName()));
            productDtos.add(exportDto);
        }
        String productsJson = this.gson.toJson(productDtos);
        this.fileUtil.writeFile(productsJson, QUERY_1_FILE_PATH);
    }

    @Override
    public List<ProductExportDto> getProductsWithBuyerBySellerId(long sellerId) {
        List<Product> productsDB = this.productRepository.getProductsWithBuyerBySellerId(sellerId);
        List<ProductExportDto> productDtos = new ArrayList<>();
        for (Product p : productsDB) {
            ProductExportDto exportDto = this.modelMapper.map(p, ProductExportDto.class);
            productDtos.add(exportDto);
        }
        return productDtos;
    }
}
