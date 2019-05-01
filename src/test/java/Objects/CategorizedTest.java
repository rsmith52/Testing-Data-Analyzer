package Objects;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import File_IO.Categorized_In;

public class CategorizedTest {

	@Test
	public void testFindTopCategories() {
		Categorized test = new Categorized();
		ArrayList<String> topCategories = new ArrayList<String>();
		test = Categorized_In.readFromDatabase("Data Set 1.cat");
		topCategories = Categorized.findTopCategories(test.getCaseList(), 5, false);
		String[] categoriesString = {"Onsite Assistance", "Shared/Network Drive", "Admin Account/Password", "Network Connectivity", "Computer Support Settings"};
		for(int i = 0; i < topCategories.size(); i++) {
			assertEquals(categoriesString[i], topCategories.get(i));
		}
	}
	
	@Test
	public void testFindTopRequestors() {
		Categorized test = new Categorized();
		ArrayList<String> topRequestors = new ArrayList<String>();
		test = Categorized_In.readFromDatabase("Data Set 1.cat");
		topRequestors = Categorized.findTopRequestors(test.getCaseList(), 5);
		String[] requestorsString = {"ROBERT J MAYVILLE", "JAMES  ACKERMAN", "CAMERON EDWARD OLSON", "PETER A FOWLER","JAKE  THALACKER"};
		for(int i = 0; i < topRequestors.size(); i++) {
			assertEquals(requestorsString[i], topRequestors.get(i));
		}
	}

	@Test
	public void testGetTopPerRequestor() {
		Categorized test = new Categorized();
		String topRequestor;
		test = Categorized_In.readFromDatabase("Data Set 1.cat");
		topRequestor = Categorized.findTopRequestor(test.getCaseList());
		ArrayList<String> topCategories = Categorized.getTopPerRequestor(test.getCaseList(), topRequestor, 3);
		String[] requestorsPer = {"Onsite Assistance" ,  "Office 365 Email and Calendar"};
		for(int i = 0; i < topCategories.size(); i++) {
			assertEquals(requestorsPer[i], topCategories.get(i));
		}
	}
	
	@Test
	public void testGetTopPerCategory() {
		Categorized test = new Categorized();
		String topCategory;
		test = Categorized_In.readFromDatabase("Data Set 1.cat");
		topCategory = Categorized.findTopCategory(test.getCaseList());
		ArrayList<String> topRequestors = Categorized.getTopPerCategory(test.getCaseList(), topCategory, 3);
		String[] categoriesPer = {"ROBERT J MAYVILLE", "CAMERON EDWARD OLSON", "JAKE  THALACKER"};
		for(int i = 0; i < topRequestors.size(); i++) {
			assertEquals(categoriesPer[i], topRequestors.get(i));
		}
	}
	
}
