package File_IO;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import Objects.Case;
import junit.framework.Assert;

public class csv_in_test {

	@Test
	public void testTestRead() throws IOException {
		CSV_In test = new CSV_In();
		File file = test.getFile("Bascom_Pull.csv");
		ArrayList<Case> cases = test.csvRead(file);
		
		
		Assert.assertEquals(1000374, cases.get(2).getCaseNumber());
	}

}
