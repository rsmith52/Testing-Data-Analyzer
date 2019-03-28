import Objects.*;
import java.util.*;
import java.io.*;

public class Main {

  // Demoing Functionality for Iteration 1 Demo
  public static void main (String[] args) {
    System.out.println("Hello, Welcome to Data Analyzer for DoIT");

    /*
        Basic Running of Neural Network with Input Data to Show Network Produces Output and Training Can Be Done
    */
    ArrayList<Case> testData = new ArrayList<Case>();
    try {
      File testFile = new File("./Database/Test_Data.txt");
      Scanner scnr = new Scanner(testFile);
      while (scnr.hasNextLine()) {
        String line = scnr.nextLine();
        String[] parts = line.split(",");
        Case thisCase = new Case(Integer.parseInt(parts[0]), "", "", 0, 0, parts[1], "", "");
        testData.add(thisCase);
        
      }
    } catch (Exception e) {

    }

  }

}
