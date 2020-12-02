package softuni.exam.models.dtos.json_dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;

public class TownSeedDto {

    @Expose
    private String name;

    @Expose
    private Integer population;

    @Expose
    private String guide;

    public TownSeedDto() {
    }

    public TownSeedDto(String name, Integer population, String guide) {
        this.name = name;
        this.population = population;
        this.guide = guide;
    }

    @Length(min = 2)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Min(0)
    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }
}
