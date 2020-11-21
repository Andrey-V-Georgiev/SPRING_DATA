package spring_data.product_shop.models.dtos;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class ProductWithBuyerDetailsDto {

    @Expose
    private long id;
    @Expose
    private BigDecimal price;
    @Expose
    private String buyerFirstName;
    @Expose
    private String buyerLastName;

    public ProductWithBuyerDetailsDto() {
    }

    public ProductWithBuyerDetailsDto(long id, BigDecimal price, String buyerFirstName, String buyerLastName) {
        this.id = id;
        this.price = price;
        this.buyerFirstName = buyerFirstName;
        this.buyerLastName = buyerLastName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBuyerFirstName() {
        return buyerFirstName;
    }

    public void setBuyerFirstName(String buyerFirstName) {
        this.buyerFirstName = buyerFirstName;
    }

    public String getBuyerLastName() {
        return buyerLastName;
    }

    public void setBuyerLastName(String buyerLastName) {
        this.buyerLastName = buyerLastName;
    }
}
