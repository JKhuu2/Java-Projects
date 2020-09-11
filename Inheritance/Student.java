
public class Student extends Person {
    protected String campusAddress;
    protected double gpa;
    public Student(String campusAddress, double gpa) {
        this.campusAddress=campusAddress;
        this.gpa=gpa;
       
    }
    public Student() {
        this.gpa=0.0;
        this.campusAddress="";
    }
    
}
