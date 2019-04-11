package File_IO;

import java.io.*; 

public class Tokenization {

  // I feel like toLowerCase() should just be called right when we have input...

  // segmentation: separate words, remove punctuation -> Array/List of tokens. 
  // Also makes things lower case
  public static String[] segmentation(String description) {
    String[] segmentDescription = description.replaceAll("[\\p{Punct}&&[^-']]", "").toLowerCase().split("\\s+");

    // testing out the segmentation
    /*
    for (int i = 0; i < segmentDescription.length; i++)
      System.out.println(segmentDescription[i]);
    */

    return segmentDescription;
  }

}