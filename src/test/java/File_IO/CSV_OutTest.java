package File_IO;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import Objects.Case;

public class CSV_OutTest {
	
	@Test
	public void testWriteCSV() {
		try
		{
			File file = FileAccess.getFile("/Bascom_Pull.csv");
			ArrayList<Case> cases = CSV_In.csvRead(file);
			CSV_Out.writeCSV(cases);
		} catch (IOException e)
		{
			fail("IOException Thrown");
		}
	}

}
