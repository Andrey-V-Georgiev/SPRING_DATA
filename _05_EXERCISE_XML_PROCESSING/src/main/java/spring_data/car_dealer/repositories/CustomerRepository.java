package spring_data.car_dealer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring_data.car_dealer.models.entities.Customer;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT c FROM Customer AS c WHERE c.name = ?1 AND c.birthDate = ?2 AND c.youngDriver = ?3")
    Optional<Customer> findCustomer(String name, LocalDateTime dateOfBirth, boolean isYoungDriver);
}
