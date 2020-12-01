package softuni.library.models.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@Entity
@Table(name = "characters")
public class Character extends BaseEntity {

    private String firstName;
    private String middleName;
    private String lastName;
    private Integer age;
    private String role;
    private LocalDate birthDate;
    private Book book;

    public Character() {
    }

    public Character(String firstName, String middleName, String lastName, Integer age, String role, LocalDate birthDate) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.role = role;
        this.birthDate = birthDate;
    }

    public Character(String firstName, String middleName, String lastName, Integer age, String role, LocalDate birthDate, Book book) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.role = role;
        this.birthDate = birthDate;
        this.book = book;
    }

    @Column(name = "first_name", nullable = false)
    @Length(min = 3)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "middle_name", nullable = false)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Column(name = "last_name", nullable = false)
    @Length(min = 3)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "age", nullable = false)
    @Min(10)
    @Max(66)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(name = "role", nullable = false)
    @Length(min = 5)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "birth_date", nullable = false)
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
