package softuni.exam.domain.dtos.playerdtos;

import com.google.gson.annotations.Expose;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;


public class PlayerSeedTeamDto {

    @Expose
    private String name;

    @Expose
    private PlayerSeedPictureDto picture;

    public PlayerSeedTeamDto() {
    }

    public PlayerSeedTeamDto(String name, PlayerSeedPictureDto picture) {
        this.name = name;
        this.picture = picture;
    }

    @Length(min = 3, max = 20)
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull
    public PlayerSeedPictureDto getPicture() {
        return picture;
    }

    public void setPicture(PlayerSeedPictureDto picture) {
        this.picture = picture;
    }
}
