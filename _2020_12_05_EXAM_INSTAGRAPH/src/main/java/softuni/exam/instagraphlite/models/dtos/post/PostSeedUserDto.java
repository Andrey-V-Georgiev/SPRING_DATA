package softuni.exam.instagraphlite.models.dtos.post;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostSeedUserDto {

    @XmlElement(name = "username")
    private String username;

    public PostSeedUserDto() {
    }

    public PostSeedUserDto(String username) {
        this.username = username;
    }

    @Length(min = 2, max = 18)
    @NotNull
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
