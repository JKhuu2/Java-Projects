import java.util.*;

public class Regex{

    public char operation;
    public List<Regex> operands;
    private static char default_op = '\u2202'; // the default is to a character that I wouldn't expect you to ever really want. I picked a random unicode character. Basically, I just need to know when I didn't find an outer-most operation, which I do by checking if the default ever changed.
    
    private Regex(char operation, List<Regex> operands){
        this.operation = operation;
        this.operands = operands;
    }
    
    private Regex(char singleton){
        this.operation = singleton;
        this.operands = new ArrayList<Regex>();
        //System.out.println(singleton);
    }
    
    private Regex(){
        this.operation = NFA.epsilon;
        this.operands = new ArrayList<Regex>();
    }
    
    private Regex(String regex){
        Regex r = tokenize(regex);
        this.operation = r.operation;
        this.operands = r.operands;    
    }
    
    public static String insertConcat(String regex){
        List<Character> noConcatAfter = Arrays.asList('(', '|', '.');
        List<Character> noConcatBefore = Arrays.asList(')', '|', '.', '*');
        String newRegex = "";
        newRegex += Character.toString(regex.charAt(0));
        for(int i = 1; i < regex.length(); i++){
            char newRegexEnd = newRegex.charAt(newRegex.length()-1);
            char nextChar = regex.charAt(i);
            if(!noConcatAfter.contains(newRegexEnd) && !noConcatBefore.contains(nextChar)){
                newRegex += ".";
            }
            newRegex += Character.toString(nextChar);
        }
        return newRegex;
    }
    
    public static Regex tokenize(String regex){
        // This is really the most complicated part of building the regex engine.
        // This is what takes the regex and converts it into the prefix representation.
        // Essentially, it checks that parentheses are balanced, and if so then it 
        // finds which operation is not contained in any parenteses (if one exists).
        // If there are multiple, it does union, then concatenation, then star
        // (this is the reverse of order of operations).
        // If there is no out-most operation, this means that the entire regex
        // was wrapped in outer-most parentheses, so it removes them.
        if(regex.length() == 1){
            return new Regex(regex.charAt(0));
        }
        if(regex.length() == 0){
            return new Regex();
        }
        regex = insertConcat(regex);
        char op = default_op; 
        int parens = 0;
        String currExpr = "";
        List<Regex> tokens = new ArrayList<Regex>();
        
        // this for loop checks for union
        for(int i = 0; i < regex.length(); i++){
            char c = regex.charAt(i);
            if(c == '('){
                parens++;
            }
            else if(c == ')'){
                parens--;
            }
            if(parens < 0){
                System.out.println("Unbalanced Parentheses!");
                return new Regex();
            }
            if ((c == '|') && (parens == 0)){
                //we're doing a union
                op = '|';
                //System.out.println(Character.toString(op) + " " + currExpr);
                tokens.add(tokenize(currExpr));
                currExpr = "";
            }
            else{
                currExpr += Character.toString(c);
            }
        }
        if(parens != 0){
            System.out.println("Unbalanced Parentheses!");
            return new Regex();
        }
        if(op != default_op){
            tokens.add(tokenize(currExpr));
            return new Regex(op, tokens);
        }
        currExpr = "";
        tokens = new ArrayList<Regex>();
        // this for loop checks for concatenation
        for(int i = 0; i < regex.length(); i++){
            char c = regex.charAt(i);
            if(c == '('){
                parens++;
            }
            else if(c == ')'){
                parens--;
            }
            if ((c == '.') && (parens == 0)){
                //we're doing a union
                op = '.';
                tokens.add(tokenize(currExpr));
                currExpr = "";
            }
            else{
                currExpr += Character.toString(c);
            }
        }
        if(op != default_op){
            tokens.add(tokenize(currExpr));
            return new Regex(op, tokens);
        }
        if(regex.charAt(regex.length()-1) == '*'){
            op = '*';
            currExpr = regex.substring(0, regex.length()-1);
            tokens.add(tokenize(currExpr));
            return new Regex(op, tokens);
        }
        currExpr = regex.substring(1, regex.length()-1);        
        return tokenize(currExpr);
    }
    
    private int token_depth(){
        /*
        determines the "depth" of a regex 
        prefix tree. Used for guaranteeing
        unique node labels.
        */
        if (this.operands.size() == 0){
            return 1;
        }
        else{
            int depth = 0;
            for (Regex operand : this.operands){
                if (operand.token_depth() > depth){
                    depth = operand.token_depth();
                }
            }
            return depth + 1;
        }
    }
    
    private static NFA token_to_NFA(Regex rt){
        // Produces a NFA from the tokenized regular expression
        // You can think of a Regex object as the root of a prefix tree
        // based on the operation associated with the token, it will apply
        // the proper closure construction to the NFA its sub-tokens represent
        List<Character> ops = Arrays.asList('|', '.', '*');
        if(!ops.contains(rt.operation)){
            return singleton_NFA(rt.operation, "");
        }
        int depth = rt.token_depth();
        if(rt.operation == '.'){
            NFA nfa = NFA.copy_nfa(token_to_NFA(rt.operands.get(0)), "_d" + depth + "o0");
            for(int i = 1; i < rt.operands.size(); i++){
                nfa = NFA.concat(nfa, token_to_NFA(rt.operands.get(i)), "", "_d" + depth + "o" + i);
            }
            return nfa;
        }
        if(rt.operation == '|'){
            NFA nfa = token_to_NFA(rt.operands.get(0));
            for(int i = 1; i < rt.operands.size(); i++){
                nfa = NFA.union(nfa, token_to_NFA(rt.operands.get(i)), "", "_d" + depth + "o" + i);
            }
            return nfa;
        }
        if(rt.operation == '*'){
            NFA nfa = NFA.kleene(token_to_NFA(rt.operands.get(0)), "_d" + depth);
            return nfa;
        }
        
        return new NFA();
    }
    
    public static NFA regex_to_NFA(String regex){
        /*
        converts a regex from a 
        string to a prefix tree
        representation, then 
        converts the regex into
        a NFA.
        */
        Regex rt = new Regex(regex);
        return token_to_NFA(rt);
    }
    
    public static NFA singleton_NFA(char c, String suffix){
        /* 
        Creates a NFA which returns 1 for
        only a single string of length 1
        */
        if (c == NFA.epsilon){
            Set<String> states = new HashSet<>(Arrays.asList("start"));
            Set<Character> alphabet = new HashSet<Character>();
            String start = "start";
            Set<String> finals = new HashSet<>(Arrays.asList("start"));
            Map<Tuple,Set<String>> delta = new HashMap<Tuple,Set<String>>();
            return new NFA(states, alphabet, delta, start, finals);
        }
        Set<String> states = new HashSet<>(Arrays.asList("start", "end"));
        Set<Character> alphabet = new HashSet<>(Arrays.asList(c));
        Set<String> finals = new HashSet<>(Arrays.asList("end"));
        Map<Tuple,Set<String>> delta = new HashMap<Tuple,Set<String>>();
        delta.put(new Tuple("start", c), new HashSet<>(Arrays.asList("end")));
        String start = "start";
        NFA nfa = new NFA(states, alphabet, delta, start, finals);
        nfa.add_transition("start", c, "end");
        return nfa;
    } 
}
