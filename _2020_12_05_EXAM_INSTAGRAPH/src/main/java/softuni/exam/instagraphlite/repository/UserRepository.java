package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.dtos.user.UserViewDto;
import softuni.exam.instagraphlite.models.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByUsernameAndPassword(String userName, String password);

    Optional<User> findUserByUsername(String username);


    @Query(value = " SELECT new softuni.exam.instagraphlite.models.dtos.user.UserViewDto(" +
            " u.id, u.username, count(p.id))" +
            " FROM Post p " +
            " JOIN  p.user u " +
            " GROUP BY u.id " +
            " ORDER BY count(p.id) DESC, u.id")
    List<UserViewDto> findUsersWithTheirPostsCount();
}
