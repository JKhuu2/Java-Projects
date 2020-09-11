
/**
 * Homework 3 
 * Jennifer Khuu, jtk2eh
 * 
 * Sources: Lecture Notes, Big Java book, TA Help
 */
import java.util.ArrayList;

abstract public class PhotographContainer {
    // created the name and photos fields
    protected String name;
    protected ArrayList<Photograph> photos;

    // constructor: initialized name field after being called
    public PhotographContainer(String name) {
        this.name = name;
        photos = new ArrayList<Photograph>();
    }

    // accessors: returns references to the name and photo fields
    public String getName() {
        return this.name;
    }

    public ArrayList<Photograph> getPhotos() {
        return this.photos;
    }

    // mutators: rewrites the name field when called
    public void setName(String name) {
        this.name = name;
    }

    // add photograph p to current list of photos if it wasn't already there
    public boolean addPhoto(Photograph p) {
        // only adds photo if not in list and isn't null and returns true
        if (!photos.contains(p) && p != null) {
            photos.add(p);
            return true;
        }
        return false;

    }

    // returns true if photos has photograph
    public boolean hasPhoto(Photograph p) {
        // returns true if photos contains p and vice versa
        return photos.contains(p);
    }

    // remove photo if in list of photos
    public boolean removePhoto(Photograph p) {
        // checks to see if photos contains p and remove it if so and return true
        if (photos.contains(p)) {
            photos.remove(p);
            return true;
        }
        return false;
    }

    // returns the number of photos in the photos HashSet
    public int numPhotographs() {
        return photos.size();
    }

    // returns true if object's name equals name in PhotographContainer class
    public boolean equals(Object o) {
        // checks to see if object o is type PhotographContainer
        if (o instanceof PhotographContainer) {
            // gets the name of the object and checks to see if it's
            // the same as the one currently in the class
            String newName = ((PhotographContainer) o).getName();
            if (newName == this.name) {
                return true;
            }
        }
        return false;
    }

    // returns the name of album and its list of photos
    public String toString() {
        return "Name: " + this.name + ", Photos: " + this.photos;
    }

    // returns hashcode of the name field
    public int hashCode() {
        return name.hashCode();
    }

    // returns photos list with photos that have a rating that's
    // greater than or equal to the one in the parameter
    public ArrayList<Photograph> getPhotos(int rating) {

        // creating a new local variable photos list
        ArrayList<Photograph> newPhotos = new ArrayList<Photograph>();

        // returns null if invalid rating
        if (rating < 0 || rating > 5) {
            return null;
        } else {
            // iterate through each photo in photos list
            for (Photograph p : this.getPhotos()) {

                // adds to newPhotos list if its rating >= specified rating
                if (p.getRating() >= rating) {
                    newPhotos.add(p);
                }
            }
        }

        return newPhotos;
    }

    // returning a list of photos from a given year
    public ArrayList<Photograph> getPhotosInYear(int year) {

        // creating a new local variable photos list
        ArrayList<Photograph> newPhotos = new ArrayList<Photograph>();

        // returns null if invalid year
        if (year < 1000 || year > 9999) {
            return null;
        } else {
            // iterates through each photo in photos list
            for (Photograph p : this.getPhotos()) {

                // getting the year in a photo from dateTaken
                String dateTaken = p.getDateTaken();
                String myear = dateTaken.substring(0, 4);
                int yearInt = Integer.parseInt(myear);

                // checks to see if year is the same as the one in the parameter
                // and adding it to the new list if so
                if (yearInt == year) {
                    newPhotos.add(p);
                }
            }
        }

        return newPhotos;
    }

