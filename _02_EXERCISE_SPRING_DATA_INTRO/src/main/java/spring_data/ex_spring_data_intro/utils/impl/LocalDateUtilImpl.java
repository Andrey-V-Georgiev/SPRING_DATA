package spring_data.ex_spring_data_intro.utils.impl;

import org.springframework.stereotype.Component;
import spring_data.ex_spring_data_intro.utils.LocalDateUtil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class LocalDateUtilImpl implements LocalDateUtil {
    @Override
    public LocalDate parseByPattern(String pattern, String dateString) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        LocalDate localDate = LocalDate.from(dtf.parse(dateString));
        return localDate;
    }
}
