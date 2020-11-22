package spring_data.product_shop.models.dtos.userDtos;

import com.google.gson.annotations.Expose;

import java.util.*;

public class UsersAndProductsRootDto {

    @Expose
    private int usersCount;
    @Expose
    private Set<UserAndProductDto> users;

    public UsersAndProductsRootDto() {
    }

    public UsersAndProductsRootDto(int usersCount, Set<UserAndProductDto> users) {
        this.usersCount = usersCount;
        this.users = users;
    }

    public int getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(int usersCount) {
        this.usersCount = usersCount;
    }

    public Set<UserAndProductDto> getUsers() {
        return users;
    }

    public void setUsers(Set<UserAndProductDto> users) {
        this.users = users;
    }
}
