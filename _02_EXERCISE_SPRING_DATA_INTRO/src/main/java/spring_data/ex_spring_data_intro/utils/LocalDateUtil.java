package spring_data.ex_spring_data_intro.utils;

import java.time.LocalDate;

public interface LocalDateUtil {

    LocalDate parseByPattern(String pattern, String dateString);
}
