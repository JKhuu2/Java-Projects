import java.util.*;

public class SeveralDFAs{

    public static DFA XOR(){
        /*
        * This is the automaton for the XOR language from class.
        */
        
        // I recommend that all implementations include these first 4 lines verbatim
        Set<Character> alphabet = new HashSet<Character>();
        Set<String> states = new HashSet<String>();
        Set<String> finals = new HashSet<String>();
        Map<Tuple, String> delta = new HashMap<Tuple, String>();
        
        
        // I recommend you include these 4 as well, adapted for each automaton
        Collections.addAll(alphabet, '0', '1');
        Collections.addAll(states, "E", "O");
        Collections.addAll(finals, "O");
        String start = "E";
        
        
        // Then you should add all transitions to delta
        delta.put(new Tuple("E",'1'), "O"); // when in state E, transition to state O on input 1
        delta.put(new Tuple("E",'0'), "E");
        delta.put(new Tuple("O",'1'), "E");
        delta.put(new Tuple("O",'0'), "O");
        
        // And finally you should make the DFA
        return new DFA(states, alphabet, delta, start, finals);
    }
    
    public static DFA AND(){
        /*
        * This is an implementation of infinite AND from class (it returns 0 on the empty string)
        */
        
        Set<Character> alphabet = new HashSet<Character>();
        Set<String> states = new HashSet<String>();
        Set<String> finals = new HashSet<String>();
        Map<Tuple, String> delta = new HashMap<Tuple, String>();
        
        Collections.addAll(alphabet, '0', '1');
        Collections.addAll(states, "start", "No0s", "Some0s");
        Collections.addAll(finals, "No0s");
        String start = "start";
        
        delta.put(new Tuple("start", '0'), "Some0s");
        delta.put(new Tuple("start", '1'), "No0s");
        delta.put(new Tuple("Some0s", '0'), "Some0s");
        delta.put(new Tuple("Some0s", '1'), "Some0s");
        delta.put(new Tuple("No0s", '0'), "Some0s");
        delta.put(new Tuple("No0s", '1'), "No0s");
        
        return new DFA(states, alphabet, delta, start, finals);
    }
    
    public static DFA oneEvenZeroOdd(){
        /*TODO
        * Build a DFA which, when given a binary string, 
        * returns true if it either starts with a 1 and has
        * even length, or starts with a 0 and has odd length.
        * 
        * For example, it should return true for:
        * 10
        * 11
        * 100000
        * 0
        * 000
        * 010
        *
        * It should return false for:
        * the empty string
        * 1
        * 00
        * 01
        * 110
        *
        * I've given you some code to start with, feel free
        * to change it if you'd prefer to do something else.
        */
    
        Set<Character> alphabet = new HashSet<Character>();
        Set<String> states = new HashSet<String>();
        Set<String> finals = new HashSet<String>();
        Map<Tuple, String> delta = new HashMap<Tuple, String>();
        
        Collections.addAll(alphabet, '0', '1');
        Collections.addAll(states, "start", "1odd", "1even", "0odd", "0even");
        Collections.addAll(finals, "1even", "0odd");
        String start = "start";
        
        delta.put(new Tuple("start",'1'), "1odd"); 
        delta.put(new Tuple("start",'0'), "0odd");
        delta.put(new Tuple("1odd",'1'), "1even");
        delta.put(new Tuple("1odd",'0'), "1even");
        delta.put(new Tuple("1even",'1'), "1odd"); 
        delta.put(new Tuple("1even",'0'), "1odd");
        delta.put(new Tuple("0odd",'1'), "0even");
        delta.put(new Tuple("0odd",'0'), "0even");
        delta.put(new Tuple("0even",'1'), "0odd");
        delta.put(new Tuple("0even",'0'), "0odd");
        
        return new DFA(states, alphabet, delta, start, finals);
    }
    
    public static DFA div8(){
        /*TODO
        * Build a DFA which, when given a binary string
        * representing a natural number, determines whether
        * that number is divisible by 8.
        *
        * I've given you some code to start with, feel free
        * to change it if you'd prefer to do something else.
        */
        Set<Character> alphabet = new HashSet<Character>();
        Set<String> states = new HashSet<String>();
        Set<String> finals = new HashSet<String>();
        Map<Tuple, String> delta = new HashMap<Tuple, String>();
        
        Collections.addAll(alphabet, '0', '1');
        Collections.addAll(states, "start", "1", "0", "10", "100", "1000");
        Collections.addAll(finals, "start", "0", "1000");
        String start = "start";
        
        delta.put(new Tuple("start",'1'), "1"); 
        delta.put(new Tuple("start",'0'), "0");
        delta.put(new Tuple("1",'1'), "1");
        delta.put(new Tuple("1",'0'), "10");
        delta.put(new Tuple("0",'1'), "1"); 
        delta.put(new Tuple("0",'0'), "0");
        delta.put(new Tuple("10",'1'), "1");
        delta.put(new Tuple("10",'0'), "100");
        delta.put(new Tuple("100",'1'), "1");
        delta.put(new Tuple("100",'0'), "1000");
        delta.put(new Tuple("1000",'1'), "1");
        delta.put(new Tuple("1000",'0'), "1000");
        
        return new DFA(states, alphabet, delta, start, finals);
    }

    
    
