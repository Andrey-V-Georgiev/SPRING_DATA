package spring_data.product_shop.utils;

import javax.validation.ConstraintViolation;
import java.util.Set;

public interface ValidationUtil {

    <T> boolean isValid(T entity);

    <T> Set<ConstraintViolation<T>> violations(T entity);

    <T> void printConstraintViolations(T entity);
}
