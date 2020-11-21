package spring_data.product_shop.models.dtos;

import com.google.gson.annotations.Expose;

import java.util.*;

public class UserExportDto {

    @Expose
    private long id;
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private List<ProductWithBuyerDetailsDto> soldProducts = new ArrayList<>();

    public UserExportDto() {
    }

    public UserExportDto(long id, String firstName, String lastName, List<ProductWithBuyerDetailsDto> soldProducts) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.soldProducts = soldProducts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<ProductWithBuyerDetailsDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<ProductWithBuyerDetailsDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
