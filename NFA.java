import java.util.*;

public class NFA
{

    public Set<String> active;
    public Map<Tuple,Set<String>> delta; // \delta
    public static char epsilon = '\u03b5';
    public Set<Character> alphabet;
    public Set<String> states;
    public Set<String> finals;
    public String start;
    
    public NFA(Set<String> states, Set<Character> alphabet, Map<Tuple,Set<String>> delta, String start, Set<String> finals){
        //Standard constructore, similar to that for DFAs
        this.states = states;
        this.delta = delta;
        this.start = start;
        this.finals = finals;
        this.alphabet = alphabet;
        this.active = new HashSet<String>();
        this.active.add(start);
        this.epsilon_spread();
    }
    
    public NFA(){
        //An empty constructor. Gives a 1-state NFA that doesn't accept anything
        this.states = new HashSet<String>();
        this.delta = new HashMap<Tuple, Set<String>>();
        this.start = "start";
        this.finals = new HashSet<String>();
        this.alphabet = new HashSet<Character>();
        this.active = new HashSet<String>();
        this.states.add(start);
        this.active.add(start);
    }
    
    public void add_transition(String source, char character, String dest){
        // This function makes it easier to add a transition to a delta map
        Tuple t = new Tuple(source, character);
        if(!this.delta.containsKey(t)){
            this.delta.put(t, new HashSet<String>());
        }
        this.delta.get(t).add(dest);
    }
    
    private void epsilon_spread(){
        /*
        Activates all states reachable from an active state
        by only epsilon-transitions.
        */
        boolean expanded = true;
        while(expanded){ // as long as you've added more states
            expanded = false; // we haven't added new states yet
            Set<String> additionalActive = new HashSet<String>();
            for(String act : this.active){
                Tuple t = new Tuple(act, epsilon);
                if (!this.delta.containsKey(t)){
                    continue;
                }
                for (String dest : this.delta.get(t)){
                    expanded = expanded || !this.active.contains(dest);
                    additionalActive.add(dest);
                }
            }
            this.active.addAll(additionalActive);
        }
    }
    
    private boolean return_value(){
        /*If the NFA stops here, what does it return?*/
        for(String state : active){
            if(this.finals.contains(state)){
                return true;
            }
        }
        return false;
    }
    
    public boolean step(char c){
        // Take a transition on a single character
        Set<String> nextActive = new HashSet<String>();
        for(String active : this.active){
            Tuple t = new Tuple(active, c);
            if(delta.containsKey(t)){
                nextActive.addAll(delta.get(t));
            }
        }
        this.active = nextActive;
        this.epsilon_spread();
        return this.return_value();
    }
    
    public boolean reset(){
        // resets the automaton to run on a new string
        // returns active state to be start state
        this.active = new HashSet<String>();
        this.active.add(start);
        this.epsilon_spread();
        return this.return_value();
    }
    
    public boolean execute(String input_string){
        /*Run the nfa on an entire input string*/
        boolean result = this.return_value();
        for (int i = 0; i < input_string.length(); i++){
            char c = input_string.charAt(i);
            result = this.step(c);
        }
        this.reset();
        return result;
    }
    
    public String toString(){
        String display = "Start state: " + this.start + "\n";
        String curr_state;
        if (this.active.size() == 0){
            curr_state = "empty set";
        }
        else{
            curr_state = this.active.toString();
        }
        display += "Current state: " + curr_state + "\n";
        display += "delta function: \n";
        for (Tuple tup : this.delta.keySet()){
            display += "\t" + tup + " -> " + this.delta.get(tup) + "\n";
        }
        display += "Final states: \n";
        for (String state : this.finals){
            display += "\t" + state + "\n";
        }
        display += "Alphabet: \n";
        for (char c : this.alphabet){
            display += "\t" + c + "\n";
        }
        return display;
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
        
        for(String source : this.states){
            for (String dest : this.states){
                String matching = "";
                HashSet<Character> matches = new HashSet<Character>(this.alphabet);
                matches.add(epsilon);
                for (char c : matches){
                    Tuple t = new Tuple(source, c);
                    if (this.delta.containsKey(t) && this.delta.get(t).contains(dest)){
                        matching += Character.toString(c) + ',';
                    }
                }
                if (matching.length() > 0){
                    String transition = source + " -> " + dest + " [ label = \"" + matching.substring(0, matching.length()-1) + "\" ];";
                    System.out.println(transition);
                }
            }
        }
        System.out.println("}");
    }    
    
