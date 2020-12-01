package softuni.exam.domain.dtos.playerdtos;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class PlayerSeedPictureDto {

    @Expose
    private String url;

    public PlayerSeedPictureDto() {
    }

    public PlayerSeedPictureDto(String url) {
        this.url = url;
    }

    @NotNull
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
