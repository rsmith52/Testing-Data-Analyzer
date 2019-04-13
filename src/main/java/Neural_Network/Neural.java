package Neural_Network;

import Neural_Network.*;
import Neural_Network.Cell;
import File_IO.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.io.*;
import java.util.Random;

public class Neural implements Serializable {

  private static final long serialVersionUID = 1L;
  Cell[] firstLayer;
  Cell[] secondLayer;
  public double[][] weights;
  final Random rand = new Random();
  final int numOutputs = 19;
  final int numInputs = 153; // MUST BE UPDATED IF INPUTS IS UPDATED
  final String inputFile = "inputs.txt";
  final String outputFile = "outputs.txt";

  //Hashmap to store our inputs. Each key will be an input word and each output will be the number of instances
  Map<String, Integer> inputs;
  Map<String, Integer> outputs;


  //TODO: write code to save/load serialized data for the neural network

  public Neural () {
    Cell[] firstLayer = new Cell[numInputs];

    for(int i = 0; i < firstLayer.length; i++){
      firstLayer[i] = new Cell(i, "ReLU");
    }
    this.firstLayer = firstLayer;

    Cell[] secondLayer = new Cell[numOutputs];

    for(int i = 0; i < secondLayer.length; i++){
      secondLayer[i] = new Cell(numInputs + i, "Sigmoid");
    }
    this.secondLayer = secondLayer;

    /** weights[i][j] is the weight for the edge going from j to i (when j > 0)
     * each cell (in either first or second layer) will have an array of size
     * numInputs + 1 to hold its input weights, with index 0 being the bias weight
     */
    double[][] weights = new double[this.numInputs + this.numOutputs][this.numInputs + 1];
    for (int i = 0; i < weights.length; i++) {
    	for (int j = 0; j < weights[0].length; j++) {
    		if (rand.nextBoolean()) weights[i][j] = 0.1;
    		else weights[i][j] = -0.1;          		
    	}
    }    
    this.weights = weights;
  }
  
  // TODO: do we actually want to do this?
	  // Holds inputs (list of input variables)
	  // Holds outputs/labels (list of output labels)


  //getters and setters for all the above
  public Cell[] getFirstLayer() {
    return this.firstLayer;
  }
  
  public Cell[] getSecondLayer() {
    return this.secondLayer;
  }

  public double[] getInputs() {
    double[] doubleInputs = new double[this.inputs.size()];
    int i = 0;
    
    for(Map.Entry<String, Integer> entry : this.inputs.entrySet()) {
      doubleInputs[i] = entry.getValue();
      i++;
    }
    
    return doubleInputs;
  }
  
  public double[][] getWeights() {
    return this.weights;
  }

  public double[] getWeights(int i) {
	return this.weights[i];
  }
  
  public void setWeights(double newWeights[][]) {
    weights = newWeights;
  }
 
  public void clearCounts() {
    for(Map.Entry<String, Integer> entry : this.inputs.entrySet()) {
      entry.setValue(0);
    }
  }
  
}
