import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Homework 4 
 * Jennifer Khuu, jtk2eh 
 * 
 * Sources: Lecture Notes, Big Java book, TA Help
 */
public class Photograph implements Comparable<Photograph> {

    // created caption, filename, and imageData fields
    private String caption;
    private String filename;
    protected BufferedImage imageData;

    // created dateTaken and rating fields;
    private String dateTaken;
    private int rating;

    // constructor - initialized caption and filename
    public Photograph(String caption, String filename) {
        this.caption = caption;
        this.filename = filename;
        // setting default values for dateTaken and rating
        this.dateTaken = "1901-01-01";
        this.rating = 0;
    }

    // constructor that takes in caption, filename, dateTaken, and rating and assigns it to the class variables
    public Photograph(String caption, String filename, String dateTaken, int rating) {
        this.caption = caption;
        this.filename = filename;
        if (checkDate(dateTaken)) {
            this.dateTaken = dateTaken;
        }
        // returns default date if invalid date
        if (!checkDate(dateTaken)) {
            this.dateTaken = "1901-01-01";
        }
        // returns rating=0 if invalid rating (not between 1 and 5)
        if (rating < 0 || rating > 5) {
            this.rating = 0;
        }
        if (rating >= 0 && rating <= 5) {
            this.rating = rating;
        }
    }

    public boolean loadImageData(String filename) {
        try {
            // loads image data from filename and stores into imageData
            BufferedImage img = ImageIO.read(new File(filename));
            this.imageData = img;
            // filename stored in filename field
            this.filename = filename;
            // return true if successful
            return true;
        }
        
        // exception - return false
        catch (IOException e) {
            return false;
        }
    }

    // getter for imageData
    public BufferedImage getImageData() {
        return this.imageData;
    }

    // setter for imageData
    public BufferedImage setImageData(BufferedImage data) {
        this.imageData = data;
        return this.imageData;
    }

    // checks to see if a string can be turned into an int
    public static boolean isNumeric(String strNum) {
        try {
            Integer.parseInt(strNum);
        }
        // returns false if error occurs when converting to int
        catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    // checks to see if valid date
    public boolean checkDate(String date) {

        // checks to see if the date is correct length
        if (date.length() == 10) {

            // get the year part of date and convert to int
            String year = date.substring(0, 4);

            // checks to see if the year substring can be turned into an int
            if (isNumeric(year)) {

                // converts to string to int
                int yearInt = Integer.parseInt(year);

                // check for valid year
                if (yearInt >= 1000 && yearInt <= 9999) {

                    // get month and convert to int
                    String month = date.substring(5, 7);

                    // checks if month string is int
                    if (isNumeric(month)) {

                        // converts to int
                        int monthInt = Integer.parseInt(month);

                        // check for valid month
                        if (monthInt > 0 && monthInt < 13) {

                            // get day and convert to int
                            String day = date.substring(8, 10);

                            // checks if day string is int
                            if (isNumeric(day)) {

                                // converts to int
                                int dayInt = Integer.parseInt(day);

                                // check for valid day
                                if (dayInt > 0 && dayInt < 32) {

                                    // return true if everything passes
                                    return true;

                                }
                            }

                        }
                    }
                }
            }

        }

        return false;
    }

    // accessors - allows access to the fields of Photograph
    public String getCaption() {
        return this.caption;
    }

    public String getFilename() {
        return this.filename;
    }

    public String getDateTaken() {
        return this.dateTaken;
    }

    public int getRating() {
        return this.rating;
    }

    // mutators - changes the rating and caption fields when called
    public void setRating(int rating) {
        // invalid rating will return rating=0
        if (rating < 0 || rating > 5) {
            this.rating = 0;
        } else {
            this.rating = rating;
        }
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    // hashCode - returns the filename hashCode
    public int hashCode() {
        return filename.hashCode();
    }

    // equals - checks to see if object caption and filename matches the one in the class
    public boolean equals(Object o) {
        // check to see if o is type Photograph
        if (o instanceof Photograph) {
            // gets caption and filename of object o
            String newCaption = ((Photograph) o).getCaption();
            String newFilename = ((Photograph) o).getFilename();
            // checks to see if the caption and filename of object equals the one in the class and returns true if so
            if (this.caption == newCaption && this.filename == newFilename) {
                return true;
            }
        }

        return false;
    }

    // toString - defining the print statement of the photograph
    public String toString() {
        return "Caption: " + this.caption + ", Filename: " + this.filename + ", Date Taken: " + this.dateTaken + ", Rating: "
                + this.rating + "\n";
    }

    // compares the class photograph with photograph p in order to sort them with natural ordering
    public int compareTo(Photograph p) {

        // compares the two dateTakens and if returns neg number, the class's photograph will be placed before p and vice versa
        int retValDate = this.dateTaken.compareTo(p.dateTaken);

        // return retValDate if they're not equal
        if (retValDate != 0) {
            return retValDate;
        }

        // orders photographs by caption and captions are ordered in alphabetical order
        int retValCap = this.caption.compareTo(p.caption);

        // return retValCap if they're not equal
        if (retValCap != 0) {
            return retValCap;
        }

        // otherwise; return 0
        return 0;
    }
}
