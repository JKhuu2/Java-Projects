/*The goal of this exercise is to develop your understanding of universality by building the EVAL function discussed in class. For better readability, we will be using bitstrings as inputs rather than individual bits. To keep this as formal as the algorithm discussed in class, avoid maninpulating substrings longer than just a single bit. You may not use any for loops or if statements in your implementation, but you may invoke functions that are provided or that you define (so long as this also abide by the above rules). If your function should return multiple bits as output, return those as a string.*/

public class eval{

    /*
    0. From here until "END" (line 94) it's mostly utility functions for debugging/asserts, and functions you're already familiar with. You'll welcome to use them if you want, or you can skip them to move on.
    */

    /*This function enumerates all binary strings of a certain length. This will primarily be used for testing purposes.*/
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
    
    public static String nat_to_string(int number, int num_bits){
        //Given a natural number, converts it into a binary string of the given length by the procedure described in lecture.  Do not modify this function.
        String binary = "";
        for (int i = 0; i < num_bits; i++){
            String bit = Integer.toString(number%2);
            number = number/2;
            binary = bit + binary;
        }
        return binary;
    }
    
    public static boolean is_binary(String b){
        return b.equals("1") || b.equals("0");
    }
    
    
    /*Here are the definitions of a bunch of functions you've seen before, and may be helpful in this exercise. Feel free to use any of them.*/
            
    public static String NAND(String a,String b){
        assert(is_binary(a) && is_binary(b));
        return Integer.toString(1-Integer.parseInt(a)*Integer.parseInt(b));
    }
        
    public static String NOT(String a){
        return NAND(a, a);
    }

    public static String AND(String a, String b){
        String temp = NAND(a, b);
        return NAND(temp, temp);
    }

    public static String OR(String a, String b){
        String temp1 = NAND(a,a);
        String temp2 = NAND(b,b);
        return NAND(temp1, temp2);
    }

    public static String XOR(String a, String b){
        String or_ab = OR(a, b);
        String and_ab = AND(a, b);
        String not_and_ab = NOT(and_ab);
        return AND(or_ab, not_and_ab);
    }

    public static String IF(String cond,String a,String b){
        String not_cond = NAND(cond, cond);
        String temp1 = NAND(cond, a);
        String temp2 = NAND(not_cond, b);
        return NAND(temp1, temp2);
    }

    public static String LOOKUP_1(String x0, String x1, String i0){
        return IF(i0, x1, x0);
    }

    public static String LOOKUP_2(String x0,String x1,String x2,String x3,String i0,String i1){
        String first_half = LOOKUP_1(x0, x1, i1);
        String second_half = LOOKUP_1(x2, x3, i1);
        return IF(i0, second_half, first_half);
    }

    public static String LOOKUP_3(String x0,String x1,String x2,String x3,String x4,String x5,String x6,String x7,String i0,String i1,String i2){
        String first_half = LOOKUP_2(x0, x1, x2, x3, i1, i2);
        String second_half = LOOKUP_2(x4, x5, x6, x7, i1, i2);
        return IF(i0, second_half, first_half);
    }
    
    /*END*/
    
    

    /*1. This next function will take a NAND-Straightline program as described by a list of triples of natural numbers,
     *  and will return a bitstring to represent that list of triples*/
    
    public static String prog2bits(int[][] prog, int num_bits){
        String bits_prog = "";
        for(int[]triple : prog){
            String bits0 = nat_to_string(triple[0], num_bits);
            String bits1 = nat_to_string(triple[1], num_bits);
            String bits2 = nat_to_string(triple[2], num_bits);
            String triple_bits = bits0 + bits1 + bits2;
            bits_prog += triple_bits;
        }
        return bits_prog;
    }
    
    /*3. We will now begin building up the components necessary to eventually implement EVAL. The first new function we'll need is GET_7, which will index into the table `T` of 7 rows (since the programs we're evaluating will have 7 variables). This function gives you an idea for how we can take bit strings as input and consider it as if it was several inputs of a single bit each.*/

    public static String GET_7(String T, String i){
        assert(T.length() == 7 && i.length() == 3);
        // gives the value of the variable indexed by the length 3 bitstring i from a 7-bit table T
        String t0 = T.substring(0,1);
        String t1 = T.substring(1,2);
        String t2 = T.substring(2,3);
        String t3 = T.substring(3,4);
        String t4 = T.substring(4,5);
        String t5 = T.substring(5,6);
        String t6 = T.substring(6,7);
        String i0 = i.substring(0,1);
        String i1 = i.substring(1,2);
        String i2 = i.substring(2,3);
        return LOOKUP_3(t0, t1, t2, t3, t4, t5, t6, "0", i0, i1, i2);
    }


