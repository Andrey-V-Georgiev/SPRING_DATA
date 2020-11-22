package spring_data.car_dealer.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring_data.car_dealer.utils.ValidationUtil;
import spring_data.car_dealer.utils.impl.ValidationUtilImpl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

@Configuration
public class AppBeanConfig {

    @Bean
    public BufferedReader bufferedReader() {
        return new BufferedReader(new InputStreamReader(System.in));
    }

    @Bean
    public Random random(){
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
                .create();
    }
}
