package spring_data.product_shop.services;

import spring_data.product_shop.models.dtos.productDtos.ProductExportDto;
import spring_data.product_shop.models.dtos.productDtos.ProductSoldByUser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public interface ProductService {

    void seedProducts(String filePath) throws FileNotFoundException;

    void productsInRange(BigDecimal priceFrom, BigDecimal priceTo) throws IOException;

    List<ProductExportDto> getProductsWithBuyerBySellerId(long sellerId);

    List<ProductSoldByUser> getProductsSoldByUser(long userId);
}
