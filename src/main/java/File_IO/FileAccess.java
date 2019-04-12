package File_IO;

import java.io.File;
import java.io.IOException;

public class FileAccess {
	
	public static File getFile(String fileName) {
		try {
			File file = new File(FileAccess.class.getResource(fileName).toURI());
			return file;
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return null;
		
	}
	
	/*
	 * Error List:
	 * 	pathname is a directory : throw an IOException
	 * 	pathname is null : throw an IOException
	 * 	pathname is already a file : return existing file
	 * 
	 */
	public static File createFile(String pathname) throws IOException
	{
		File file;
		
		//if null is passed in
		if(pathname == null)
		{
			throw new IOException("Null Path");
		}
		try {
			file = new File(pathname);
			if (!file.isFile())
			{
				throw new IOException("Directory");
			}
		} catch(Exception e) {
			throw e;
		}
		
		return file;
		
}

}
