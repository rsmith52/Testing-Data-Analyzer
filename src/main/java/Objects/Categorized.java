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
  public ArrayList<Case> getCaseList () {
	  return this.caseList;
  }
  
  public void combineLists(ArrayList<Case> secondList){
    ArrayList<Case> combined = new ArrayList<Case>();
    combined.addAll(this.caseList);
    combined.addAll(secondList);
    caseList = combined;
  }
}