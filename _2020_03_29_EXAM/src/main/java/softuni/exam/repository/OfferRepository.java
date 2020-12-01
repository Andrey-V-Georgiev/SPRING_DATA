package softuni.car_dealer_exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.car_dealer_exam.models.entities.Offer;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    Optional<Offer> findOfferByPriceAndCarIdAndSellerId(BigDecimal price, Long carId, Long sellerId);
}
