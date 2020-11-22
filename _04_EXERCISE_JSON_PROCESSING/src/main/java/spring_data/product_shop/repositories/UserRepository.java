package spring_data.product_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring_data.product_shop.models.dtos.userDtos.UserAndProductDto;
import spring_data.product_shop.models.entities.User;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByFirstNameAndLastNameAndAge(String firstName, String lastName, int age);

    User getUserById(long id);

    /* Query 2 – Successfully Sold Products */
    @Query(value = "SELECT u.* " +
            "FROM users AS u " +
            "INNER JOIN products AS p ON u.id = p.seller_id " +
            "WHERE p.buyer_id IS NOT NULL " +
            "GROUP BY u.id, u.last_name, u.first_name " +
            "ORDER BY u.last_name, u.first_name",
            nativeQuery = true)
    List<User> getUsersWhoSoldMoreThanOneProduct();


    @Query(value = "SELECT new spring_data.product_shop.models.dtos.userDtos.UserAndProductDto(u.id, u.firstName, u.lastName, u.age)" +
            " FROM Product AS p " +
            " JOIN p.seller AS u" +
            " WHERE p.buyer IS NOT NULL " +
            " GROUP BY u.id" +
            " ORDER BY count(p.id) desc, u.lastName ")
    Set<UserAndProductDto> getUsersWithSoldProducts();
}
