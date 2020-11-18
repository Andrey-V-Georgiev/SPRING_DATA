package spring_data.product_shop.controllers;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class AppController implements CommandLineRunner {

    private final Gson gson;

    @Autowired
    public AppController(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
