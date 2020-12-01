package softuni.library.models.dtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;
import softuni.library.models.entities.Author;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class BookDto {

    @Expose
    private String name;
    @Expose
    private Integer edition;
    @Expose
    private LocalDate written;
    @Expose
    private String description;
    @Expose
    private Long author;

    public BookDto() {
    }

    public BookDto(String name, Integer edition, LocalDate written, String description, Long author) {
        this.name = name;
        this.edition = edition;
        this.written = written;
        this.description = description;
        this.author = author;
    }

    @Length(min = 5)
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Min(1)
    @Max(5)
    public Integer getEdition() {
        return edition;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }

    @NotNull
    public LocalDate getWritten() {
        return written;
    }

    public void setWritten(LocalDate written) {
        this.written = written;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }
}
