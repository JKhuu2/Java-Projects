/**
 * Homework 3 
 * Jennifer Khuu, jtk2eh 
 * 
 * Sources: Lecture Notes, Big Java book, TA Help
 */
import java.util.Comparator;

public class CompareByCaption implements Comparator<Photograph> {

    public int compare(Photograph a, Photograph b) {

        // gets the captions of photograph a and b
        String captionA = a.getCaption();
        String captionB = b.getCaption();

        // will place a and b in alphabetical order based on caption
        int retValCap = captionA.compareTo(captionB);
        if (retValCap != 0) {
            return retValCap;
        }

        // gets ratings for photographs a and b
        int ratingA = a.getRating();
        int ratingB = b.getRating();

        // orders a and b in descending order depending on rating
        int retValRating = ratingB - ratingA;
        if (retValRating != 0) {
            return retValRating;
        }
        return 0;
    }
}
