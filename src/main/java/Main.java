import Objects.*;
import Neural_Network.*;
import File_IO.*;
import java.util.*;
import java.io.*;

public class Main {

  // Demo Functionality for Iteration 1 Demo
  public static void main (String[] args) {
    System.out.println("Hello, Welcome to Data Analyzer for DoIT");

    /*
        Basic Running of Neural Network with Input Data to Show Network Produces Output and Training Can Be Done
    */
    System.out.println("Running Neural Network Demo");
    ArrayList<Case> testData = new ArrayList<Case>();
    // Fill List with Data from Test_Data.txt
    try {
      File testFile = FileAccess.getFile("Test_Data.txt");
      Scanner scnr = new Scanner(testFile);
      while (scnr.hasNextLine()) {
        String line = scnr.nextLine();
        String[] parts = line.split(",");
        String[] tokenized = Tokenization.segmentation(parts[1]);
        Case thisCase = new Case(Integer.parseInt(parts[0]), "", "", "", parts[1], tokenized, "");
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
    	thisCase.findOccurrences(thisCase.getTokenizedDescription());
    }

    // Create Neural Network
    System.out.println("Creating network object");
    Neural network = new Neural();

    // Run a case through Network and get output
    System.out.println("Running a single case through untrained network");
    double[] results = Run_Neural.runNetwork(network, testData.get(0));
    for (int i = 0; i < results.length; i++) {
    	System.out.print(results[i] + " ");
    }
    System.out.println();
    
    // Testing Network Error
    System.out.println("Testing for network error against the 3 test cases");
    for (int i = 0; i < testData.size(); i++) {
    	System.out.println("Case: " + testData.get(i).getDescription());
    	// Get the output of the network
	    double[] networkOutput = Run_Neural.runNetwork(network, testData.get(i));
	    System.out.print("Network Output: ");
	    for (int j = 0; j < networkOutput.length; j++) {
	    	System.out.print(networkOutput[j] + " ");
	    }
	    System.out.println();
	    // Get the correct output
	    double[] correctOutput = testData.get(i).getLabelsIfKnown();
	    System.out.print("Correct Output: ");
	    for (int j = 0; j < correctOutput.length; j++) {
	    	System.out.print(correctOutput[j] + " ");
	    }
	    System.out.println();
	    System.out.print("Network Error: ");
	    System.out.println();
    }
    

    // Train network with 3 cases
    //System.out.println("Training network with tiny training set 1000 times");
    Case[] testArray = new Case[testData.size()];
    for (int i = 0; i < testArray.length; i++) {
    	testArray[i] = testData.get(i);
    }
    Train_Neural.trainNeuralEpochs(network, testArray, 1);

    // Run a case through trained network
    //System.out.println("Running the cases through the trained network");
    Categorized categorized = new Categorized("Test List", 0);
    double[][] finalResults = new double[3][20];
    finalResults[0] = Run_Neural.runNetwork(network, testData.get(0));
    finalResults[1] = Run_Neural.runNetwork(network, testData.get(1));
    finalResults[2] = Run_Neural.runNetwork(network, testData.get(2));
    for (int j = 0; j < finalResults.length; j++) {
    	for (int i = 0; i < results.length; i++) {
        	//System.out.print(finalResults[j][i] + " ");
        }
        //System.out.println();
    }


    /*
        Basic Use of CSV_In to show we can read from .csv files
    */
    
    //System.out.println("CSV Reading Demo");



  }
  

}
