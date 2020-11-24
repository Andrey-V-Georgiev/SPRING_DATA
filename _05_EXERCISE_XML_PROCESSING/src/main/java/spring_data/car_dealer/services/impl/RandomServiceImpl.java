package spring_data.car_dealer.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import spring_data.car_dealer.services.RandomService;
import spring_data.car_dealer.utils.RandomUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RandomServiceImpl implements RandomService {

    private final RandomUtil randomUtil;

    @Autowired
    public RandomServiceImpl(RandomUtil randomUtil) {
        this.randomUtil = randomUtil;
    }

    @Override
    public <T> T getRandomInstance(int repoCount, Class<T> tClass, JpaRepository<T, Long> tRepository) {
        /* get random id */
        long randomId = (long) this.randomUtil.getRandomId(repoCount);
        /* find entity by id */
        Optional<T> e = tRepository.findById(randomId);
        if (e.isEmpty()) {
            return null;
        } else {
            return e.get();
        }
    }

    @Override
    public <T> List<T> getListOfRandomInstances(
            int lowerBorder, int upperBorder,  Class<T> tClass,  JpaRepository<T, Long> tRepository
    ) {
        /* get random list size */
        int randomListSize = this.randomUtil.getRandomInt(lowerBorder, upperBorder);
        List<T> listT = new ArrayList<>();
        for (int i = 0; i < randomListSize; i++) {
            /* get random entity id */
            long randomId = (long) this.randomUtil.getRandomId((int) tRepository.count());
            /* find entity by id */
            Optional<T> e = tRepository.findById(randomId);
            e.ifPresent(listT::add);
        }
        return listT;
    }
}
