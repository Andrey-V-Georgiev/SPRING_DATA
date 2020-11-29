package softuni.library.models.dtos;

public class CharacterExportDto {

    private String name;
    private Integer age;
    private String bookName;

    public CharacterExportDto() {
    }

    public CharacterExportDto(String name, Integer age, String bookName) {
        this.name = name;
        this.age = age;
        this.bookName = bookName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
