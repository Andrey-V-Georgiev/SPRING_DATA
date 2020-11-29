package softuni.exam.repository;

//ToDo
public interface PictureRepository {

    interface RandomUtil {
        /* return int from zero to given border*/
        int getRandomId(int upperBorder);
        /* return int from one to given border*/
        int getRandomInt(int lowerBorder, int upperBorder);
    }
}
