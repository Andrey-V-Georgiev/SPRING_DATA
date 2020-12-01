package softuni.library.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.library.models.dtos.CharacterExportDto;
import softuni.library.models.entities.Character;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    Optional<Character> findCharacterByFirstNameAndLastName(String firstName, String lastName);


    @Query(value = "select new softuni.library.models.dtos.CharacterExportDto(concat(c.firstName, ' ', c.lastName), c.age, b.name)" +
            "from Character as c join c.book as b where c.age >= 32 order by b.name, c.lastName desc, c.age")
    List<CharacterExportDto> findCharsByCriteria();
}
