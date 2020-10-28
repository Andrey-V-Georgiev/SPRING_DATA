package spring_data.ex_spring_data_intro.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_data.ex_spring_data_intro.constants.PathConstants;
import spring_data.ex_spring_data_intro.entities.Category;
import spring_data.ex_spring_data_intro.repositories.CategoryRepository;
import spring_data.ex_spring_data_intro.utils.ReadFileUtil;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import static spring_data.ex_spring_data_intro.constants.PathConstants.*;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ReadFileUtil readFileUtil;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ReadFileUtil readFileUtil) {
        this.categoryRepository = categoryRepository;
        this.readFileUtil = readFileUtil;
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() != 0) {
            return;
        }
        String[] lines = readFileUtil.read(CATEGORIES_FILE_RELATIVE_PATH);
        for (int i = 0; i < lines.length; i++) {
            String name = lines[i];
            Category category = new Category(name);
            this.categoryRepository.saveAndFlush(category);
        }
    }
}
