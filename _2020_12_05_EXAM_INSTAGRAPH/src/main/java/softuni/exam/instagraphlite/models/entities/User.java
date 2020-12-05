package softuni.exam.instagraphlite.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"})
})
public class User extends BaseEntity {

    private String username;
    private String password;
    private Picture profilePicture;

    public User() {
    }

    public User(String username, String password, Picture profilePicture) {
        this.username = username;
        this.password = password;
        this.profilePicture = profilePicture;
    }

    @Column(name = "username", nullable = false)
    @Length(min = 2, max = 18)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "pasword", nullable = false)
    @Length(min = 4)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_picture_id", nullable = false)
    public Picture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Picture profilePicture) {
        this.profilePicture = profilePicture;
    }
}