    public static NFA merge(NFA nfa1, NFA nfa2, String suffix1, String suffix2){
        /*
        creates a new nfa from two source nfas. This new nfa has all the states
        and transitions from the original 2 nfas (with distinct suffixes added
        to each state label to guarantee unique names). The start state is set
        as the start state from the first nfa given. The nfa has no final
        states.
        */
        Set<String> states = new HashSet<String>();
        Set<String> finals = new HashSet<String>();
        for (String state : nfa1.states){
            states.add(state + suffix1);
        }
        for (String state : nfa1.finals){
            states.add(state + suffix1);
        }
        for (String state : nfa2.states){
            states.add(state + suffix2);
        }
        for (String state : nfa2.finals){
            states.add(state + suffix2);
        }
        Set<Character> alphabet = new HashSet<Character>(nfa1.alphabet);
        alphabet.addAll(nfa2.alphabet);
        Map<Tuple,Set<String>> delta = new HashMap<Tuple,Set<String>>();
        for (Tuple match : nfa1.delta.keySet()){
            Set<String> destinations = nfa1.delta.get(match);
            String source = match.state;
            char c = match.input_char;
            Tuple new_match = new Tuple(source + suffix1, c);
            delta.put(new_match, new HashSet<String>());
            for (String dest : destinations){
                delta.get(new_match).add(dest + suffix1);
            }
        }
        for (Tuple match : nfa2.delta.keySet()){
            Set<String> destinations = nfa2.delta.get(match);
            String source = match.state;
            char c = match.input_char;
            Tuple new_match = new Tuple(source + suffix2, c);
            delta.put(new_match, new HashSet<String>());
            for (String dest : destinations){
                delta.get(new_match).add(dest + suffix2);
            }
        }
        String start = nfa1.start + suffix1;
        return new NFA(states, alphabet, delta, start, finals);
    }
    
    public static NFA union(NFA nfa1, NFA nfa2, String suffix1, String suffix2){
        /*creates a new nfa whose language is the
        union of the given nfas'. A different suffix is added
        onto then end of each nfa's states' label to make it
        more likely that state labels are unique.
        */
        NFA merged = merge(nfa1, nfa2, suffix1, suffix2);
        merged.states.add("new");
        merged.start = "new";
        merged.add_transition("new", epsilon, nfa1.start + suffix1);
        merged.add_transition("new", epsilon, nfa2.start + suffix2);
        for (String state : nfa1.finals){
            merged.finals.add(state + suffix1);
        }
        for (String state : nfa2.finals){
            merged.finals.add(state + suffix2);
        }
        return merged;
    }
    
    public static NFA concat(NFA nfa1, NFA nfa2, String suffix1, String suffix2){
        /*creates a new nfa whose language is the
        concatenation of the given nfas'. A different suffix is added
        onto then end of each nfa's states' label to make it
        more likely that state labels are unique.
        */
        NFA merged = merge(nfa1, nfa2, suffix1, suffix2);
        merged.start = nfa1.start + suffix1;
        for (String state : nfa1.finals){
            state += suffix1;
            merged.add_transition(state, epsilon, nfa2.start + suffix2);
        }
        for (String state : nfa2.finals){
            merged.finals.add(state + suffix2);
        }
        return merged;
    }
    
    public static NFA copy_nfa(NFA nfa1, String suffix){
        /* creates a new nfa whose language is the
        the same as the given nfa's. A different suffix is added
        onto then end of each state's label to make it
        more likely that state labels are unique.
        */
        Set<String> states = new HashSet<String>();
        for (String state : nfa1.states){
            states.add(state + suffix);
        }
        Set<Character> alphabet = new HashSet<Character>(nfa1.alphabet);
        Map<Tuple,Set<String>> delta = new HashMap<Tuple,Set<String>>();
        for (Tuple match : nfa1.delta.keySet()){
            Set<String> destinations = nfa1.delta.get(match);
            String state = match.state;
            char c = match.input_char;
            Tuple new_match = new Tuple(state + suffix, c);
            delta.put(new_match, new HashSet<String>());
            for (String dest : destinations){
                delta.get(new_match).add(dest + suffix);
            }
        }            
        String start = nfa1.start + suffix;
        Set<String> finals = new HashSet<String>();
        for (String state : nfa1.finals){
            finals.add(state + suffix);
        }
        return new NFA(states, alphabet, delta, start, finals);
    }
    
    public static NFA kleene(NFA nfa1, String suffix){
        /*creates a new nfa whose language is the
        the kleene start of the given nfa's. A different suffix is added
        onto then end of each state's label to make it
        more likely that state labels are unique.
        */
        NFA copy = copy_nfa(nfa1, suffix);
        String eps_state = "eps_state" + suffix;
        for (String f : nfa1.finals){
            copy.add_transition(f  + suffix, epsilon, copy.start);
        }
        copy.states.add(eps_state);
        copy.add_transition(copy.start, epsilon, eps_state);
        copy.finals.add(eps_state);
        return copy;
    }
}
