import java.util.*;

public class Tuple {
// This class is used to represent the input to the delta function
// It contains a String for the name of the current state, and
// a character for the input which that transition matches.


    public final String curr_state;
    public final char input_char;
    
    public Tuple(String s, char c) {
        this.curr_state = s;
        this.input_char = c;
    }
    
    public boolean equals(Object o){
        if (this == o) return true;
        if(!(o instanceof Tuple)) return false;
        Tuple t = (Tuple) o;
        return (this.curr_state).equals(t.curr_state) && this.input_char == t.input_char;
    }
    
    public int hashCode(){
        return this.curr_state.hashCode();
    }
    
    public String toString(){
        return "(" + curr_state + "," + Character.toString(input_char) + ")";
    }
    
    public static Set<Tuple> delta_domain(Set<String> states, Set<Character> alphabet){
        Set<Tuple> domain = new HashSet<Tuple>();
        for(String state : states){
            for(Character c : alphabet){
                domain.add(new Tuple(state, c));
            }
        }
        return domain;
    }

}
