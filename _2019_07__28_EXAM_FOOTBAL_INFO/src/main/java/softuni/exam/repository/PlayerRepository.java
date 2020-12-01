package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.domain.dtos.exportdtos.PlayerInATeamDto;
import softuni.exam.domain.dtos.exportdtos.PlayerWhereSalaryBiggerThanDto;
import softuni.exam.domain.entities.Player;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findPlayerByFirstNameAndLastNameAndNumber(String firstName, String lastName, Integer number);

    @Query(value = " SELECT new softuni.exam.domain.dtos.exportdtos.PlayerInATeamDto( " +
            " p.firstName, p.lastName, p.position, p.number)" +
            " FROM Player p " +
            " JOIN p.team t " +
            " WHERE t.name = ?1 ")
    List<PlayerInATeamDto> findPlayersInATeam(String teamName);

    @Query(value = " SELECT new softuni.exam.domain.dtos.exportdtos.PlayerWhereSalaryBiggerThanDto( " +
            " concat(p.firstName, ' ', p.lastName), p.number, p.salary, t.name)" +
            " FROM Player p " +
            " JOIN p.team t " +
            " WHERE p.salary > ?1 ")
    List<PlayerWhereSalaryBiggerThanDto> findPlayersWhereSalaryBiggerThan(BigDecimal salary);
}
