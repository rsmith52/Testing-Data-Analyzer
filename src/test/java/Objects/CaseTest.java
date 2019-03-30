package Objects;

import static org.junit.Assert.*;

import org.junit.Test;

public class CaseTest {
	
	
	

//	@Test
//	public void testGetActualCategoriesKnown() {
//		fail("Not yet implemented");
//	}
//	@Test 
	public void testFindOccurrences() {
		
		try {
			Case testCase = new Case();
			String[] testOccurrences = new String[5];
			testOccurrences[0] = "office";
			testOccurrences[1] = "365";
			testOccurrences[2] = "office";
			testOccurrences[3] = "a";
			testOccurrences[4] = "license";
			testCase.findOccurrences(testOccurrences);
			assertEquals(2, (int)testCase.occurrences.get("office"));
		}
		catch(Exception e) {
			fail("Caused an exception");
		}
	} 
	@Test
	public void testGetAsInput() {
		Case testCase = new Case();
		double[] testInputs = new double[144];
		String[] testDes = new String[25];
		testDes[0] = "office";
		testDes[1] = "365";
		testDes[2] = "365";
		testDes[3] = "drive";
		testDes[4] = "drive";
		testDes[5] = "drive";
		testDes[6] = "word";
		testDes[7] = "word";
		testDes[8] = "word";
		testDes[9] = "word";
		for(int i = 10; i < testDes.length; i++) {
			testDes[i] = "the";
		}
		testCase.setTokenizedDescription(testDes);
		try {
		testInputs = testCase.getAsInput();
		for(int i = 0; i < testInputs.length; i++) {
			System.out.print(i + " ");
			System.out.println(testInputs[i]);
		}
		
		}
		catch(Exception e) {
			fail("caused an exception");
		}
	}

}
