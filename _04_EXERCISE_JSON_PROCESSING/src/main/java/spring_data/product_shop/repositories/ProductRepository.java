package spring_data.product_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_data.product_shop.models.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
