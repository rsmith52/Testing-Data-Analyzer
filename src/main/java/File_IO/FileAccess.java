package File_IO;

import java.io.File;

public class FileAccess {
	
	public static File getFile(String fileName) {
		try {
			File file = new File(FileAccess.class.getResource( "/Test_Data.txt" ).toURI());
			return file;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
		
	}

}
