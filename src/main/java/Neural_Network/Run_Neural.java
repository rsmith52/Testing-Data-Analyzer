package Neural_Network;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import File_IO.FileAccess;
import Objects.*;

public class Run_Neural {
	
	final static double CATEGORY_THRESHOLD = 0.3;

  // Method runs a single case through the neural network and returns an array of outputs
  public static double[] runNetwork (Neural network, Case data) {
    // Get input to network from case

	double[] inputs = data.getAsInput();	


    // Run through first layer of network
    Cell[] firstLayer = network.getFirstLayer();
    double[] outputs = new double[firstLayer.length];
    double[][] weights = network.getWeights();
    for (int i = 0; i < firstLayer.length; i++) {
      outputs[i] = firstLayer[i].function(inputs, weights[i]);
    }

    // Run through second layer of network
    Cell[] secondLayer = network.getSecondLayer();
    double[] outputs2 = new double[secondLayer.length];
    for (int i = 0; i < secondLayer.length; i++) {
      outputs2[i] = secondLayer[i].function(outputs, weights[firstLayer.length + i]);
    }

    // Return outputs from second layer
    return outputs2;

  }
  
  public static double assignCategoriesAndCheckCorrectness (Neural network, Case[] data) {
	  ArrayList<String> categories = new ArrayList<String>();
	  try {
		  File file = FileAccess.getFile("/outputs.txt");
		  Scanner in = new Scanner(file);
		  while (in.hasNextLine()) {
			  categories.add(in.nextLine());
		  }
		  in.close();
		  categories.add("General Question"); // Always at end of list
	  } catch (Exception e) {
		  System.out.println("Error reading in output labels: " + e);
	  }
	  
	  // Go through all cases
	  int correct = 0;
	  int total = 0;
	  for (int i = 0; i < data.length; i++) {
		  // Run case through network
		  double[] outputs = runNetwork(network, data[i]);
		  // Get highest rated label
		  int bestIndex = 0;
		  int secondBest = 0;
		  for (int j = 0; j < outputs.length; j++) {
			  if (outputs[j] > outputs[bestIndex]) {
				  secondBest = bestIndex;
				  bestIndex = j;
			  } else if (outputs[j] > outputs[secondBest]) {
				  secondBest = j;
			  }
		  }
		  // Check if strong label or weak
		  if (outputs[bestIndex] < CATEGORY_THRESHOLD) {
			  // Weak label - General Question
			  data[i].setCategory(categories.get(categories.size() - 1));
		  } else if (outputs[secondBest] >= CATEGORY_THRESHOLD ) {
			  // 2 Strong labels - General Question
			  data[i].setCategory(categories.get(categories.size() - 1));
		  } else {
			  data[i].setCategory((categories.get(bestIndex)));
		  }
		  if (data[i].getOriginalCategory().equals(data[i].getCategory())) {
			  correct++;
		  }
		  total++;
		  
	  }
	  return (double)correct / (double)total;
  }

}
