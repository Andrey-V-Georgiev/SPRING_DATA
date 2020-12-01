package softuni.car_dealer_exam.utils;

import java.io.IOException;

public interface FileUtil {

    String readFile(String filePath) throws IOException;

    String readFileAddedNewLines(String filePath) throws IOException;

    void writeFile(String content, String filePath) throws IOException;
}
