
/**
 * Homework 3 
 * Jennifer Khuu, jtk2eh 
 * 
 * Sources: Lecture Notes, Big Java book, TA Help
 */
import java.util.ArrayList;
import java.util.HashSet;

public class PhotoLibrary extends PhotographContainer {

    // created id and albums fields
    private int id;
    private HashSet<Album> albums = new HashSet<Album>();

    // constructor - initialized name and id
    public PhotoLibrary(String name, int id) {
        super(name);
        this.id = id;
    }

    // accessors - methods for retrieving the fields of a photo library
    public int getId() {
        return this.id;
    }

    public HashSet<Album> getAlbums() {
        return this.albums;
    }

    // erasePhoto - erases photo if in photos list of object and returns true (false otherwise)
    public boolean removePhoto(Photograph p) {

        // remove and return true only if the list contains the photo
        if (photos.contains(p)) {
            photos.remove(p);

            // iterate through each album in albums set
            for (Album album : this.getAlbums()) {

                // checks if same name as one in parameter and if that album doesn't have the photo before adding it
                if (album.hasPhoto(p)) {
                    album.removePhoto(p);
                }
            }
            return true;
        }
        return false;
    }

    // equals - returns true if current PhotoLibrary object's id value equals the one being passed through the function
    public boolean equals(Object o) {

        if (o instanceof PhotoLibrary) {                // checking to see if object o is type PhotoLibrary
            int newId = ((PhotoLibrary) o).getId();    // getting the id of the object
            if (this.id == newId) {                    // only return true if object id equals class id
                return true;
            }
        }

        return false;
    }

    // toString - print statement for PhotoLibrary object
    public String toString() {
        return "Name: " + this.name + ", ID: " + this.id + "\nPhotos: " + this.photos + "\nAlbums: " + this.getAlbums();
    }

    // returns common photos from two photo libraries
    public static ArrayList<Photograph> commonPhotos(PhotoLibrary a, PhotoLibrary b) {

        // creating local arraylist to return common photos
        ArrayList<Photograph> commonPhotos = new ArrayList<Photograph>();

        // getting the photos of a and b
        ArrayList<Photograph> photosA = new ArrayList<Photograph>(((PhotoLibrary) a).getPhotos());
        ArrayList<Photograph> photosB = new ArrayList<Photograph>(((PhotoLibrary) b).getPhotos());

        // for each photo in photosA, if it's in photosB, then it's added to the commonPhotos list
        for (int i = 0; i < photosA.size(); i++) {
            if (photosB.contains(photosA.get(i))) {
                commonPhotos.add((Photograph) photosA.get(i));
            }
        }
        return commonPhotos;
    }

    // Returns how similar the photo feeds are between PhotoLibrary a and PhotoLibrary b by value between 0 and 1
    public static double similarity(PhotoLibrary a, PhotoLibrary b) {
        // getting the photos of a and b
        ArrayList<Photograph> photosA = new ArrayList<Photograph>(((PhotoLibrary) a).getPhotos());
        ArrayList<Photograph> photosB = new ArrayList<Photograph>(((PhotoLibrary) b).getPhotos());

        // returns this if either photo library has no photos
        double similarity = 0.0;

        // finding commonPhotos between a and b by referring to a previously-defined method
        ArrayList<Photograph> commonPhotos = commonPhotos(a, b);

        // made the common photos size a double so when divided, it will return a double
        double commonPhotosSize = commonPhotos.size();

        // if the size photosA and photosB aren't 0, then check to see which one is smaller out of the two and divide the
        // commonPhotosSize by the smaller one
        if (photosA.size() != 0 && photosB.size() != 0) {
            if (photosA.size() < photosB.size()) {
                similarity = commonPhotosSize / photosA.size();
            }
            if (photosB.size() < photosA.size()) {
                similarity = commonPhotosSize / photosB.size();
            }
            if (photosB.size() == photosA.size()) {
                similarity = commonPhotosSize / photosB.size();
            }
        }
        return similarity;
    }

    // creates new album and adds to albums list if not already there
    public boolean createAlbum(String albumName) {

        // creates copy of album with name albumName
        Album newAlbum = new Album(albumName);

        // if not in albums list, add it
        if (!albums.contains(newAlbum)) {
            albums.add(newAlbum);
            return true;
        }
        return false;
    }

