
public class Insertion {
    private static Comparable[] Insertion(Comparable[] arr) 
    { 
        int n = arr.length; 
        for (int i = 1; i < n; i++) { 
            Comparable key = arr[i]; 
            int j = i - 1; 
            while (j >= 0 && arr[j].compareTo(key) > 0) { 
                arr[j + 1] = arr[j]; 
                j--; 
            } 
            arr[j + 1] = key; 
        }
        return arr;
    } 
    static void printArray(Comparable[] arr) 
    { 
        int n = arr.length; 
        for (int i = 0; i < n; i++) 
            System.out.print(arr[i] + " "); 
        System.out.println(); 
    } 
    public static void main(String args[]) 
    { 
         Comparable[] arr = {9,6,4,3,0,10,-2};
         Comparable[] arr2 = Insertion(arr);
         printArray(arr2);
         System.out.println("0 " + Algorithms.algorithm0(100));
         System.out.println("1 "+Algorithms.algorithm1(100));
         System.out.println("2 "+Algorithms.algorithm2(10));
         System.out.println("3 "+Algorithms.algorithm3(100));
         System.out.println("4 "+Algorithms.algorithm4(100));
    }
    
}
