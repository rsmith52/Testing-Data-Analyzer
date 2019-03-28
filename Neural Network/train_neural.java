public class train_neural {

  // Run through an epoch with a training set - updating the weights of the network
  public static void trainNeural(Neural network, Case[] dataSet) {
    // Go through each case in the training set
    for (Case data: dataSet) {
      // Get the output of the network
      double[] networkOutput = run_neural.runNeural(network, data);
      // Get the correct output
      double[] correctOutput = data.getLabelsIfKnown();
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

  // TODO FINISH THIS
  // Find error with respect to output of first layer (ReLU function output "v")
  public static double[] firstLayerOutputError(Neural network, double[] actual, double[] expected) {
    double[] secondLayerErrors = secondLayerDataError(network, actual, expected);
    Cell[] firstLayer = network.getFirstLayer();
    // One error term for each first layer cell/unit
    double[] errors = new double[firstLayer.length];
    for (int i = 0; i < errors.length; i++) {
      // Error with respect to output of first layer is sum of (errors from data in second
      // layer times the corresponding weight between the layers)

    }
  }

  // TODO FINISH THIS
  // Find error with respect to data in first layer (ReLU function input "u")
  public static double[] firstLayerDataError(Neural network, double[] actual, double[] expected) {
    double[] firstLayerOutputErrors = firstLayerOutputError(network, actual, expected);
    Cell[] firstLayer = network.getFirstLayer();
    // One error term for each first layer cell/unit
    double[] errors = new double[firstLayer.length];
    for (int i = 0; i < errors.length); i++) {
      // Error with respect to data in first layer is ??? TODO
    }
  }

  // TODO FINISH THIS
  // Find error with respect to weights
  public static double[] weightError(Neural network, double[] actual, double[] expected) {

  }


}
