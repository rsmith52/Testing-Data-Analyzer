package Neural_Network;

import java.util.ArrayList;
//import Neural_Network.*;

import Objects.Case;

public class k_Fold_Evaluation {
	
	// this will return approximately 0.125 without any training
	public static double[][] kFoldAnalysis(ArrayList<Case> cases, int k, int numEpochs) {
		// this is just so we get getWeights to dynamically get the number of weights
		Neural throwAwayNetwork = new Neural();
		double[][] throwAwayWeights = throwAwayNetwork.getWeights();
		double[][][] weights = new double[k][throwAwayWeights.length][throwAwayWeights[0].length];
		double[] overallErrors = new double[k];
		ArrayList<ArrayList<Case>> caseGroups = Split_Data.splitData(cases, k);
	
		for (int i = 0; i < k; i++) {
			// creating arraylists to be used to hold the test and train data
//			System.out.println("Testing the network with group " + i + " as the testing data.");
			ArrayList<Case> testCases = new ArrayList<Case>();
			ArrayList<Case> trainCases = new ArrayList<Case>();
			
			// the ith group will be the test group, and the rest will be used for training
			testCases.addAll(caseGroups.get(i));
			for (int j = 0; j < k; j++) {
				if (j != i) trainCases.addAll(caseGroups.get(j));
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
		    // creating a new network
			Neural network = new Neural();
		    
		    Train_Neural.trainNeuralEpochs(network, trainCasesArray, numEpochs);
		    weights[i] = network.getWeights();
		    overallErrors[i] = Test_Network.testNetwork(network, testCasesArray);
		    
		}
		double sum = 0;
		for (int i = 0; i < k; i++) sum += overallErrors[i];
		
		// this will be the percent of error that exists relative to the expected error of an untrained network
		double average = sum / k;
		System.out.println("    Average Indexed Error : " + average);
		
		// finding the best set of weights
		int bestIndex = 0;
		int worstIndex = 0;
		double bestError = 2; // should be no more than around 1, so this should always be replaced
		double worstError = 0; // should be greater than 0 unless perfect, so this will be replaced
		for (int i = 0; i < k; i++) {
			if (overallErrors[i] < bestError) {
				bestIndex = i;
				bestError = overallErrors[i];
			}
			if (overallErrors[i] > worstError) {
				worstIndex = i;
				worstError = overallErrors[i];
			}
		}
		System.out.println("    Worst Indexed Error: " + overallErrors[worstIndex]);
		System.out.println("    Best Indexed Error: " + overallErrors[bestIndex]);
		return weights[bestIndex];
	}
	
	public static double testNetwork(Neural network, Case[] testCases) {
		double[] networkErrors = new double[testCases.length];
	    
	    for (int i = 0; i < testCases.length; i++) {
		    // Get the output of the network
		    double[] networkOutput = Run_Neural.runNetwork(network, testCases[i]);
		    // Get the correct output
		    double[] correctOutput = testCases[i].getLabelsIfKnown();
		    // find the network errors for this case
		    double[] errors = Train_Neural.networkError(networkOutput, correctOutput);
		    double sum = 0;
		    for (int j = 0; j < errors.length; j++) {
		    	sum += errors[j];
		    }
		    double average = sum / errors.length;
		    networkErrors[i] = average ;
	    }
	    
	    double sum = 0;
	    for (int j = 0; j < networkErrors.length; j++) {
	    	sum += networkErrors[j];
	    }
	    double average = sum / networkErrors.length;
		double scaledAverage = average / 0.125;
		// this should be the percent of error that exists relative to the expected error of an untrained network
		return scaledAverage;
	}
	
}
