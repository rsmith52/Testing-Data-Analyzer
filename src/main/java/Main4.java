import Objects.*;
import Neural_Network.*;
import File_IO.*;
import java.util.*;
import java.io.*;

public class Main4 {

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
		
		
//		Case[][] splitCases = new Case[k][groupSize];
//		for (int i = 0; i < k; i++) {
//			for (int j = 0; j < groupSize; j++) {
//				splitCases.get(i).add(cases.get(permutation[i * groupSize + j]));
//			}
//		}
	    
	    for (int i = 0; i < 5; i++) {
	    	double error = k_Fold_Evaluation.kFoldAnalysis(newCases, 10, i);
	    	System.out.println(error);	    	
	    }
//	    for (int i = 0; i < error.length; i++) {
//	    	System.out.println(error[i]);
//	    }
//	    
//	    
////	    /*
//	    // Train network with cases, 100 epochs
//	    System.out.println("Training network with training set 1000 times");
//	    Train_Neural.trainNeuralEpochs(network, testArray[0], 10);
//	    System.out.println("Trained");
//	    System.out.println("Saving Weights to File");
//	    Train_Neural.saveWeightsToFile(network);
//	    
//	    // Run through trained network
//	    ArrayList<double[]> finalResults = new ArrayList<double[]>();
//	    for (int i = 0; i < testArray.length; i++) {
//	    	finalResults.add(Run_Neural.runNetwork(network, testArray[0][i]));
//	    }
//	    	   */ 

  }
  
}