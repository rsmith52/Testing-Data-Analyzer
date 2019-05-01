package File_IO;

import static org.junit.Assert.*;
import org.junit.Test;

import Neural_Network.Neural;
import Objects.Categorized;

public class Categorized_InTest {

	
	@Test
	public void testReadFromFile() {
		Categorized cat = new Categorized();
		cat  = Categorized_In.readFromDatabase("Data Set 1.cat");
		assertNotNull("File wasn't read", cat);
	}
}
