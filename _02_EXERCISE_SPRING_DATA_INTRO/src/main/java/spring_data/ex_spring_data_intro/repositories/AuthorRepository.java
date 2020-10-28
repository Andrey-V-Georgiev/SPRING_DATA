package spring_data.ex_spring_data_intro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_data.ex_spring_data_intro.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
