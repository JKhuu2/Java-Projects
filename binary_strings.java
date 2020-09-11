import java.util.Arrays;

public class binary_strings{

    
    /* 1. This function enumerates all binary strings of a certain length. This will primarily be used for testing purposes.*/
    public static String[] list_strings(int n){
        //lists all binary strings of length n, do not modify this function
        if(n == 0){
            return new String[] {""};
        }
        else{
            String[] n_minus_one = list_strings(n-1);
            String[] strings = new String[2*n_minus_one.length];
            for(int i = 0; i < strings.length; i += 2){
                strings[i] = n_minus_one[i / 2] + "0";
                strings[i+1] = n_minus_one[i / 2] + "1";
            }
            return strings;
        }
    }
    
    /*2. The next two functions together implement the procedure for representing natural numbers using binary strings that was presented in class. The first function will "encode" a natural number into a string. The second function will "decode" a string into a natural number.*/
    
    public static int string_to_nat(String b){
        //Given a binary string, converts it into a natural number by the procedure described in lecture.  Do not modify this function.
        if(b.equals("")){
            return 0;
        }
        else{
            int least_sig_bit = Character.getNumericValue((b.charAt(b.length()-1)));
            int most_sig_bits = string_to_nat(b.substring(0, b.length()-1));
            return 2 * most_sig_bits + least_sig_bit;
        }
    }
    
    public static String nat_to_string(int x){
        //Given a natural number, converts it into a binary string by the procedure described in lecture.  Do not modify this function.
        assert(x >= 0);
        if(x == 0){
            return "";
        }
        else{
            return nat_to_string(x/2) + Integer.toString(x%2);
        }

    }
    

    
    /*3. The next two functions will together define a way of representing integers using binary strings. The first function will "encode" an int into a string. The second function will "decode" a string into an int. There are many ways to do this successfully. I recommend using the nat_to_string and string_to_nat functions I provided to help you out, but that is not required.*/
    
    public static String int_to_string(int x){
        //4. TODO: Implement a procedure to convert a given integer into a binary string. This mapping should be injective, but not necessarily bijective. In other words, come up with the mapping from an int to a string representation for it.
        if(x==0) {
            return "";
        }
        if(x>0) {
            return nat_to_string(x)+"1";
        }
        else {
            x=-1*x;
            return nat_to_string(x)+"0";
        }
    }
    
    public static int string_to_int(String b){
        //6. TODO: Implement a procedure to convert a given binary string into an integer. This mapping should be surjective, but not necessarily bijective. Additionally, this function must do the inverse of the previous function. That is, if I represent an int as a string using the previous function, then give the result to this function, I should get the original int back.
        if (b.equals("")) {
            return 0;
        }
        else {
            int sign_bit = Character.getNumericValue((b.charAt(b.length()-1)));
            int value=string_to_nat(b.substring(0,b.length()-1));
            if (sign_bit==0) {
                return -1*value;
            }
            else {
                return value;
            }
        }
    }
    
    
    
    /*8. The next two functions will together define a way of representing integer cartesian coordinates (i.e. ordered pairs of integers) using binary strings. The first function will "encode" a coordinate pair into a string. The second function will "decode" a string into a cordinate pair. There are many ways to do this successfully. I recommend using the int_to_string and string_to_int functions you implemented, but that is not required.*/
    
    public static String coord_to_string(int[] c){
        //9. TODO: Implement a procedure to convert a given coordinate pair of integers into a binary string. This mapping should be injective, but not necessarily bijective. In other words, come up with the mapping from a coordinate pair to a string representation for it.
        assert(c.length == 2);
        String temp1=int_to_string(c[0]);
        String temp2=int_to_string(c[1]);
        if(temp1.length()==temp2.length()) {
            return temp1+temp2;
        }
        else {
            while(temp1.length()>temp2.length()) {
                temp2="0"+temp2;
            }
            while (temp2.length()>temp1.length()) {
                temp1="0"+temp1;
            }
            return temp1+temp2;
        }
    }
    
    public static int[] string_to_coord(String b){
        // 11. TODO: Implement a procedure to convert a given binary string into a coordinate pair of integers. This mapping should be surjective, but not necessarily bijective. Additionally, this function must do the inverse of the previous function. That is, if I represent a coordinate pair as a string using the previous function, then give the result to this function, I should get the original coordinate pair back. This function must be defined for all strings, i.e. even if your coord_to_string function only ever produces even-length strings, this function must still produce a coordinate pair for odd-length strings.
        String temp1=b.substring(0,b.length()/2);
        String temp2=b.substring(b.length()/2);
        int int1=string_to_int(temp1);
        int int2=string_to_int(temp2);
        return new int[] {int1,int2};

    }
    
    
    
    
    public static void main(String[] args){
        // 5. Tests whether string_to_int is defined for all (well, ... many) strings. Do not modify this code (or if you do, restore it to the original).
        for(int i = 0; i < 13; i++){
            for (String b : list_strings(i)){
                string_to_int(b);
            }
        }
        
        // 7. Tests whether decode undoes the encode. Do not modify this code (or if you do, restore it to the original).
        int[] test_nats = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 100, 10000, 123456789};
        int[] test_ints = new int[2*test_nats.length];
        for(int i = 0; i<test_nats.length; i++){
            test_ints[i] = test_nats[i];
            test_ints[test_nats.length + i] = -test_nats[i];
        }
        
        for(int i : test_ints){
            assert(string_to_int(int_to_string(i)) == i);
        }
        System.out.println("Looks like your integer representation works! HOORAY!!");
        
        //10. Tests whether string_to_int is defined for all (well, ... many) strings. Do not modify this code (or if you do, restore it to the original).
        for(int i = 0; i < 13; i++){
            for(String b : list_strings(i)){
                string_to_coord(b);
            }
        }
        
        //12. Tests whether decode undoes the encode. Do not modify this code (or if you do, restore it to the original).
        int[][] test_coords = new int[test_ints.length*test_ints.length][2];
        int i = 0;
        for(int x : test_ints){
            for(int y : test_ints){
                test_coords[i++] = new int[] {x,y};
            }
        }
        //System.out.println(Arrays.deepToString(test_coords)); //I recommend deepToString if you're going to print coordinate pairs.
        
        for(int[] c : test_coords){
            int[] decoded = string_to_coord(coord_to_string(c));
            assert(decoded[0] == c[0] && decoded[1] == c[1]);
        }
        System.out.println("Looks like your Cartesian representation is working. You're done!!");


        
    }
    
}
