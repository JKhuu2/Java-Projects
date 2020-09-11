import java.util.Comparator;

public class CompareByDate implements Comparator<Photograph> {
public int compare(Photograph a, Photograph b) {
    String dateA=a.getDateTaken();
    String dateB=b.getDateTaken();
    return dateA.compareTo(dateB);
}
}
