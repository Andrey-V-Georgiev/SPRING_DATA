package softuni.library.models.dtos;

import org.hibernate.validator.constraints.Length;
import softuni.library.adapters.LocalDateAdapterXML;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

@XmlRootElement(name = "character")
@XmlAccessorType(XmlAccessType.FIELD)
public class CharacterDto {

    @XmlElement(name = "first-name")
    private String firstName;

    @XmlElement(name = "middle-name")
    private String middleName;

    @XmlElement(name = "last-name")
    private String lastName;

    @XmlElement(name = "age")
    private Integer age;

    @XmlElement(name = "role")
    private String role;

    @XmlElement(name = "birthday")
    @XmlJavaTypeAdapter(LocalDateAdapterXML.class)
    private LocalDate birthDate;

    @XmlElement(name = "book")
    private CharacterBookDto book;

    public CharacterDto() {
    }

    public CharacterDto(String firstName, String middleName, String lastName, Integer age, String role, LocalDate birthDate, CharacterBookDto book) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.age = age;
        this.role = role;
        this.birthDate = birthDate;
        this.book = book;
    }

    @Length(min = 3)
    @NotNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @NotNull
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Length(min = 3)
    @NotNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotNull
    @Min(10)
    @Max(66)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @NotNull
    @Length(min = 5)
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @NotNull
    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public CharacterBookDto getBook() {
        return book;
    }

    public void setBook(CharacterBookDto book) {
        this.book = book;
    }
}
