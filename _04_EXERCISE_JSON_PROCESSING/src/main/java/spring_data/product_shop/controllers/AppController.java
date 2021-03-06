package spring_data.product_shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import spring_data.product_shop.services.CategoryService;
import spring_data.product_shop.services.ProductService;
import spring_data.product_shop.services.UserService;


import java.math.BigDecimal;

import static spring_data.product_shop.constants.GlobalConstants.*;

@Controller
public class AppController implements CommandLineRunner {

    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public AppController(UserService userService, CategoryService categoryService, ProductService productService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        /* Seed data */
        this.userService.seedUsers(USERS_FILE_PATH);
        this.categoryService.seedCategories(CATEGORIES_FILE_PATH);
        this.productService.seedProducts(PRODUCT_FILE_PATH);

        /* Queries */
        this.productService.productsInRange(new BigDecimal(500), new BigDecimal(1000));
        this.userService.successfullySoldProducts(QUERY_2_FILE_PATH);
        this.categoryService.categoriesByProductsCount(QUERY_3_FILE_PATH);
        this.userService.usersAndProducts(QUERY_4_FILE_PATH);
    }
}
