package File_IO;

import java.io.*;
import java.util.*;
import Objects.*;

public class Categorized_In implements Serializable{
	static final long serialVersionUID = 10;
	
	
	public static Categorized readFromDatabase(File filename) {
		try {
			Categorized alreadyCategorized = new Categorized();
			FileInputStream fin = new FileInputStream(filename);
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
}