    public static DFA hammingDistance(String match, int distance){
        /* TODO
        * For bioinformatics and network transmission, it's helpful 
        * to be able to measure how different various strings are
        * from one another. These metrics are often called string
        * distance. There are various methods from measuring string
        * distance, and which to use mostly depends on what is an
        * allowable change. 
        * For this problem we're asking you to write a function to
        * accept all strings that are within a certain Hamming
        * distance of another string (the match parameter).
        * The Hamming distance between two strings is the smallest
        * number of single-character substitutions that must be 
        * made to convert one string into the other.
        *
        * For example, if we invoked this function on:
        * match = "nate"
        * distance = 2
        *
        * The automaton should return true for:
        * nate (distance 0, exact match)
        * gate (distance 1, substituting n->g)
        * note (distance 1, substituting a->o)
        * hath (distance 2, substituting n->h and e->h)
        * pale (distance 2, substituting n->p and t->l)
        *
        * The automaton should return false for:
        * math (3 substitutions required)
        * rich (4 substitutions required)
        * naters (cannot be converted by substitution alone)
        *
        * To keep your automata from looking too cluttered,
        * we restrict our alphabet to be DNA bases (a,t,g,c).
        */
        Set<Character> alphabet = new HashSet<Character>();
        Set<String> states = new HashSet<String>();
        Set<String> finals = new HashSet<String>();
        Map<Tuple, String> delta = new HashMap<Tuple, String>();
        
       Collections.addAll(alphabet, 'a', 't', 'g', 'c');
       String start="Read 0 Distance 0";
       states.add(start);
       List<Character> sub_alphabet = new ArrayList<Character>();
       List<Character> list_alphabet=new ArrayList<Character>();
       list_alphabet.add('a');
       list_alphabet.add('t');
       list_alphabet.add('g');
       list_alphabet.add('c');
       int new_distance=0;
       states.add("trash");
       
        for(int i=0; i<match.length(); i++) {
            for(int a=0; a<=new_distance; a++) {
                states.add("Read "+(i+1)+" Distance "+a);
                delta.put(new Tuple("Read " + i + " Distance "+a,match.charAt(i)), "Read "+(i+1)+" Distance "+a);
            }
            for(int j=0; j<list_alphabet.size(); j++) {
                if(list_alphabet.get(j)!=match.charAt(i)) {
                    sub_alphabet.add(list_alphabet.get(j));
                }
            }
            if(new_distance<distance) {
                new_distance++;
                states.add("Read "+(i+1)+" Distance "+(new_distance));
            }
            for(int y=0; y<new_distance; y++) {
                for(int k=0;k<sub_alphabet.size();k++) {
                    delta.put(new Tuple("Read "+i+" Distance "+y, sub_alphabet.get(k)), "Read "+(i+1)+" Distance "+(y+1));
                }
            }
            if(new_distance==distance) {
                for (int k=0; k<sub_alphabet.size(); k++) {
                    delta.put(new Tuple("Read "+i+" Distance "+distance, sub_alphabet.get(k)), "trash");
                }
            }
            if(i==match.length()-1) {
                for(int u=0; u<=new_distance; u++) {
                    for(int v=0; v<list_alphabet.size(); v++) {
                        delta.put(new Tuple("Read "+match.length()+" Distance "+u,list_alphabet.get(v)),"trash");
                    }
                }
            }
            sub_alphabet.clear();
        }
       for (int h=0; h<list_alphabet.size();h++) {
           delta.put(new Tuple("trash", list_alphabet.get(h)), "trash");
       }
       for (int i=0; i<=distance; i++) {
           finals.add("Read "+match.length()+" Distance "+i);
       }
        return new DFA(states, alphabet, delta, start, finals);
    }
    
    public static void main(String[] args){
        /*DFA xor = XOR();  // make the xor automaton
        System.out.println(xor);  //  you can see the definition of this automaton
        xor.step('0');  // the automaton will transition on a 0
        System.out.println(xor.active);  // you'll see that the active state is still the start state
        xor.step('1');  // the automaton will transition on a 1
        System.out.println(xor.active);  // now the active state has changed
        xor.reset();  // resets the automaton so you can run it on another input string
        System.out.println(xor.execute("1101011"));  // run the automaton on the given input string, 
                                                    // returns true/false for 1/0 (this should be true)
       
        
        DFA and = AND();  // make the and automaton
        and.toDot();  // prints a dot description of the automaton
                        // paste the text produced here:  https://dreampuf.github.io/GraphvizOnline/
         */
        System.out.println(oneEvenZeroOdd().execute("0001"));
        System.out.println(div8().execute("101000"));
        System.out.println(hammingDistance("cgat",1).execute("agat"));
    }
}
