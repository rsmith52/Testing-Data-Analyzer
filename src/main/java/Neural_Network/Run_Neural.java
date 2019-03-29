package Neural_Network;

import Objects.*;

public class Run_Neural {

  // Method runs a single case through the neural network and returns an array of outputs
  public static double[] runNetwork (Neural network, Case data) {
    // Get input to network from case
    double[] inputs = data.getAsInput().getInputs();

    // Run through first layer of network
    Cell[] firstLayer = network.getFirstLayer();
    double[] outputs = new double[firstLayer.length];
    for (int i = 0; i < firstLayer.length; i++) {
      outputs[i] = firstLayer[i].function(inputs);
    }

    // Run through second layer of network
    Cell[] secondLayer = network.getSecondLayer();
    double[] outputs2 = new double[secondLayer.length];
    for (int i = 0; i < secondLayer.length; i++) {
      outputs2[i] = secondLayer[i].function(outputs);
    }

    // Return outputs from second layer
    return outputs2;

  }

}
