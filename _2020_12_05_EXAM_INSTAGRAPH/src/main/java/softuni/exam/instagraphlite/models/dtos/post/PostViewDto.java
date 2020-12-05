package softuni.exam.instagraphlite.models.dtos.post;

public class PostViewDto {

    private String caption;
    private Double pictureSize;

    public PostViewDto() {
    }

    public PostViewDto(String caption, Double size) {
        this.caption = caption;
        this.pictureSize = size;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Double getPictureSize() {
        return pictureSize;
    }

    public void setPictureSize(Double pictureSize) {
        this.pictureSize = pictureSize;
    }
}
