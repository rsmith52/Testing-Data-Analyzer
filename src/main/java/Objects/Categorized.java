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
  
  public static ArrayList<String> findTopRequestors(ArrayList<Case> cases){
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
			  requestors.add(data.getCaseRequestor());
			  requestorTotals.add(new Integer(1));
		  }
		  
	  }
	  

	  int[] topArray = new int[5];
	  int[] topArrayInd = new int[5];
	  
	  for(int i = 0; i < 5; i++)
	  {
		  topArray[i] = requestorTotals.get(i);
		  topArrayInd[i] = i;
	  }
	  
	  for(int i = 6; i < requestors.size(); i++)
	  {
		  int value = requestorTotals.get(i);
		  int ind = i;
		  
		  for(int k = 0; k < 5; k++)
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
	  for(int i = 0; i < 5; i++)
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
  
  
}