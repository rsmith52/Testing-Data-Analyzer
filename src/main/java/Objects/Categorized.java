package Objects;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import File_IO.FileAccess;

public class Categorized implements Serializable {
  static final long serialVersionUID = 11;
  public String name;
  public String dateCreated;
  public ArrayList<Case> caseList;
  
  public Categorized(){
	  caseList = new ArrayList<Case>();
  }

  public Categorized(String name, String dateCreated) {
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
	Case[] listArray = (Case[])list1.getCaseList().toArray();
    Categorized newList = new Categorized(list1.getName() + ", " + list2.getName(), "DATE ADD", listArray);
    for (int i = 0; i < list2.getCaseList().size(); i++) {
    	newList.getCaseList().add(list2.getCaseList().get(i));
    }
    return newList;
    
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
		  
}