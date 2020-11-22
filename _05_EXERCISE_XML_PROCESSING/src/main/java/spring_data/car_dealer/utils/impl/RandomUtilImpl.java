package spring_data.car_dealer.utils.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import spring_data.car_dealer.utils.RandomUtil;

import java.util.*;

@Component
public class RandomUtilImpl implements RandomUtil {

    private final Random random;

    @Autowired
    public RandomUtilImpl(Random random) {
        this.random = random;
    }

    @Override
    public int getRandomId(int upperBorder) {
        int randomInt = this.random.nextInt((int) upperBorder) + 1;
        return randomInt;
    }

    @Override
    public int getRandomInt(int lowerBorder, int upperBorder) {
        int finalUpperBorder = lowerBorder + upperBorder;
        int randomInt = this.random.nextInt(finalUpperBorder) + lowerBorder;
        return randomInt;
    }

    @Override
    public <T> T getRandomInstance(int repoCount, Class<T> tClass, JpaRepository<T, Long> tRepository) {
        /* get random id */
        long randomId = (long) this.getRandomId(repoCount);
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
        int randomListSize = this.getRandomInt(lowerBorder, upperBorder);
        List<T> listT = new ArrayList<>();
        for (int i = 0; i < randomListSize; i++) {
            /* get random entity id */
            long randomId = (long) this.getRandomId((int) tRepository.count());
            /* find entity by id */
            Optional<T> e = tRepository.findById(randomId);
            e.ifPresent(listT::add);
        }
        return listT;
    }
}
