package softuni.exam.service;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.constants.GlobalConstants;
import softuni.exam.domain.dtos.exportdtos.PlayerInATeamDto;
import softuni.exam.domain.dtos.exportdtos.PlayerWhereSalaryBiggerThanDto;
import softuni.exam.domain.dtos.playerdtos.PlayerSeedDto;
import softuni.exam.domain.entities.Player;
import softuni.exam.repository.PlayerRepository;
import softuni.exam.utils.FileUtil;
import softuni.exam.utils.ValidationUtil;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final PlayerRepository playerRepository;
    private final PictureService pictureService;
    private final TeamService teamService;

    public PlayerServiceImpl(Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, PlayerRepository playerRepository, PictureService pictureService, TeamService teamService) {
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.playerRepository = playerRepository;
        this.pictureService = pictureService;
        this.teamService = teamService;
    }

    @Override
    public String importPlayers() throws IOException {
        StringBuilder sb = new StringBuilder();

        /* Parse the JSON to Dto */
        String playersJson = this.fileUtil.readFileAddedNewLines(GlobalConstants.PLAYERS_INPUT_PATH);
        PlayerSeedDto[] dtos = this.gson.fromJson(playersJson, PlayerSeedDto[].class);

        /* Validate the Dtos */
        for (PlayerSeedDto dto : dtos) {

            /* Prevent duplicates */
            Optional<Player> playerOptional = this.playerRepository
                    .findPlayerByFirstNameAndLastNameAndNumber(dto.getFirstName(), dto.getLastName(), dto.getNumber());

            if (playerOptional.isEmpty()) {
                if (this.validationUtil.isValid(dto)) {
                    Player player = this.modelMapper.map(dto, Player.class);

                    this.playerRepository.saveAndFlush(player);
                    sb.append(String
                            .format("Successfully imported player - %s %s\n", dto.getFirstName(), dto.getLastName()));
                } else {
                    sb.append("Invalid player\n");
                }
            }
        }
        return sb.toString();
    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() > 0;
    }

    @Override
    public String readPlayersJsonFile() throws IOException {
        String inputString = this.fileUtil.readFileAddedNewLines(GlobalConstants.PLAYERS_INPUT_PATH);
        return inputString;
    }

    @Override
    public String exportPlayersWhereSalaryBiggerThan() {
        BigDecimal salary = new BigDecimal(100000);
        List<PlayerWhereSalaryBiggerThanDto> dtos = this.playerRepository.findPlayersWhereSalaryBiggerThan(salary);

        StringBuilder sb = new StringBuilder();
        for (PlayerWhereSalaryBiggerThanDto dto : dtos) {
            sb.append(String.format("Player name: %s\n", dto.getName() ));
            sb.append(String.format("\tNumber: %d\n", dto.getNumber()));
            sb.append(String.format("\tSalary: %s\n", dto.getSalary()));
            sb.append(String.format("\tTeam: %s\n", dto.getTeamName()));
        }
        return sb.toString();
    }

    @Override
    public String exportPlayersInATeam() {
        String teamName = "North Hub";
        List<PlayerInATeamDto> dtos = this.playerRepository.findPlayersInATeam(teamName);

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Team: %s\n", teamName));
        for (PlayerInATeamDto dto : dtos) {
            sb.append(String.format("\tPlayer name: %s %s - %s\n", dto.getFirstName(), dto.getLastName(), dto.getPosition()));
            sb.append(String.format("\tNumber: %d\n", dto.getNumber()));
        }
        return sb.toString();
    }
}
