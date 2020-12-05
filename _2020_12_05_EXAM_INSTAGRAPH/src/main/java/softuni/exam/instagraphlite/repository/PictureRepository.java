package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.entities.Picture;

import java.util.List;
import java.util.Optional;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {

    Optional<Picture> findPictureByPath(String path);

    @Query(value = " SELECT p " +
            " FROM Picture p " +
            " WHERE p.size > ?1 " +
            " GROUP BY p.id " +
            " ORDER BY p.size")
    List<Picture>  findPicturesBySizeGreaterThan(Double size);
}
