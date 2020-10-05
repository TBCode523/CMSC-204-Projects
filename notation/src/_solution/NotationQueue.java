package _solution;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.LinkedList;
/** A Queue that specializes in Converting Notations
 *
 * @author Talal Brek
 *
 * @param <T> data type
 */
public class NotationQueue<T> implements QueueInterface<T>{
    NotationNode firstNode;
    NotationNode lastNode;
    int capacity;
    int size;
    /**
     * Determines if Queue is empty
     *
     * @return true if Queue is empty, false if not
     */
    NotationQueue(int capacity){
        firstNode = null;
        lastNode = null;
        this.capacity = capacity;
        size = 0;
    }

    public boolean isEmpty() {
        return firstNode == null;
    }

    /**
     * Determines of the Queue is empty
     *
     * @return
     */
    @Override
    public boolean isFull() {
      return size == capacity;
    }

    /**
     * Deletes and returns the element at the front of the Queue
     *
     * @return the element at the front of the Queue
     */
    @Override
    public T dequeue() throws QueueUnderflowException {
        if(isEmpty()) throw new QueueUnderflowException();
        else{
            T front = firstNode.getData();
            firstNode.setData(null);
            firstNode = firstNode.getNext();
            if( firstNode == null) lastNode = null;
            size--;
            return front;
        }

    }

    /**
     * Number of elements in the Queue
     *
     * @return the number of elements in the Queue
     */
    @Override
    public int size() {
        return size;
    }

    public boolean enqueue(T a) throws QueueOverflowException {
        NotationNode newNode = new NotationNode(a);
        if(isFull()) {

            throw new QueueOverflowException();
        }
       else if(isEmpty()){
           firstNode = newNode;

       }
        else{ lastNode.setNextNode(newNode);}
        lastNode = newNode;
        size++;
        return true;
    }

    /**
     * Returns the string representation of the elements in the Queue,
     * the beginning of the string is the front of the queue
     *
     * @return string representation of the Queue with elements
     */
    @Override
    public String toString() {
        String str = "";
        NotationNode n = firstNode;
        while(n != null){
            str+= n.getData();
            n = n.getNext();
        }
        return str;
    }

    /**
     * Returns the string representation of the elements in the Queue, the beginning of the string is the front of the queue
     * Place the delimiter between all elements of the Queue
     *
     * @param delimiter
     * @return string representation of the Queue with elements separated with the delimiter
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

    /**
     * Fills the Queue with the elements of the ArrayList, First element in the ArrayList
     * is the first element in the Queue
     * YOU MUST MAKE A COPY OF LIST AND ADD THOSE ELEMENTS TO THE QUEUE, if you use the
     * list reference within your Queue, you will be allowing direct access to the data of
     * your Queue causing a possible security breech.
     *
     * @param list elements to be added to the Queue
     */
    @Override
    public void fill(ArrayList<T> list) {
        for(T data: list){
            try {
                enqueue(data);
            } catch (QueueOverflowException e) {
                e.printStackTrace();
            }
        }
    }
    private class NotationNode {
        T data;
        NotationNode next;
        private NotationNode(T dataentry){
            data = dataentry;
            next = null;
        }
        private NotationNode(T dataentry, NotationNode nextNode){
            data = dataentry;
            next = nextNode;
        }

        public void setNextNode(NotationNode newNode) {
            next = newNode;
        }

        public void setData(T data) {
            this.data = data;
        }

        public NotationNode getNext() {
            return next;
        }
        public boolean hasNext(){
            return next != null;
        }

        public T getData() {
            return data;
        }
    }

}
