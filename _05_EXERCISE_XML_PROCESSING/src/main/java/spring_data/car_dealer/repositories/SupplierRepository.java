package spring_data.car_dealer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_data.car_dealer.models.entities.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
