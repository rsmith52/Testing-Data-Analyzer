package Neural_Network;

import java.util.ArrayList;
import Objects.Case;

public class k_Fold_Evaluation {
	
	public static double[][] kFoldAnalysis(ArrayList<Case> cases, int k, int numEpochs) {
		ArrayList<double[][]> weights = new ArrayList<double[][]>();
		double[] accuracy = new double[k];
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
		    weights.add(network.getWeights());
		    accuracy[i] = Run_Neural.assignCategoriesAndCheckCorrectness(network, testCasesArray); 
		    
		}		
		// finding the best set of weights
		double sum = 0;
		int bestIndex = 0;
		int worstIndex = 0;
		
		for (int i = 0; i < accuracy.length; i++) {
			if (accuracy[i] < accuracy[worstIndex]) {
				worstIndex = i;
			} 
			if (accuracy[i] > accuracy[bestIndex]) {
				bestIndex = i;
			}
			sum += accuracy[i];
		}
		double average = sum / accuracy.length;

		System.out.println("    Worst Accuracy: " + accuracy[worstIndex]);
		System.out.println("    Average Accuracy: " + average);
		System.out.println("    Best Accuracy: " + accuracy[bestIndex]);
		return weights.get(bestIndex);
	}
	
	public static String kFoldAnalysis(ArrayList<Case> cases, int k, int numEpochs, boolean TRUE) {
		double[] accuracy = new double[k];
		ArrayList<ArrayList<Case>> caseGroups = Split_Data.splitData(cases, k);
	
		for (int i = 0; i < k; i++) {

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
		    
			System.out.println(numEpochs + " - Group " + i);
			
		    Train_Neural.trainNeuralEpochs(network, trainCasesArray, numEpochs);
		    accuracy[i] = Run_Neural.assignCategoriesAndCheckCorrectness(network, testCasesArray); 
		    
		}		
		// finding the best set of weights
		double sum = 0;
		int bestIndex = 0;
		int worstIndex = 0;
		
		for (int i = 0; i < accuracy.length; i++) {
			if (accuracy[i] < accuracy[worstIndex]) {
				worstIndex = i;
			} 
			if (accuracy[i] > accuracy[bestIndex]) {
				bestIndex = i;
			}
			sum += accuracy[i];
		}
		double average = sum / accuracy.length;

		System.out.println(numEpochs + " Epochs - Average Accuracy: " + average);
		String str = accuracy[worstIndex] + "," + average + "," + accuracy[bestIndex];
		return str;
	}
	
}