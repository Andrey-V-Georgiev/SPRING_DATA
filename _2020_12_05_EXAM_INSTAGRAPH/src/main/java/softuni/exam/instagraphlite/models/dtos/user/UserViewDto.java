package softuni.exam.instagraphlite.models.dtos.user;

public class UserViewDto {

    private Long id;
    private String username;
    private Long postsCount;

    public UserViewDto() {
    }

    public UserViewDto(Long id, String username, Long postsCount) {
        this.id = id;
        this.username = username;
        this.postsCount = postsCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPostsCount() {
        return postsCount;
    }

    public void setPostsCount(Long postsCount) {
        this.postsCount = postsCount;
    }
}
