package spring_data.product_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring_data.product_shop.models.dtos.productDtos.ProductSoldByUser;
import spring_data.product_shop.models.entities.Product;

import java.math.BigDecimal;
import java.util.*;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product getProductByNameAndPrice(String name, BigDecimal price);

    /* Query 1 – Products in Range */
    @Query("SELECT p " +
            "FROM Product AS p " +
            "WHERE p.price BETWEEN ?1 AND ?2 " +
            "AND p.buyer IS NULL " +
            "ORDER BY p.price ")
    List<Product> getAllByPriceBetweenAndBuyerIsNull(BigDecimal priceFrom, BigDecimal priceTo);

    @Query(value = "SELECT p FROM Product AS p WHERE p.buyer IS NOT NULL AND p.seller.id = ?1")
    List<Product> getProductsWithBuyerBySellerId(long sellerId);

    @Query("  SELECT new spring_data.product_shop.models.dtos.productDtos.ProductSoldByUser(p.id, p.name, p.price)" +
            " FROM Product AS p " +
            " JOIN p.seller AS u " +
            " WHERE u.id = ?1" +
            " AND p.buyer IS NOT NULL " +
            " GROUP BY u.id, p.id ")
    List<ProductSoldByUser> getSoldProductsByUser(long userId);
}
