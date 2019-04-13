package File_IO;
import java.io.*;
import Objects.*;
import java.util.*;
import Neural_Network.*;


public class Categorized_Out implements Serializable{
	static final long serialVersionUID = 10;
	
	public static void writeToDatabase(String filename, Categorized alreadyCategorized) {
		try {
			File database;
			database = FileAccess.createFile(filename);
			FileOutputStream fout = new FileOutputStream(database);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(alreadyCategorized);
			out.close();
			fout.close();
		}
		catch(IOException e) {
			System.err.print("Error writing to database: IO exception" + e);
		} 
		
	}
	
	public static void writeToFile(String filename, Neural network) {
		try {
			File database;
			database = FileAccess.createFile(filename);
			FileOutputStream fout = new FileOutputStream(database);
			ObjectOutputStream out = new ObjectOutputStream(fout);
			out.writeObject(network);
			System.out.println("Wrote to file");
			out.close();
			fout.close();
		}
		catch (IOException e) {
			System.err.print("Error writing to database: IO exception");
			System.err.print(e);
		} 
		
	}
}

