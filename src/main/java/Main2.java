import Objects.*;
import Neural_Network.*;
import File_IO.*;
import java.util.*;
import java.io.*;

public class Main2 {

  // Demo Functionality for Iteration 1 Demo
  public static void main (String[] args) {
	  
	  
	  	// Create Neural Network
	    System.out.println("Creating network object");    
	    Neural network = new Neural();
	    
	    
	  	System.out.println("Reading in Test Data");
	    ArrayList<Case> newCases = new ArrayList<Case>();
	    File newFile = FileAccess.getFile("/categorizedCases.csv");
	    try {
	    	newCases = CSV_In.csvRead(newFile, true);
	    } catch (Exception e) {
	    	System.out.println(e);
	    }
	    Case[] testArray = new Case[newCases.size()];
	    for (int i = 0; i < testArray.length; i++) {
	    	testArray[i] = newCases.get(i);
	    }
	    
	    // Test to make sure data is right
	    for (int i = 284; i < 285; i++) {
	    	System.out.println("Case Number: " + testArray[i].getCaseNumber());
	    	System.out.println("Case Description: " + testArray[i].getDescription());
	    	System.out.println("Case Category: " + testArray[i].getCategory());
	    }
	    
	    
	    // Train network with cases, 100 epochs
	    System.out.println("Training network with training set 20 times");
	    Train_Neural.trainNeuralEpochs(network, testArray, 20);
	    System.out.println("Trained");
	    System.out.println("Saving Weights to File");
	    Train_Neural.saveWeightsToFile(network);
	    
	    // Run through trained network
	    ArrayList<double[]> finalResults = new ArrayList<double[]>();
	    for (int i = 0; i < testArray.length; i++) {
	    	finalResults.add(Run_Neural.runNetwork(network, testArray[i]));
	    }
	    
	    // Print Results
	    
	    for (int i = 0; i < finalResults.get(0).length; i++) {
	    	System.out.print(finalResults.get(0)[i] + " "); // 40% confident, but 0 on all else
	    }
	    System.out.println();
	    for (int i = 0; i < finalResults.get(284).length; i++) {
	    	System.out.print(finalResults.get(284)[i] + " "); // 99% confident
	    }
	    
	    /*
	    System.out.println();
	    System.out.println("Results of all Cases");
	    for (int i = 0; i < finalResults.size(); i++) {
	    	for (int j = 0; j < finalResults.get(i).length; j++) {
	    		System.out.print(finalResults.get(i)[j] + " ");
	    	}
	    	System.out.println();
	    }
	    */
	    
  
  
  }
  
}