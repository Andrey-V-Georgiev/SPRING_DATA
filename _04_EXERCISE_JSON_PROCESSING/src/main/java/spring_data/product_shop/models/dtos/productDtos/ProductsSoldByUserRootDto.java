package spring_data.product_shop.models.dtos.productDtos;

import com.google.gson.annotations.Expose;

import java.util.*;

public class ProductsSoldByUserRootDto {

    @Expose
    private long count;
    @Expose
    private List<ProductSoldByUser> products;

    public ProductsSoldByUserRootDto() {
    }

    public ProductsSoldByUserRootDto(long count, List<ProductSoldByUser> products) {
        this.count = count;
        this.products = products;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<ProductSoldByUser> getProducts() {
        return products;
    }

    public void setProducts(List<ProductSoldByUser> products) {
        this.products = products;
    }
}
