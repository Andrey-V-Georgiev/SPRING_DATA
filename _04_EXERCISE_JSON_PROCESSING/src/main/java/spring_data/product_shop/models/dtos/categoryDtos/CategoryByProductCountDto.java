package spring_data.product_shop.models.dtos.categoryDtos;

import com.google.gson.annotations.Expose;
import java.math.BigDecimal;


public class CategoryByProductCountDto {

    @Expose
    private String category;
    @Expose
    private long productsCount;
    @Expose
    private double averagePrice;
    @Expose
    private BigDecimal totalRevenue;

    public CategoryByProductCountDto() {
    }

    public CategoryByProductCountDto(String category) {
        this.category = category;
    }

    public CategoryByProductCountDto(String category, long productsCount) {
        this.category = category;
        this.productsCount = productsCount;
    }

    public CategoryByProductCountDto(String category, long productsCount, double averagePrice, BigDecimal totalRevenue) {
        this.category = category;
        this.productsCount = productsCount;
        this.averagePrice = averagePrice;
        this.totalRevenue = totalRevenue;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(long productsCount) {
        this.productsCount = productsCount;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
