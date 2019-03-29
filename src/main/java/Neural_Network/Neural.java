package Neural_Network;

import Neural_Network.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

public class Neural {

  Cell[] firstLayer;
  Cell[] secondLayer;
  final int numOutputs = 20;
  final int numInputs = 155;

  // Holds weights (double) (Done in Cell.java)
  // Holds cells and connections (need new class/type)

  //Hashmap to store our inputs. Each key will be an input word and each output will be the number of instances
  Map<String, Integer> inputs = new HashMap<String, Integer>();


  //TODO: write code to save/load serialized data for the neural network

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


  // Holds functions (math functions for RELU and Sigmoid) (done in Cell.java)


  // Holds inputs (list of input variables)

  // Holds outputs/labels (list of output labels)


  //getters and setters for all the above
  public Cell[] getFirstLayer() {
	  return this.firstLayer;
  }
  
  public Cell[] getSecondLayer() {
	  return this.secondLayer;
  }
  
  public static void loadInputs() {

  }

  public double[] getInputs() {
	  double[] doubleInputs = new double[this.inputs.size()];
	  int i = 0;
	  
	  for(Map.Entry<String, Integer> entry : this.inputs.entrySet()) {
		  doubleInputs[i] = entry.getValue();
	  }
	  
	  return doubleInputs;
  }
  
  public void clearCounts() {
    for(Map.Entry<String, Integer> entry : this.inputs.entrySet()) {
      entry.setValue(0);
    }
}
}
