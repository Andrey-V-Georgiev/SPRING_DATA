package softuni.car_dealer_exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.car_dealer_exam.models.dtos.carDtos.CarViewDto;
import softuni.car_dealer_exam.models.entities.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    Optional<Car> findCarByMakeAndModelAndKilometers(String make, String model, Integer kilometers);

    @Query(value = "SELECT c FROM Car AS c WHERE c.id = ?1")
    Car findCarById(Long id);


    @Query(value = " SELECT new softuni.exam.models.dtos.carDtos.CarViewDto(" +
            " c.make, c.model, c.kilometers, c.registeredOn, count(p.id)) " +
            " FROM Picture p " +
            " JOIN p.car c " +
            " GROUP BY c.id " +
            " ORDER BY count(p.id) DESC, c.make")
    List<CarViewDto> getCarsOrderByPicturesCountThenByMake();
}
