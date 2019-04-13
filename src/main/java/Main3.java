import Objects.*;
import Neural_Network.*;
import File_IO.*;
import java.util.*;
import java.io.*;

public class Main3 {

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
	    
	    // Get weights from file
	    System.out.println("Reading Weights from File");
	    Train_Neural.readWeightsFromFile(network);
	    
	    
	    
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
  }
  
}