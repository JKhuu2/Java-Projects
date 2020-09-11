
/**
 * Homework 5 
 * Implement the following methods on recursion as defined in the 
 * homework 5 document.
 * 
 * @author jtk2eh
 */
public class Recursion {

    public static boolean palindrome(String s) {
        if (s.length() == 1) {
            return true;
        }
        if (s.length() == 2) {
            if (s.charAt(0) == s.charAt(s.length() - 1)) {
                return true;
            }
            return false;
        }
        if (s.charAt(0) == s.charAt(s.length() - 1)) {
            return palindrome(s.substring(1, s.length() - 1));
        }
        return false;
    }

    public static String reverseString(String s) {
        // reverse of a palindrome is the same thing
        if (palindrome(s)) {
            return s;
        }
        // if empty string, return that
        if (s.isEmpty()) {
            return s;
        }
        // reverse the string after the first character and add char to end
        return reverseString(s.substring(1)) + s.charAt(0);
    }

    public static int handshakes(int n) {
        // no handshakes if 1 person or 0 people
        if (n <= 1) {
            return 0;
        }
        // two people, one handshake
        if (n == 2) {
            return 1;
        }
        // three people, three handshakes
        if (n == 3) {
            return 3;
        }
        // number of handshakes for n is the number of handshakes for n-1
        // plus a handshake for each one of those people
        return handshakes(n - 1) + (n - 1);
    }

    public static long ackermann(long m, long n) {
        // if m=0, return n+1
        if (m == 0) {
            return n + 1;
        }
        // if m>0 and n=0, then use recursion to return
        // Ackermann(m-1,1)
        if (m > 0 && n == 0) {
            return ackermann(m - 1, 1);
        }
        // use recursion to return Ackermann(m-1,Ackermann(m,n-1))
        // if n and m are >0
        return ackermann(m - 1, ackermann(m, n - 1));
    }

    public static void main(String[] args) {
        // write your tests here
        String s1 = "abba";
        String p1 = Boolean.toString(palindrome(s1));
        String s2 = "smith";
        String p2 = Boolean.toString(palindrome(s2));
        // palindrome test 1
        // expected true, actual true
        System.out.println("Palindrome test 1: abba");
        System.out.println("Expected: true " + "Actual: " + p1);
        System.out.println(" ");

        // palindrome test 2
        // expected false, actual false
        System.out.println("Palindrome test 2: smith");
        System.out.println("Expected: false " + "Actual: " + p2);
        System.out.println(" ");

        // reverseString test 1
        // expected - abba
        // actual - abba
        System.out.println("Reverse String test 1: abba");
        System.out.println("Expected: abba");
        System.out.println("Results: " + reverseString(s1));
        System.out.println(" ");

        // reverseString test2
        // expected: htims
        // actual: htims
        System.out.println("Reverse String test 2: smith");
        System.out.println("Expected: htims");
        System.out.println("Results: " + reverseString(s2));
        System.out.println(" ");

        // handshakes test 1
        // expected: 3
        // actual: 3
        System.out.println("Handshakes Test 1: 3");
        System.out.println("Expected: 3");
        System.out.println("Results: " + handshakes(3));
        System.out.println(" ");

        // handshakes test 2
        // expected: 15
        // actual: 15
        System.out.println("Handshakes Test 2: 6");
        System.out.println("Expected: 15");
        System.out.println("Actual: " + handshakes(6));
        System.out.println(" ");

        // ackermann test 1
        // expected: 3
        // actual: 3
        System.out.println("Ackermann Test 1: (2,0)");
        System.out.println("Expected: 3");
        System.out.println("Actual: " + ackermann(2, 0));
        System.out.println(" ");

        // ackermann test 2
        // expected: 125
        // actual: 125
        System.out.println("Ackermann Test 2: (3,4)");
        System.out.println("Expected: 125");
        System.out.println("Actual: " + ackermann(3, 4));
    }
}
