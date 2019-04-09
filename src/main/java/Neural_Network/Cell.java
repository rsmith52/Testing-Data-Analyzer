package Neural_Network;

public class Cell {

  String functionType; // ReLU or Sigmoid
  final int cellNum; // Just an index to help with calculating weights of input edges

  // Constructor for a Cell
  public Cell (int cellNum, String functionType) {
	  this.cellNum = cellNum;
	  this.functionType = functionType;
  }

  // function - runs ReLU or Sigmoid based on functionType field
  public double function(double[] inputs, double[] weights) {
    switch(functionType) {
      case "ReLU":
        return relu(getU(inputs, weights));
      case "Sigmoid":
        return sigmoid(getU(inputs, weights));
      default:
    	return -1;
    }
  }

  // returns the weighted sum of inputs and their weights
  public double getU(double[] inputs, double[] weights) {
	double u = 1 * weights[0]; // bias = 1
    for (int i = 0; i < inputs.length; i++) {
      u += inputs[i] * weights[1+i];
    }
    return u;
  }

  // RELU function
  public double relu(double u) {
    return Math.max(0,u);
  }

  // Sigmoid function
  public double sigmoid(double u) {
    return 1 / (1 + Math.exp(-1 * u));
  }

  // getter
  public int getCellNum() {
	  return this.cellNum;
  }

}
