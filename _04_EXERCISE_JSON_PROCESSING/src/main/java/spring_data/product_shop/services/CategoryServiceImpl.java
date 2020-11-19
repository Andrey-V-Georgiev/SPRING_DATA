package spring_data.product_shop.services;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_data.product_shop.models.dtos.CategorySeedDto;
import spring_data.product_shop.models.dtos.UserSeedDto;
import spring_data.product_shop.models.entities.Category;
import spring_data.product_shop.models.entities.User;
import spring_data.product_shop.repositories.CategoryRepository;
import spring_data.product_shop.utils.ValidationUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(ModelMapper modelMapper, ValidationUtil validationUtil, Gson gson, CategoryRepository categoryRepository) {
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.categoryRepository = categoryRepository;
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
}
