
/**
 * Homework 3 
 * Jennifer Khuu, jtk2eh 
 * 
 * Sources: Lecture Notes, Big Java book, TA Help
 */
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Test;

public class HW3Tests {

    @Test
    public void removePhotoTestTrue() {
        PhotoLibrary myLibrary = new PhotoLibrary("Jennifer", 2);
        Photograph a = new Photograph("hi", "what");
        myLibrary.addPhoto(a);
        assertEquals(true, myLibrary.removePhoto(a));
    }

    @Test
    public void removePhotoTestFalse() {
        PhotoLibrary myLibrary = new PhotoLibrary("Jennifer", 2);
        Photograph a = new Photograph("hi", "what");
        myLibrary.addPhoto(a);
        Photograph b = new Photograph("hello", "bye");
        assertEquals(false, myLibrary.removePhoto(b));
    }

    @Test
    public void compareToTestDateTaken() {
        Photograph a = new Photograph("a", "b");
        Photograph b = new Photograph("b", "c", "1999-01-01", 2);
        boolean bool = false;
        if (a.compareTo(b) < 0) {
            bool = true;
        }
        assertTrue(bool);
    }

    @Test
    public void compareToTestCaption() {
        Photograph a = new Photograph("a", "b");
        Photograph b = new Photograph("b", "c");
        boolean bool = false;
        if (a.compareTo(b) < 0) {
            bool = true;
        }
        assertTrue(bool);
    }

    @Test
    public void compareByCaptionCap() {
        Photograph a = new Photograph("a", "b");
        Photograph b = new Photograph("b", "c");
        ArrayList<Photograph> mLibrary = new ArrayList<>();
        mLibrary.add(b);
        mLibrary.add(a);
        Collections.sort(mLibrary, new CompareByCaption());
        ArrayList<Photograph> expected = new ArrayList<>();
        expected.add(a);
        expected.add(b);
        assertEquals(expected, mLibrary);

    }

    @Test
    public void compareByCaptionRating() {
        Photograph a = new Photograph("b", "b", "1909-01-01", 2);
        Photograph b = new Photograph("b", "c", "1909-01-01", 1);
        ArrayList<Photograph> mLibrary = new ArrayList<>();
        mLibrary.add(b);
        mLibrary.add(a);
        Collections.sort(mLibrary, new CompareByCaption());
        ArrayList<Photograph> expected = new ArrayList<>();
        expected.add(a);
        expected.add(b);
        assertEquals(expected, mLibrary);
    }

    @Test
    public void compareByRatingRat() {
        Photograph a = new Photograph("b", "b", "1909-01-01", 2);
        Photograph b = new Photograph("a", "c", "1909-01-01", 1);
        ArrayList<Photograph> mLibrary = new ArrayList<>();
        mLibrary.add(b);
        mLibrary.add(a);
        Collections.sort(mLibrary, new CompareByRating());
        ArrayList<Photograph> expected = new ArrayList<>();
        expected.add(a);
        expected.add(b);
        assertEquals(expected, mLibrary);
    }

    @Test
    public void compareByRatingCap() {
        Photograph a = new Photograph("b", "b", "1909-01-01", 2);
        Photograph b = new Photograph("a", "c", "1909-01-01", 2);
        ArrayList<Photograph> mLibrary = new ArrayList<>();
        mLibrary.add(a);
        mLibrary.add(b);
        Collections.sort(mLibrary, new CompareByRating());
        ArrayList<Photograph> expected = new ArrayList<>();
        expected.add(b);
        expected.add(a);
        assertEquals(expected, mLibrary);
    }
}
