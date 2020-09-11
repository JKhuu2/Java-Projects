
/**
 * Homework 2 
 * Jennifer Khuu, jtk2eh 
 * 
 * Sources: Lecture Notes, Big Java book, TA Help
 */
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class PhotoLibraryTest2 {

    Photograph p = new Photograph("Hi", "Jennifer's Photos", "2019-09-26", 0);
    PhotoLibrary myLibrary = new PhotoLibrary("Jennifer", 2);
    Photograph p1 = new Photograph("what", "me", "2019-01-26", 0);
    PhotoLibrary yourLibrary = new PhotoLibrary("you", 3);
    Photograph p2 = new Photograph("Hello", "Jennifer");
    Photograph p3 = new Photograph("Good", "me", "2019-01-01", 0);
    Photograph p4 = new Photograph("Hi", "Hello", "2019-09-30", 0);
    Photograph p5 = new Photograph("why", "me", "2018-01-01", 0);

    @Test
    public void testGetPhotosWorks() {
        myLibrary.addPhoto(p);
        ArrayList<Photograph> actual = myLibrary.getPhotos(0);
        ArrayList<Photograph> expected = new ArrayList<Photograph>();
        expected.add(p);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPhotosNull() {
        myLibrary.addPhoto(p);
        assertNull(myLibrary.getPhotos(-1));
    }

    @Test
    public void testGetPhotosEmptyList() {
        myLibrary.addPhoto(p);
        ArrayList<Photograph> actual = myLibrary.getPhotos(1);
        ArrayList<Photograph> expected = new ArrayList<Photograph>();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPhotosInMonthBadMonth() {
        assertNull(myLibrary.getPhotosInMonth(00, 2019));
    }

    @Test
    public void testGetPhotosInMonthBadYear() {
        assertNull(myLibrary.getPhotosInMonth(01, 20190));
    }

    @Test
    public void testGetPhotosInMonthGood() {
        myLibrary.addPhoto(p1);
        ArrayList<Photograph> actual = myLibrary.getPhotosInMonth(01, 2019);
        ArrayList<Photograph> expected = new ArrayList<Photograph>();
        expected.add(p1);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPhotosBetweenGood() {
        myLibrary.addPhoto(p);
        myLibrary.addPhoto(p1);
        myLibrary.addPhoto(p3);
        myLibrary.addPhoto(p4);
        myLibrary.addPhoto(p5);
        myLibrary.addPhoto(p2);
        ArrayList<Photograph> actual = myLibrary.getPhotosBetween("2018-01-01", "2019-09-30");
        ArrayList<Photograph> expected = new ArrayList<Photograph>();
        expected.add(p);
        expected.add(p1);
        expected.add(p3);
        expected.add(p4);
        expected.add(p5);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPhotosBetweenGood2() {
        Photograph p6 = new Photograph("hello", "fire", "2019-01-02", 0);
        myLibrary.addPhoto(p6);
        ArrayList<Photograph> actual = myLibrary.getPhotosBetween("2018-06-01", "2019-07-30");
        ArrayList<Photograph> expected = new ArrayList<Photograph>();
        expected.add(p6);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPhotosBetweenNull() {
        ArrayList<Photograph> actual = myLibrary.getPhotosBetween("2018-01-01", "2017-01-01");
        assertNull(actual);
    }

    @Test
    public void testGetPhotosBetweenNull2() {
        ArrayList<Photograph> actual = myLibrary.getPhotosBetween("2019-02-01", "2019-01-02");
        ArrayList<Photograph> expected = null;
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPhotosBetweenNull3() {
        ArrayList<Photograph> actual = myLibrary.getPhotosBetween("2019-01-04", "2019-01-03");
        ArrayList<Photograph> expected = null;
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPhotosBetweenNull4() {
        ArrayList<Photograph> actual = myLibrary.getPhotosBetween("hi", "2019-01-03");
        ArrayList<Photograph> expected = null;
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPhotosBetweenNull5() {
        ArrayList<Photograph> actual = myLibrary.getPhotosBetween("2019-01-04", "hi");
        ArrayList<Photograph> expected = null;
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPhotosBetweenEmpty() {
        myLibrary.addPhoto(p);
        ArrayList<Photograph> actual = myLibrary.getPhotosBetween("2010-01-01", "2018-01-01");
        ArrayList<Photograph> expected = new ArrayList<Photograph>();
        assertEquals(expected, actual);
    }

    @Test
    public void testGetPhotosBetweenEmpty2() {
        myLibrary.addPhoto(p2);
        ArrayList<Photograph> actual = myLibrary.getPhotosBetween("1901-02-01", "1901-03-01");
        ArrayList<Photograph> expected = new ArrayList<Photograph>();
        assertEquals(expected, actual);
    }

    @Test
    public void testErasePhotoTrue() {
        myLibrary.addPhoto(p);
        boolean actual = myLibrary.erasePhoto(p);
        assertTrue(actual);
    }

    @Test
    public void testErasePhotoFalse() {
        boolean actual = myLibrary.erasePhoto(p);
        assertFalse(actual);
    }

    @Test
    public void testSimilarityOneCommon() {
        myLibrary.addPhoto(p);
        myLibrary.addPhoto(p1);
        yourLibrary.addPhoto(p);
        double actual = PhotoLibrary.similarity(myLibrary, yourLibrary);
        double expected = 1.0;
        assertEquals(expected, actual, 0.0);
    }

    @Test
    public void testSimilarityZeroCommon() {
        myLibrary.addPhoto(p);
        myLibrary.addPhoto(p1);
        yourLibrary.addPhoto(p2);
        double actual = PhotoLibrary.similarity(myLibrary, yourLibrary);
        double expected = 0.0;
        assertEquals(expected, actual, 0.0);
    }
}
