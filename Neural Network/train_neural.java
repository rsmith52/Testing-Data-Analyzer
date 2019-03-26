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
  
  
    // Find overall error for each output produced by the network
    public static double[] networkError(double[] actual, double[] expected) {
      double[] errors = new double[actual.length];
      for (int i = 0; i < errors.length; i++) {
        // Error of network for each label is 1/2 (output - expected output)^2
        errors[i] = (1/2) * Math.pow(actual[i] - expected[i], 2);
      }
      return errors;
    }
  
    // Find error with respect to output layer itself
    public static double[] outputLayerError(double[] actual, double[] expected) {
      double[] errors = new double[actual.length];
      for (int i = 0; i < errors.length; i++) {
        // Error with respect to output layer is just output - expected output
        errors[i] = actual - expected;
      }
      return errors;
    }
  
    // Find error with respect to data in second layer (Sigmoid function Input "u")
    public static double[] secondLayerDataError(double[] actual, double[] expected) {
      double[] outputLayerErrors = outputLayerError(actual, expected);
      double[] errors = new double[actual.length];
      for (int i = 0; i < errors.length; i++) {
        // Error with respect to data in second layer is error with respect to output layer * (output * (1 - output))
        errors[i] = outputLayerErrors[i] * (actual[i] * (1 - actual[i]));
      }
      return errors;
    }
  
  
  }