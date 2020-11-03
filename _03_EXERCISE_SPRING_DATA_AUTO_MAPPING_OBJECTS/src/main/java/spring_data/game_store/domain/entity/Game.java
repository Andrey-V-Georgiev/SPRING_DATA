package spring_data.game_store.domain.entity;

import spring_data.game_store.constants.ErrorConstants;
import spring_data.game_store.constants.RegexConstants;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "games")
public class Game extends BaseEntity {

    private String title;
    private BigDecimal price;
    private Double size;
    private String trailer;
    private String image;
    private String description;
    private LocalDate releaseDate;
    private Set<User> users = new HashSet<>();

    public Game() {
    }

    public Game(String title, BigDecimal price, Double size, String trailer, String image, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.image = image;
        this.description = description;
        this.releaseDate = releaseDate;

    }

    @Pattern(regexp = RegexConstants.GAME_TITLE_REGEX, message = ErrorConstants.GAME_TITLE_ERROR)
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DecimalMin(value = "0", message = "Price cannot be negative")
    @Column(name = "price", nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Size(min = 0, message = "Size cannot be negative")
    @Column(name = "size", nullable = false)
    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @Size(min = 11, max = 11, message = "Trailer string must be 11 characters")
    @Column(name = "trailer", nullable = false)
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Pattern(regexp = RegexConstants.HTTP_HTTPS_REGEX, message = ErrorConstants.GAME_IMAGE_URL_ERROR)
    @Column(name = "image", nullable = false)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Size(min = 20, message = "Description must be at least 20 characters")
    @Column(name = "description", nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "release_date", nullable = false)
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    @ManyToMany
    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
