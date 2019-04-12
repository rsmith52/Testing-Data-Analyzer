package File_IO;

import java.io.*;
import java.util.*;
import Objects.*;

public class Categorized_In implements Serializable{
	static final long serialVersionUID = 10;
	
	
	public static Categorized readFromDatabase(String filename) {
		try {
			File database;
			database = FileAccess.getFile(filename);
			Categorized alreadyCategorized = new Categorized();
			FileInputStream fin = new FileInputStream(database);
			ObjectInputStream in = new ObjectInputStream(fin);
			alreadyCategorized = (Categorized)in.readObject();
			in.close();
			fin.close();
			return alreadyCategorized;
		}
		catch(IOException e) {
			System.err.print("Error reading from database: IO exception");
		} catch (ClassNotFoundException e) {
			System.err.print("Error reading from database: class not found");
		}
		return null;
	}
	
	public static double[][] readFromFile(String filename) {
		try {
			double[][] array;
			File database;
			database = FileAccess.getFile(filename);
			FileInputStream fin = new FileInputStream(database);
			ObjectInputStream in = new ObjectInputStream(fin);
			array = (double[][])in.readObject();
			in.close();
			fin.close();
			return array;
		}
		catch(IOException e) {
			System.err.print("Error reading from database: IO exception");
		} catch (ClassNotFoundException e) {
			System.err.print("Error reading from database: class not found");
		}
		return null;
	}
}