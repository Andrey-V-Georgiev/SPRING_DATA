package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entities.Car;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findCarByMakeAndModelAndKilometers(String make, String model, Integer kilometers);

    @Query(value = "SELECT c FROM Car AS c WHERE c.id = ?1")
    Car findCarById(Long id);
}
