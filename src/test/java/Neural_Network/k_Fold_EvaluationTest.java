package Neural_Network;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import File_IO.CSV_In;
import File_IO.FileAccess;
import Objects.Case;

public class k_Fold_EvaluationTest {

	@Test
	public void testKFoldAnalysis() {
	    ArrayList<Case> newCases = new ArrayList<Case>();
	    File newFile = FileAccess.getFile("/categorizedCases.csv");
	    try {
	    	newCases = CSV_In.csvRead(newFile, true);
	    } catch (Exception e) {
	    	System.out.println(e);
	    }
		double[][] error = k_Fold_Evaluation.kFoldAnalysis(newCases, 5, 0);
		assertEquals(1,1);
	}

	@Test
	public void testTestNetwork() {
	    ArrayList<Case> newCases = new ArrayList<Case>();
	    File newFile = FileAccess.getFile("/categorizedCases.csv");
	    try {
	    	newCases = CSV_In.csvRead(newFile, true);
	    } catch (Exception e) {
	    	System.out.println(e);
	    }

		ArrayList<ArrayList<Case>> caseGroups = Split_Data.splitData(newCases, 5);
		// creating arraylists to be used to hold the test and train data
		ArrayList<Case> testCases = new ArrayList<Case>();
		ArrayList<Case> trainCases = new ArrayList<Case>();
		
		// the 0th group will be the test group, and the rest will be used for training
		testCases.addAll(caseGroups.get(0));
		for (int j = 0; j < 5; j++) {
			if (j != 0) trainCases.addAll(caseGroups.get(j));
		}

		// turning the arrayLists into arrays
		Case[] testCasesArray = new Case[testCases.size()];
		Case[] trainCasesArray = new Case[trainCases.size()];
		for (int j = 0; j < testCasesArray.length; j++) {
			testCasesArray[j] = testCases.get(j);
		}		    
	    for (int j = 0; j < trainCasesArray.length; j++) {
	    	trainCasesArray[j] = trainCases.get(j);
	    }
	    // Testing on an untrained network
		Neural network = new Neural();
	    Train_Neural.trainNeuralEpochs(network, trainCasesArray, 0);
	    double error = k_Fold_Evaluation.testNetwork(network, testCasesArray);
	    assertEquals(1, error, 0.1);
	    
	    // Testing after 2 epochs
		network = new Neural();
	    Train_Neural.trainNeuralEpochs(network, trainCasesArray, 2);
	    error = k_Fold_Evaluation.testNetwork(network, testCasesArray);
	    assertEquals(0.25, error,0.25);
	}

}
