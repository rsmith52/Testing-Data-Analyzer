package File_IO;
import java.io.*;
import Objects.*;
import java.util.*;


public class Categorized_Out implements Serializable{
	static final long serialVersionUID = 10;
	
	public static void writeToDatabase(String filename, Categorized alreadyCategorized) {
		try {
			File database;
			database = FileAccess.createFile(filename);
			FileOutputStream fout = new FileOutputStream(database);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(alreadyCategorized.caseList);
			out.close();
			fout.close();
		}
		catch(IOException e) {
			System.err.print("Error writing to database: IO exception");
		} 
		
	}
}
