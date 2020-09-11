//package examples.junit;               // update this line based on where your test file is

//import sut.CustomizedHangman;         // update this line based on where your program under test is

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CustomizedHangmanTest 
{
	private CustomizedHangman hangman;
	private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
     
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }
    
    @AfterEach
        public void tearDown() {
            System.setOut(standardOut);
    }

	@Test
	void testCreateCustomizedHangmanWithoutSticks() {
	   hangman=new CustomizedHangman("Humpty");
	   assertAll(
	           ()->assertEquals("Humpty", hangman.getName()),
	           ()->assertEquals(5,hangman.getSticksRemaining())
	           );
	}
	
	@Test
	void testCreateHangmanWithSticks() {
	    hangman=new CustomizedHangman("Humpty",7);
	    assertAll(
	            ()->assertEquals("Humpty", hangman.getName()),
	            ()->assertEquals(7, hangman.getSticksRemaining())        
	            );
	}
	
	@Test
	void testIsDead() {
	    hangman=new CustomizedHangman("Humpty",0);
	    assertTrue(hangman.isDead());
	}
	
	@Test 
	void testLostStick() {
	    hangman=new CustomizedHangman("Humpty",0);
	    hangman.lostStick();
	    assertEquals("Humpty has no sticks left to lose!", outputStreamCaptor.toString());
	}

}
