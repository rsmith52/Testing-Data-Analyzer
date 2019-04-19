import Objects.*;
import File_IO.*;
import Neural_Network.*;

import java.util.*;
import java.io.*;

public class Main {

  // Demo Functionality for Iteration 2 Demo
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
    
    Neural network = new Neural();
    double[][] weights = k_Fold_Evaluation.kFoldAnalysis(newCases, 10, 800);
    network.setWeights(weights);
    Train_Neural.saveWeightsToFile(network);
    
    System.out.println("Running k-Fold-Cross-Validation:");
    System.out.println();
    
  }
  
}
