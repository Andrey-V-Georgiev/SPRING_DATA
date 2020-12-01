package softuni.library.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.library.models.entities.Book;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookByNameAndEdition(String name, Integer edition);

    Book findBookById(Long id);
}
