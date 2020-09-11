import java.util.Arrays;

public class straightline{

    /* 
    1. You saw the first three functions in the binary_strings program from exercise 1. list_strings gives a list of all strings of length n, string_to_nat and nat_to_string convert back and forth between binary strings and natural numbers. These are really just used for testing purposes for this assignment
    */
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
    
/*2. This next block of functions is setting us up to program in AON-Straightline. The is_binary functino checks that we are only computing on binary inputs/outputs (the only data type that AON-Straightlin can handle). The AND, OR, and NOT functions implement the corresponding boolean operators in Java. 
*/
    
    public static boolean is_binary(String b){
        return b.equals("0") || b.equals("1");
    }
    
    public static String AND(String a, String b){
        assert(is_binary(a) && is_binary(b));
        int inta = Integer.parseInt(a);
        int intb = Integer.parseInt(b);
        return Integer.toString(inta * intb);
    }
    
    public static String OR(String a, String b){
        assert(is_binary(a) && is_binary(b));
        int inta = Integer.parseInt(a);
        int intb = Integer.parseInt(b);
        return Integer.toString((inta + intb) - (inta * intb));
    }
    
    public static String NOT(String a){
        assert(is_binary(a));
        int inta = Integer.parseInt(a);
        return Integer.toString(1-inta);
    }
    
/*
3. Finally, from here we're programming in AON-Straightline. We start out by implementing some of the functions in class in AON-Straightline: XOR, NAND, and MAJ. These give you an idea for how AON-Straightline should be written (in general and by you in this assignment). Each line in the program should consist of a new variable declaration on the lef-had side and an AND/OR/NOT operation on the right-hand side, or else a return statement. Please note that you cannot use any operations besides AND/OR/NOT on the right-hand side (except for one case where you'll be directed otherwise), you cannot have multiple operations nested (e.g. NOT(AND(a,b)) is not permitted), and you cannot re-assign and already assigned variable (e.g. a = NOT(a) is not permitted).
*/
    
    public static String XOR(String a, String b){
        String not_a = NOT(a);
        String not_b = NOT(b);
        String a_not_b = AND(a, not_b);
        String b_not_a = AND(b, not_a);
        String ans = OR(a_not_b, b_not_a);
        return ans;
    }
    
    public static String NAND(String a, String b){
        String a_and_b = AND(a, b);
        String ans = NOT(a_and_b);
        return ans;
    }
    
    public static String MAJ(String a, String b, String c){
        String first_two = AND(a,b);
        String last_two = AND(b,c);
        String first_last = AND(a,c);
        String temp = OR(first_two, last_two);
        String ans = OR(temp, first_last);
        return ans;
    }
    
/*
5. Now you're ready to start implementing programs in AON straightline for yourself. Implement the 3 functions (IMPL, PAL5, COMPARE4) described below.
*/
    
    public static String IMPL(String a, String b){
        //TODO: Implement the boolean operator Implies which is true whenever "a implies b" is true. Check the asserts in main below for a truth table.
        String not_a=NOT(a);
        String not_b=NOT(b);
        String a_and_b=AND(a,b);
        String na_and_nb=AND(not_a,not_b);
        String o1=OR(a_and_b,na_and_nb);
        String na_and_b=AND(not_a,b);
        String ans=OR(o1,na_and_b);
        return ans;
    }
    
    public static String PAL5(String a5, String a4, String a3, String a2, String a1){
        //TODO: Implement this function which returns "1" if the 5-bit input is a palindrome (the same when read forwards vs backwards), and "0" otherwise.
        String a=a5+a4+a3+a2+a1;
        String rev_a=new StringBuilder(a).reverse().toString();
        if (a.equals(rev_a)) {
            return "1";
        }
        return "0";
    }
    
    //if condition is true, return a. otherwise, return b
    public static String IF(String cond, String a, String b) {
        String not_cond=NAND(cond, cond);
        String temp1=NAND(b,not_cond);
        String temp2=NAND(a,cond);
        return NAND(temp1,temp2);
    }
    
    public static String COMPARE1(String a1, String b1) {
        String a1_b1_imp=IMPL(a1,b1);
        String a1_eq_b1=NOT(a1_b1_imp);
        return a1_eq_b1;
    }
    
    public static String COMPARE2(String a2, String a1, String b2, String b1) {
        String a2_b2_xor=XOR(a2,b2);
        String a2_eq_b2=NOT(a2_b2_xor);
        String c1=COMPARE1(a1, b1);
        return IF(a2_eq_b2,c1,a2);
    }
    
    public static String COMPARE3(String a3, String a2, String a1, String b3, String b2, String b1) {
        String a3_b3_xor=XOR(a3,b3);
        String a3_eq_b3=NOT(a3_b3_xor);
        String c2=COMPARE2(a2,a1,b2,b1);
        return IF(a3_eq_b3, c2, a3);
    }
    
