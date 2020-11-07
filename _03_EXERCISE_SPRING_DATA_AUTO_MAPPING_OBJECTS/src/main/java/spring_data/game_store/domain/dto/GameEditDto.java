package spring_data.game_store.domain.dto;

import spring_data.game_store.constants.ErrorConstants;
import spring_data.game_store.constants.RegexConstants;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

public class GameEditDto {

    private long id;
    private String title;
    private BigDecimal price;
    private Double size;
    private String trailer;
    private String image;
    private String description;
    private LocalDate releaseDate;

    public GameEditDto() {
    }

    public GameEditDto(long id,String title, BigDecimal price, Double size, String trailer, String image, String description, LocalDate releaseDate) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.image = image;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    @Min(value = 1)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Pattern(regexp = RegexConstants.GAME_TITLE_REGEX, message = ErrorConstants.GAME_TITLE_ERROR)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @DecimalMin(value = "0", message = "Price cannot be negative")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @DecimalMin(value = "0", message = "Size cannot be negative")
    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    @Size(min = 11, max = 11, message = "Trailer string must be 11 characters")
    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    @Pattern(regexp = RegexConstants.HTTP_HTTPS_REGEX, message = ErrorConstants.GAME_IMAGE_URL_ERROR)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Size(min = 20, message = "Description must be at least 20 characters")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
