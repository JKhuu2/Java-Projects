import java.util.Arrays;
import java.util.EmptyStackException;
public class Stack<E> {
    private static final int INITSIZE = 10; //initial array size
    private E[] items;
    private int numItems; //the number of items on the stack
    private int top; //the index of top of the stack
    /*
     * NOTE: top refers to the next available index to insert an element, so in a sense the index after the last element of the stack
     * For example, if stack = [0, 3, 2, 4, null, null, ...]
     *                                        ^ top
     */
    
    /**
     * Constructor
     */
    public Stack() {
        items = (E[]) new Object[INITSIZE];
        numItems = 0;
        top = 0;
    }
    
    /**
     * Pushes the specified parameter onto the stack
     * @param obj
     */
    public void push(E obj) {
        //TODO: implement push method
        
        growIfNecessary();
        
        items[top] = obj;
        numItems++;
        top++;
    }
    
    /**
     * Removes the top element from the stack and returns its value
     * Return null if the stack is empty
     * @return the removed element on the stack
     */
    public E pop() {
        //TODO: implement pop method
        if (isEmpty()) {
            return null;
        }
        top--;
        E retval=items[top];
        numItems--;
        return retval;
    }
    
    /**
     * Grows the stack if it is already full
     */
    private void growIfNecessary() {
        if(top == items.length) {
            //double the length of the original stack
            E[] newItems = (E[]) new Object[2*items.length];
            
            //copy the items from the original stack into the new stack
            for(int i = 0; i < items.length; i++) {
                newItems[i] = items[i];
            }
            
            //set the stack to be the new stack
            items = newItems;
        }
    }
    
    /**
     * Peeks at the top element on the stack, but doesn't remove it
     * Return null if stack is empty
     * @return the top element of the stack
     */
    public E peek() {
        //TODO: implement peek method
        if (!isEmpty()) {
            return items[top-1];
        }
        return null; 
    }
    
    /**
     * @return whether the queue is empty or not
     */
    public boolean isEmpty() {
        //TODO: implement isEmpty method
        if (numItems==0) {
            return true;
        }
        return false;
    }
    
    /**
     * Prints the stack as an array
     */
    public void printStack() {
        String stack = "";
        for(int i = 0; i < numItems; i++) {
            stack += items[i] + " ";
        }
        System.out.println("Stack of Size " + numItems + ": [" + stack + "]");
    }
    
    //for main method testing
    public static void main(String[] args) {
 
    }
}
