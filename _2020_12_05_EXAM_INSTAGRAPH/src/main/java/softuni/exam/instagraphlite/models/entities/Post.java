package softuni.exam.instagraphlite.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "posts")
public class Post extends BaseEntity {

    private String caption;
    private User user;
    private Picture picture;

    public Post() {
    }

    public Post(String caption, User user, Picture picture) {
        this.caption = caption;
        this.user = user;
        this.picture = picture;
    }
    @Column(name = "caption", nullable = false)
    @Length(min = 21)
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "picture_id", nullable = false)
    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
