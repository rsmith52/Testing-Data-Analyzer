package File_IO;

import static org.junit.Assert.*;

import org.junit.Test;

public class TokenizationTest {


	
	@Test
	public void testSegmentation() {
		String[] expectedOut1 = new String[5];
		expectedOut1[0] = "this";
		expectedOut1[1] = "string";
		expectedOut1[2] = "is";
		expectedOut1[3] = "a";
		expectedOut1[4] = "test";
		
		String[] expectedOut2 = new String[4];
		expectedOut2[0] = "this";
		expectedOut2[1] = "'string'";
		expectedOut2[2] = "is";
		expectedOut2[3] = "a-test";
		
		String[] expectedOut3 = new String[5];
		expectedOut3[0] = "this";
		expectedOut3[1] = "string";
		expectedOut3[2] = "is";
		expectedOut3[3] = "1";
		expectedOut3[4] = "test";
				
		// Test casing (should be all lower case)
		String inputStr1 = "THIS string IS A TEST!";
		// Test - and ' remain in string while other punctuation is removed
		String inputStr2 = "This, 'string' is A-Test!";
		// Test random punctuation
		String inputStr3 = "This ~~String is `1_ test!";
		
		// use segmentation method to split string
		Tokenization split = new Tokenization();
		String[] segment1 = split.segmentation(inputStr1);
		String[] segment2 = split.segmentation(inputStr2);
		String[] segment3 = split.segmentation(inputStr3);
		
		
		assertArrayEquals(segment1, expectedOut1);
		assertArrayEquals(segment2, expectedOut2);
		assertArrayEquals(segment3, expectedOut3);
		
	}

}