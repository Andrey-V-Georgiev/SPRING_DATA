package spring_data.product_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_data.product_shop.models.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User getUserByFirstNameAndLastNameAndAge(String firstName, String lastName, int age);

    User getUserById(long id);
}