    // removes album if in albums list and returns true if successful
    public boolean removeAlbum(String albumName) {

        // creates copy of album with name albumName
        Album newAlbum = new Album(albumName);

        // if in albums list, remove and return true
        if (albums.contains(newAlbum)) {
            albums.remove(newAlbum);
            return true;
        }
        return false;
    }

    // add photo to album if in photos list and album doesn't already have it
    public boolean addPhotoToAlbum(Photograph p, String albumName) {

        // iterate through each album in albums set
        for (Album album : this.getAlbums()) {

            // gets name of album
            String name = album.getName();

            // checks if same name as one in parameter and if that album doesn't have the photo before adding it
            if (name == albumName && !album.hasPhoto(p) && this.photos.contains(p)) {
                album.addPhoto(p);
                return true;
            }
        }
        return false;
    }

    // removes photograph p from album
    public boolean removePhotoFromAlbum(Photograph p, String albumName) {

        // iterate through each album in albums set
        for (Album album : this.getAlbums()) {

            // gets name of album
            String name = album.getName();

            // checks if same name as one in parameter and if that album doesn't have the photo before adding it
            if (name == albumName && album.hasPhoto(p)) {
                album.removePhoto(p);
                return true;
            }
        }
        return false;
    }

    // returns the album with the albumName
    private Album getAlbumByName(String albumName) {

        Album newAlbum = new Album(albumName);

        // checks to see if albumName in albums
        if (albums.contains(newAlbum)) {
            return newAlbum;
        }
        return null;
    }

    // main method testing
    public static void main(String[] args) {
        PhotoLibrary myLibrary = new PhotoLibrary("Jennifer", 2);
        Photograph p4 = new Photograph("what", "me", "2019-01-26", 1);

        // addPhoto testing
        Photograph p = new Photograph("Hi", "Me");
        System.out.println(myLibrary.addPhoto(p));
        System.out.println(myLibrary);

        Photograph p2 = p;
        System.out.println(myLibrary.addPhoto(p2));
        System.out.println(myLibrary);

        // hasPhoto testing

        Photograph p3 = new Photograph("Why", "Me");
        System.out.println(myLibrary.hasPhoto(p));
        System.out.println(myLibrary.hasPhoto(p3));

        // erasePhoto testing
        // System.out.println(myLibrary.erasePhoto(p));
        // System.out.println(myLibrary);
        // System.out.println(myLibrary.erasePhoto(p3));

        // numPhotographs testing
        System.out.println(myLibrary.numPhotographs());
        myLibrary.addPhoto(p3);
        System.out.println(myLibrary.numPhotographs());

        // equals testing
        PhotoLibrary yourLibrary = new PhotoLibrary("You", 3);
        System.out.println(yourLibrary.equals(myLibrary));
        PhotoLibrary myLibrary2 = new PhotoLibrary("me2", 2);
        System.out.println(myLibrary2.equals(myLibrary));

        // common photos testing
        myLibrary2.addPhoto(p3);
        System.out.println(commonPhotos(myLibrary2, myLibrary));
        yourLibrary.addPhoto(p);
        System.out.println(commonPhotos(myLibrary, yourLibrary));

        // similarities testing
        System.out.println(myLibrary2);
        System.out.println(myLibrary);
        System.out.println(similarity(myLibrary2, myLibrary));
        myLibrary.addPhoto(p);
        System.out.println(similarity(myLibrary2, myLibrary));

        // getPhotosInMonthTesting
        System.out.println(myLibrary.addPhoto(p4));
        System.out.println(myLibrary);
        System.out.println(myLibrary.getPhotosInMonth(01, 2019));
        System.out.println(myLibrary.checkDate("2019-01-26"));
        String dateTaken = p4.getDateTaken();
        String substring = dateTaken.substring(0, 4);
        int subInt = Integer.parseInt(substring);
        System.out.println(2019 > 1000);

        // erase photo testing
        System.out.println(myLibrary);
        System.out.println(myLibrary.createAlbum("Me"));
        System.out.println(myLibrary.addPhotoToAlbum(p, "Me"));
        System.out.println(myLibrary);
        myLibrary.removePhotoFromAlbum(p, "Me");
        System.out.println(myLibrary);
        System.out.println(myLibrary.checkDate("hi"));

    }
}
