package File_IO;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Objects.*;
import java.io.*;

public class Categorized_OutTest {

	@Before
	public void setUp() throws Exception {
	
	}

	@Test
	public void testWriteToDatabase() {
		try {
		Categorized alreadyCategorized = new Categorized("test", "testDate");
		Case testCase = new Case();
		Case testCase2 = new Case();
		testCase.setCaseNumber(1);
		testCase2.setCaseNumber(2);
		alreadyCategorized.addToList(testCase);
		alreadyCategorized.addToList(testCase2);
		Categorized_Out.writeToDatabase("Test_Data.txt", alreadyCategorized);
		}
		catch(Exception e) {
			System.out.println("exception thrown");
		}
	}

}
