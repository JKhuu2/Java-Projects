package examples.junit;         // update this line based on where your test class is

import sut.ArrayOperations;     // update this line based on where your subject under test is

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArrayOperationsNumZeroTest 
{
   @BeforeAll
   static void setUpBeforeClass() throws Exception 
   {
   }

   @AfterAll
   static void tearDownAfterClass() throws Exception 
   {
   }

   @BeforeEach
   void setUp() throws Exception 
   {
   }

   @AfterEach
   void tearDown() throws Exception 
   {
   }

   @Test
   void testNumZeroArrayWithNoZero() 
   {
       int[]x= {1,2,3}; //1. setup
       int n=ArrayOperations.numZero(x);           //2. execute
       //assertEquals(0,n);   //3. verify assertEquals(expected,actual)
       assertTrue(0==n);
   }
   
   @Test
   void testNumZeroArrayWithZeroAtIndexZero() 
   {
       int[]x= {0,2,3}; //1. setup
       int n=ArrayOperations.numZero(x);           //2. execute
       //assertEquals(1,n, "check zero at x[0]");   //3. verify assertEquals(expected,actual)
       //text is printed if the test fails - if passes, text doesn't appear
       assertTrue(n==1, "check zero at x[0]");
   }

}
