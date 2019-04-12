package File_IO;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import Objects.Case;

public class CSV_Out {
	
	public static void writeCSV(ArrayList<Case> cases) throws IOException
	{
		File file;
		BufferedWriter writer;
		CSVPrinter csvprint;
		try {
			file = FileAccess.createFile("dataOut.csv");
			writer = new BufferedWriter(new FileWriter(file));
			csvprint = new CSVPrinter(writer, CSVFormat.DEFAULT .withHeader("Incident ID", 
																			"Owned By", 
																			"Customer Display Name", 
																			"Category", 
																			"Created Date Time", 
																			"Description"));
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		
		for(Case data : cases)
		{
			csvprint.printRecord(data.getCaseNumber(),
								 data.getCaseOwner(),
								 data.getCaseRequestor(),
								 data.getCategory(), 
								 data.getDateRequested(),
								 data.getDescription());
		}
		
		csvprint.flush();
		csvprint.close();
		writer.close();
		
		
		
	}
}
