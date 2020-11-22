package spring_data.car_dealer.utils.impl;

import org.springframework.stereotype.Component;
import spring_data.car_dealer.utils.ValidationUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidationUtilImpl implements ValidationUtil {

    private Validator validator;

    public ValidationUtilImpl() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public <T> boolean isValid(T entity) {
        return this.validator.validate(entity).isEmpty();
    }

    @Override
    public <T> Set<ConstraintViolation<T>> violations(T entity) {
        return this.validator.validate(entity);
    }

    @Override
    public <T> void printConstraintViolations(T entity) {
        this.validator.validate(entity)
                .stream()
                .map(ConstraintViolation::getMessage)
                .forEach(System.out::println);
    }
}
