//Lab 5: Inheritance 

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;


public class PersonTests {
    
    ArrayList<Person> list = new ArrayList<Person>();
	Person p1;
	Person p2;
	Person p3;
	
	Person e1;
    
    Student s1;
	
	@Before
	public void setUp()
	{
		p1 = new Person("Mai", "3156 Grove Rd, Somewhere");
		p2 = new Person("Steve", "001 Terrace Road, Streetsville");
		p3 = new Person("Jimmy", "12345 Six Street, Right here");
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		e1 = new Employee("Don", "6562 Trask Way, Elsewhere", "Front Desk", 2110);
		list.add(e1);
        
//         UNCOMMENT THIS LINE AND FILL IN THE STUDENT CONSTRUCTOR AFTER CREATING YOUR STUDENT CLASS
         s1 = new Student();
         list.add(s1);
	}
	
	@Test
	public void testPersonEmployee() 
	{	
		// This test makes sure that the toString() methods for the provided classes work as intended.
		assertEquals("toString", "{Person: name=Mai, homeAddress=3156 Grove Rd, Somewhere|", list.get(0).toString());
		assertEquals("toString", "{Empl: n=Don, ha=6562 Trask Way, Elsewhere, wa=Front Desk, id=2110}", list.get(3).toString());
	}
	
	@Test
	public void testPersonEmployeeStudent() 
	{
		// TODO: Verify the personhood of every object in the ArrayList. (There should be 5 objects.)
        /* Hint: part of the equals() methods that you have written for HW2 and HW3 likely included
         * verifying that the parameter was of a certain Object type. You can use that same approach
         * in this test method, even if the object you pass is an instance_of a subclass of Person.
         */
	    boolean bool=true;
	    for (Person p: list) {
	        
	        if (p instanceof Person){
	            
	        }
	        else {
	            bool=false;
	            break;
	        }
	        
	    }
	    assertEquals(true, bool);
	    
        
		// MAKE SURE TO COMMENT OUT THE LINE BELOW WHEN YOU'RE DONE
//		fail("Have you implemented testPersonEmployeeStudent() yet? You're not done until you've commented out this line!");
	}
	
	@Test
	public void testCollectionsSort() 
	{
		// TODO: Use Collections.sort(/*List*/) to sort the list of Person objects.
		/* Hint: make sure you've implemented Comparable and Comparator, as those are important
         * for Collections.sort(List) to use.
         */
	    Person p1=new Person("Jennifer","Chaptico");
	    Person p2=new Person("Vivian","Dolly");
	    Person p3=new Person("Lucy","Hurst");
	    ArrayList<Person> list=new ArrayList<Person>();
	    list.add(p1);
	    list.add(p2);
	    list.add(p3);
	    Collections.sort(list);
	    ArrayList<Person> expected=new ArrayList<>();
	    expected.add(p1);
	    expected.add(p3);
	    expected.add(p2);
	    assertEquals(expected,list);
	    
	    

        // MAKE SURE TO COMMENT OUT THE LINE BELOW WHEN YOU'RE DONE
//        fail("Have you implemented testPersonEmployeeStudent() yet? You're not done until you've commented out this line!");
	}
	@Test
	public void testComparator() {
	    Person p1=new Person("Jennifer","Chaptico");
        Person p2=new Person("Vivian","Dolly");
        Person p3=new Person("Lucy","Hurst");
        ArrayList<Person> list=new ArrayList<Person>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        Collections.sort(list,new CmpByAddress());
        ArrayList<Person> expected=new ArrayList<>();
        expected.add(p1);
        expected.add(p2);
        expected.add(p3);
        assertEquals(list,expected);
	}
	
    // ACTIVITY 5
     @Test
     public void testGetClass() {
          System.out.println("p3.getName(): " + p3.getClass().getName());
    		System.out.println("p3.getSimpleName(): " + p3.getClass().getSimpleName());
    		System.out.println("p3.getCanonicalName(): " + p3.getClass().getCanonicalName());
     }
}
