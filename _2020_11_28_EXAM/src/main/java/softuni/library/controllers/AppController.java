package softuni.library.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class AppController implements CommandLineRunner {


    @Autowired
    public AppController() {

    }

    @Override
    public void run(String... args) throws Exception {

    }
}
