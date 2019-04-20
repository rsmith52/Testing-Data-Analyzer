package Objects;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

import File_IO.*;

public class CategorizedTest {

	@Test
	public void testFindTopCategories() {
		Categorized test = new Categorized();
		ArrayList<String> topCategories = new ArrayList<String>();
		test = Categorized_In.readFromDatabase("data1.cat");
		topCategories = Categorized.findTopCategories(test.getCaseList(), 5);
		for(int i = 0; i < topCategories.size(); i++) {
			System.out.println(topCategories.get(i));
		}
	}
	@Test
	public void testFindTopRequestors() {
		Categorized test = new Categorized();
		ArrayList<String> topRequestors = new ArrayList<String>();
		test = Categorized_In.readFromDatabase("data1.cat");
		topRequestors = Categorized.findTopRequestors(test.getCaseList(), 5);
		for(int i = 0; i < topRequestors.size(); i++) {
			System.out.println(topRequestors.get(i));
		}
	}

	@Test
	public void testGetTopPerRequestor() {
		Categorized test = new Categorized();
		String topRequestor;
		test = Categorized_In.readFromDatabase("data1.cat");
		topRequestor = Categorized.findTopRequestor(test.getCaseList());
		ArrayList<String> topCategories = Categorized.getTopPerRequestor(test.getCaseList(), topRequestor, 3);
		for(int i = 0; i < topCategories.size(); i++) {
			System.out.println(topCategories.get(i));
		}
	}
	@Test
	public void testGetTopPerCategory() {
		Categorized test = new Categorized();
		String topCategory;
		test = Categorized_In.readFromDatabase("data1.cat");
		topCategory = Categorized.findTopCategory(test.getCaseList());
		ArrayList<String> topRequestors = Categorized.getTopPerCategory(test.getCaseList(), topCategory, 3);
		for(int i = 0; i < topRequestors.size(); i++) {
			System.out.println(topRequestors.get(i));
		}
	}
}
