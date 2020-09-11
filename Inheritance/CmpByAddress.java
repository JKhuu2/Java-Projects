import java.util.Comparator;

public class CmpByAddress implements Comparator<Person> {
    public int compare(Person p1, Person p2) {
        return p1.getAddress().compareTo(p2.getAddress());
    }
}
