package spring_data.car_dealer.utils;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RandomUtil {

    int getRandomId(int upperBorder);

    int getRandomInt(int lowerBorder, int upperBorder);

    <T> Object getRandomInstance(int repoCount,Class<T> tClass, JpaRepository<T, Long> tRepository);

    <T> List<T> getListOfRandomInstances(int lowerBorder, int upperBorder,  Class<T> tClass, JpaRepository<T, Long> tRepository);
}
