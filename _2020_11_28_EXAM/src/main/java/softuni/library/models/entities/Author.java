package softuni.library.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "authors",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"first_name", "last_name"})})
public class Author extends BaseEntity {

    private String firstName;
    private String lastName;
    private String birthTown;

    public Author() {
    }

    public Author(String firstName, String lastName, String birthTown) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthTown = birthTown;
    }

    @Column(name = "first_name", nullable = false)
    @Length(min = 2)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    @Length(min = 2)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "birth_town", nullable = false)
    @Length(min = 3, max = 12)
    public String getBirthTown() {
        return birthTown;
    }

    public void setBirthTown(String birthTown) {
        this.birthTown = birthTown;
    }
}
