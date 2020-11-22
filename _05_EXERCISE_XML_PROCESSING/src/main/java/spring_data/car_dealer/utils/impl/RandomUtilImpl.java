package spring_data.car_dealer.utils.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import spring_data.car_dealer.utils.RandomUtil;

import java.util.Random;

@Component
public class RandomUtilImpl implements RandomUtil {

    private final Random random;

    @Autowired
    public RandomUtilImpl(Random random) {
        this.random = random;
    }

    @Override
    public int getRandomIntFromOneTo(int upperBorder) {
        int randomInt = this.random.nextInt((int) upperBorder) + 1;
        return randomInt;
    }

    @Override
    public int getRandomIntFromZeroTo(int upperBorder) {
        int randomInt = this.random.nextInt((int) upperBorder);
        return randomInt;
    }
}
