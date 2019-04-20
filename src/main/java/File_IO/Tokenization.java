package File_IO;

public class Tokenization {
	
  // segmentation: separate words, remove punctuation -> Array/List of tokens. 
  // Also makes things lower case
  public static String[] segmentation(String description) {
    String[] segmentDescription = description.replaceAll("[\\p{Punct}&&[^-']]", "").toLowerCase().split("\\s+");

    return segmentDescription;
  }

}