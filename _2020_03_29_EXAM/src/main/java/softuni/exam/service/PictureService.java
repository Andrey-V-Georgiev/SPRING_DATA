package softuni.car_dealer_exam.service;


import java.io.IOException;

public interface PictureService {

    boolean areImported();

    String readPicturesFromFile() throws IOException;
	
	String importPictures() throws IOException;

}
