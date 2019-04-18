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
    System.out.println("Running Neural Network Demo 2");
    
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
    
    System.out.println("Running k-Fold-Cross-Validation:");
    System.out.println();
    
    for (int i = 0; i < 3; i+=2) {
    	System.out.println("10-Fold-Cross-Validation with " + i + " epoch(s):");
    	double[][] weights = k_Fold_Evaluation.kFoldAnalysis(newCases, 10, i);
    	if (i == 2) {
    		Neural network = new Neural();
    		network.setWeights(weights);
    		System.out.println();
    		System.out.println("Writing weights to file");
    		Categorized_Out.writeToFile("demo2Weights.txt", network);
    	}
    }
	
    System.out.println();
    System.out.println();

    
    System.out.println("Creating new, untrained network");
    Neural network = new Neural();

    // Get weights from file
    System.out.println("Reading Weights from File");
    Train_Neural.readWeightsFromFile(network);

    System.out.println();
    System.out.println();
    
    Case[] sampleCases = new Case[11];
    for (int i = 0; i < testArray.length; i+=70) {
    	sampleCases[i/70] = testArray[i];
    }
    
    
    // Run Network
    Run_Neural.assignCategories(network, sampleCases);
    ArrayList<double[]> outputs = new ArrayList<double[]>();
    for (int i = 0; i < sampleCases.length; i++) {
    	outputs.add(Run_Neural.runNetwork(network, sampleCases[i]));
    }
       
    // Print Results
    System.out.println("Case Number : Original Category - Network's Label");
    for (int i = 0; i < sampleCases.length; i++) {
    	System.out.println(sampleCases[i].getCaseNumber() + " : " + sampleCases[i].getOriginalCategory() +
    			" - " + sampleCases[i].getCategory());
    }  
   }
  }
