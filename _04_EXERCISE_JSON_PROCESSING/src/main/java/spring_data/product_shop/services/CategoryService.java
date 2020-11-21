package spring_data.product_shop.services;

import spring_data.product_shop.models.entities.Category;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public interface CategoryService {

    void seedCategories(String filePath) throws FileNotFoundException;

    Set<Category> getRandomCategories();

    void categoriesByProductsCount(String filePath) throws IOException;
}
