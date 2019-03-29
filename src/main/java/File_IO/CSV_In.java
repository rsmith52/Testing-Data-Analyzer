package File_IO;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import Objects.Case;

public class CSV_In {
	
	public static ArrayList<Case> csvRead(File file) throws IOException
	{
		ArrayList<Case> cases = new ArrayList<Case>();
		Reader in = new FileReader(file);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		for (CSVRecord record : records) {
			Case myCase = new Case();
			myCase.setCaseNumber(Integer.parseInt(record.get("Incident ID")));
			myCase.setActualCategoriesKnown(false);
			myCase.setCaseOwner(record.get("Owned By"));
			myCase.setCaseRequestor(record.get("Customer Display Name"));
			myCase.setCategory(record.get("Category"));
			myCase.setDateRequested(record.get("Created Date Time"));
			myCase.setDescription(record.get("Description"));
			cases.add(myCase);
		}

		
		return cases;
	}
	
	public File getFile(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		
		return file;
	}
}
