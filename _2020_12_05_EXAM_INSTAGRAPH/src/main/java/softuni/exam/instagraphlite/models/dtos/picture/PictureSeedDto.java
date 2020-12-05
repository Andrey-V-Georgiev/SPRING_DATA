package softuni.exam.instagraphlite.models.dtos.picture;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

public class PictureSeedDto {

    @Expose
    private String path;

    @Expose
    private Double size;

    public PictureSeedDto() {
    }

    public PictureSeedDto(String path, Double size) {
        this.path = path;
        this.size = size;
    }


    @NotNull
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @DecimalMin(value = "500")
    @DecimalMax(value = "60000")
    @NotNull
    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }
}
