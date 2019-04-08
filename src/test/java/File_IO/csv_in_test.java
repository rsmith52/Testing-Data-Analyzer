package File_IO;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.junit.Test;

import Objects.Case;
import junit.framework.Assert;

public class csv_in_test {

	@Test

	public void testCsvRead() throws IOException, URISyntaxException {
		File file = FileAccess.getFile("/Bascom_Pull.csv");
		ArrayList<Case> cases = CSV_In.csvRead(file);
		assertEquals(1000374, cases.get(2).getCaseNumber());
		assertEquals("William Crickman", cases.get(2).getCaseOwner());
		assertEquals("CHRISTOPHER J VERHAEGHE", cases.get(2).getCaseRequestor());
		assertEquals("Endpoint Management (EPM)", cases.get(2).getCategory());
		assertEquals("8/4/2014 10:07", cases.get(2).getDateRequested());
		assertEquals("Customer needs help installing some fonts onto his machine.", cases.get(2).getDescription());

	}
	

}
