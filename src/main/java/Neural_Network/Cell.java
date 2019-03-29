package Neural_Network;

public class Cell {

  String functionType; // ReLU or Sigmoid
  final int bias = 1; // Bias term added to function each time
  double[] inputWeights; // Weights for all inputs including bias term as index 0
  double[] outputWeights; // Weights for all outputs from cell

  // Constructor for a Cell
  public Cell (int numInputs, int numOutputs) {
	  this.inputWeights = new double[numInputs + 1]; // +1 for bias weight
	  for (int i = 0; i < inputWeights.length; i++) {
		  inputWeights[i] = 1;
	  }
	  this.outputWeights = new double[numOutputs];
	  for (int i = 0; i < outputWeights.length; i++) {
		  outputWeights[i] = 1;
	  }
  }

  // function - runs RELU or Sigmoid based on functionType field
  public double function(double[] inputs) {
    double u = getU(inputs);

    switch(functionType) {
      case "ReLU":
        return relu(u);
      case "Sigmoid":
        return sigmoid(u);
    }
    return -1;
  }

  // returns the weighted sum of inputs and their weights
  public double getU(double[] inputs) {
    double u = bias * inputWeights[0];
    for (int i = 0; i < inputs.length; i++) {
      u += inputs[i] * inputWeights[i+1];
    }
    return u;
  }

  // RELU function
  public double relu(double u) {
    double output = Math.max(0,u);
    return output;
  }

  // sigmoid function
  public double sigmoid(double u) {
    double output = 1 / (1 + Math.exp(-1 * u));
    return output;
  }

  // gets outputWeights
  public double[] getOutputWeights() {
    return this.outputWeights;
  }

  // sets outputWeights
  public void setOutputWeights(double[] weights) {
    outputWeights = weights;
  }

  // gets inputWeights
  public double[] getInputWeights() {
    return this.inputWeights;
  }

  // sets inputWeights
  public void setInputWeights(double[] weights) {
    inputWeights = weights;
  }

}
