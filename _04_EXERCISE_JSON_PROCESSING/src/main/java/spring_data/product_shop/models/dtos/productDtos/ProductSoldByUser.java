package spring_data.product_shop.models.dtos.productDtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ProductSoldByUser {

    @Expose(serialize = false)
    private long id;
    @Expose
    private String name;
    @Expose
    private BigDecimal price;

    public ProductSoldByUser() {
    }

    public ProductSoldByUser(long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
