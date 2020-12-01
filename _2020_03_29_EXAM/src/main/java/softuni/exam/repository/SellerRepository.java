package softuni.car_dealer_exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.car_dealer_exam.models.entities.Seller;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {

    Optional<Seller> findSellerByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);

    Seller findSellerById(Long id);
}
