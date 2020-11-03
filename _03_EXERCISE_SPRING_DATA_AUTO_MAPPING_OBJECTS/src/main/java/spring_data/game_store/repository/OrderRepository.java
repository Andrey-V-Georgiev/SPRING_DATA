package spring_data.game_store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_data.game_store.domain.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
