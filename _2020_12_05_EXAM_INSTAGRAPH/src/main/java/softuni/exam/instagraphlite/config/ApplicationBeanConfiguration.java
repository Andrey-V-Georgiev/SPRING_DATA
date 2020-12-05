package softuni.exam.instagraphlite.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.exam.instagraphlite.adapters.LocalDateAdapterJSON;
import softuni.exam.instagraphlite.adapters.LocalDateTimeAdapterJSON;
import softuni.exam.instagraphlite.utils.ValidationUtil;
import softuni.exam.instagraphlite.utils.XmlParser;
import softuni.exam.instagraphlite.utils.impl.ValidationUtilImpl;
import softuni.exam.instagraphlite.utils.impl.XmlParserImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;


@Configuration
public class ApplicationBeanConfiguration {


    @Bean
    public BufferedReader bufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    @Bean
    public Random random() {
        return new Random();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl();
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapterJSON().nullSafe())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapterJSON().nullSafe())
                .create();
    }

    @Bean
    public XmlParser xmlParser() {
        return new XmlParserImpl();
    }
}
