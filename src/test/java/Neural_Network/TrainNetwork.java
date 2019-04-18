package Neural_Network;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import File_IO.CSV_In;
import File_IO.FileAccess;
import Objects.Case;

public class TrainNetwork {
	
	final static int NUM_EPOCHS = 20;

	@Test
	public void test() {
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
	    
	    // Train network with cases, 1000 epochs
	    System.out.println("Training network with training set " + NUM_EPOCHS + " times");
	    Train_Neural.trainNeuralEpochs(network, testArray, NUM_EPOCHS);
	    System.out.println("Trained");
	    System.out.println("Saving Weights to File");
	    Train_Neural.saveWeightsToFile(network);
	}

}
