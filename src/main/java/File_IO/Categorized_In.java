package File_IO;

import java.io.*;
import java.util.*;
import Objects.*;
import Neural_Network.*;

public class Categorized_In {
	
	public static Categorized readFromDatabase(String filename) {
		try {
			File database;
			database = FileAccess.createFile(filename);
			Categorized alreadyCategorized;
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
	
	public static Categorized readFromDatabase(File file) {
		try {
			File database = file;
			Categorized alreadyCategorized;
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
	
	public static Neural readFromFile(String filename) {
		try {
			Neural network;
			File database;
			database = FileAccess.createFile(filename);
			FileInputStream fin = new FileInputStream(database);
			ObjectInputStream in = new ObjectInputStream(fin);
			network = (Neural)in.readObject();
			in.close();
			fin.close();
			return network;
		}
		catch (IOException e) {
			System.err.print("Error reading from database: IO exception");
		} catch (ClassNotFoundException e) {
			System.err.print("Error reading from database: class not found");
		}
		return null;
	}
}