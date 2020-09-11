import java.util.Arrays;

public class mset{


    /*1. We're going to start with an empty set, then proceed to add things into that.*/
    public static int[] mset_empty(){
        return new int[0];
    }  
    
    /*2. We need two built-in operations to define a set: membership test (contains) and insert:*/
    public static Boolean mset_contains(int[] t, int x){
        //Returns a Boolean indicating if x is in the set t.
        for(int i : t){
            if(x == i){
                return true;
            }
        }
        return false;
    }
        
    public static int[] mset_insert(int[] t, int x){
        //3. Returns a new set that contains all of the elements of s as well as the new element x.
        int[] s = new int[t.length + 1];
        for(int i = 0; i < t.length; i++){
            s[i] = t[i];
        }
        s[t.length] = x;
        return s;
    }
    
    public static Boolean mset_subset(int[] s, int[] t){
        //7. Returns Boolean indicating if set s is a subset of set t. (see TCS definition 1.4.1)
        for(int i : s){
            if(!mset_contains(t, i)){
                return false;
            }
        }
        return true;
    }
    
    public static Boolean mset_equal(int[] s, int[] t){
        //9. TODO: Implement this function that returns True if the mset s is equivalent to the mset t, and returns False otherwise. The body of this function must be only a single line.
        return mset_subset(s,t)&&mset_subset(t,s);
    }


    public static void main(String[] args){
    
        /*4. Here's an example usage of our mset data structure. Print this and play with contains to see what it looks like and how it behaves (remove all print statements before submitting)*/
        int[] s = mset_insert(mset_insert(mset_insert(mset_empty(), 1), 2), 3);
        
        //5. TODO: assign a value to this variable such mset_contains(s, int_in_s) evaluates to true, then uncomment the code below.
        int int_in_s=1;
        assert(mset_contains(s, int_in_s));
        System.out.println("Good job!");

        
        //6. TODO: assign a value to this variable such mset_contains(s, int_in_s) evaluates to false, then uncomment the code below
        int int_not_in_s = 4;
        assert(!mset_contains(s, int_not_in_s));
        System.out.println("Keep it up!");
        
        //8. TODO: Is the empty set a subset of the empty set? If so, set this variable's value to True, otherwise make it False. Then uncomment the code below.
        Boolean empty_subset_empty = true;
        assert(mset_subset(mset_empty(), mset_empty()) == empty_subset_empty);
        System.out.println("One more, you can do it!");
        
        //10. uncomment the code below:
        assert(mset_equal(mset_empty(), mset_empty()));
        int[] s1 = mset_insert(mset_empty(), 1);
        int[] s2 = mset_insert(s1, 2);
        int[] s3 = mset_insert(s2, 3);
        assert(mset_equal(s3, s3));
        assert(!mset_equal(s2, s3));
        assert(!mset_equal(s3, mset_insert(s2, 110110)));
        System.out.println("HOORAY!!!! You did it! YIPEEEE");
        
    }
    

}
