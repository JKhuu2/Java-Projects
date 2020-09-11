/**
 * Homework 3 
 * Jennifer Khuu, jtk2eh 
 * 
 * Sources: Lecture Notes, Big Java book, TA Help
 */
import java.util.Comparator;

public class CompareByRating implements Comparator<Photograph> {
    public int compare(Photograph a, Photograph b) {

        // get ratings of photographs a and b
        int ratingA = a.getRating();
        int ratingB = b.getRating();

        // orders photographs a and b by ratings in descending order
        int retValRating = ratingB - ratingA;
        if (retValRating != 0) {
            return retValRating;
        }

        // gets captions of photographs a and b
        String captionA = a.getCaption();
        String captionB = b.getCaption();

        // orders photographs a and b based on captions, which are ordered in alphabetical order
        int retValCap = captionA.compareTo(captionB);
        return retValCap;
    }
}
