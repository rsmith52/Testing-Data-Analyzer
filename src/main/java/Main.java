import Objects.*;
import Neural_Network.*;
import File_IO.*;
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
    // Fill List with Data from Test_Data.txt
    try {
      File testFile = FileAccess.getFile("Test_Data.txt");
      Scanner scnr = new Scanner(testFile);
      while (scnr.hasNextLine()) {
        String line = scnr.nextLine();
        String[] parts = line.split(",");
        System.out.println(parts[1]);
        Case thisCase = new Case(Integer.parseInt(parts[0]), "", "", 0, 0, parts[1], new String[] {}, "");
        testData.add(thisCase);

      }
      scnr.close();
    } catch (Exception e) {
      System.out.print("Error reading from test data: ");
      System.out.println(e);
    }
    // Tokenize descriptions
    
    // Get Input Values

    // Create Neural Network

    // Run a case through Network and get output

    // Train network with 3 cases

    // Run a case through trained network

    // Form a "Categorized" object of the 3 labeled cases


    /*
        Basic Use of CSV_In to show we can read from .csv files
    */



  }
  

}
