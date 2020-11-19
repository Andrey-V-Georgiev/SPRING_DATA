package spring_data.product_shop.services;

import java.io.FileNotFoundException;

public interface ProductService {

    void seedProducts(String filePath) throws FileNotFoundException;
}
