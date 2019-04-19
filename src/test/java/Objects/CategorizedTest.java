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
		topCategories = Categorized.findTopCategories(test.getCaseList());
		for(int i = 0; i < topCategories.size(); i++) {
			System.out.println(topCategories.get(i));
		}
	}
	@Test
	public void testFindTopRequestors() {
		Categorized test = new Categorized();
		ArrayList<String> topRequestors = new ArrayList<String>();
		test = Categorized_In.readFromDatabase("data1.cat");
		topRequestors = Categorized.findTopRequestors(test.getCaseList());
		for(int i = 0; i < topRequestors.size(); i++) {
			System.out.println(topRequestors.get(i));
		}
	}

}
