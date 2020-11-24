package spring_data.car_dealer.services;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RandomService {

    <T> T getRandomInstance(int repoCount,Class<T> tClass, JpaRepository<T, Long> tRepository);

    <T> List<T> getListOfRandomInstances(int lowerBorder, int upperBorder, Class<T> tClass, JpaRepository<T, Long> tRepository);
}
