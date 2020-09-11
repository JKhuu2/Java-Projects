import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Test;

public class HW2Tests {

    @Test(timeout=100)
    public void testPLConstructor() {
        PhotoLibrary b = new PhotoLibrary("MyLibrary", 14);
        assertEquals("PhotoLibrary's constructor failed to initialize name (getter did not return expected value)", "MyLibrary", b.getName());
        assertEquals("PhotoLibrary's constructor failed to initialize id (getter did not return expected value)", 14, b.getId());
        assertEquals("PhotoLibrary's constructor failed to initialize photos (getter did not return expected value)", new ArrayList<Photograph>(), b.getPhotos());
        assertEquals("PhotoLibrary's constructor failed to initialize albums (getter did not return expected value)", new HashSet<Album>(), b.getAlbums());
    }

    @Test(timeout=100)
    public void testSetName() {
        PhotoLibrary b = new PhotoLibrary("Arphaxad", 14);
        b.setName("Peleg");
        assertEquals("PhotoLibrary.setName() failed (getter did not return expected value)", "Peleg", b.getName());
    }


    @Test(timeout=100)
    public void testEraseEmpty() {
        PhotoLibrary b = new PhotoLibrary("Peleg", 17);
        assertFalse("PhotoLibrary.erasePhoto() failed (edge case)", b.erasePhoto(new Photograph("Caption","Filename")));
    }
    

    @Test(timeout=100)
    public void testEraseThere() {
        PhotoLibrary b = new PhotoLibrary("Peleg", 17);
        b.getPhotos().add(new Photograph("C1", "F1"));
        b.getPhotos().add(new Photograph("C2", "F2"));
        b.getPhotos().add(new Photograph("C3", "F3"));
        assertTrue("PhotoLibrary createAlbum did not create the specified album.", b.createAlbum("Vacation"));
        assertTrue("PhotoLibrary addPhotoToAlbum did not add a photo to an empty album", b.addPhotoToAlbum(new Photograph("C2", "F2"), "Vacation"));
        assertTrue("PhotoLibrary createAlbum did not create the specified album.", b.createAlbum("Vacation2"));
        assertTrue("PhotoLibrary addPhotoToAlbum did not add a photo to an empty album", b.addPhotoToAlbum(new Photograph("C1", "F1"), "Vacation2"));
        assertTrue("PhotoLibrary addPhotoToAlbum did not add a photo to an empty album", b.addPhotoToAlbum(new Photograph("C2", "F2"), "Vacation2"));
        assertTrue("PhotoLibrary addPhotoToAlbum did not add a photo to an empty album", b.addPhotoToAlbum(new Photograph("C3", "F3"), "Vacation2"));

        assertTrue(b.erasePhoto(new Photograph("C2","F2")));
        assertFalse("PhotoLibrary.erasePhoto() failed (something wasn't removed) (also check getPhotos())", b.getPhotos().contains(new Photograph("C2","F2")));
        Album a = null;
        for (Album c: b.getAlbums()) {
            if (c.getName().equals("Vacation"))
                a = c;
        }
        assertEquals("PhotoLibrary.erasePhoto() failed (photo wasn't removed from the albums) (also check getAlbums())", new HashSet<Photograph>(), a.getPhotos());
        a = null;
        for (Album c: b.getAlbums()) {
            if (c.getName().equals("Vacation2"))
                a = c;
        }
        assertFalse("PhotoLibrary.erasePhoto() failed (something wasn't removed from an album) (also check getPhotos())", a.getPhotos().contains(new Photograph("C2","F2")));
        assertTrue("PhotoLibrary.erasePhoto() failed (removed too much from albums) (also check getPhotos())", a.getPhotos().contains(new Photograph("C1","F1")));
        assertTrue("PhotoLibrary.erasePhoto() failed (removed too much from albums) (also check getPhotos())", a.getPhotos().contains(new Photograph("C3","F3")));
        assertEquals("PhotoLibrary erasePhoto removed too much from albums", 2, a.getPhotos().size());
        assertTrue("PhotoLibrary.erasePhoto() failed (removed too much) (also check getPhotos())", b.getPhotos().contains(new Photograph("C1","F1")));
        assertTrue("PhotoLibrary.erasePhoto() failed (removed too much) (also check getPhotos())", b.getPhotos().contains(new Photograph("C3","F3")));
        assertEquals("PhotoLibrary erasePhoto removed too much", 2, b.getPhotos().size());
    }
    
