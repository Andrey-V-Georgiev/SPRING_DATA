package softuni.exam.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.constants.GlobalConstants;
import softuni.exam.domain.dtos.picturedtos.PictureSeedDto;
import softuni.exam.domain.dtos.picturedtos.PictureSeedRootDto;
import softuni.exam.domain.dtos.teamdtos.TeamSeedDto;
import softuni.exam.domain.dtos.teamdtos.TeamSeedRootDto;
import softuni.exam.domain.entities.Picture;
import softuni.exam.domain.entities.Team;
import softuni.exam.repository.TeamRepository;
import softuni.exam.utils.FileUtil;
import softuni.exam.utils.ValidationUtil;
import softuni.exam.utils.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImpl implements TeamService {

    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final TeamRepository teamRepository;

    @Autowired
    public TeamServiceImpl(XmlParser xmlParser, ModelMapper modelMapper, ValidationUtil validationUtil, FileUtil fileUtil, TeamRepository teamRepository) {
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.teamRepository = teamRepository;
    }

    @Override
    public String importTeams() throws JAXBException, FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        /* Parse the XMLs to Dtos */
        TeamSeedRootDto teamSeedRootDto = this.xmlParser
                .unmarshalFromFile(GlobalConstants.TEAMS_INPUT_PATH, TeamSeedRootDto.class);

        /* Validate the Dtos */
        List<TeamSeedDto> teamSeedDtos = teamSeedRootDto.getTeams();
        for (TeamSeedDto dto : teamSeedDtos) {
            if (this.validationUtil.isValid(dto)) {

                Optional<Team> teamOptional = this.teamRepository.findTeamByName(dto.getName());
                if (teamOptional.isEmpty()) {
                    Team team = this.modelMapper.map(dto, Team.class);
                    this.teamRepository.saveAndFlush(team);
                    sb.append(String.format("Successfully imported team - %s\n", dto.getName()));
                }
            } else {
                sb.append("Invalid team\n");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsXmlFile() throws IOException {
        String teamsXML = this.fileUtil.readFileAddedNewLines(GlobalConstants.TEAMS_INPUT_PATH);
        return teamsXML;
    }
}