    /*4. TODO: Implement the function EQUAL_3 below, which will return 1 if two given 3-bit numbers are the same, and 0 otherwise.*/
    public static String EQUAL_3(String i, String j){
        assert(i.length() == j.length() && j.length() == 3);
        //TODO: Replace the body of this function to implement EQUAL_3 correctly
        String i1=i.substring(0,1);
        String i2=i.substring(1,2);
        String i3=i.substring(2,3);
        String j1=j.substring(0,1);
        String j2=j.substring(1,2);
        String j3=j.substring(2,3);
        
//      if(i1.equals(j1)&&i2.equals(j2)&&i3.equals(j3)) {
//      return "1";
//  }
//  return "0";

        String not_i1=NAND(i1,i1);
        String not_j1=NAND(j1,j1);
        String not_i2=NAND(i2,i2);
        String not_j2=NAND(j2,j2);
        String not_i3=NAND(i3,i3);
        String not_j3=NAND(j3,j3);
        
        String i1_j1_1=NAND(not_i1,j1);
        String i1_j1_2=NAND(i1, not_j1);
        String i1_j1_3=NAND(i1_j1_1,i1_j1_2);
        String i1_j1_eq=NAND(i1_j1_3,i1_j1_3);
        
        String i2_j2_1=NAND(not_i2,j2);
        String i2_j2_2=NAND(i2,not_j2);
        String i2_j2_3=NAND(i2_j2_1,i2_j2_2);
        String i2_j2_eq=NAND(i2_j2_3,i2_j2_3);
        
        String nand_12=NAND(i1_j1_eq,i2_j2_eq);
        String eq_12=NAND(nand_12,nand_12);
        
        String i3_j3_1=NAND(not_i3,j3);
        String i3_j3_2=NAND(i3,not_j3);
        String i3_j3_3=NAND(i3_j3_1,i3_j3_2);
        String i3_j3_eq=NAND(i3_j3_3,i3_j3_3);
        
        String nand_12_3=NAND(eq_12,i3_j3_eq);
        String eq_123=NAND(nand_12_3,nand_12_3);
        
        return eq_123;
        
    }
    
    
    /*6. TODO: Implement the function UPDATE_7 below, which will change the given index (given by the triple of bits i) of the 7-row table T to become the bit b. You will likely need EQUAL_3 and IF to do this.*/
    
    public static String UPDATE_7(String T, String b, String i){
        assert(T.length() == 7);
        assert(i.length() == 3);
        //TODO: Replace the body of this function to implement UPDATE_7 correctly
        String t0 = T.substring(0,1);
        String t1 = T.substring(1,2);
        String t2 = T.substring(2,3);
        String t3 = T.substring(3,4);
        String t4 = T.substring(4,5);
        String t5 = T.substring(5,6);
        String t6 = T.substring(6,7);
        String new_T="";
        if(EQUAL_3(i,"000")=="1") {
            t0=b;
        }
        else if(EQUAL_3(i,"001")=="1") {
            t1=b;
        }
        else if (EQUAL_3(i,"010")=="1") {
            t2=b;
        }
        else if(EQUAL_3(i,"011")=="1") {
            t3=b;
        }
        else if(EQUAL_3(i,"100")=="1") {
            t4=b;
        }
        else if(EQUAL_3(i,"101")=="1") {
            t5=b;
        }
        else if(EQUAL_3(i,"110")=="1") {
            t6=b;
        }
        new_T=t0+t1+t2+t3+t4+t5+t6;
        return new_T;
        
    }


    public static String pseudo_update_7(String T, String b, String i){
        //This update works by manipulating indices directly. It is meant to show you what your update should return, and to use for the assert below.
        int i0 = Integer.parseInt(i.substring(0,1));
        int i1 = Integer.parseInt(i.substring(1,2));
        int i2 = Integer.parseInt(i.substring(2,3));
        int index = i2 + 2*i1 + 4*i0;
        return T.substring(0,index) + b + T.substring(index+1, T.length());
    }
    
    
    /* 8. TODO: Now we have all the pieces we need in order to implement EVAL_3_7_4_1. This is a function that will take 2 inputs: 
     * a bitstring representing a NAND-Straightline program, and a bitstring to be given as input to that program. The output of EVAL_3_7_4_1 
     * should be the same as the output of that program running on that input. The 3,7,4,1 refer to the number of input bits, variables, lines, 
     * and output bits of the program (respectively).*/
    
