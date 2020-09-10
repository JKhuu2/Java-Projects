import java.util.*;

public class Exercise5{
    public static void nfa_example(){
        /*This function gives you and example of
        how to build an nfa. I built an nfa
        for determining whether a given binary
        string represents a natural number that
        is divisible by 8.*/
        // I recommend that, to build an nfa, you:
        // 1) make the alphabet
        // 2) make the state set
        // 3) make the start state
        // 4) make the final state set
        // 5) make an empty transition function
        // 6) make the nfa
        // 7) add the desired transitions
        Set<Character> alphabet = new HashSet<>(Arrays.asList('0', '1'));
        Set<String> states = new HashSet<>(Arrays.asList("start", "one", "zero1", "zero2", "zero3"));
        String start = "start";
        Set<String> finals = new HashSet<>(Arrays.asList("zero3"));
        Map<Tuple,Set<String>> delta = new HashMap<Tuple,Set<String>>();
        NFA the_nfa = new NFA(states, alphabet, delta, start, finals);
        the_nfa.add_transition("start", '0', "start");
        the_nfa.add_transition("start", '1', "start");
        the_nfa.add_transition("start", '1', "one");
        the_nfa.add_transition("one", '0', "zero1");
        the_nfa.add_transition("zero1", '0', "zero2");
        the_nfa.add_transition("zero2", '0', "zero3");
        the_nfa.toDot();
    }
    
    public static void regex_example(){
        /*This demonstrates how you can use the system provided
        for regex matching*/
        String the_regex = "(a|b|c)*d";
        NFA equivalent_nfa = Regex.regex_to_NFA(the_regex);
        String matches = "aaabcacaccaaacccbd";
        String doesnt_match = "daacacc";
        boolean this_is_true = equivalent_nfa.execute(matches);
        boolean this_is_false = equivalent_nfa.execute(doesnt_match);
        System.out.println(this_is_true);
        System.out.println(this_is_false);
    }
    
    public static NFA trivial(){
        /*TODO: 
        Create an NFA which, for the alphabet {0,1}, 
        returns 1 on the empty string (and 0 on all 
        other strings).
        Your nfa should contain no more than
        1 state*/
        Set<Character> alphabet = new HashSet<>(Arrays.asList('0','1'));
        Set<String> states = new HashSet<>(Arrays.asList("start"));
        String start = "start";
        Set<String> finals = new HashSet<>(Arrays.asList("start"));
        Map<Tuple,Set<String>> delta = new HashMap<Tuple,Set<String>>();
        NFA the_nfa = new NFA(states, alphabet, delta, start, finals);
        return the_nfa;
    }
        
    public static NFA substring0101(){
        /*TODO: 
        Create an NFA which returns 1 on
        all strings from the alphabet {0,1}
        that contain 0101 as a substring
        (and 0 on all other strings).
        Your nfa should contain no more than
        5 states*/
        Set<Character> alphabet = new HashSet<>(Arrays.asList('0','1'));
        Set<String> states = new HashSet<>(Arrays.asList("1", "0", "01", "010", "0101"));
        String start = "1";
        Set<String> finals = new HashSet<>(Arrays.asList("0101"));
        Map<Tuple,Set<String>> delta = new HashMap<Tuple,Set<String>>();
        NFA the_nfa = new NFA(states, alphabet, delta, start, finals);
        the_nfa.add_transition("1", '0', "0");
        the_nfa.add_transition("1", '1', "1");
        the_nfa.add_transition("0", '1', "01");
        the_nfa.add_transition("0", '0', "0");
        the_nfa.add_transition("01", '0', "010");
        the_nfa.add_transition("01", '1', "1");
        the_nfa.add_transition("010", '0', "0");
        the_nfa.add_transition("010", '1', "0101");
        the_nfa.add_transition("0101", '1', "0101");
        the_nfa.add_transition("0101", '0', "0101");
        return the_nfa;
    }
        
