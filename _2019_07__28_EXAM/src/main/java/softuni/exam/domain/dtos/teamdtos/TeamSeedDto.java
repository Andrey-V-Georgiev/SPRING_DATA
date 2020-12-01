package softuni.exam.domain.dtos.teamdtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "team")
@XmlAccessorType(XmlAccessType.FIELD)
public class TeamSeedDto {

    @XmlElement(name = "name")
    @Length(min = 3, max = 20)
    @NotNull
    private String name;

    @XmlElement(name = "picture")
    private TeamSeedPictureDto picture;

    public TeamSeedDto() {
    }

    public TeamSeedDto(String name, TeamSeedPictureDto picture) {
        this.name = name;
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeamSeedPictureDto getUrl() {
        return picture;
    }

    public void setUrl(TeamSeedPictureDto url) {
        this.picture = picture;
    }
}
