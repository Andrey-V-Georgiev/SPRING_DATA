package spring_data.ex_spring_data_intro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_data.ex_spring_data_intro.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
