package spring_data.product_shop.services;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_data.product_shop.models.dtos.productDtos.ProductSoldByUser;
import spring_data.product_shop.models.dtos.productDtos.ProductWithBuyerDetailsDto;
import spring_data.product_shop.models.dtos.productDtos.ProductsSoldByUserRootDto;
import spring_data.product_shop.models.dtos.userDtos.UserAndProductDto;
import spring_data.product_shop.models.dtos.userDtos.UserExportDto;
import spring_data.product_shop.models.dtos.userDtos.UserSeedDto;
import spring_data.product_shop.models.dtos.userDtos.UsersAndProductsRootDto;
import spring_data.product_shop.models.entities.Product;
import spring_data.product_shop.models.entities.User;
import spring_data.product_shop.repositories.ProductRepository;
import spring_data.product_shop.repositories.UserRepository;
import spring_data.product_shop.utils.FileUtil;
import spring_data.product_shop.utils.RandomUtil;
import spring_data.product_shop.utils.ValidationUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final UserRepository userRepository;
    private final RandomUtil randomUtil;
    private final ProductRepository productRepository;
    private final FileUtil fileUtil;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, UserRepository userRepository, RandomUtil randomUtil, ProductRepository productRepository, FileUtil fileUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.userRepository = userRepository;
        this.randomUtil = randomUtil;
        this.productRepository = productRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedUsers(String filePath) throws FileNotFoundException {

        /* Read the json */
        UserSeedDto[] dtos = this.gson.fromJson(new FileReader(filePath), UserSeedDto[].class);
        for (UserSeedDto dto : dtos) {
            User userFromDb = this.userRepository
                    .getUserByFirstNameAndLastNameAndAge(dto.getFirstName(), dto.getLastName(), dto.getAge());
            if (userFromDb != null) {
                continue;
            }
            /* Validate the dtos */
            if (this.validationUtil.isValid(dto)) {
                User user = this.modelMapper.map(dto, User.class);
                this.userRepository.saveAndFlush(user);
            } else {
                this.validationUtil.printConstraintViolations(dto);
            }
        }
    }

    @Override
    public User getRandomUser() {
        /* Get random user id */
        int randomUserId = this.randomUtil.getRandomIntFromOneTo((int) this.userRepository.count());
        User randomUser = this.userRepository.getUserById(randomUserId);
        return randomUser;
    }

    @Override
    public User getRandomUserOrNull() {
        /* Get random user id */
        int randomUserId = this.randomUtil.getRandomIntFromZeroTo((int) this.userRepository.count());
        User randomUserOrNull = this.userRepository.getUserById(randomUserId);
        return randomUserOrNull;
    }

    @Override
    public void successfullySoldProducts(String filePath) throws IOException {
        /* Get user from DB*/
        List<User> usersBD = this.userRepository.getUsersWhoSoldMoreThanOneProduct();
        List<UserExportDto> userDtos = new ArrayList<>();
        for (User u : usersBD) {
            /* Map user to dto */
            UserExportDto userDto = this.modelMapper.map(u, UserExportDto.class);
            /* Get products from DB*/
            List<Product> productsDB = this.productRepository.getProductsWithBuyerBySellerId(userDto.getId());
            List<ProductWithBuyerDetailsDto> productDtos = new ArrayList<>();
            for (Product p : productsDB) {
                ProductWithBuyerDetailsDto productDto = this.modelMapper.map(p, ProductWithBuyerDetailsDto.class);
                productDtos.add(productDto);
            }
            userDto.setSoldProducts(productDtos);
            userDtos.add(userDto);
        }
        String userDtosJson = this.gson.toJson(userDtos);
        this.fileUtil.writeFile(userDtosJson, filePath);
    }

    @Override
    public void usersAndProducts(String filePath) throws IOException {
        /* Create user root Dto */
        UsersAndProductsRootDto usersRootDto = new UsersAndProductsRootDto();
        Set<UserAndProductDto> usersDtos = this.userRepository.getUsersWithSoldProducts();
        for (UserAndProductDto u : usersDtos) {
            /* Create product root Dto */
            ProductsSoldByUserRootDto productsRootDto = new ProductsSoldByUserRootDto();
            List<ProductSoldByUser> soldProductDtos = this.productRepository.getSoldProductsByUser(u.getId());
            /* Set product root Dto properties */
            productsRootDto.setCount(soldProductDtos.size());
            productsRootDto.setProducts(soldProductDtos);
            /* Set sold products to user */
            u.setSoldProducts(productsRootDto);
        }
        /* Set users with products to root Dto */
        usersRootDto.setUsersCount(usersDtos.size());
        usersRootDto.setUsers(usersDtos);
        /* Convert in Json and write to file */
        String usersRootDtoJson = this.gson.toJson(usersRootDto);
        this.fileUtil.writeFile(usersRootDtoJson, filePath);
    }
}
