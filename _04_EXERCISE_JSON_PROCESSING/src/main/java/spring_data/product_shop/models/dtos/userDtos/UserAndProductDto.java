package spring_data.product_shop.models.dtos.userDtos;

import com.google.gson.annotations.Expose;
import spring_data.product_shop.models.dtos.productDtos.ProductsSoldByUserRootDto;

public class UserAndProductDto {

    @Expose(serialize = false)
    private long id;
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private int age;
    @Expose
    private ProductsSoldByUserRootDto soldProducts;

    public UserAndProductDto() {
    }

    public UserAndProductDto(long id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ProductsSoldByUserRootDto getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(ProductsSoldByUserRootDto soldProducts) {
        this.soldProducts = soldProducts;
    }
}
