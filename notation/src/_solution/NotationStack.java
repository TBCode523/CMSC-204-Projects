package _solution;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
/** A Stack that specializes in Converting Notations
 *
 * @author Talal Brek
 *
 * @param <T> data type
 */
public class NotationStack<T> implements StackInterface<T>{

    private T[] elements;
   private int size;
   private int top;
    public NotationStack(int i) {
        size = i;
        top = -1;
        elements = (T[]) new Object[i];
    }

    /**
     * Determines if Stack is empty
     *
     * @return true if Stack is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        return top < 0;
    }

    /**
     * Determines if Stack is full
     *
     * @return true if Stack is full, false if not
     */
    @Override
    public boolean isFull() {
        return top >= size-1;
    }

    /**
     * Deletes and returns the element at the top of the Stack
     *
     * @return the element at the top of the Stack
     */
    @Override
    public T pop() throws StackUnderflowException {
        if(isEmpty()) throw new StackUnderflowException();
        else {
                T t = elements[top];
                elements[top] = null;
                top--;
                return t;
        }
    }

    /**
     * Returns the element at the top of the Stack, does not pop it off the Stack
     *
     * @return the element at the top of the Stack
     */
    @Override
    public T top() throws StackUnderflowException {
        return elements[top];
    }

    /**
     * Number of elements in the Stack
     *
     * @return the number of elements in the Stack
     */
    @Override
    public int size() {
        return top+1;
    }


    /**
     * Adds an element to the top of the Stack
     *
     * @param e the element to add to the top of the Stack
     * @return true if the add was successful, false if not
     */


    /**
     * Adds an element to the top of the Stack
     *
     * @param e the element to add to the top of the Stack
     * @return true if the add was successful, false if not
     */
@Override
public boolean push(T e) throws StackOverflowException{
    if (isFull()) throw new StackOverflowException();
    else {
        top++;
        elements[top] = e;
        return true;
    }
}


    /**
     * Returns the string representation of the elements in the Stack, the beginning of the
     * string is the bottom of the stack
     * Place the delimiter between all elements of the Stack
     *
     * @param delimiter
     * @return string representation of the Stack from bottom to top with elements
     * separated with the delimiter
     */
    @Override
    public String toString(String delimiter) {
        String str = toString();
        String delimString = "";
        for(int i = 0; i < str.length(); i++) {
            delimString+= str.substring(i,i+1);
            if(i != str.length()-1) delimString+= delimiter;
        }

            return delimString;

    }

    @Override
    public String toString() {
        String str = "";
        for(int i = 0; i < size(); i++){
            str+= elements[i];
        }
        return str;
    }

    /**
     * Fills the Stack with the elements of the ArrayList, First element in the ArrayList
     * is the first bottom element of the Stack
     * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE STACK, if you use the
     * list reference within your Stack, you will be allowing direct access to the data of
     * your Stack causing a possible security breech.
     *
     * @param list elements to be added to the Stack from bottom to top
     */
    @Override
    public void fill(ArrayList list) {
        ArrayList<Object> temp = new ArrayList<>();
            for(Object e: list){
                temp.add(e);
            }
            for(int i = 0; i < temp.size(); i++){
                elements[i] = (T) temp.get(i);
                top++;
            }
    }
}
