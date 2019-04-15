package Neural_Network;

import Objects.Case;

public class Test_Network {
	
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
