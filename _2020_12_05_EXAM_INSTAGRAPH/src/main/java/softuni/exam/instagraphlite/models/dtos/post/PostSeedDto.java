package softuni.exam.instagraphlite.models.dtos.post;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostSeedDto {

    @XmlElement(name = "caption")
    private String caption;

    @XmlElement(name = "user")
    private PostSeedUserDto user;

    @XmlElement(name = "picture")
    private PostSeedPictureDto picture;

    public PostSeedDto() {
    }

    public PostSeedDto(String caption, PostSeedUserDto user, PostSeedPictureDto picture) {
        this.caption = caption;
        this.user = user;
        this.picture = picture;
    }

    @Length(min = 21)
    @NotNull
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @NotNull
    public PostSeedUserDto getUser() {
        return user;
    }

    public void setUser(PostSeedUserDto user) {
        this.user = user;
    }

    @NotNull
    public PostSeedPictureDto getPicture() {
        return picture;
    }

    public void setPicture(PostSeedPictureDto picture) {
        this.picture = picture;
    }
}
