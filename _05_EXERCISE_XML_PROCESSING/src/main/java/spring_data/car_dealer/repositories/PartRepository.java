package spring_data.car_dealer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_data.car_dealer.models.entities.Part;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
}