public class run_neural.java {

  // Method runs a single case through the neural network and returns an array of outputs
  public double[] runNetwork(Neural network, Case data) {
    // Get input to network from case
    int[] inputs = data.getAsInput();

    // Run through first layer of network
    Cell[] firstLayer = network.getFirstLayer();
    double[] outputs = new double[firstLayer.size()];
    for (int i = 0; i < firstLayer.size(); i++) {
      outputs[i] = firstLayer[i].function(inputs);
    }

    // Run through second layer of network
    Cell[] secondLayer = network.getSecondLayer();
    double[] outputs2 = new double[secondLayer.size()];
    for (int i = 0; i < secondLayer.size(); i++) {
      outputs2[i] = secondLayer[i].function(outputs);
    }

    // Return outputs from second layer
    return outputs2;

  }

}
