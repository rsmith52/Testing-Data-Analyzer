///////////////////////////////////////////////////////////////////////////////
//
// Title:            WiscIT Data Analyzer
// Semester:         Spring 2019
//
// Author:           Matthew T. Duff
//
///////////////////////////////////////////////////////////////////////////////

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
	
	/*
	 * csvRead takes in a file, uses apache commons csv reader to store the values into a Case object
	 * then puts that Case into an ArrayList which is returned.
	 * 
	 * @param file is the expected 
	 * @return ArrayList<Case> filled with all of the cases stored within the csv file
	 */
	public static ArrayList<Case> csvRead(File file) throws IOException
	{
		//Variable declarations
		ArrayList<Case> cases = new ArrayList<Case>();
		Reader in = new FileReader(file);
		Iterable<CSVRecord> records = CSVFormat.EXCEL.withHeader().parse(in);
		Case myCase;
		
		//For each loop, for every entry in csv, store into case
		for (CSVRecord record : records) {
			myCase = new Case();
			//stores by column header
			myCase.setCaseNumber(Integer.parseInt(record.get("Incident ID")));
			myCase.setActualCategoriesKnown(false);
			myCase.setCaseOwner(record.get("Owned By"));
			myCase.setCaseRequestor(record.get("Customer Display Name"));
			myCase.setCategory(record.get("Category"));
			myCase.setDateRequested(record.get("Created Date Time"));
			myCase.setDescription(record.get("Description"));

			//stores into ArrayList
			cases.add(myCase);
		}
		in.close();

		return cases;
	} //end csvRead

} //end CSV_In
