package softuni.library.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "libraries")
public class Library extends BaseEntity {

    private String name;
    private String location;
    private Integer rating;
    private List<Book> books = new ArrayList<>();

    public Library() {
    }

    public Library(String name, String location, Integer rating) {
        this.name = name;
        this.location = location;
        this.rating = rating;
    }


    @Column(name = "name", nullable = false, unique = true)
    @Length(min = 3)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "location", nullable = false)
    @Length(min = 5)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Column(name = "rating", nullable = false)
    @Min(1)
    @Max(10)
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @ManyToMany(mappedBy = "libraries", fetch = FetchType.EAGER)
    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
