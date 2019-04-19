package Objects;

import java.io.*;
import java.util.ArrayList;

public class Categorized implements Serializable{
  static final long serialVersionUID = 10;
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
  public void setName(String name) {
	  this.name = name;
  }
  public void setDate(String dateCreated) {
	  this.dateCreated = dateCreated;
  }
  public void addToList(Case newCase){
    caseList.add(newCase);
  }
  
  public void combineLists(ArrayList<Case> firstList,
    ArrayList<Case> secondList){
    ArrayList<Case> combined = new ArrayList<Case>();
    combined.addAll(firstList);
    combined.addAll(secondList);
    caseList = combined;
  }
  public static ArrayList<String> findTopCategories(ArrayList<Case> cases){
	  ArrayList<String> categories = new ArrayList<String>();
	  ArrayList<Integer> counts = new ArrayList<Integer>();
	  boolean found = false;
	  int index = 0;
	  
	  for (Case data : cases) {
		  found = false;
		  index = 0;
		  for(int i = 0; i < categories.size(); i++) {
			  if(categories.get(i).contentEquals(data.getCategory())) {
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
			  categories.add(data.getCategory());
			  counts.add(new Integer(1));
		  }
	  }
	  int[] topArray = new int[5];
	  int[] indexes = new int[5];
	  ArrayList<String> topCategories = new ArrayList<String>();
	  for(int i = 0; i < 5; i++) {
		  topArray[i] = counts.get(i);
		  indexes[i] = i;
	  }
	  for(int i = 6; i < counts.size(); i++) {
		  int value = counts.get(i);
		  int ind = i;
		  for(int k = 0; k < 5; k++) {
			  if(topArray[k] < value) {
				  int tempInt = topArray[k];
				  int tempInd = indexes[k];
				  topArray[k] = value;
				  value = tempInt;
				  ind = tempInd;
			  }
		  }
	  }
	  for(int i = 0; i < 5; i++) {
		  topCategories.add(categories.get(indexes[i]));
	  }
	  
	  return topCategories;
  }
  
 
  
}