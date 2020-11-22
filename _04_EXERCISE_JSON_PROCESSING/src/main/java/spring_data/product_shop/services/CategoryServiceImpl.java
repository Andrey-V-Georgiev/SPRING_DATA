package spring_data.product_shop.services;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_data.product_shop.models.dtos.categoryDtos.CategoryByProductCountDto;
import spring_data.product_shop.models.dtos.categoryDtos.CategorySeedDto;
import spring_data.product_shop.models.entities.Category;
import spring_data.product_shop.repositories.CategoryRepository;
import spring_data.product_shop.utils.FileUtil;
import spring_data.product_shop.utils.RandomUtil;
import spring_data.product_shop.utils.ValidationUtil;

import javax.transaction.Transactional;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final CategoryRepository categoryRepository;
    private final RandomUtil randomUtil;
    private final FileUtil fileUtil;

    @Autowired
    public CategoryServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, CategoryRepository categoryRepository, RandomUtil randomUtil, FileUtil fileUtil) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.categoryRepository = categoryRepository;
        this.randomUtil = randomUtil;
        this.fileUtil = fileUtil;
    }

    @Override
    public void seedCategories(String filePath) throws FileNotFoundException {
        /* Read the json */
        CategorySeedDto[] dtos = this.gson.fromJson(new FileReader(filePath), CategorySeedDto[].class);
        for (CategorySeedDto dto : dtos) {
            Category categoryFromDb = this.categoryRepository.getCategoryByName(dto.getName());
            if (categoryFromDb != null) {
                continue;
            }
            /* Validate the dtos */
            if (this.validationUtil.isValid(dto)) {
                Category category = this.modelMapper.map(dto, Category.class);
                this.categoryRepository.saveAndFlush(category);
            } else {
                this.validationUtil.printConstraintViolations(dto);
            }
        }
    }

    @Override
    public Set<Category> getRandomCategories() {
        /* Get random categories count*/
        int randomCategoriesCount = this.randomUtil.getRandomIntFromOneTo(5);
        Set<Category> randomCategories = new HashSet<>();
        for (int i = 0; i < randomCategoriesCount; i++) {
            /* Get random category id */
            int randomCategoryId = this.randomUtil.getRandomIntFromOneTo((int) this.categoryRepository.count());
            Category randomCategory = this.categoryRepository.getCategoryById(randomCategoryId);
            randomCategories.add(randomCategory);
        }
        return randomCategories;
    }

    @Override
    public void categoriesByProductsCount(String filePath) throws IOException {
        List<CategoryByProductCountDto> categoryDtos =  this.categoryRepository.getCategoryWithProductStatistics();
        String categoryDtosJson = this.gson.toJson(categoryDtos);
        this.fileUtil.writeFile(categoryDtosJson, filePath);
    }
}