    @Test(timeout=100)
    public void testGetPhotosYear() {
        PhotoLibrary a = new PhotoLibrary("MyLibrary", 1);
        Photograph b = new Photograph("Grand Canyon", "mypic45.jpg", "2014-01-11", 2);
        Photograph c = new Photograph("Rafting", "water.jpg", "2016-05-11", 5);
        Photograph d = new Photograph("Rafting 2", "water2.jpg", "2016-09-30", 1);

        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(b));
        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(c));
        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(d));
        assertEquals("PhotoLibrary getPhotos(rating) did not return photos of rating 0 or higher", 3, a.getPhotos(0).size());
        assertTrue("PhotoLibrary getPhotos(rating) did not return correct photos of rating 0 or higher", a.getPhotos(0).contains(b));
        assertTrue("PhotoLibrary getPhotos(rating) did not return correct photos of rating 0 or higher", a.getPhotos(0).contains(c));
        assertTrue("PhotoLibrary getPhotos(rating) did not return correct photos of rating 0 or higher", a.getPhotos(0).contains(d));
    }


    @Test(timeout=100)
    public void testGetPhotosYear2() {
        PhotoLibrary a = new PhotoLibrary("MyLibrary", 1);
        Photograph b = new Photograph("Grand Canyon", "mypic45.jpg", "2014-01-11", 2);
        Photograph c = new Photograph("Rafting", "water.jpg", "2016-05-11", 5);
        Photograph d = new Photograph("Rafting 2", "water2.jpg", "2016-09-30", 1);

        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(b));
        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(c));
        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(d));
        assertEquals("PhotoLibrary getPhotosInYear() returned photos year is negative", null, a.getPhotosInYear(-2229));
    }
    
    @Test(timeout=100)
    public void testGetPhotosBetween() {
        PhotoLibrary a = new PhotoLibrary("MyLibrary", 1);
        Photograph b = new Photograph("Grand Canyon", "mypic45.jpg", "2016-09-11", 2);
        Photograph c = new Photograph("Rafting", "water.jpg", "2016-05-11", 5);
        Photograph d = new Photograph("Rafting 2", "water2.jpg", "2016-09-30", 1);

        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(b));
        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(c));
        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(d));
        assertEquals("PhotoLibrary getPhotosBetween() did not return photos in Between", 2, a.getPhotosBetween("2016-05-12", "2017-01-01").size());
        assertTrue("PhotoLibrary getPhotosBetween() did not return correct photos between good dates", a.getPhotosBetween("2016-05-12", "2017-01-01").contains(d));
        assertTrue("PhotoLibrary getPhotosBetween() did not return correct photos between good dates", a.getPhotosBetween("2016-05-12", "2017-01-01").contains(b));
        assertFalse("PhotoLibrary getPhotosBetween() did not return correct photos between good dates", a.getPhotosBetween("2016-05-12", "2017-01-01").contains(c));
    }


    @Test(timeout=100)
    public void testGetPhotosBetween2() {
        PhotoLibrary a = new PhotoLibrary("MyLibrary", 1);
        Photograph b = new Photograph("Grand Canyon", "mypic45.jpg", "2014-01-11", 2);
        Photograph c = new Photograph("Rafting", "water.jpg", "2016-05-11", 5);
        Photograph d = new Photograph("Rafting 2", "water2.jpg", "2016-09-30", 1);

        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(b));
        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(c));
        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(d));
        assertEquals("PhotoLibrary getPhotosInBetween() returned photos when dates not formatted correctly", null, a.getPhotosBetween("2016", "2017"));
        assertEquals("PhotoLibrary getPhotosInBetween() returned photos when dates not formatted correctly", null, a.getPhotosBetween("2016-12-33", "2017-01-02"));
        assertEquals("PhotoLibrary getPhotosInBetween() returned photos when dates not formatted correctly", null, a.getPhotosBetween("2016-15-11", "2017-01-02"));
        assertEquals("PhotoLibrary getPhotosInBetween() returned photos when dates not formatted correctly", null, a.getPhotosBetween("2016-10-11", "2017-21-02"));
        assertEquals("PhotoLibrary getPhotosInBetween() returned photos when dates not formatted correctly", null, a.getPhotosBetween("2016-10-11", "2017-02-34"));
    }


    @Test(timeout=100)
    public void testGetPhotosBetween3() {
        PhotoLibrary a = new PhotoLibrary("MyLibrary", 1);
        Photograph b = new Photograph("Grand Canyon", "mypic45.jpg", "2014-01-11", 2);
        Photograph c = new Photograph("Rafting", "water.jpg", "2016-05-11", 3);
        Photograph d = new Photograph("Rafting 2", "water2.jpg", "2016-09-30", 1);

        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(b));
        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(c));
        assertTrue("PhotoLibrary takePhoto did not add a photo", a.addPhoto(d));
        assertEquals("PhotoLibrary getPhotosInBetween() returned photos when it shouldn't have", 0, a.getPhotosBetween("2019-01-01", "2019-02-11").size());
        assertEquals("PhotoLibrary getPhotosInBetween() returned photos when it shouldn't have", 0, a.getPhotosBetween("2016-05-12", "2016-09-29").size());
    }
    

    @Test(timeout=100)
    public void testRemoveAlbumNotThere() {
        PhotoLibrary a = new PhotoLibrary("MyLibrary", 1);

        assertTrue("PhotoLibrary createAlbum() did not create an album", a.createAlbum("My Album"));
        assertTrue("PhotoLibrary createAlbum() did not create an album", a.createAlbum("Vacation Photos"));
        assertTrue("PhotoLibrary createAlbum() did not create an album", a.createAlbum("Vacation Photos 2"));

        assertFalse("PhotoLibrary removeAlbum() allowed removal of an album that was not there", a.removeAlbum("Vacation Photos 3"));
        boolean contains = false;
        Album c = new Album("Vacation Photos 3");
        for (Album b : a.getAlbums()) {
            if (c.equals(b))
                contains = true;
        }
        assertFalse("PhotoLibrary removeAlbum() accidentally added an album instead of removing", contains);
    }
    

    @Test(timeout=100)
    public void testAddPhotoToAlbumEdge() {
        PhotoLibrary a = new PhotoLibrary("MyLibrary", 1);
        Photograph p = new Photograph("Caption", "My Filename");
        a.addPhoto(p);

        assertFalse("PhotoLibrary addPhotoToAlbum() allowed adding photo to non-existant album", a.addPhotoToAlbum(p, "My Album"));      
    }
    
    @Test(timeout=100)
    public void testEqualsType() throws Exception {
        try {
            PhotoLibrary.class.getDeclaredMethod("equals", Object.class);
        } catch (NoSuchMethodException e) {
            fail("PhotoLibrary equals method not specified correctly.");
        } catch (Exception e) {
            throw e;
        }
    }

    @Test(timeout=100)
    public void testEqualsSame() {
        PhotoLibrary b = new PhotoLibrary("Le Petit Prince", 42);
        assertTrue("PhotoLibrary.equals() failed: Symmetric", b.equals(b));
    }

    @Test(timeout=100)
    public void testEqualsSimilar() {
        PhotoLibrary b = new PhotoLibrary("Le Petit Prince", 42);
        PhotoLibrary c = new PhotoLibrary("Le Petit Prince", 42);
        assertTrue("PhotoLibrary.equals() failed: Same id and name", b.equals(c));
    }
    
    @Test(timeout=100)
    public void equalsDifferentTypes() throws Exception {
        try {
            PhotoLibrary b = new PhotoLibrary("Le Petit Prince", 42);
            assertFalse("PhotoLibrary.equals() failed: Compare PhotoLibrary to Integer", b.equals(42));
        } catch (ClassCastException e) {
            fail("PhotoLibrary.equals() failed: Casting a non-PhotoLibrary to a PhotoLibrary");
        } catch (Exception e) {
            throw e;
        }
    }
    
    

}
