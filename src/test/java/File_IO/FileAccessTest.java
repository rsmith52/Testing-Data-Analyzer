package File_IO;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class FileAccessTest {

	@Test
	public void testGetFile() {
		File file = FileAccess.getFile("/inputs.txt");
		if(file == null)
		{
			fail("Null object returned from getFile");
		}
	}
	
	@Test
	public void testGetFileException() {
		File file = FileAccess.getFile("/definitelyDoesNotExist.txt");
		if(file != null)
		{
			fail("Null object returned from getFile");
		}
	}


	@Test
	public void testCreateFile() {
		File file = null;
		try {
			file = FileAccess.createFile("weights.txt");
		} catch (IOException e) {
			fail("IOException thrown");
		}
		if(file == null)
		{
			fail("Null object returned from getFile");
		}
	}
	
	@Test
	public void testCreateFileNull() {
		File file = null;
		try {
			file = FileAccess.createFile(null);
		} catch (IOException e) {
			//Expected
		}
		if(file != null)
		{
			fail("Null object not returned from getFile");
		}
	}

}
