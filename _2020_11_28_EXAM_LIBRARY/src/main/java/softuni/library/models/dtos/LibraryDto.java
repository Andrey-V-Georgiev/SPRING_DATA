package softuni.library.models.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "library")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibraryDto {

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "location")
    private String location;

    @XmlElement(name = "rating")
    private Integer rating;

    @XmlElement(name = "book")
    private LibraryBookDto book;

    public LibraryDto() {
    }

    public LibraryDto(String name, String location, Integer rating, LibraryBookDto book) {
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.book = book;
    }

    @Length(min = 3)
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Length(min = 5)
    @NotNull
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Min(1)
    @Max(10)
    @NotNull
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public LibraryBookDto getBook() {
        return book;
    }

    public void setBook(LibraryBookDto book) {
        this.book = book;
    }
}
