package softuni.exam.models.dtos.carDtos;

import java.time.LocalDate;

public class CarViewDto {

    private String make;
    private String model;
    private Integer kilometers;
    private LocalDate registeredOn;
    private Long numberOfPictures;

    public CarViewDto() {
    }

    public CarViewDto(String make, String model, Integer kilometers, LocalDate registeredOn, Long numberOfPictures) {
        this.make = make;
        this.model = model;
        this.kilometers = kilometers;
        this.registeredOn = registeredOn;
        this.numberOfPictures = numberOfPictures;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getKilometers() {
        return kilometers;
    }

    public void setKilometers(Integer kilometers) {
        this.kilometers = kilometers;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    public Long getNumberOfPictures() {
        return numberOfPictures;
    }

    public void setNumberOfPictures(Long numberOfPictures) {
        this.numberOfPictures = numberOfPictures;
    }
}
