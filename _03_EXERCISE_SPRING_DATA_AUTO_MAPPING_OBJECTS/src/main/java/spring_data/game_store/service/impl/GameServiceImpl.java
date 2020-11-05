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
        Optional<Game> game = this.gameRepository.findById(dto.getId());
        if (game.isEmpty()) {
            System.out.println("Bad request - wrong game id");
        } else {
            Field[] fields = GameEditDto.class.getDeclaredFields();
            for (Field f : fields) {
                String fieldName = f.getName();
                switch (fieldName) {
                    case "title":
                        String title = dto.getTitle();
                        if (title != null) {
                            this.gameRepository.updateTitle(title, game.get().getId());
                        }
                        break;
                    case "price":
                        BigDecimal price = dto.getPrice();
                        if (price != null) {
                            this.gameRepository.updatePrice(price, game.get().getId());
                        }
                        break;
                    case "size":
                        Double size = dto.getSize();
                        if (size != null) {
                            this.gameRepository.updateSize(size, game.get().getId());
                        }
                        break;
                    case "trailer":
                        String trailer = dto.getTrailer();
                        if (trailer != null) {
                            this.gameRepository.updateTrailer(trailer, game.get().getId());
                        }
                        break;
                    case "image":
                        String image = dto.getImage();
                        if (image != null) {
                            this.gameRepository.updateImage(image, game.get().getId());
                        }
                        break;
                    case "description":
                        String description = dto.getDescription();
                        if (description != null) {
                            this.gameRepository.updateDescription(description, game.get().getId());
                        }
                        break;
                    case "releaseDate":
                        LocalDate releaseDate = dto.getReleaseDate();
                        if (releaseDate != null) {
                            this.gameRepository.updateReleaseDate(releaseDate, game.get().getId());
                        }
                        break;
                }
            }
        }
    }

    @Override
    public void deleteGame(GameDeleteDto dto) {
    }

}
