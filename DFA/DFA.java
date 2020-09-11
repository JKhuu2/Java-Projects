import java.util.*;

public class DFA
{
    public String active;
    public String chars_seen;
    public Set<Tuple> delta_domain;
    public Map<Tuple, String> delta;
    public Set<String> finals;
    public String start;
    public Set<String> states;
    public Set<Character> alphabet;
    
    public DFA(Set<String> states, Set<Character> alphabet, Map<Tuple,String> delta, String start, Set<String> finals){
        this.states = states;
        this.delta = delta;
        this.start = start;
        this.finals = finals;
        this.alphabet = alphabet;
        this.active = start;
        this.chars_seen = "";
        this.delta_domain = Tuple.delta_domain(states, alphabet);
    }
    
    public DFA(){
        // empty constructor used only for a placeholder
        this.states = new HashSet<String>();
        this.delta = new HashMap<Tuple, String>();
        this.start = "";
        this.finals = new HashSet<String>();
        this.alphabet = new HashSet<Character>();
        this.active = this.start;
    }


    public boolean step(char c){
        // Take one transition in the machine
        Tuple t = new Tuple(active, c);
        active = delta.get(t);
        this.chars_seen += Character.toString(c);
        return finals.contains(active);
    }
    
    public boolean reset(){
        // resets the automaton to run on a new string
        // returns active state to be start state
        active = start;
        this.chars_seen = "";
        return finals.contains(start);
    }
    
    public boolean execute(String input){
        boolean result = finals.contains(active);;
        for(char c : input.toCharArray()){
            result = this.step(c);
        }
        return result;
    }
    
    public String toString(){
        String display = "Start state: " + this.start + "\n";
        display += "Current state: " + this.active + "\n";
        display += "delta function: \n";
        for(Tuple input : this.delta_domain){
            display += "\t" + input.toString() + " -> " + delta.get(input) + "\n";
        }
        display += "Final states: \n";
        for( String final_state : this.finals){
            display += "\t" + final_state + "\n";
        }        
        return display;
    }
    
    public static Map<Tuple, String> delta_from_lists(List<String> in_states, List<Character> in_char, List<String> out_states){
        // produces a delta function HashMap from the lists
        // assumes the input state, input character, and output state per transition appear at the same index
        Map<Tuple, String> delta = new HashMap<Tuple, String>();
        for(int i = 0; i < in_states.size(); i++){
            String q = in_states.get(i);
            Character c = in_char.get(i);
            String next = out_states.get(i);
            delta.put(new Tuple(q, c), next);
        }
        return delta;
    }
    
    public void toDot(){
        /*
        * Prints out a description of the automaton in dot,
        * which is a graph specification language.
        * I recommend going to this url to convert it into
        * an image: https://dreampuf.github.io/GraphvizOnline/
        */
        System.out.println("digraph G {");
        
        String start_shape;
        if (this.finals.contains(start)){
            start_shape = "doubleoctagon";
        }
        else {
            start_shape = "octagon";
        }
        System.out.println("node [shape = " + start_shape + "]; " + this.start + ";");
        
        String double_circled = "node [shape = doublecircle];";
        boolean additional_finals = false;
        for(String s : this.finals){
            if (s.equals(start)){
                continue;
            }
            double_circled += " " + s;
            additional_finals = true;
        }
        if(additional_finals){
            System.out.println(double_circled + ";");
        }
        
        String single_circled = "node [shape = circle];";
        boolean additional_states = false;
        for(String s : this.states){
            if (s.equals(start) || finals.contains(s)){
                continue;
            }
            single_circled += " " + s;
            additional_states = true;
        }
        if (additional_states){
            System.out.println(single_circled);
        }
        
        for (Tuple in : delta.keySet()){
            String transition = "";
            transition += in.curr_state + " -> " + delta.get(in) + " [ label = \"" + in.input_char + "\" ];";
            System.out.println(transition);
        }
        
        System.out.println("}");
    }
}
