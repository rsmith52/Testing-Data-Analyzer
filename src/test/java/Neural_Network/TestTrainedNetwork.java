package Neural_Network;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import File_IO.CSV_In;
import File_IO.Categorized_In;
import File_IO.Categorized_Out;
import File_IO.FileAccess;
import Objects.Categorized;
import Objects.Case;

public class TestTrainedNetwork {

	@Test
	public void test() {
		// Create Neural Network
	    System.out.println("Creating network object");    
	    Neural network = new Neural();
	    
	    System.out.println("Reading in Test Data");
	    ArrayList<Case> newCases = new ArrayList<Case>();
	    File testFile = FileAccess.getFile("/categorizedCases.csv");
	    //File testFile = FileAccess.getFile("/Test_Data_1.csv");
	    //File testFile = FileAccess.getFile("/Test_Data_2.csv");
	    //File testFile = FileAccess.getFile("/Test_Data_3.csv");
	    //File testFile = FileAccess.getFile("/Bascom_Pull.csv");
	    
	    try {
	    	newCases = CSV_In.csvRead(testFile, true);
	    	//newCases = CSV_In.csvRead(testFile);
	    } catch (Exception e) {
	    	System.out.println(e);
	    }
	    Case[] testArray = new Case[newCases.size()];
	    ArrayList<String> originalCategories = new ArrayList<String>();
	    for (int i = 0; i < testArray.length; i++) {
	    	testArray[i] = newCases.get(i);
	    	// Save Original Categories
	    	originalCategories.add(newCases.get(i).getCategory());
	    }
	    
	    // Get weights from file
	    System.out.println("Reading Weights from File");
	    Train_Neural.readWeightsFromFile(network);
	    
	    
	    // Run Network
	    double correctness = Run_Neural.assignCategoriesAndCheckCorrectness(network, testArray);
	    ArrayList<double[]> outputs = new ArrayList<double[]>();
	    for (int i = 0; i < testArray.length; i++) {
	    	outputs.add(Run_Neural.runNetwork(network, testArray[i]));
	    }
	    
	    // Individual Examination
	    /*
	    final int caseToExamine = 5;
	    for (int i = 0; i < outputs.get(caseToExamine).length; i++) {
	    	System.out.print(outputs.get(caseToExamine)[i] + ", ");
	    }
	    System.out.println();
	    */
	    
	    // Print Results
	    System.out.println("Case Number : Original Category - Network's Label");
	    for (int i = 0; i < testArray.length; i++) {
	    	System.out.println(testArray[i].getCaseNumber() + " : " + originalCategories.get(i) + " - " + testArray[i].getCategory());
	    }
	    
	    System.out.println("Percentage Correct: " + correctness);
	    
	    // Make categorized object - Test reading and writing to file
	    
	    Categorized list = new Categorized("testNetwork", "Now", testArray);
	    Categorized_Out.writeToDatabase("testNetwork.txt", list);
	    Categorized list2 = Categorized_In.readFromDatabase("testNetwork.txt");
	    
	}

}
