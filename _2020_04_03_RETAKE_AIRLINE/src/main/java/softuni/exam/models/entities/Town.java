package softuni.exam.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;

@Entity
@Table(name = "towns", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"})
})
public class Town extends BaseEntity {

    private String name;
    private Integer population;
    private String guide;

    public Town() {
    }

    public Town(String name, Integer population, String guide) {
        this.name = name;
        this.population = population;
        this.guide = guide;
    }

    @Column(name = "name")
    @Length(min = 2)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "population")
    @Min(0)
    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    @Column(name = "guide", columnDefinition="TEXT")
    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }
}
