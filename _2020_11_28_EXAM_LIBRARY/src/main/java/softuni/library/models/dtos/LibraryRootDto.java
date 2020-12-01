package softuni.library.models.dtos;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "libraries")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibraryRootDto {


    @XmlElement(name = "library")
    private List<LibraryDto> libraries;

    public LibraryRootDto() {
    }

    public LibraryRootDto(List<LibraryDto> libraries) {
        this.libraries = libraries;
    }

    public List<LibraryDto> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<LibraryDto> libraries) {
        this.libraries = libraries;
    }
}
