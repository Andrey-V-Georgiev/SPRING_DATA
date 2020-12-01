package softuni.exam.domain.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity {

    private String name;
    private Picture picture;

    public Team() {
    }

    public Team(String name) {
        this.name = name;
    }

    @Column(name = "name", nullable = false)
    @Length(min = 3, max = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
