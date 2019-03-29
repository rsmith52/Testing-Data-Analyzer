
package Neural_Network;

import Objects.*;

public class Train_Neural {

	  final static double STEP_SIZE = 0.1;

	  // Run through an epoch with a training set - updating the weights of the network
	  public static void trainNeural(Neural network, Case[] dataSet) {
	    // Go through each case in the training set
	    for (Case data: dataSet) {
	      // Get the output of the network
	      double[] networkOutput = Run_Neural.runNetwork(network, data);
	      // Get the correct output
	      double[] correctOutput = data.getLabelsIfKnown();
	      // Get errors from weights
	      gradientDescent(data, network, networkOutput, correctOutput, STEP_SIZE);
	    }
	  }

	  // Run through multiple epochs with a training set - updating the weights of the network
	  public static void trainNeuralEpochs(Neural network, Case[] dataSet, int numEpochs) {
	    for (int i = 0; i < numEpochs; i++) {
	      trainNeural(network, dataSet);
	    }
	  }

	  /*
	  Input to All functions below take "actual" and "expected" where these are the data produced by and expected
	  of the network. Each index in the array corresponds to one of the outputs of the network.
	  actual[0] for example could be what the network thought was the probability of the case being about O365 Calendar and Email, while
	  expected[0] is a 1 if the label should be that, and a 0 if it shouldn't be.
	  */

	  // Find overall error for each output produced by the network after running one Case
	  public static double[] networkError(double[] actual, double[] expected) {
	    // One error term for each output
	    double[] errors = new double[actual.length];
	    for (int i = 0; i < errors.length; i++) {
	      // Error of network for each label is 1/2 (output - expected output)^2
	      errors[i] = (1/2) * Math.pow(actual[i] - expected[i], 2);
	    }
	    return errors;
	  }

	  // Find error with respect to output layer itself (Sigmoid function output "v")
	  public static double[] outputLayerError(double[] actual, double[] expected) {
	    // One error term for each output
	    double[] errors = new double[actual.length];
	    for (int i = 0; i < errors.length; i++) {
	      // Error with respect to output layer is just output - expected output
	      errors[i] = actual[i] - expected[i];
	    }
	    return errors;
	  }

	  // Find error with respect to data in second layer (Sigmoid function input "u")
	  public static double[] secondLayerDataError(Neural network, double[] actual, double[] expected) {
	    double[] outputLayerErrors = outputLayerError(actual, expected);
	    // One error term for each second layer cell/unit
	    double[] errors = new double[outputLayerErrors.length];
	    for (int i = 0; i < errors.length; i++) {
	      // Error with respect to data in second layer is error with respect to output layer * (output * (1 - output))
	      errors[i] = outputLayerErrors[i] * (actual[i] * (1 - actual[i]));
	    }
	    return errors;
	  }

	  // Find error with respect to output of first layer (ReLU function output "v")
	  public static double[] firstLayerOutputError(Neural network, double[] actual, double[] expected) {
	    double[] secondLayerErrors = secondLayerDataError(network, actual, expected);
	    Cell[] firstLayer = network.getFirstLayer();
	    // One error term for each first layer cell/unit
	    double[] errors = new double[firstLayer.length];
	    // Error with respect to output of first layer is sum of (errors from data in second
	    // layer times the corresponding weight between the layers)
	    for (int i = 0; i < errors.length; i++) {
	        double[] outputWeights = firstLayer[i].getOutputWeights();
	        errors[i] = 0;
	        for (int j = 0; j < secondLayerErrors.length; j++) {
	          errors[i] += outputWeights[j] * secondLayerErrors[j];
	      }
	    }
	    return errors;
	  }

	  // Find error with respect to data in first layer (ReLU function input "u")
	  public static double[] firstLayerDataError(Case data, Neural network, double[] actual, double[] expected) {
	    double[] firstLayerOutputErrors = firstLayerOutputError(network, actual, expected);
	    Cell[] firstLayer = network.getFirstLayer();
	    // One error term for each first layer cell/unit
	    double[] errors = new double[firstLayer.length];
	    for (int i = 0; i < errors.length; i++) {
	      double u = firstLayer[i].getU(data.getAsInput());
	      // Error w.r.t. data in first layer (u) is the error w.r.t. the output of
	      // first layer (v) times the partial derivative of u with respect to v
	      // The latter quantity is 1 if u >= 0, and 0 otherwise
	      if (u >= 0) {
	        errors[i] = firstLayerOutputErrors[i];
	      } else {
	        errors[i] = 0;
	      }
	    }
	    return errors;
	  }

