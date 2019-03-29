package File_IO;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class CSV_In {

	public String[] testRead(String path) throws IOException
	{
		
		Reader in = new FileReader(path);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
		int i = 0;
		String description[] = new String[10000];
		for (CSVRecord record : records) {
		    description[i] = record.get("Description");
		}

	    System.out.println();
		
		return null;
	}
	
	public String[] testRead(File file) throws IOException
	{
		
		Reader in = new FileReader(file);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.parse(in);
		int i = 0;
		String description[] = new String[100000];
		for (CSVRecord record : records) {
		    description[i] = record.get(6);
		    i++;
		}

	    System.out.println(description[3]);
		
		return null;
	}
	
	public File getFile(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		
		return file;
	}
}