    // returning a photos list from a certain month and year
    public ArrayList<Photograph> getPhotosInMonth(int month, int year) {

        // creating a new local variable photos list
        ArrayList<Photograph> newPhotos = new ArrayList<Photograph>();

        // returns null if invalid year and/or month
        if (year < 1000 || year > 9999) {
            return null;
        }
        if (month < 1 || month > 12) {
            return null;
        } else {
            // iterates through each photo in photos list
            for (Photograph p : this.getPhotos()) {

                // getting the year in a photo from dateTaken
                String dateTaken = p.getDateTaken();
                String myear = dateTaken.substring(0, 4);
                int yearInt = Integer.parseInt(myear);

                // getting month from each photo
                String mmonth = dateTaken.substring(5, 7);
                int monthInt = Integer.parseInt(mmonth);

                // checks to see if year and month is the same as the ones in the parameter
                // and adding it to the new list if so
                if (yearInt == year && monthInt == month) {
                    newPhotos.add(p);
                }
            }
        }

        return newPhotos;
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

    // returns a list of photos that were taken between the beginDate and the endDate (inclusive)
    public ArrayList<Photograph> getPhotosBetween(String beginDate, String endDate) {

        // creating a new local variable photos list
        ArrayList<Photograph> newPhotos = new ArrayList<Photograph>();

        // returns null if invalid beginDate or endDate
        if (!checkDate(beginDate)) {
            return null;
        }
        if (!checkDate(endDate)) {
            return null;
        }

        // getting the year of beginDate and endDate
        String beginYear = beginDate.substring(0, 4);
        int beginYearInt = Integer.parseInt(beginYear);
        String endYear = endDate.substring(0, 4);
        int endYearInt = Integer.parseInt(endYear);

        // getting the month of beginDate and endDate
        String beginMonth = beginDate.substring(5, 7);
        int beginMonthInt = Integer.parseInt(beginMonth);
        String endMonth = endDate.substring(5, 7);
        int endMonthInt = Integer.parseInt(endMonth);

        // getting the day of beginDate and endDate
        String beginDay = beginDate.substring(8, 10);
        int beginDayInt = Integer.parseInt(beginDay);
        String endDay = endDate.substring(8, 10);
        int endDayInt = Integer.parseInt(endDay);

        // return null if begin year is after end year
        if (beginYearInt > endYearInt) {
            return null;

        }
        if (beginYearInt == endYearInt) {
            // return null if begin month after end month
            if (beginMonthInt > endMonthInt) {
                return null;
            }
            // return null if begin day after end day
            if (beginMonthInt == endMonthInt) {
                if (beginDayInt > endDayInt) {
                    return null;
                }
            }
        }

        // iterate through each photo in photos list
        for (Photograph p : this.getPhotos()) {

            // getting the year of the photo
            String date = p.getDateTaken();
            String pYear = date.substring(0, 4);
            int pYearInt = Integer.parseInt(pYear);

            // valid photo if the year is between the begin year and end year
            if (pYearInt >= beginYearInt && pYearInt <= endYearInt) {

                // getting the month of photo
                String pMonth = date.substring(5, 7);
                int pMonthInt = Integer.parseInt(pMonth);

                // getting the day of photo
                String pDay = date.substring(8, 10);
                int pDayInt = Integer.parseInt(pDay);

                if (beginYearInt == endYearInt) {

                    if (beginMonthInt == endMonthInt) {

                        // if end date equals begin date, photo has to equal that date
                        if (beginDayInt == endDayInt) {
                            if (date == beginDate) {
                                newPhotos.add(p);
                            }
                        }

                        // add if photo between end and begin days
                        if (beginDayInt < endDayInt) {
                            if (pDayInt >= beginDayInt && pDayInt <= endDayInt) {
                                newPhotos.add(p);
                            }
                        }
                    }
                    if (beginMonthInt < endMonthInt) {

                        // day of photo has to be after or equal to begin day if same month as begin month
                        if (pMonthInt == beginMonthInt) {
                            if (pDayInt >= beginDayInt) {
                                newPhotos.add(p);
                            }
                        }

                        // day of photo has to be before or equal to end day if same month as end month
                        if (pMonthInt == endMonthInt) {
                            if (pDayInt <= endDayInt) {
                                newPhotos.add(p);
                            }
                        }

                        // if month of photo in between begin and end months, add it
                        if (pMonthInt > beginMonthInt && pMonthInt < endMonthInt) {
                            newPhotos.add(p);
                        }
                    }
                }

                if (pYearInt == beginYearInt && beginYearInt < endYearInt) {

                    // add photo if month is after begin month within same year
                    if (pMonthInt > beginMonthInt) {
                        newPhotos.add(p);
                    }

                    // add photo if day is after or equal to begin day within same month
                    if (pMonthInt == beginMonthInt) {
                        if (pDayInt >= beginDayInt) {
                            newPhotos.add(p);
                        }
                    }

                }

                // add photo if month is between begin and end months
                if (pYearInt > beginYearInt && pYearInt < endYearInt) {
                    newPhotos.add(p);
                }

                if (pYearInt == endYearInt && beginYearInt < endYearInt) {

                    // add photo if month is before end month within same year
                    if (pMonthInt < endMonthInt) {
                        newPhotos.add(p);
                    }

                    // add photo if day is before or equal to end day within same month
                    if (pMonthInt == endMonthInt) {
                        if (pDayInt <= endDayInt) {
                            newPhotos.add(p);
                        }
                    }

                }

            }
        }

        return newPhotos;
    }
}
