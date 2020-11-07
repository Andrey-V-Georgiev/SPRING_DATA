package spring_data.game_store.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_data.game_store.domain.dto.GameAddDto;
import spring_data.game_store.domain.dto.GameDeleteDto;
import spring_data.game_store.domain.dto.GameEditDto;
import spring_data.game_store.domain.entity.Game;
import spring_data.game_store.repository.GameRepository;
import spring_data.game_store.service.GameService;
import spring_data.game_store.service.UserService;

import javax.transaction.Transactional;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserService userService, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public long addGame(GameAddDto dto) {
        if (!this.userService.isAdmin()) {
            System.out.println("Only administrators can add games");
            return -1;
        } else {
            Game game = this.modelMapper.map(dto, Game.class);
            Game savedGame = this.gameRepository.saveAndFlush(game);
            return savedGame.getId();
        }
    }

    @Override
    public void editGame(GameEditDto dto) {
        /* Check if exists by id */
        Optional<Game> gameDB = this.gameRepository.findById(dto.getId());
        if (gameDB.isEmpty()) {
            System.out.println("Bad request - wrong game id");
        } else {
            Game gameEdit = this.modelMapper.map(dto, Game.class);
            Field[] fields = GameEditDto.class.getDeclaredFields();
            for (Field f : fields) {
                String fieldName = f.getName();

                switch (fieldName) {
                    case "title":
                        String title = gameEdit.getTitle();
                        if (title != null) {
                            this.gameRepository.updateTitle(title, gameEdit.getId());
                        }
                        break;
                    case "price":
                        BigDecimal price = gameEdit.getPrice();
                        if (price != null) {
                            this.gameRepository.updatePrice(price, gameEdit.getId());
                        }
                        break;
                    case "size":
                        Double size = gameEdit.getSize();
                        if (size != null) {
                            this.gameRepository.updateSize(size, gameEdit.getId());
                        }
                        break;
                    case "trailer":
                        String trailer = gameEdit.getTrailer();
                        if (trailer != null) {
                            this.gameRepository.updateTrailer(trailer, gameEdit.getId());
                        }
                        break;
                    case "image":
                        String image = gameEdit.getImage();
                        if (image != null) {
                            this.gameRepository.updateImage(image, gameEdit.getId());
                        }
                        break;
                    case "description":
                        String description = gameEdit.getDescription();
                        if (description != null) {
                            this.gameRepository.updateDescription(description, gameEdit.getId());
                        }
                        break;
                    case "releaseDate":
                        LocalDate releaseDate = gameEdit.getReleaseDate();
                        if (releaseDate != null) {
                            this.gameRepository.updateReleaseDate(releaseDate, gameEdit.getId());
                        }
                        break;
                }
            }
            System.out.printf("Game with id %d successfully edited\n", gameEdit.getId());
        }
    }

    @Override
    public void deleteGame(GameDeleteDto dto) {
        /* Check if exists by id */
        Optional<Game> gameDB = this.gameRepository.findById(dto.getId());
        if (gameDB.isEmpty()) {
            System.out.println("Bad request - wrong game id");
        } else {
            Game gameDelete = this.modelMapper.map(dto, Game.class);
            this.gameRepository.deleteGameById(gameDelete.getId());
            System.out.printf("Successfully delete game with id: %d", gameDelete.getId());
        }
    }

}
