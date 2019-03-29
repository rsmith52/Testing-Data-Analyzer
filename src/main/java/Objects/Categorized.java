package Objects;

import java.io.*;
import java.util.ArrayList;

public class Categorized implements Serializable{
  String name;
  int dateCreated;
  ArrayList<Case> caseList;

  public Categorized(String name, int dateCreated){
    caseList = new ArrayList<Case>();
    this.name = name;
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
}