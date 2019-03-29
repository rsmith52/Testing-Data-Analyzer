package File_IO;

import java.io.File;

public class FileAccess {
	
	public File getFile(String fileName) {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource(fileName).getFile());
		
		return file;
	}

}
