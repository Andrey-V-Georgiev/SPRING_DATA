package spring_data.ex_spring_data_intro.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;
import spring_data.ex_spring_data_intro.services.CategoryService;
import spring_data.ex_spring_data_intro.utils.ReadFileUtil;

import java.util.Arrays;

import static spring_data.ex_spring_data_intro.constants.PathConstants.CATEGORIES_FILE_RELATIVE_PATH;

@Controller
public class AppController implements CommandLineRunner {

    private final CategoryService categoryService;

    @Autowired
    public AppController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.categoryService.seedCategories();
    }
}
