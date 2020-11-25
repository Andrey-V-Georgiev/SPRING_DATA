package spring_data.car_dealer.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring_data.car_dealer.models.dtos.exportdtos.CarExportDto2;
import spring_data.car_dealer.models.entities.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = "SELECT c FROM Car AS c WHERE c.make = ?1 AND c.model = ?2 AND c.travelledDistance = ?3")
    Optional<Car> findCar(String make, String model, int travelDistance);

    @Query(value = " SELECT c " +
            " FROM Car AS c " +
            " WHERE c.make = ?1" +
            " GROUP BY c.id" +
            " ORDER BY c.model ASC, c.travelledDistance DESC")
    List<Car> findCarByMakeAndTravelDistance(String make);

    @Query(value = " SELECT new spring_data.car_dealer.models.dtos.exportdtos.CarExportDto2(" +
            " c.id, " +
            " c.make, " +
            " c.model, " +
            " c.travelledDistance) " +
            " FROM Car AS c")
    List<CarExportDto2> findAllCarExportDto2();
}
