package softuni.exam.utils.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import softuni.exam.utils.RandomUtil;

import java.util.Random;

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
}
