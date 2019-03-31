package cs2410.assn4;

import javafx.scene.image.Image;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Controller class used to handle the Image data displayed by View.
 * @author Gary Johnson
 * @version 1.0
 */
public class Controller {

    /**
     * private String used to store the name of a file
     */
    private static String fileName;

    /**
     * public ArrayList of Strings to store Image URLs
     */
    public static ArrayList<String> images;

    /**
     * public boolean that tells View when to disable the buttons
     */
    public boolean disabled;

    /**
     * Constructor: Take a String as a parameter and apply it to the fileName member.
     *              This constructor also defaults the disabled boolean to false.
     *              It then calls fillArray() to fill the ArrayList.
     * @param fN
     */
    public Controller(String fN){
        fileName = fN;
        disabled = false;
        fillArray();
    }

    /**
     * Method: Read the file given to the constructor and add its contents to the ArrayList.
     */
    public static void fillArray(){
        images = new ArrayList();
        Scanner readFile;
        try {
            readFile = new Scanner(new FileReader(fileName));
            while(readFile.hasNext()){
                images.add(readFile.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method: Write the contents of the ArrayList to the file given to the constructor.
     *         This method should replace any contents currently in the file.
     *         To be called when there is a close request on the GUI to save any changes to the file.
     */
    public static void saveList() {
        try (PrintWriter writeFile = new PrintWriter(new FileOutputStream(fileName), true)) {
            for (int i = 0; i < images.size(); i++) {
                writeFile.println(String.valueOf(images.get(i)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method: Take a URL String and make a new Image object and add that new Image into the ArrayList.
     *         The image should be added at the index that gets passed in.
     * @param imgURL //Image URL from which the new Image is made
     * @param cIndex //Current Index that specifies where to add the new Image in the ArrayList
     * @return a new Image
     */
    public Image addImage(String imgURL, int cIndex) {
        Image image = new Image(imgURL);
        if (cIndex + 1 > images.size() - 1){
            cIndex = 0;
        }
        else {
            cIndex += 1;
        }
        images.add(cIndex, imgURL);
        disabled = false;
        return image;
    }

    /**
     * Method: Delete the image at the specified index from the ArrayList.
     * @param cIndex  //Current Index to specify which image in the list should be removed
     * @return the next image in the ArrayList.
     */
    public Image deleteImage(int cIndex) {
        int nextIndex = cIndex + 1;
        int lastIndex = images.size() - 1;
        if (nextIndex > lastIndex){
            nextIndex = 0;
        }
        Image image = stringToImage(images.get(nextIndex)); //Make an image that's next to the image that's being deleted
        images.remove(cIndex);
        if (images.isEmpty()){ //If the ArrayList is empty, disabled becomes true and the buttons become disabled
            disabled = true;
        }
        return image;  //Return the next image
    }

    /**
     * Method: Create a new image from the URL at the specified index of the ArrayList.
     * @param cIndex //Current Index to specify which URL should be made into an Image
     * @return the Image.
     */
    public Image showNextImage(int cIndex) {
        Image image = stringToImage(images.get(cIndex));
        return image;
    }

    /**
     * Method: Create a new image from the URL at the specified index of the ArrayList.
     * @param cIndex //Current Index to specify which URL should be made into an Image
     * @return the Image.
     */
    public Image showPrevImage(int cIndex) {
        Image image = stringToImage(images.get(cIndex));
        return image;
    }

    /**
     * Method: Turn a String into an Image object that the other methods can use.
     * @param imgURL //Image URL used to create the new Image
     * @return the new Image.
     */
    private Image stringToImage(String imgURL){
        Image image = new Image(imgURL);
        return image;
    }

    /**
     * Method: Exit the program.
     *         To be called when the GUI is closed
     */
    public void quit() {
        System.exit(0);
    }
}