    public static String EVAL_3_7_4_1(String program, String input_bits){
        assert(program.length() == 4*3*3); // the length of the program is lines * 3 * log_2(variables);
        assert(input_bits.length() == 3);
        String T="0000000";
        // TODO: fill in the body of this function.
        
        //make T table
        T=UPDATE_7(T, input_bits.substring(0,1),"000");
        T=UPDATE_7(T, input_bits.substring(1,2),"001");
        T=UPDATE_7(T, input_bits.substring(2,3),"010");

        //go through the lines
        String a=GET_7(T,program.substring(3,6));
        String b=GET_7(T, program.substring(6,9));
        T=UPDATE_7(T, NAND(a,b), program.substring(0,3));
        
        String a1=GET_7(T,program.substring(12,15));
        String b1=GET_7(T, program.substring(15,18));
        T=UPDATE_7(T, NAND(a1,b1), program.substring(9,12));

        String a2=GET_7(T,program.substring(21,24));
        String b2=GET_7(T, program.substring(24,27));
        T=UPDATE_7(T, NAND(a2,b2), program.substring(18,21));

        String a3=GET_7(T,program.substring(30,33));
        String b3=GET_7(T, program.substring(33));
        T=UPDATE_7(T, NAND(a3,b3), program.substring(27,30));

        //return output
        return GET_7(T,"110");
    }

    
    public static void main(String[] args){
        
        /*2. Here I've define IF as a list-of-triples (according to the procedure from class)*/
        int[][] if_program_ints = new int[][] {
            {3,0,0},
            {4,0,1},
            {5,3,2},
            {6,4,5}
        };
        
        /*Next I've converted that list of triples into a string*/
        String if_program = prog2bits(if_program_ints, 3);
    
    
        /*4. This is test code, so you can see what GET_7 is meant to do*/
        for(String t : list_strings(7)){
            for(int i = 0; i < 7; i++){
                String ti = t.substring(i,i+1);
                assert(ti.equals(GET_7(t, nat_to_string(i, 3))));
            }
        }
        System.out.println("GET works!");
        
        /*5. Code to test EQUAL_3*/
        for(String s1 : list_strings(3)){
            for (String s2 : list_strings(3)){
                if (s1.equals(s2)){
                    assert(EQUAL_3(s1, s2).equals("1"));
                }
                else{
                    assert(EQUAL_3(s1, s2).equals("0"));
                }
            }
        }
        System.out.println("Bravo!");
        
        /* 7. Code to test UPDATE_7*/
        for(String t : list_strings(7)){
            for(int i = 0; i < 7; i++){
                for(int b = 0; b < 2; b++){
                    String bit = Integer.toString(b);
                    assert(pseudo_update_7(t, bit, nat_to_string(i, 3)).equals(UPDATE_7(t, bit, nat_to_string(i, 3))));
                }
            }
        }
        System.out.println("You got it!");

        /* 9. Code to verify that your eval works*/
        String cond = "0";
        String a = "1";
        String b = "0";
        assert(EVAL_3_7_4_1(if_program, cond+a+b).equals(IF(cond,a,b)));
        System.out.println("Gucci!");

        cond = "1";
        a = "1";
        b = "0";
        assert(EVAL_3_7_4_1(if_program, cond+a+b).equals(IF(cond,a,b)));
        System.out.println("Sweet!");

        cond = "0";
        a = "0";
        b = "1";
        assert(EVAL_3_7_4_1(if_program, cond+a+b).equals(IF(cond,a,b)));
        System.out.println("Cool Beans!");

        cond = "1";
        a = "0";
        b = "1";
        assert(EVAL_3_7_4_1(if_program, cond+a+b).equals(IF(cond,a,b)));
        System.out.println("You Rock!");
        
        
        
        
        /*10. If we were to slightly modify our procedure for converting programs into a list of triples, we can use `EVAL_3_7_4_1` to 
         * evaluate any program that uses no more than 3 inputs, 7 variables, 4 lines, and 1 output.

        TODO: Write OR as a list of triples so that we can evaluate it using EVAL_3_7_4_1 above. Note that OR requires fewer inputs, variables, 
        and lines of code than IF does. You'll need to do something about that. You may use the prog2bits function we provided above.*/

        int[][] or_program = new int[][] {
            {3,0,0},
            {4,1,1},
            {5,4,1},
            {6,4,3}
        }; // TODO: fill this in as a list of triples of natural numbers
        
        String or_program_bits = prog2bits(or_program, 3);

        a = "0";
        b = "0";
        assert(EVAL_3_7_4_1(or_program_bits, a+b+"0").equals(OR(a,b)));
        a = "0";
        b = "1";
        assert(EVAL_3_7_4_1(or_program_bits, a+b+"0").equals(OR(a,b)));
        a = "1";
        b = "0";
        assert(EVAL_3_7_4_1(or_program_bits, a+b+"0").equals(OR(a,b)));
        a = "1";
        b = "1";
        assert(EVAL_3_7_4_1(or_program_bits, a+b+"0").equals(OR(a,b)));
        System.out.println("You did it!!");

    }


}
