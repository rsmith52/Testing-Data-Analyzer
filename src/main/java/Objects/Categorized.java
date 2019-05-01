package Objects;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import File_IO.FileAccess;

public class Categorized implements Serializable{
	static final long serialVersionUID = 11;
	public String name;
	public String dateCreated;
	public ArrayList<Case> caseList;

	public Categorized(){
		caseList = new ArrayList<Case>();
	}

	public Categorized(String name, String dateCreated){
		caseList = new ArrayList<Case>();
		this.name = name;
		this.dateCreated = dateCreated;
	}

	public Categorized (String name, String dateCreated, Case[] data) {
		caseList = new ArrayList<Case>();
		this.name = name;
		this.dateCreated = dateCreated;
		for (int i = 0; i < data.length; i++) {
			caseList.add(data[i]);
		}
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	public void setDate(String dateCreated) {
		this.dateCreated = dateCreated;
	}
	public void addToList(Case newCase){
		caseList.add(newCase);
	}
	public ArrayList<Case> getCaseList () {
		return this.caseList;
	}

	public static Categorized combineLists(Categorized list1, Categorized list2) {
		Case[] listArray = new Case[list1.getCaseList().size()];
		listArray = list1.getCaseList().toArray(listArray);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate dateCreated = LocalDate.now();
		String dateString = dateCreated.format(dtf);
	    Categorized newList = new Categorized(list1.getName() + ", " + list2.getName(), dateString, listArray);
	    for (int i = 0; i < list2.getCaseList().size(); i++) {
	    	newList.getCaseList().add(list2.getCaseList().get(i));
	    }
	return newList;

	}

	public static ArrayList<String> findTopCategories(ArrayList<Case> cases, int numCategories, boolean includeGeneral){
	      ArrayList<String> categories = new ArrayList<String>();
	      ArrayList<Integer> counts = new ArrayList<Integer>();
	      boolean found = false;
	      int index = 0;
	      
	      for (Case data : cases) {
	          found = false;
	          index = 0;
	          for(int i = 0; i < categories.size(); i++) {
	              if(categories.get(i).equals(data.getCategory())) {
	                  index = i;
	                  found = true;
	                  break;
	              }
	          }
	          if(found) {
	              int value = counts.get(index);
	              value++;
	              counts.set(index, value);
	          }
	          else {
	              if(!data.getCategory().equals("General Question") || includeGeneral == true) {
	                  categories.add(data.getCategory());
	                  counts.add(new Integer(1));
	              }
	          }
	      }
	      if(numCategories > categories.size())
	      {
	          numCategories = categories.size();
	      }
	      int[] topArray = new int[numCategories];
	      int[] indexes = new int[numCategories];
	      ArrayList<String> topCategories = new ArrayList<String>();
	      for(int i = 0; i < numCategories; i++) {
	          topArray[i] = counts.get(i);
	          indexes[i] = i;
	      }
	      for(int i = numCategories + 1; i < counts.size(); i++) {
	          int value = counts.get(i);
	          int ind = i;
	          for(int k = 0; k < numCategories; k++) {
	              if(topArray[k] < value) {
	                  int tempInt = topArray[k];
	                  int tempInd = indexes[k];
	                  topArray[k] = value;
	                  indexes[k] = ind;
	                  value = tempInt;
	                  ind = tempInd;
	              }
	              
	          }
	      }
	      for(int i = 0; i < numCategories; i++) {
	          topCategories.add(categories.get(indexes[i]));
	      }
	      
	      return topCategories;
	}
	
	public static ArrayList<String> findTopRequestors(ArrayList<Case> cases, int numRequestors){
		ArrayList<String> requestors = new ArrayList<String>();
		ArrayList<Integer> requestorTotals = new ArrayList<Integer>();
		boolean found = false;
		int index = 0;

		for(Case data : cases) {
			found = false;
			index = 0;
			for(int i = 0; i < requestors.size(); i++) {
				if(requestors.get(i).equals(data.getCaseRequestor())) {
					found = true;
					index = i;
					break;
				}
			}

			if(found)
			{
				Integer value = requestorTotals.get(index); // get value
				value = value + 1; // increment value
				requestorTotals.set(index, value); // replace value
			} else {
				if(!data.getCaseRequestor().equals("SE  Default")) {
					requestors.add(data.getCaseRequestor());
					requestorTotals.add(new Integer(1));
				}
			}

		}


		if(numRequestors > requestors.size())
		{
			numRequestors = requestors.size();
		}
		int[] topArray = new int[numRequestors];
		int[] topArrayInd = new int[numRequestors];

		for(int i = 0; i < numRequestors; i++)
		{
			topArray[i] = requestorTotals.get(i);
			topArrayInd[i] = i;
		}

		for(int i = numRequestors + 1; i < requestors.size(); i++)
		{
			int value = requestorTotals.get(i);
			int ind = i;

			for(int k = 0; k < numRequestors; k++)
			{
				if(topArray[k] < value)
				{
					int tempInt = topArray[k];
					int tempInd = topArrayInd[k];
					topArray[k] = value;
					topArrayInd[k] = ind;
					value = tempInt;
					ind = tempInd;
				}
			}


		}

		ArrayList<String> returnStrings = new ArrayList<String>();
		for(int i = 0; i < numRequestors; i++)
		{
			returnStrings.add(requestors.get(topArrayInd[i]));
		}


		return returnStrings;
	}

	public static String findTopRequestor(ArrayList<Case> cases){
		ArrayList<String> requestors = new ArrayList<String>();
		ArrayList<Integer> requestorTotals = new ArrayList<Integer>();
		boolean found = false;
		int index = 0;

		for(Case data : cases) {
			found = false;
			index = 0;
			for(int i = 0; i < requestors.size(); i++) {
				if(requestors.get(i).equals(data.getCaseRequestor())) {
					found = true;
					index = i;
					break;
				}
			}

			if(found)
			{
				Integer value = requestorTotals.get(index);// get value
				value = value + 1; // increment value
				requestorTotals.set(index, value); // replace value
			} else {
				requestors.add(data.getCaseRequestor());
				requestorTotals.add(new Integer(1));
			}

		}

		int top = requestorTotals.get(0);
		int topInd = 0;

		for(int i = 1; i < requestors.size(); i++)
		{
			if(requestorTotals.get(i) > top) {
				top = requestorTotals.get(i);
				topInd = i;
			}
		}



		return requestors.get(topInd);
	}
	public static String findTopCategory(ArrayList<Case> cases){
		ArrayList<String> categories = new ArrayList<String>();
		ArrayList<Integer> counts = new ArrayList<Integer>();
		boolean found = false;
		int index = 0;

		for(Case data : cases) {
			found = false;
			index = 0;
			for(int i = 0; i < categories.size(); i++) {
				if(categories.get(i).equals(data.getCategory())) {
					found = true;
					index = i;
					break;
				}
			}

			if(found)
			{
				Integer value = counts.get(index);// get value
				value = value + 1; // increment value
				counts.set(index, value); // replace value
			} else {
				categories.add(data.getCategory());
				counts.add(new Integer(1));
			}

		}

		int top = counts.get(0);
		int topInd = 0;

		for(int i = 1; i < counts.size(); i++)
		{
			if(counts.get(i) > top) {
				top = counts.get(i);
				topInd = i;
			}
		}



		return categories.get(topInd);
	}
	public static ArrayList<Case> allRequestorCases(ArrayList<Case> cases, String requestor)
	{
		ArrayList<Case> sortedCases = new ArrayList<Case>();
		for(Case data : cases)
		{
			if(data.getCaseRequestor().equals(requestor))
			{
				sortedCases.add(data);
			}
		}
		return sortedCases;

	}

	public static ArrayList<Case> allCategoryCases(ArrayList<Case> cases, String category)
	{
		ArrayList<Case> sortedCases = new ArrayList<Case>();
		for(Case data : cases)
		{
			if(data.getCategory().equals(category))
			{
				sortedCases.add(data);
			}
		}
		return sortedCases;

	}

	public static ArrayList<String> getTopPerRequestor(ArrayList<Case> cases, String requestor, int numCategories){
		ArrayList<Case> requestCases = new ArrayList<Case>();
		ArrayList<String> topCategories = new ArrayList<String>();
		requestCases = allRequestorCases(cases, requestor);
		topCategories = findTopCategories(requestCases, numCategories, true);
		return topCategories;
	}
	public static ArrayList<String> getTopPerCategory(ArrayList<Case> cases, String category, int numRequestors){
		ArrayList<Case> requestCases = new ArrayList<Case>();
		ArrayList<String> topRequestors = new ArrayList<String>();
		requestCases = allCategoryCases(cases, category);
		topRequestors = findTopRequestors(requestCases, numRequestors);
		return topRequestors;
	}

	public static double[] getPercents(ArrayList<Case> caseList) {

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
		double[] percents = new double[categories.size()];

		for (int i = 0; i < caseList.size(); i++) {
			for (int j = 0; j < categories.size(); j++) {
				if (caseList.get(i).getCategory().equals(categories.get(j))) {
					percents[j]++;
					break;
				}
				if (j == categories.size() - 1) System.out.println(caseList.get(i).getCategory());			  
			}
		}
		for (int i = 0; i < percents.length; i++) {
			percents[i] = percents[i] / (double) caseList.size();
		}

		return percents;
	}

	public static int getNumCases(ArrayList<Case> caseList, String caseRequestor, String category) {
		int count = 0;
		for (int i = 0; i < caseList.size(); i++) {
			if (caseList.get(i).getCaseRequestor().equals(caseRequestor) && caseList.get(i).getCategory().equals(category)) {
				count++;
			}
		}

		return count;
	}

	public static double[] getCounts(ArrayList<Case> caseList) {

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
		double[] counts = new double[categories.size()];

		for (int i = 0; i < caseList.size(); i++) {
			for (int j = 0; j < categories.size(); j++) {
				if (caseList.get(i).getCategory().equals(categories.get(j))) {
					counts[j]++;
					break;
				}
				if (j == categories.size() - 1) System.out.println(caseList.get(i).getCategory());              
			}
		}
		return counts;
	}
}