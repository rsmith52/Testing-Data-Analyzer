package Neural_Network;

import java.util.ArrayList;

import Objects.Case;

public class k_Fold_Evaluation {

	public static double kFoldAnalysis(ArrayList<Case> cases, int k, int numEpochs) {
		// this is just so we get getWeights to dynamically get the number of weights
		Neural throwAwayNetwork = new Neural();
		double[][] throwAwayWeights = throwAwayNetwork.getWeights();
		// we may not need these, but
		double[][][] weights = new double[k][throwAwayWeights.length][throwAwayWeights[0].length];
		double[] overallErrors = new double[k];
		ArrayList<ArrayList<Case>> caseGroups = Split_Data.splitData(cases, k);
	
		for (int i = 0; i < k; i++) {
			// creating arraylists to be used to hold the test and train data
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
		    overallErrors[i] = testNetwork(network, testCasesArray);
		    
		}
		double sum = 0;
		for (int i = 0; i < k; i++) {
			sum += overallErrors[i];
		}
		double average = sum / double(k);
		return 0;
	}
	
	public static double testNetwork(Neural network, Case[] testCases) {
		return 0;
	}
	
}
