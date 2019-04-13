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
    for (int i = 0; i < 5; i++) {
    	System.out.println("k-Fold-Cross-Validation with " + i + " epoch(s):");
    	double[][] weights = k_Fold_Evaluation.kFoldAnalysis(newCases, 10, i);
//        System.out.println();
    }
   }
  }
