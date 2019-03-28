public class Train_Neural {

  final int STEP_SIZE = 0.1;

  // Run through an epoch with a training set - updating the weights of the network
  public static void trainNeural(Neural network, Case[] dataSet) {
    // Go through each case in the training set
    for (Case data: dataSet) {
      // Get the output of the network
      double[] networkOutput = run_neural.runNeural(network, data);
      // Get the correct output
      double[] correctOutput = data.getLabelsIfKnown();
      // Get errors from weights
      gradientDescent(network, networkOutput, correctOutput, STEP_SIZE);
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
      errors[i] = actual - expected;
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
  public static double[] firstLayerDataError(Neural network, double[] actual, double[] expected) {
    double[] firstLayerOutputErrors = firstLayerOutputError(network, actual, expected);
    Cell[] firstLayer = network.getFirstLayer();
    // One error term for each first layer cell/unit
    double[] errors = new double[firstLayer.length];
    for (int i = 0; i < errors.length; i++) {
      // TODO: make sure network.getInputs() gives an array, not a mapping
      double u = firstLayer[i].getU(network.getInputs());
      // Error w.r.t. data in first layer (u) is the error w.r.t. the output of
      // first layer (v) times the partial derivative of u with respect to v
      // The latter quantity is 1 if u >= 0, and 0 otherwise
      if (u >= 0) {
        errors[i] = firstLayerOutputErrors[i];
      } else {
        errors[i] = 0;
      }
    }
  }

  // TODO FINISH THIS - the for loop currently misses a lot of edges
  // Find error with respect to weights
  public static double[] weightError(Neural network, double[] actual, double[] expected) {
    double[] firstLayerDataErrors = firstLayerDataError(network, actual, expected);
    Cell[] firstLayer = network.getFirstLayer();
    // The error w.r.t. edge weights is the output of the first layer times the
    // error w.r.t the data of the first layer
    numWeights = firstLayer.length * (firstLayer.length + 1) + firstLayer.length * network.numOuputs;
    double[] errors = new double[numWeights];
    for (int i = 0; i < errors.length; i++) {
      errors[i] = firstLayer[i].function(network.getInputs()) * firstLayerDataErrors[i];
    }
    return errors;
  }


  // TODO FINISH THIS
  /** perform one step of stochastic gradient descent with step size n.
   * In general, a new weight is obtained by subtracting (from the old weight)
   * n times the partial derivative w.r.t. the edge weight
   */
  public void gradientDescent(Neural network, double[] actual, double[] expected, double n) {
    // TODO: Make sure inputWeights and outputWeights are updated for each cell
    Cell[] firstLayer = network.getFirstLayer();
    Cell[] secondLayer = network.getSecondLayer();
    double[] firstLayerDataErrors = firstLayerDataError(network, actual, expected);

    // We'll iterate through the firstLayer first, updating the inputWeights and
    // outputWeights of the firstLayer, and also the inputWeights of the secondLayer
    // (which will be the same as the outputWeights of the firstLayer)
      for (int i = 0; i < firstLayer.length; i++) {
        // TODO: verify that this is how clone is actually used...
        double[] newInputWeights = firstLayer[i].getInputWeights().clone();
        // handling the weight of the bias
        //TODO: newInputWeights[0] -= n *
      }
  }


}
