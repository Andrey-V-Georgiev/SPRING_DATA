package spring_data.product_shop.services;

import java.io.FileNotFoundException;

public interface CategoryService {

    void seedCategories(String filePath) throws FileNotFoundException;
}
