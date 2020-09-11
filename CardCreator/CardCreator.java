import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Homework 5 Card Creator 
 * 
 * This class defines the thread task that will "come up with" and submit greeting card ideas to
 * the print queue. We have added the code necessary to read from the file, but it's up to you to handle turning off the
 * printer (keeping track of how many threads are open) and adding the read-in line from the inspiration file to the
 * queue.
 * 
 * @author jtk2eh
 */
public class CardCreator implements Runnable {

    /**
     * Print queue to add new card ideas
     */
    private PrintQueue printQueue;

    /**
     * Inspiration file name
     */
    private String filename;

    /**
     * counter
     */
    private static int counter = 0;

    /**
     * lock for counter (shared variable between threads)
     */
    private Lock counterLock;

    /***
     * Constructor
     * 
     * @param d:        printQueue of card
     * @param filename: file where the verses will be taken from
     */
    public CardCreator(PrintQueue d, String filename) {
        printQueue = d;
        this.filename = filename;
        counterLock = new ReentrantLock();
    }

    /**
     * Run method that is the main method for the thread
     */
    @Override
    public void run() {
        // lock counter so only one thread can access at a time
        counterLock.lock();
        counter++;       // increase the counter for each thread
        counterLock.unlock();
        // instantiate scanner s
        Scanner s = null;
        try {
            // create new scanner that will read the contents in the file
            s = new Scanner(new FileReader(filename));
            while (s.hasNextLine()) {
                // TODO: Read the next line from the inspiration file
                String line = s.nextLine();
                // TODO: Enqueue the line into the print queue
                printQueue.enqueue(line);
                // sleep for 1000 milliseconds
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            System.out.println("Could not read file");
        } catch (InterruptedException ex) {
            System.out.println("Interrupted Exception caught");
        } finally {
            // close scanner if it's not null
            if (s != null)
                s.close();
            // lock so only one thread can decrement counter at a time
            counterLock.lock();
            counter--;             // decrement the counter
            counterLock.unlock();
            // TODO: Turn off the print queue (if applicable)
            // i.e., if you're the last card creator left
            if (counter == 0) {
                printQueue.turnOff();
            }
        }
    }

}
