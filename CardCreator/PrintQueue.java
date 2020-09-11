import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Homework 5 PrintQueue 
 * 
 * Implement the class below as specified in the homework 5 document.
 * 
 * @author jtk2eh
 */

// Don't forget to include the appropriate import statements

public class PrintQueue {

    private LinkedList<String> toPrint;     // the printer's list of documents
    private Lock documentChangeLock;  // a ReentrantLock lock
    private Condition hasPrintTask;   // a condition object
    private boolean isOn;             // boolean variable describing if the
                                      // queue is on (accepting jobs)

    // TODO: Handle locking and conditions around the
    // enqueueing and dequeuing of documents
    // in this PrintQueue's document list (toPrint)
    // Note: See example in the zip folder 'Thread Example 6 - Bank Deadlock'

    /**
     * Constructor
     */
    public PrintQueue() {
        toPrint = new LinkedList<String>(); // create the list of documents
        isOn = true; // turn on the print queue
        documentChangeLock = new ReentrantLock();// initialize the Reentrant Lock
        hasPrintTask = documentChangeLock.newCondition();// adding condition to documentChangeLock
    }

    /**
     * enqueue - add string to end of print queue 
     * 
     * TODO: Write more comments
     * 
     * @param s: string added to end of queue
     */
    public void enqueue(String s) {
        // lock it so only one thread adds to the queue at a time
        documentChangeLock.lock();
        try {
            toPrint.add(s); // add to the list of documents
            hasPrintTask.signalAll();
        }
        // when finished, unlock so other threads can now add to the queue
        finally {
            documentChangeLock.unlock();
        }
    }

    /**
     * dequeue - removes head element off the queue and return it 
     * 
     * TODO: Write more comments
     * 
     * @return element removed
     */
    public String dequeue() {
        // lock so only one thread can dequeue at a time
        documentChangeLock.lock();
        String removedItem = null;
        try {
            // if queue is empty, wait until it's not before dequeuing
            while (toPrint.isEmpty() && isOn) {
                hasPrintTask.await();
            }
            // remove element if queue not empty
            if (!toPrint.isEmpty()) {
                removedItem = toPrint.remove();
            }
        } catch (InterruptedException e) {

        }
        // unlock
        finally {
            documentChangeLock.unlock();
        }
        return removedItem;
    }

    /**
     * turnOff - turns off print queue so accept no more jobs
     */
    public void turnOff() {
        // lock
        documentChangeLock.lock();
        try {
            // change isOn to false and signalAll so that other methods know
            isOn = false;
            hasPrintTask.signalAll();
        }
        // unlock
        finally {
            documentChangeLock.unlock();
        }
    }

    /***
     * isOn
     * 
     * @return isOn: true if still accepting jobs and false otherwise
     */
    public boolean isOn() {
        return isOn;
    }
}