	  // Find error with respect to weights
	  public static double[] weightError(Case data, Neural network, double[] actual, double[] expected) {
	    Cell[] firstLayer = network.getFirstLayer();
	    Cell[] secondLayer = network.getSecondLayer();
	    double[] firstLayerDataErrors = firstLayerDataError(data, network, actual, expected);
	    double[] secondLayerDataErrors = secondLayerDataError(network, actual, expected);
	    double[] inputs = data.getAsInput();
	    // The error with respect to the weight of edge ij is the output of cell i
	    // times the error w.r.t the data of cell j

	    /**
	     * The errors will be ordered as follows: for each cell in firstLayer, all
	     * errors will be entered (starting with the error associated with the bias),
	     * then, for each cell in secondLayer, all errors will be entered (starting
	     * with the error associated with bias)
	     */
	    int numWeights = (firstLayer.length + network.numOutputs) * (firstLayer.length + 1);
	    double[] errors = new double[numWeights];
	    double[] firstLayerOutput = new double[firstLayer.length];
	    // handling weights of edges going into firstLayer
	    // j is used in the outer loop for consistency with edges going from i to j
	    for (int j = 0; j < firstLayer.length; j++) {
	      errors[j*(firstLayer.length+1)] = 1 * firstLayerDataErrors[j]; // handling the bias input
	      for (int i = 0; i < firstLayer.length; i++) {
	        errors[j*(firstLayer.length+1)+i+1] = inputs[i] * firstLayerDataErrors[j];
	      }
	      firstLayerOutput[j] = firstLayer[j].function(inputs);
	    }
	    // handling weights of edges going into secondLayer
	    int offset = firstLayer.length * (firstLayer.length + 1);
	    for (int j = 0; j < secondLayer.length; j++) {
	      errors[offset + j*(firstLayer.length+1)] = 1 * secondLayerDataErrors[j]; // handling the bias input
	      for (int i = 0; i < firstLayer.length; i++) {
	        errors[offset + j*(firstLayer.length+1)+i+1] = firstLayerOutput[i] * secondLayerDataErrors[j];
	      }
	    }
	    return errors;
	  }

	  /** perform one step of stochastic gradient descent with step size n.
	   * In general, a weight is updated by subtracting n times the partial
	   * derivative w.r.t. the edge weight
	   */
	  public static void gradientDescent(Case data, Neural network, double[] actual, double[] expected, double n) {
	    Cell[] firstLayer = network.getFirstLayer();
	    Cell[] secondLayer = network.getSecondLayer();
	    double[] weightErrors = weightError(data, network, actual, expected);
	    // updating weights for edges going into firstLayer
	    for (int j = 0; j < firstLayer.length; j++) {
	      double[] newWeights = firstLayer[j].getInputWeights().clone();
	      for (int i = 0; i < newWeights.length; i++) {
	        newWeights[i] -= n * weightErrors[j * newWeights.length + i];
	      }
	      firstLayer[j].setInputWeights(newWeights);
	    }
	    double[][] outputWeights = new double[firstLayer.length][secondLayer.length];
	    // updating inputWeights for edges going into secondLayer
	    int offset = firstLayer.length * (firstLayer.length + 1);
	    for (int j = 0; j < secondLayer.length; j++) {
	      double[] newWeights = secondLayer[j].getInputWeights().clone();
	      for (int i = 0; i < newWeights.length; i++) {
	        newWeights[i] -= n * weightErrors[offset + j * newWeights.length + i];
	        if (i > 0)
	          outputWeights[i-1][j] = newWeights[i];
	      }
	      secondLayer[j].setInputWeights(newWeights);
	    }
	    // updating outputWeights for edges leaving firstLayer
	    for (int i = 0; i < firstLayer.length; i++) {
	      firstLayer[i].setOutputWeights(outputWeights[i]);
	    }
	  }
}
