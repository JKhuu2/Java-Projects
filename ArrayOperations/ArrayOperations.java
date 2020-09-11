package sut;

public class ArrayOperations 
{
// Adapted from "Introduction to Software Testing", 
// by Ammann and Offutt (page 13) 
	
	     	
   public static int numZero(int[] x) 
   {
      if (x == null) 
        throw new NullPointerException();
        // throw new NullPointerException("array is null");

      int count = 0;
      for (int i=1; i<x.length; i++) 
         if (x[i] == 0) 
            count++;
      return count;
   }
 
   
   public static int findLast(int[] x, int y) 
   {
      if (x == null) 
         throw new NullPointerException();
      for (int i=x.length-1; i>0; i--) 
      {
         if (x[i] == y) 
            return i; 
      }
      return -1; 
   }
   // test: x = [2, 3, 5]; y = 2; 
   // expected = 0

   
   public static int countPositive (int[] x)
   {  
      if (x == null) 
         throw new NullPointerException();
      int count = 0; 
      for (int i=0; i<x.length; i++) 
      {
         if (x[i] >= 0) 
            count++;
      }
      return count;
   }
   // test:  x = [-4, 2, 0, 2]             
   // expected = 2  
   
   
   public static int lastZero (int[] x) 
   {
      if (x == null) 
         throw new NullPointerException();
      for (int i=0; i<x.length; i++) 
      {
         if (x[i] == 0)    
            return i; 
      }
      return -1; 
   }
   // test: x = [0, 1, 0]
   // expected = 2


   public static int oddOrPos (int[] x) 
   {  
      if (x == null) 
         throw new NullPointerException();
      int count = 0;   
      for (int i=0; i<x.length; i++)  
      {
         if (x[i] % 2 == 1 || x[i] > 0) 
            count++; 
      }   
      return count; 
   }
   // test: x = [-3, -2, 0, 1, 4]           
   // expected = 3

}
