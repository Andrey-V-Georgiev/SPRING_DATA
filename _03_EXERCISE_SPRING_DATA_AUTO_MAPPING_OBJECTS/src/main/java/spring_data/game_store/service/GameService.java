package spring_data.game_store.service;

import spring_data.game_store.domain.dto.GameAddDto;
import spring_data.game_store.domain.dto.GameDeleteDto;
import spring_data.game_store.domain.dto.GameEditDto;

public interface GameService {
    long addGame(GameAddDto dto);
    void editGame(GameEditDto dto);
    void deleteGame(GameDeleteDto dto);
}
