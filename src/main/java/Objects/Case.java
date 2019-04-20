package Objects;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import File_IO.FileAccess;

import java.io.*;

public class Case implements Serializable{
  static final long serialVersionUID = 10;
  HashMap<String, Integer> occurrences = new HashMap<String, Integer>();
  boolean actualCategoriesKnown; // true if this is part of a training set we know the categorization of already, false otherwise
  int caseNumber;
  String caseOwner;
  String originalCategory;
  String caseRequestor;
  String dateRequested;
  String description;
  String[] tokenizedDescription;
  String category;
  static int numKeywords = 169; // MUST BE UPDATED IF INPUTS IS UPDATED

  /**
   * (Default constructor to create a case and initialize occurrences counts)
   */
  public Case(){
  	getInputs();
  }
  /**
   * (Constructor when all variables are known)
   */
  public Case(int caseNumber, String caseOwner,String caseRequestor,

    String dateRequested, String description,

    String[] tokenizedDescription, String category){
    this.caseNumber = caseNumber;
    this.caseOwner = caseOwner;
    this.caseRequestor = caseRequestor;
    this.dateRequested = dateRequested;
    this.description = description;
    this.tokenizedDescription = tokenizedDescription;
    this.category = category;
    this.originalCategory = category;
    getInputs();
  }
  
  private void getInputs() {
	  try {
		  File file = FileAccess.getFile("/inputs.txt");
		  Scanner sc = new Scanner(file);
		  while(sc.hasNextLine()) {
			  occurrences.put(sc.nextLine(), 0);
		  }
		  sc.close();
	  } catch (FileNotFoundException e) {
		  e.printStackTrace();
	  }
  }

  //setters for case variables
  public void setActualCategoriesKnown(boolean known){
    this.actualCategoriesKnown = known;
  }
  public void setCaseNumber(int caseNumber){
    this.caseNumber = caseNumber;
  }
  public void setCaseOwner(String caseOwner){
    this.caseOwner = caseOwner;
  }
  public void setCaseRequestor(String caseRequestor){
    this.caseRequestor = caseRequestor;
  }
  public void setDateRequested(String dateRequested){
    this.dateRequested = dateRequested;
  }

  public void setDescription(String description){
    this.description = description;
  }
  public void setTokenizedDescription(String[] tokenizedDescription){
    this.tokenizedDescription = tokenizedDescription;
  }
  public void setCategory(String category){
    this.category = category;
  }
  
  public void setOriginalCategory(String originalCategory){
	  this.originalCategory = originalCategory;
  }

  public boolean getActualCategoriesKnown(){
    return this.actualCategoriesKnown;
  }
  public int getCaseNumber(){
    return this.caseNumber;
  }
  public String getCaseOwner(){
    return this.caseOwner;
  }
  public String getCaseRequestor(){
    return this.caseRequestor;
  }
  public String getDateRequested(){
    return this.dateRequested;
  }
  
  public String getDescription(){
    return this.description;
  }
  public String[] getTokenizedDescription(){
    return this.tokenizedDescription;
  }
  public String getCategory(){
    return this.category;
  }

  public String getOriginalCategory(){
	    return this.originalCategory;
  }
  
  /**
   * (iterates through tokenized description to count occurrences of keywords)
   * @param (tokenizedDescription) Contains a tokenized description that is easily iterable)
   */
  public void findOccurrences(String[] tokenizedDescription){
    for(int i = 0; i < tokenizedDescription.length; i++){
      if(occurrences.containsKey(tokenizedDescription[i])){
        occurrences.put(tokenizedDescription[i], occurrences.get(tokenizedDescription[i]) + 1);
      }
    }
  }
  
  /**
   * Function to return all of the data from the case as input to neural network
   * @return
   */
  public double[] getAsInput() {
	  double[] inputs = new double[numKeywords];
	  for (Map.Entry<String, Integer> entry : occurrences.entrySet()) {
          occurrences.put(entry.getKey(), 0);
      }
	  int i = 0;
    findOccurrences(tokenizedDescription);
    Iterator<Map.Entry<String, Integer>> it = occurrences.entrySet().iterator();
    while(it.hasNext()) {
    	Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>)it.next();
    	inputs[i] = (double) pair.getValue();
    	i++;
    }
    return inputs;
  }


  // Function to return correct labels of the data from the case
  public double[] getLabelsIfKnown() {
    double[] labelMatch = new double[19];
    for(int i = 0; i < labelMatch.length; i++){
      labelMatch[i] = 0;
    }
    if (actualCategoriesKnown) {
      switch(category){
        case "Office 365 Email and Calendar":
          labelMatch[0] = 1;
          break;
        case "Shared/Network Drive":
          labelMatch[1] = 1;
          break;
        case "Microsoft Office":
          labelMatch[2] = 1;
          break;
        case "Computer Support Settings":
          labelMatch[3] = 1;
          break;
        case "Purchase Request":
          labelMatch[4] = 1;
          break;
        case "Onsite Assistance":
          labelMatch[5] = 1;
          break;
        case "WiscLists":
          labelMatch[6] = 1;
          break;
        case "Audio/Visual Support":
          labelMatch[7] = 1;
          break;
        case "Device Repair":
          labelMatch[8] = 1;
          break;
        case "Service Account Creation (Email)":
          labelMatch[9] = 1;
          break;
        case "Admin Account/Password":
          labelMatch[10] = 1;
          break;
        case "Login Issues":
          labelMatch[11] = 1;
          break;
        case "Virus/Malware":
          labelMatch[12] = 1;
          break;
        case "Printer Support":
          labelMatch[13] = 1;
          break;
        case "Multi Factor Authentication":
          labelMatch[14] = 1;
          break;
        case "Room Access":
          labelMatch[15] = 1;
          break;
        case "Network Connectivity":
          labelMatch[16] = 1;
          break;
        case "Adobe Suite":
          labelMatch[17] = 1;
          break;
        case "Loaner Request (Computer/Mifi)":
          labelMatch[18] = 1;
          break;
        default:
          break;
      }
      return labelMatch;
      // return categories as double array with a 1 if it matches and a 0 if it doesn't
    }
    return null;
  }
}
