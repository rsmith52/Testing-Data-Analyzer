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
      File testFile = FileAccess.getFile("/Test_Data.txt");
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
    System.out.println();
    
    // Testing Network Error
    System.out.println("Testing for network error against the test cases");
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
	    
	    // Get overall network error
	    System.out.print("Network Error: ");
	    double[] networkError = Train_Neural.networkError(networkOutput, correctOutput);
	    for (int j = 0; j < networkError.length; j++) {
	    	System.out.print(networkError[j] + " ");
	    }
	    System.out.println();
	    
	    // Get output error with respect to output layer
	    System.out.print("Output Layer Error: ");
	    double[] outputLayerError = Train_Neural.outputLayerError(networkOutput, correctOutput);
	    for (int j = 0; j < outputLayerError.length; j++) {
	    	System.out.print(outputLayerError[j] + " ");
	    }
	    System.out.println();
	    
	    // Get error from second layer data
	    System.out.print("Second Layer Data Error: ");
	    double[] outputLayerDataError = Train_Neural.secondLayerDataError(network, networkOutput, correctOutput);
	    for (int j = 0; j < outputLayerDataError.length; j++) {
	    	System.out.print(outputLayerDataError[j] + " ");
	    }
	    System.out.println();
	    System.out.println();
	    
    }
    System.out.println();
    
    // Train network with 4 cases, 20 epochs
    System.out.println("Training network with tiny training set 20 times");
    Case[] testArray1 = new Case[testData.size()];
    for (int i = 0; i < testArray1.length; i++) {
    	testArray1[i] = testData.get(i);
    }
    Train_Neural.trainNeuralEpochs(network, testArray1, 20);

    // Run the cases through trained network
    System.out.println("Running the cases through the trained network");
    System.out.println("Shows the chance a case fits a label, indexed by outputs.txt");
    ArrayList<double[]> finalResults1 = new ArrayList<double[]>();
    for (int j = 0; j < testArray1.length; j++) {
    	finalResults1.add(Run_Neural.runNetwork(network, testArray1[j]));
    	for (int i = 0; i < results.length; i++) {
        	System.out.print(finalResults1.get(j)[i] + " ");
        }
        System.out.println();
    }
    System.out.println();
    
    // Train network with 4 cases, 50 epochs
    System.out.println("Training network with tiny training set 50 times");
    Case[] testArray2 = new Case[testData.size()];
    for (int i = 0; i < testArray2.length; i++) {
    	testArray2[i] = testData.get(i);
    }
    Train_Neural.trainNeuralEpochs(network, testArray2, 50);

    // Run the cases through trained network
    System.out.println("Running the cases through the trained network");
    System.out.println("Shows the chance a case fits a label, indexed by outputs.txt");
    ArrayList<double[]> finalResults2 = new ArrayList<double[]>();
    for (int j = 0; j < testArray2.length; j++) {
    	finalResults2.add(Run_Neural.runNetwork(network, testArray2[j]));
    	for (int i = 0; i < results.length; i++) {
        	System.out.print(finalResults2.get(j)[i] + " ");
        }
        System.out.println();
    }
    System.out.println();
    

    // Train network with 4 cases, 1000 epochs
    System.out.println("Training network with tiny training set 1000 times");
    Case[] testArray = new Case[testData.size()];
    for (int i = 0; i < testArray.length; i++) {
    	testArray[i] = testData.get(i);
    }
    Train_Neural.trainNeuralEpochs(network, testArray, 1000);

    // Run the cases through trained network
    System.out.println("Running the cases through the trained network");
    System.out.println("Shows the chance a case fits a label, indexed by outputs.txt");
    ArrayList<double[]> finalResults = new ArrayList<double[]>();
    for (int j = 0; j < testArray.length; j++) {
    	finalResults.add(Run_Neural.runNetwork(network, testArray[j]));
    	for (int i = 0; i < results.length; i++) {
        	System.out.print(finalResults.get(j)[i] + " ");
        }
        System.out.println();
    }
    System.out.println();
    System.out.println();


    
    /*
        Basic Use of CSV_In to show we can read from .csv files
    */
    
    System.out.println("CSV Reading Demo");
    ArrayList<Case> cases = new ArrayList<Case>();
    File dataPull = FileAccess.getFile("/Bascom_Pull.csv");
    try {
    	System.out.println("Reading in data from Bascom Pull to an ArrayList of cases");
    	cases = CSV_In.csvRead(dataPull);
    	System.out.println("Reading a few random cases");
    	int[] casesToDemo = new int[] {1, 20, 56, 242};
    	for (int i = 0; i < casesToDemo.length; i++) {
    		System.out.print("Case Number: " + cases.get(casesToDemo[i]).getCaseNumber() + " ");
    		System.out.print("Description: " + cases.get(casesToDemo[i]).getDescription());
    		System.out.println();
    	}
    } catch (Exception e) {
    	System.out.println(e);
    }   

    
    /*
    System.out.println("Reading in Test Data");
    ArrayList<Case> newCases = new ArrayList<Case>();
    File newFile = FileAccess.getFile("/categorizedCases.csv");
    try {
    	newCases = CSV_In.csvRead(newFile, true);
    } catch (Exception e) {
    	System.out.println(e);
    }   

    // Train network with 4 cases, 1000 epochs
    System.out.println("Training network with tiny training set 1000 times");
    Case[] testArray7 = new Case[newCases.size()];
    for (int i = 0; i < testArray7.length; i++) {
    	testArray7[i] = newCases.get(i);
    }
    
    
    Train_Neural.trainNeuralEpochs(network, testArray7, 100);

    // Run the cases through trained network
    network = new Neural();
    System.out.println("Running the cases through the trained network");
    System.out.println("Shows the chance a case fits a label, indexed by outputs.txt");
    //ArrayList<double[]> finalResults = new ArrayList<double[]>();
    for (int j = 0; j < testArray7.length; j++) {
    	finalResults.add(Run_Neural.runNetwork(network, testArray7[j]));
    	for (int i = 0; i < 10; i++) {
        	System.out.print(finalResults.get(j)[i] + " ");
        }
        System.out.println();
    }
    */
    
  }

}
