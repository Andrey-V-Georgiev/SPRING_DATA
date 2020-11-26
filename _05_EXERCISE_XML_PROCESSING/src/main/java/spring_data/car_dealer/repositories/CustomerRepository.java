package spring_data.car_dealer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring_data.car_dealer.models.dtos.exportdtos.CustomerExportDto2;
import spring_data.car_dealer.models.entities.Customer;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "SELECT c FROM Customer AS c WHERE c.name = ?1 AND c.birthDate = ?2 AND c.youngDriver = ?3")
    Optional<Customer> findCustomer(String name, LocalDateTime dateOfBirth, boolean isYoungDriver);


    @Query(value = " SELECT c" +
            " FROM Customer AS c " +
            " GROUP BY c.id")
    List<Customer> findCustomersAllOrderByBirthDateTHenByYoungDriver();

    @Query(value = "SELECT new spring_data.car_dealer.models.dtos.exportdtos.CustomerExportDto2(cu.id, cu.name)" +
            " FROM Sale AS s " +
            " JOIN s.customer AS cu " +
            " JOIN s.car AS ca " +
            " JOIN ca.parts AS p " +
            " WHERE s.customer IS NOT NULL " +
            " AND s.car IS NOT NULL " +
            " GROUP BY cu.id " +
            " ORDER BY sum(p.price) DESC, count(ca.id) " )
    List<CustomerExportDto2> findCustomersBoughtAtLeastOneCar();
}