    public static String COMPARE4(String a4, String a3, String a2, String a1, String b4, String b3, String b2, String b1) {
        String a4_xor_b4=XOR(a4,b4);
        String a4_eq_b4=NOT(a4_xor_b4);
        String c3=COMPARE3(a3,a2,a1,b3,b2,b1);
        return IF(a4_eq_b4, c3, a4);
    }
    
//    public static String COMPARE4(String a3, String a2, String a1, String a0, String b3, String b2, String b1, String b0){
//        //TODO: Write a function which returns 1 provided that the natural number represented by a3a2a1a0 is greater than the natural number represented by b3b2b1b0, and 0 otherwise.
//        String a=a3+a2+a1+a0;
//        String b=b3+b2+b1+b0;
//        int nat_a=string_to_nat(a);
//        int nat_b=string_to_nat(b);
//        if(nat_a>nat_b) {
//            return "1";
//        }
//        return "0";
//    }
    
    
/*
7. Now re-implement XOR in the NAND-Straightline language (follow the same rules as AON-Straightline, but only use the NAND operation on the right-hand side).
*/
    public static String XOR_nand(String a, String b){
        //TODO: Implement XOR using only NAND
        String not_a=NAND(a,a);
        String not_b=NAND(b,b);
        String a_not_b=NAND(NAND(a,not_b),NAND(a,not_b));
        String b_not_a=NAND(NAND(b,not_a),NAND(b,not_a));
        String ans=NAND(NAND(a_not_b,a_not_b),NAND(b_not_a,b_not_a));
        return ans;
    }
    
    public static void main(String[] args){
        /*
        4. Uncomment the code block below to see XOR, NAND, and MAJ in action. Here we're enumerating all possible inputs and printing out each function's output for those inputs. This is also how the following asserts will test the code you write (except instead of printing it compares the output to the correct answers). Make sure you re-comment the code before submitting.
        */
        /*
        for(String i : list_strings(2)){
            String a = i.substring(0,1);
            String b = i.substring(1,2);
            System.out.println("And " + i + " " + AND(a, b)); 
        }
        for(String i : list_strings(2)){
            String a = i.substring(0,1);
            String b = i.substring(1,2);
            System.out.println("or " + i + " " + OR(a, b)); 
        }
        for(String i : list_strings(2)){
            String a = i.substring(0,1);
            String b = i.substring(1,2);
            System.out.println("Xor " + i + " " + XOR(a, b)); 
        }
        for(String i : list_strings(2)){
            String a = i.substring(0,1);
            String b = i.substring(1,2);
            System.out.println("NAND " + i + " " + NAND(a, b)); 
        }
        for(String i : list_strings(3)){
            String a = i.substring(0,1);
            String b = i.substring(1,2);
            String c = i.substring(2,3);
            System.out.println("MAJ " + i + " " + MAJ(a, b, c)); 
        }*/
        
        
        /*
        6. This chunk of code is checking that your implementations of #5 are correct. Run these with asserts enabled. Each of them enumerates all inputs for those functions (note there are only finitely many, so we can be exhaustive), and checks the answer by some other means. (This function will not be assessed for a grade, so you may skip it if you like)
        */
        assert(IMPL("0","0").equals("1"));
        assert(IMPL("0","1").equals("1"));
        assert(IMPL("1","0").equals("0"));
        assert(IMPL("1","1").equals("1"));
        System.out.println("IMPL works! Nicely, done!");
        
        for (String a : list_strings(5)){
            String reva = new StringBuilder(a).reverse().toString();
            String a0 = a.substring(0,1);
            String a1 = a.substring(1,2);
            String a2 = a.substring(2,3);
            String a3 = a.substring(3,4);
            String a4 = a.substring(4,5);
            if (a.equals(reva)){
                assert(PAL5(a0, a1, a2, a3, a4).equals("1"));
            }
        }
        System.out.println("a nut for a jar of tuna");
        
        for (String a : list_strings(4)){
            for (String b: list_strings(4)){
                int na = string_to_nat(a);
                int nb = string_to_nat(b);
                String a3 = a.substring(0,1);
                String a2 = a.substring(1,2);
                String a1 = a.substring(2,3);
                String a0 = a.substring(3,4);
                String b3 = b.substring(0,1);
                String b2 = b.substring(1,2);
                String b1 = b.substring(2,3);
                String b0 = b.substring(3,4);
                if(na > nb){
                    assert(COMPARE4(a3, a2, a1, a0, b3, b2, b1, b0).equals("1"));
                }
                else{
                    assert(COMPARE4(a3, a2, a1, a0, b3, b2, b1, b0).equals("0"));
                }
            }
        }
        System.out.println("Great Scott, you got COMPARE4 working!!");
        
        /* 
        8. Verifies that your NAND-Straightline XOR implementation is correct.
        */
        for(String i : list_strings(2)){
            String a = i.substring(0,1);
            String b = i.substring(1,2);
            assert(XOR(a,b).equals(XOR_nand(a,b)));
        }
        System.out.println("YAY, you're done!!");
    }
}
