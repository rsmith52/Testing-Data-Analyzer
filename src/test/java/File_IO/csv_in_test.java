package File_IO;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

public class csv_in_test {

	@Test
	public void testTestRead() throws IOException {
		CSV_In test = new CSV_In();
		File file = test.getFile("Bascom_Pull.csv");
		test.testRead(file);
	}

}
