package spring_data.game_store.domain.dto;

import javax.validation.constraints.Min;

public class GameDeleteDto {
    private long id;

    public GameDeleteDto() {
    }

    public GameDeleteDto(long id) {
        this.id = id;
    }


    @Min(value = 1)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
