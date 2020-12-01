package softuni.car_dealer_exam.models.dtos.pictureDtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public class PictureSeedDto {

    @Expose
    private String name;
    @Expose
    private LocalDateTime dateAndTime;
    @Expose
    private Long car;

    public PictureSeedDto() {
    }

    public PictureSeedDto(String name, LocalDateTime dateAndTime, Long car) {
        this.name = name;
        this.dateAndTime = dateAndTime;
        this.car = car;
    }

    @Length(min = 2, max = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public Long getCar() {
        return car;
    }

    public void setCar(Long car) {
        this.car = car;
    }
}
