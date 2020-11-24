package spring_data.car_dealer.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring_data.car_dealer.models.entities.Car;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "SELECT c FROM Car AS c WHERE c.make = ?1 AND c.model = ?2 AND c.travelledDistance = ?3")
    Optional<Car> findCar(String make, String model, int travelDistance);
}
