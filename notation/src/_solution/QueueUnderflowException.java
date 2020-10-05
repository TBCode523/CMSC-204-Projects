package _solution;
/**An Exception Class that is thrown when the Queue attempts to dequeue when there's nothing in the queue.
 *
 * @author Talal Brek
 *
 *
 */
public class QueueUnderflowException extends Exception{
    String message;
}