package spring_data.car_dealer.utils;

public interface RandomUtil {
    /* return int from zero to given border*/
    int getRandomId(int upperBorder);
    /* return int from one to given border*/
    int getRandomInt(int lowerBorder, int upperBorder);
}
