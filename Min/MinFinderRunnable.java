import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MinFinderRunnable implements Runnable
{
	/* These are the class variables in MinFinderRunnable.
	 * The MinFinalRunnable class reads the value at min, so be sure to update it as you use the run() method.
	 */
	String filename;
	int min;

	/* This is the constructor for the MinFinderRunnable class.
	 * It takes in a String for the filename so that the run() method can have its Scanner instance read that file.
	 * It should also initialize min, to avoid NullPointerExceptions when the run() method is called.
	 */
	public MinFinderRunnable(String filename)
	{
	    this.filename=filename;
	    min=1000;
	}


	/* This is the run method for the MinFinderRunnable class.
	 * It starts with a Scanner class, to read in the file with the String filename.
	 * Then it should go through the file & set class variable min to the minimum value of the file.
	 */
	@Override
	public void run()
	{
		try {
			Scanner reader;
			reader = new Scanner(new File(filename));
			// WRITE YOUR CODE HERE
			while(reader.hasNext()) {
		        Integer i=Integer.parseInt(reader.next());
                if(i<min) {
                    min=i;
                    System.out.println("Testing "+ i+" is less than "+ min +" on file "+filename);
			    }
			}
			System.out.println("Global min value is "+ min+".");
			reader.close();
		}
		catch (FileNotFoundException e)
		{
			System.err.println("ERROR: File " + filename + " not found.");
		}
	}
}
