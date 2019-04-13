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
}