package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.dtos.export_dtos.PassengerExportDto;
import softuni.exam.models.entities.Passenger;

import java.util.List;
import java.util.Optional;


@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    Optional<Passenger> findPassengerByEmail(String email);

    @Query(value = "SELECT new softuni.exam.models.dtos.export_dtos.PassengerExportDto(" +
            " p.firstName, p.lastName, p.email, p.phoneNumber, count(t.id))" +
            " FROM Ticket t" +
            " JOIN t.passenger p " +
            " GROUP BY p.id" +
            " ORDER BY count(t.id) DESC, p.email")
    List<PassengerExportDto> findPassengers();
}
