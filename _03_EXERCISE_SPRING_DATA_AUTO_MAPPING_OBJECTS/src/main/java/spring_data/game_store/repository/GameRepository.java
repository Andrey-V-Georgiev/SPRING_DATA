package spring_data.game_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring_data.game_store.domain.entity.Game;

import java.math.BigDecimal;
import java.time.LocalDate;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    @Modifying
    @Query(value = "UPDATE Game g SET g.title = ?1 WHERE g.id = ?2")
    void updateTitle(String title, long id);

    @Modifying
    @Query(value = "UPDATE Game g SET g.price = ?1 WHERE g.id = ?2")
    void updatePrice(BigDecimal price, long id);

    @Modifying
    @Query(value = "UPDATE Game g SET g.size = ?1 WHERE g.id = ?2")
    void updateSize(Double size, long id);

    @Modifying
    @Query(value = "UPDATE Game g SET g.trailer = ?1 WHERE g.id = ?2")
    void updateTrailer(String trailer, long id);

    @Modifying
    @Query(value = "UPDATE Game g SET g.image = ?1 WHERE g.id = ?2")
    void updateImage(String image, long id);

    @Modifying
    @Query(value = "UPDATE Game g SET g.description = ?1 WHERE g.id = ?2")
    void updateDescription(String description, long id);

    @Modifying
    @Query(value = "UPDATE Game g SET g.releaseDate = ?1 WHERE g.id = ?2")
    void updateReleaseDate(LocalDate releaseDate, long id);

}
