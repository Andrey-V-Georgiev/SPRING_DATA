package spring_data.ex_spring_data_intro.utils.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring_data.ex_spring_data_intro.entities.Category;
import spring_data.ex_spring_data_intro.repositories.CategoryRepository;
import spring_data.ex_spring_data_intro.utils.RandomCategoriesUtil;

import java.util.*;

@Component
public class RandomCategoriesUtilImpl implements RandomCategoriesUtil {

    private final CategoryRepository categoryRepository;

    @Autowired
    public RandomCategoriesUtilImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Set<Category> getRandom() {
        Random random = new Random();
        long repoSize = this.categoryRepository.count();
        Set<Category> randomCategories = new LinkedHashSet<>();
        for (int i = 0; i < 3; i++) {
            long randomId  = random.nextInt((int) repoSize) + 1;
            Category randomCategory = this.categoryRepository.findCategoryById(randomId);
            randomCategories.add(randomCategory);
        }
        return randomCategories;
    }
}
