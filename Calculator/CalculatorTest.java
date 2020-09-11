//package examples.junit;      // update this line based on where your file is

//import sut.Calculator;       // update this line based on where your subject under test is

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName; 
import org.junit.jupiter.api.Test;



// These are needed for data driven test
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.Collection;


class CalculatorTest {
    private final Calculator calculator=new Calculator();

	//2+3=5
	@Test
	void testAdd() {
	    assertEquals(5, calculator.add(2, 3));
	}
	
	@Test
	@DisplayName("My custom test name")
	void testAdd_DisplayName() {
	   assertEquals(5, calculator.add(2, 3));
	}

	@Test
	void testAdd_withMultipleAssertions() {
	  assertEquals(5, calculator.add(2, 3));
	  assertEquals(3,calculator.add(3, 0));
	  assertEquals(-3,calculator.add(-3, 0));
	}
	
	@Test
    void testAdd_withGroupAssertions() {
      assertAll(
              ()->assertEquals(5, calculator.add(2, 3)),
              ()->assertEquals(3,calculator.add(3, 0)),
              ()->assertEquals(-3,calculator.add(-3, 0))
              );
    }
	
	//if "first step" fails, then "message is optional" would never be ran/tested
	@Test
	void testAdd_DependentAssertions() {
	    assertAll(
	            ()->{
	                int number=calculator.add(2, 3);
	                assertTrue(number>0,"first step");
	                
	                assertAll("message is optional",
	                        ()->assertTrue(calculator.squareroot(number)>0)
	                        );
	            },
	            ()->{
	                int number=calculator.add(3, 0);
	                assertTrue(number>0, "2nd assertion, first step");
	                assertAll("the rest of 2nd assertion",
	                        ()->assertTrue(calculator.squareroot(number)>0, "the rest")
	                        );
	            }
	            );
	    
	}
	
	@Test
	void TestDivideByZero() {
	    try {
	        calculator.divide(1, 0);
	        fail("expected ArithmeticException");
	    }
	    catch(ArithmeticException e) {
	}
	}
	
	@Test
	void testDividedByZero2(){
	    Exception exception=assertThrows(ArithmeticException.class,
	            ()->calculator.divide(1,0));
	    assertEquals("/ by zero",exception.getMessage());
	    
	}
	
	//data driven
	//1. create a factory
	static Collection<Object[]> calcValues(){
	    return Arrays.asList(new Object[][] {{1,1,2},{2,-3,-1},{0,4,4},{-2,-5,-7}});  
	    //test: two positives, two negatives, pos + neg, pos + 0
	    //[][] - two-dimensional array
	    //used when you want to test multiple values at once
	    //if one fails, it will tell you which one did
	}
	
	//2. create parameterized method
	@ParameterizedTest(name="{index}=> a= {0}, b= {1}, sum= {2}")   //name is optional
	@MethodSource("calcValues")
	void testAdd_withDataDriven(int a, int b, int sum) {
	    assertTrue(sum==calculator.add(a, b),"test add("+a+","+b+")");
	}
}
