package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entities.Offer;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    Optional<Offer> findOfferByPriceAndCarIdAndSellerId(BigDecimal price, Long carId, Long sellerId);
}
