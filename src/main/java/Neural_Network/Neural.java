package Neural_Network;

import Neural_Network.*;
import File_IO.*;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.io.*;

public class Neural {

  Cell[] firstLayer;
  Cell[] secondLayer;
  final int numOutputs = 20;
  final int numInputs = 144;
  final String inputFile = "inputs.txt";
  final String outputFile = "outputs.txt";

  // Holds weights (double) (Done in Cell.java)
  // Holds cells and connections (need new class/type)

  //Hashmap to store our inputs. Each key will be an input word and each output will be the number of instances
  Map<String, Integer> inputs;
  Map<String, Integer> outputs;


  //TODO: write code to save/load serialized data for the neural network

  public Neural () {
    Cell[] firstLayer = new Cell[numInputs];

    for(int i = 0; i < firstLayer.length; i++){
      firstLayer[i] = new Cell();
      firstLayer[i].functionType = "ReLU";
    }

    Cell[] secondLayer = new Cell[numOutputs];

    for(int i = 0; i < secondLayer.length; i++){
      secondLayer[i] = new Cell();
      secondLayer[i].functionType = "Sigmoid";
    }

    loadText(inputFile, inputs);
    loadText(outputFile, outputs);
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

  //We will un-hardcode the inputs in iteration 2
  public static void loadText(String fileName, Map<String, Integer> hm) {
	  hm = new HashMap<String, Integer>();

	  File file = FileAccess.getFile(fileName);

	  try {
		  Scanner scanner = new Scanner(file);

		  while(scanner.hasNextLine()) {
			  hm.put(scanner.nextLine(), 0);
		  }

		  scanner.close();
	  } catch (FileNotFoundException e) {
	  }

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

  public void clearCounts() {
    for(Map.Entry<String, Integer> entry : this.inputs.entrySet()) {
      entry.setValue(0);
    }
  }
}
