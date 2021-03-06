package spring_data.product_shop.models.dtos.categoryDtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

public class CategorySeedDto {
    @Expose
    private String name;

    public CategorySeedDto() {
    }

    public CategorySeedDto(String name) {
        this.name = name;
    }

    @Length(min = 3, max = 15)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
