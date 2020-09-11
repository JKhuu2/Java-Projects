import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class CenteredAverage{
  
  public static int main (int[] nums) {
  ArrayList<Integer> list=new ArrayList<Integer>();
  for(int i=0; i<nums.length; i++) {
	  list.add(nums(i));
  }
  
  int smallest=list.get(0);
  for (int i=0;i<list.size();i++){
    if (list.get(i)<smallest){
     smallest=list.get(i) ;
    }
    }
    
  list.remove(smallest);
  
  int largest=list.get(0);
  for(int i=0;i<list.size();i++){
    if (list.get(i)>largest){
      largest=list.get(i);
    }
  }
  list.remove(largest);
  
  int sum=0;
  for (int i=0;i<list.size();i++){
    int j=list.get(i);
    sum=sum+j;
  } 
  int average=sum/(list.size());
  return average;
}

private static Integer nums(int i) {
	// TODO Auto-generated method stub
	return null;
}

public void Classname(){
  System.out.println(centeredAverage(1,2,3,4,5));
}

private char[] centeredAverage(int i, int j, int k, int l, int m) {
	// TODO Auto-generated method stub
	return null;
}
}

