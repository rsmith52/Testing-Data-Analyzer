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
        String[] tokenized = Tokenization.segmentation(parts[1]);
        Case thisCase = new Case(Integer.parseInt(parts[0]), "", "", 0, 0, parts[1], tokenized, "");
        thisCase.setActualCategoriesKnown(true);
        thisCase.setCategory(parts[2]);
        testData.add(thisCase);

      }
      scnr.close();
    } catch (Exception e) {
      System.out.print("Error reading from test data: ");
      System.out.println(e);
    }
    // Get Input Values
    for (Case thisCase: testData) {
    	thisCase.findOccurences(thisCase.getTokenizedDescription());
    }

    // Create Neural Network
    Neural network = new Neural();

    // Run a case through Network and get output
    double[] results = Run_Neural.runNetwork(network, testData.get(0));

    // Train network with 3 cases
    Case[] testArray = (Case[])testData.toArray();
    Train_Neural.trainNeural(network, testArray);

    // Run a case through trained network

    // Form a "Categorized" object of the 3 labeled cases


    /*
        Basic Use of CSV_In to show we can read from .csv files
    */



  }
  

}
