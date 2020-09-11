//package sut;      // update this line based on where your file is

public class CustomizedHangman 
{
   private String name;
   private int sticksRemaining; 
    
   public CustomizedHangman(String name)
   {
      this.name = name;
      sticksRemaining = 5;    
   }
   
   public CustomizedHangman(String name, int sticks)
   {
      this.name = name;
      sticksRemaining = sticks;
   }
   
   public String getName()
   {
      return name;
   }
   
   public void setName(String nm)
   {
      name = nm;
   }
   
   public int getSticksRemaining()
   {
      return sticksRemaining;
   }
   
   public boolean isDead()
   {
      return (sticksRemaining < 1);        // dead: lost all sticks
   }
   
   public void lostStick()
   {
      //if (sticksRemaining == 0)            // correct version
      if (sticksRemaining < 0)             // faulty version        	  
         System.out.println(name + " has no sticks left to lose!");
      else
      {
         sticksRemaining--;
         System.out.println(name + " says, 'I lost my stick! \'");
      }
   }
   
}
