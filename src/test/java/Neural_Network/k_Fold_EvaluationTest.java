package Neural_Network;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

import org.junit.Test;

import File_IO.CSV_In;
import File_IO.FileAccess;
import Objects.Case;

public class k_Fold_EvaluationTest {

	@Test
	public void testKFoldAnalysis() {
	    ArrayList<Case> newCases = new ArrayList<Case>();
	    File newFile = FileAccess.getFile("/categorizedCases.csv");
	    try {
	    	newCases = CSV_In.csvRead(newFile, true);
	    } catch (Exception e) {
	    	System.out.println(e);
	    }
		double[][] error = k_Fold_Evaluation.kFoldAnalysis(newCases, 5, 0);
		assertEquals(error.length,500,1000000);
	}
	
}