    public static NFA zerostar_onestar_zerostar(){
        /*TODO: 
        Create an NFA which returns 1 on
        all strings from the alphabet {0,1}
        that match the regular expression
        0*1*0*
        (and 0 on all other strings).
        Your nfa should contain no more than
        3 states*/
        Set<Character> alphabet = new HashSet<>(Arrays.asList('0','1'));
        Set<String> states = new HashSet<>(Arrays.asList("zero","one","zero2"));
        String start = "zero";
        Set<String> finals = new HashSet<>(Arrays.asList("zero", "one", "zero2"));
        Map<Tuple,Set<String>> delta = new HashMap<Tuple,Set<String>>();
        NFA the_nfa = new NFA(states, alphabet, delta, start, finals);
        the_nfa.add_transition("zero", '0', "zero");
        the_nfa.add_transition("zero", '1', "one");
        the_nfa.add_transition("one", '1', "one");
        the_nfa.add_transition("one", '0', "zero2");
        the_nfa.add_transition("zero2", '0', "zero2");
        return the_nfa;
    }
    
    public static String huntingtons(String dna_sequence){
        /*TODO: See task description for details*/
        String norm_regex = "((c|a|g|t)*|(c|a|g|t)*cag(c|a|g|t)*|(c|a|g|t)*cagcag(c|a|g|t)*|"
                + "(c|a|g|t)*cagcagcag(c|a|g|t)*|(c|a|g|t)*cagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)*cagcagcagcagcag(c|a|g|t)*|(c|a|g|t)*cagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)*cagcagcagcagcagcagcag(c|a|g|t)*|(c|a|g|t)*cagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)*cagcagcagcagcagcagcagcagcag(c|a|g|t)*|(c|a|g|t)*cagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|(c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|(c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|(c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|(c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|(c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*)";
        NFA norm_nfa = Regex.regex_to_NFA(norm_regex);
        
        String carrier_regex=
                 "((c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*)";
        NFA carrier_nfa=Regex.regex_to_NFA(carrier_regex);
        
        String atrisk_regex=""
                + "((c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*|"
                + "(c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(c|a|g|t)*)";
        NFA atrisk_nfa=Regex.regex_to_NFA(atrisk_regex);
        
        String affected_regex="((c|a|g|t)*cagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag(cag)*(c|a|g|t)*)";
        NFA affected_nfa=Regex.regex_to_NFA(affected_regex);
        
        if(affected_nfa.execute(dna_sequence)) {
            return "affected";
        }
        else if(atrisk_nfa.execute(dna_sequence)) {
            return "at risk";
        }
        else if(carrier_nfa.execute(dna_sequence)) {
            return "carrier";
        }
        else {
            return "normal";
        }
    }

    //Testing
    public static void main(String[] args){
        System.out.println("trivial testing:");
        System.out.println(trivial().execute(""));//1
        System.out.println(trivial().execute("1"));//0
        System.out.println(trivial().execute("0"));//0
        System.out.println(trivial().execute("10"));//0
        System.out.println("substring 0101 testing:");
        System.out.println(substring0101().execute("0101"));//1
        System.out.println(substring0101().execute("1"));//0
        System.out.println(substring0101().execute("0"));//0
        System.out.println(substring0101().execute("10"));//0
        System.out.println(substring0101().execute("010110"));//1
        System.out.println("zerostar_onestar_zerostar testing:");
        System.out.println(zerostar_onestar_zerostar().execute("010"));//1
        System.out.println(zerostar_onestar_zerostar().execute("0101"));//0
        System.out.println(zerostar_onestar_zerostar().execute("01000"));//1
        System.out.println(zerostar_onestar_zerostar().execute("000010"));//1
        System.out.println(zerostar_onestar_zerostar().execute("0111"));//1
        System.out.println(zerostar_onestar_zerostar().execute("110000"));//1
        System.out.println(zerostar_onestar_zerostar().execute("110001100"));//0
        System.out.println(zerostar_onestar_zerostar().execute(""));//1
        System.out.println(zerostar_onestar_zerostar().execute("0"));//1
        System.out.println(zerostar_onestar_zerostar().execute("1"));//1
        System.out.println("huntingtons testing:");
        System.out.println(huntingtons(""));//normal
        System.out.println(huntingtons("cagtcagcag"));//normal
        System.out.println(huntingtons("gggggttttcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagaaa"));//normal
        System.out.println(huntingtons("gggaaaccccagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcag"));//carrier
        System.out.println(huntingtons("gggaaaccccagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagagtc"));//carrier
        System.out.println(huntingtons("agtccagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagaa"));//at risk
        System.out.println(huntingtons("agtccagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagaa"));//at risk
        System.out.println(huntingtons("agtccagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagaa"));//affected
        System.out.println(huntingtons("agtccagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagcagaa"));//affected
    }
}
