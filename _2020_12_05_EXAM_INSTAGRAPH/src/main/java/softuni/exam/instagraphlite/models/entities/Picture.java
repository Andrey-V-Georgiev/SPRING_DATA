package softuni.exam.instagraphlite.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

@Entity
@Table(name = "pictures", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"path"})
})
public class Picture extends BaseEntity {

    private String path;
    private Double size;

    public Picture() {
    }

    public Picture(String path, Double size) {
        this.path = path;
        this.size = size;
    }

    @Column(name = "path", nullable = false)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Column(name = "size", nullable = false)
    @DecimalMin(value = "500")
    @DecimalMax(value = "60000")
    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }
}
