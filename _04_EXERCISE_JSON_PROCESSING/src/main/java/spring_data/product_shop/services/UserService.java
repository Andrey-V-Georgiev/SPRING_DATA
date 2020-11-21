package spring_data.product_shop.services;

import spring_data.product_shop.models.entities.User;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;

public interface UserService {

    void seedUsers(String filePath) throws FileNotFoundException;

    User getRandomUser();

    User getRandomUserOrNull();

    void successfullySoldProducts() throws IOException;
}
