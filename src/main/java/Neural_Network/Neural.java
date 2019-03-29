package Neural_Network;

/**
 * The neural network
 */
public class Neural {

  Cell[] firstLayer;
  Cell[] secondLayer;
  final int numOutputs = 20;
  final int numInputs = 144;

  /**
   * Constructor for the Neural class.
   */
  public Neural () {
    Cell[] firstLayer = new Cell[numInputs];

    for(int i = 0; i < firstLayer.length; i++){
      firstLayer[i].functionType = "ReLU";
    }

    Cell[] secondLayer = new Cell[numOutputs];

    for(int i = 0; i < firstLayer.length; i++){
      secondLayer[i].functionType = "Sigmoid";
    }

  }

  /**
   * Returns the first layer of the neural network.
   * <p>This is the "hidden layer" in the Neural Net Concept in the
   * requirements and specifications document.
   *
   * @return firstLayer
   */
  public Cell[] getFirstLayer() {
	  return this.firstLayer;
  }

  /**
   * Returns the second layer of the neural network.
   * <p>This is the "output layer" in the Neural Net Concept in the
   * requirements and specifications document.
   *
   * @return secondLayer
   */
  public Cell[] getSecondLayer() {
	  return this.secondLayer;
  }

}
