package spring_data.car_dealer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring_data.car_dealer.models.dtos.exportdtos.PartExportDto;
import spring_data.car_dealer.models.entities.Part;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

    Optional<Part> findPartByName(String name);

    @Query(value = "SELECT new spring_data.car_dealer.models.dtos.exportdtos.PartExportDto(p.name, p.price)" +
            " FROM Part AS p " +
            " JOIN p.cars AS c " +
            " WHERE c.id = ?1 ")
    List<PartExportDto> findPartsAllPerCarId(Long carId);
}
