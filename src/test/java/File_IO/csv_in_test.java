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
		File file = FileAccess.getFile("Bascom_Pull.csv");
		ArrayList<Case> cases = CSV_In.csvRead(file);
		Assert.assertEquals(1000374, cases.get(2).getCaseNumber());
	}

}
