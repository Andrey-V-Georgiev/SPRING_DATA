package softuni.exam.instagraphlite.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.instagraphlite.models.dtos.post.PostViewDto;
import softuni.exam.instagraphlite.models.entities.Post;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findPostByCaption(String caption);

    @Query(value = " SELECT new softuni.exam.instagraphlite.models.dtos.post.PostViewDto(p.caption, p.picture.size) " +
            " FROM Post p " +
            " JOIN p.user u " +
            " WHERE u.id = ?1 " +
            " GROUP BY p.id")
    List<PostViewDto> findPostDetailsByUserId(Long userId);
}
