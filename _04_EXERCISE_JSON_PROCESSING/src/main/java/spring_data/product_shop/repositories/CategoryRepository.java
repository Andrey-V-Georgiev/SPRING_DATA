package spring_data.product_shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring_data.product_shop.models.dtos.categoryDtos.CategoryByProductCountDto;
import spring_data.product_shop.models.entities.Category;

import java.util.*;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category getCategoryByName(String name);

    Category getCategoryById(long id);

    /* Query 3 – Categories by Products Count */
    @Query(value = " SELECT new  spring_data.product_shop.models.dtos.categoryDtos.CategoryByProductCountDto(" +
            " c.name, count(p.id), avg(p.price), sum(p.price)) " +
            " FROM Category c" +
            " JOIN c.products p" +
            " GROUP BY c.id" +
            " ORDER BY count(p.id)")
    List<CategoryByProductCountDto> getCategoryWithProductStatistics();
}
